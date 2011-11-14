package com.csci.finalproject.agileassistant.client;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;

public class AgileUserStoryPile extends AbstractUserStoryPile {

	public AgileUserStoryPile(AbstractProject project) {
		super(project);
		dragDropPanel.setStyleName("UserStoryPile-DragDropPanel");
		titleLabel.setText("User Story Pile");
		addUserStoryButton.setText("Add User Story!");
		addUserStoryButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AbstractUserStoryPile.project.getAddUserStoryPopup().center();
			}
		});
	}

	
	/*
	 * METHOD OVERRIDES
	 */
	@Override
	public void registerDropControllers() {
		project.getDragCon_notecard().registerDropController(uspDropCon);
	}


	@Override
	public void add(Widget w) {
		if( w.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) w;
			dragDropPanel.add(nc);
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
		if( w.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) w;
			dragDropPanel.remove(nc);
		}
		return false;
	}
}
