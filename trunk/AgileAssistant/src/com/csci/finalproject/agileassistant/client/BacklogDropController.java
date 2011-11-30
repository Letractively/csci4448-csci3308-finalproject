package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractInsertPanelDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.allen_sauer.gwt.dnd.client.util.DragClientBundle;
import com.allen_sauer.gwt.dnd.client.util.LocationWidgetComparator;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BacklogDropController extends AbstractInsertPanelDropController {

	/*
	 * PRIVATE FIELDS
	 */
	private final AbstractBacklog bl;

	/*
	 * CONSTRUCTORS
	 */
	public BacklogDropController(InsertPanel dropTarget, AbstractBacklog bl) {
		super(dropTarget);
		this.bl = bl;
	}


	/*
	 * OVERRIDES
	 */
	@Override
	protected LocationWidgetComparator getLocationWidgetComparator() {
		return LocationWidgetComparator.BOTTOM_HALF_COMPARATOR;
	}

	@Override
	protected Widget newPositioner(DragContext context) {
		// Use two widgets so that setPixelSize() consistently affects dimensions
		// excluding positioner border in quirks and strict modes
		SimplePanel outer = new SimplePanel();
		outer.addStyleName(DragClientBundle.INSTANCE.css().positioner());

		// place off screen for border calculation
		RootPanel.get().add(outer, -500, -500);

		// Ensure IE quirks mode returns valid outer.offsetHeight, and thus valid
		// DOMUtil.getVerticalBorders(outer)
		outer.setWidget(new Label("x"));

		int width = context.draggable.getOffsetWidth();
		int height = context.draggable.getOffsetHeight();

		SimplePanel inner = new SimplePanel();
		inner.setPixelSize(width - DOMUtil.getHorizontalBorders(outer), height
				- DOMUtil.getVerticalBorders(outer));

		outer.setWidget(inner);

		return outer;
	}

	@Override
	public void onDrop(DragContext context) {
		super.onDrop(context);
		
		Notecard nc = (Notecard) context.draggable;
		int newIndex = -1;
		
		switch( nc.getCondition() ) {
		case USP:
			nc.setCondition(UserStoryCondition.BL);
			newIndex = bl.getProject().getUsp().count()
				+ dropTarget.getWidgetIndex(nc);
			break;
			
		case BL:
			newIndex = bl.getProject().getUsp().count()
				+ dropTarget.getWidgetIndex(nc);
			break;
			
		case WB:
			nc.setCondition(UserStoryCondition.BL);
			
			for( Postit p : nc.getPostits() ) {
				p.removeFromParent();
			}
			break;
		}
		
		bl.getProject().persistUserStory(nc, newIndex);
	}
}
