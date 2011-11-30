package com.csci.finalproject.agileassistant.client;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;

abstract public class AbstractBacklog extends Composite implements HasWidgets {
	/*
	 * FIELDS
	 */
	protected static AbstractProject project;

	protected final Label label = new Label("Label");
	protected final FlowPanel dragDropPanel = new FlowPanel();

	protected final BacklogDropController dropController = 
			new BacklogDropController(dragDropPanel, this);
	protected final PickupDragController dragCon_slider;

	/*
	 * CONSTRUCTORS
	 */
	public AbstractBacklog( AbstractProject project ) {
		AbstractBacklog.project = project;

		AbsolutePanel wrapper = new AbsolutePanel();
		initWidget(wrapper);

		// Set CSS styles
		addStyleName("Abstract-Backlog");
		wrapper.addStyleName("Backlog-Wrapper");
		label.addStyleName("Backlog-Label");
		dragDropPanel.addStyleName("Backlog-DragDropPanel");

		wrapper.add(label);
		wrapper.add(dragDropPanel);

		dragCon_slider = new PickupDragController(wrapper, false);
	}

	/*
	 * ABSTRACT METHODS
	 */
	public abstract void registerDropControllers();


	/*
	 * GETTERS & SETTERS
	 */
	public AbstractProject getProject() {
		return project;
	}
	
	public FlowPanel getDragDropPanel() {
		return dragDropPanel;
	}
}
