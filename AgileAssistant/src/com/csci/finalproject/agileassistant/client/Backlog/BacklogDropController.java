package com.csci.finalproject.agileassistant.client.Backlog;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.VetoDragException;
import com.allen_sauer.gwt.dnd.client.drop.AbstractInsertPanelDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.allen_sauer.gwt.dnd.client.util.DragClientBundle;
import com.allen_sauer.gwt.dnd.client.util.LocationWidgetComparator;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.csci.finalproject.agileassistant.client.Postit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class BacklogDropController extends AbstractInsertPanelDropController {

	public BacklogDropController(InsertPanel dropTarget) {
		super((InsertPanel) dropTarget);
	}

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
		
		// Remove all the Postits associated with this Notecard
		if( context.draggable.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) context.draggable;
			for( Postit p : nc.getPostits() ) {
				p.removeFromParent();
			}
		}
	}
	
	@Override
	public void onPreviewDrop(DragContext context) throws VetoDragException {
		/*
		 * Check that the current move is permissible by the projects standards
		 */
		AbstractBacklog bl = (AbstractBacklog) dropTarget;
		if( !bl.getProject().moveIsPermissable(context) ) {
			Window.alert("You do not have permission to do this!");
			throw new VetoDragException();
		}
		
		super.onPreviewDrop(context);
	}
}
