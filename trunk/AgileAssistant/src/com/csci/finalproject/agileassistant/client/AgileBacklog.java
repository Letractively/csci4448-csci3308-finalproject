package com.csci.finalproject.agileassistant.client;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Widget;

public class AgileBacklog extends AbstractBacklog {

	public AgileBacklog(AbstractProject project) {
		super(project);
		label.setText("Backlog");
		
		registerDropControllers();
	}

	/*
	 * PUBLIC METHODS
	 */
	public void insert( Widget w, int beforeIndex ) {
		dragDropPanel.insert(w, beforeIndex);
	}
	
	/*
	 * IMPLEMENTATIONS FOR HasWidgets
	 */
	@Override
	public void registerDropControllers() {
		//dragCon_slider.registerDropController(dropController);
		project.getDragCon_notecard().registerDropController(dropController);
	}
	
	
	@Override
	public void add(Widget w) {
		if(w.getClass() == Notecard.class) {
			dragDropPanel.add(w);
		}
	}


	@Override
	public void clear() {
		dragDropPanel.clear();
	}


	@Override
	public Iterator<Widget> iterator() {
		return dragDropPanel.iterator();
	}


	@Override
	public boolean remove(Widget w) {
		return dragDropPanel.remove(w);
	}

}
