package com.csci.finalproject.agileassistant.client;

import com.google.gwt.user.client.ui.RadioButton;

public class AgilePermissionsPopup extends AbstractPermissionsPopup {
	/*
	 * PERMISSIONS RADIO BUTTONS
	 */
	private RadioButton rdbtnScrumMaster;
	private RadioButton rdbtnProductOwner;
	private RadioButton rdbtnTeamDeveloper;

	public AgilePermissionsPopup(AbstractProject project) {
		super(project);
	}

	@Override
	protected void genPermissionRadioButtons() {
		rdbtnScrumMaster = new RadioButton("permissionsRadioGroup", "Scrum Master");
		rdbtnProductOwner = new RadioButton("permissionsRadioGroup", "Product Owner");
		rdbtnTeamDeveloper = new RadioButton("permissionsRadioGroup", "Team Developer");
		
		rdbtnScrumMaster.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_RADIOBUTTON);
		rdbtnProductOwner.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_RADIOBUTTON);
		rdbtnTeamDeveloper.addStyleName(CSS_PERMISSIONSLEVEL_POPUP_RADIOBUTTON);
		
		RadioButtonPanel.add(rdbtnScrumMaster);
		RadioButtonPanel.add(rdbtnProductOwner);
		RadioButtonPanel.add(rdbtnTeamDeveloper);
	}

	@Override
	protected void setProjectPermissions() {
		PermissionType_Agile permissionsLevel = null;
		
		if( rdbtnScrumMaster.getValue() ) {
			permissionsLevel = PermissionType_Agile.SCRUM_MASTER;
		} else if( rdbtnProductOwner.getValue() ) {
			permissionsLevel = PermissionType_Agile.PRODUCT_OWNER;
		} else if( rdbtnTeamDeveloper.getValue() ) {
			permissionsLevel = PermissionType_Agile.DEVELOPER;
		}
		
		if( permissionsLevel != null ) {
			AgileProject p = (AgileProject) project;
			p.setPermissionsLevel(permissionsLevel);
			
			this.hide();
		}
	}

}
