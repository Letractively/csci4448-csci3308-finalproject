package com.csci.finalproject.agileassistant.client.popups;

import com.csci.finalproject.agileassistant.client.AbstractProject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractPermissionsPopup extends PopupPanel {

	/*
	 * PRIVATE FIELDS
	 */
	protected AbstractProject project;
	
	
	/*
	 * GUI ELEMENTS
	 */
	protected VerticalPanel RadioButtonPanel;
	
	
	/*
	 * CSS STYLE NAMES
	 */
	protected static final String CSS_PERMISSIONSLEVEL_POPUP = 
			"PermissionsLevelPopup";
	protected static final String CSS_PERMISSIONSLEVEL_POPUP_WRAPPER = 
			"PermissionsLevelPopup-Wrapper";
	protected static final String CSS_PERMISSIONSLEVEL_POPUP_LABEL = 
			"PopupLabel";
	protected static final String CSS_PERMISSIONSLEVEL_POPUP_RADIOPANEL = 
			"PermissionsLevelPopup-RadioPanel";
	protected static final String CSS_PERMISSIONSLEVEL_POPUP_RADIOBUTTON = 
			"PermissionsLevelPopup-RadioButton";
	protected static final String CSS_PERMISSIONSLEVEL_POPUP_SUBMITBUTTON = 
			"PopupSubmitButton";
	
	public AbstractPermissionsPopup( AbstractProject project ) {
		super(true);
		this.project = project;

		this.setGlassEnabled(true);
		this.setAutoHideEnabled(false);
		
		/*
		 * Initialize widgets
		 */
		FlowPanel wrapper = new FlowPanel();
		setWidget(wrapper);
		
		Label lblPermissionsLevel = new Label("Permissions Level:");
		RadioButtonPanel = new VerticalPanel();
		Button btnSubmit = new Button("Submit!");
		btnSubmit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setProjectPermissions();
			}
		});
		
		
		/*
		 * Set CSS Styles
		 */
		addStyleName(CSS_PERMISSIONSLEVEL_POPUP);
		wrapper.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_WRAPPER);
		lblPermissionsLevel.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_LABEL);
		RadioButtonPanel.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_RADIOPANEL);
		btnSubmit.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_SUBMITBUTTON);
		

		/*
		 * Assemble Widgets
		 */
		wrapper.add(lblPermissionsLevel);
		wrapper.add(RadioButtonPanel);
		wrapper.add(btnSubmit);
		
		genPermissionRadioButtons();
	}
	
	
	/**
	 * Generates radio buttons specific to the type of project and adds them to 
	 * this popup panel. Each radio button will represent each permission type 
	 * for this project type.
	 */
	protected abstract void genPermissionRadioButtons();
	
	/**
	 * Called when the user clicks the Submit! button. Sets the permission level 
	 * of the current project based upon which Radio Button the use selected.
	 */
	protected abstract void setProjectPermissions();
}
