package com.csci.finalproject.agileassistant.client.Backlog;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.AbstractInsertPanelDropController;
import com.allen_sauer.gwt.dnd.client.util.DOMUtil;
import com.allen_sauer.gwt.dnd.client.util.DragClientBundle;
import com.allen_sauer.gwt.dnd.client.util.LocationWidgetComparator;
import com.csci.finalproject.agileassistant.client.Notecard;
import com.csci.finalproject.agileassistant.client.UserStoryCondition;
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
	public BacklogDropController(AbstractBacklog bl) {
		super((InsertPanel) bl);
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
/*		super.onDrop(context);
		
		if( context.draggable.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) context.draggable;
			int newIndex = calcNewIndex(nc);
			
			nc.setPoints(bl.getNextSliderPoints(newIndex));
			nc.setCondition(UserStoryCondition.BL);
			
			bl.getProject().persistUserStory(nc, newIndex);
			
		} else if( context.draggable.getClass() == ValueSlider.class ) {
			ValueSlider slider = (ValueSlider) context.draggable;
			
			Widget curWidget;
			for( int i=bl.getWidgetIndex(slider)-1; i>=0; i-- ) {
				curWidget = bl.getWidget(i);
				if(curWidget.getClass() == Notecard.class) {
					Notecard nc = (Notecard) curWidget;
					nc.setPoints(slider.getValue());
				} else if(curWidget.getClass() == ValueSlider.class) {
					break;	
				} 
			}
		}
*/	}
	
	private int calcNewIndex(Notecard nc) {
		// -1 will maintain the Notecards current index
		int newIndex = -1;
		
		/* 
		 * If this move is coming from the WhiteBoard, we don't want to change
		 * the Notecards index
		 */
/*		if( nc.getCondition() != UserStoryCondition.WB ) {
			newIndex = bl.getProject().getUsp().count() 
					+ bl.getNotecardIndexWithoutSliders(nc);
		}
*/		
		return newIndex;
	}
}
