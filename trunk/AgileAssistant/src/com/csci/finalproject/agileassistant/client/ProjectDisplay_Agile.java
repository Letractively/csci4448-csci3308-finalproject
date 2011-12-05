package com.csci.finalproject.agileassistant.client;

import com.csci.finalproject.agileassistant.client.Backlog.AbstractBacklog;
import com.csci.finalproject.agileassistant.client.UserStoryPile.AbstractUserStoryPile;
import com.csci.finalproject.agileassistant.client.WhiteBoard.AbstractWhiteBoard;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class ProjectDisplay_Agile extends Composite {

	public ProjectDisplay_Agile( AbstractProject project, 
			AbstractUserStoryPile usp, AbstractBacklog bl, 
			AbstractWhiteBoard wb) {

		DockPanel dockPanel = new DockPanel();
		dockPanel.setStyleName("ProjectDisplay-DocPanel");
		initWidget(dockPanel);

		Label projectName_lbl = new Label(project.getTitle());
		projectName_lbl.setStyleName("ProjectLabel");
		dockPanel.add(projectName_lbl, DockPanel.NORTH);
		projectName_lbl.setHeight("37px");

		FlowPanel simplePanel = new FlowPanel();
		dockPanel.add(simplePanel, DockPanel.WEST);

		Label lblNewLabel = new Label("Backlog");
		lblNewLabel.setStyleName("L1");
		simplePanel.add(lblNewLabel);

		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.setAlwaysShowScrollBars(false);
		scrollPanel.setSize("148px", "790px");
		scrollPanel.add(bl);
		simplePanel.add(scrollPanel);

		DecoratedTabPanel decoratedTabPanel = new DecoratedTabPanel();
		decoratedTabPanel.setAnimationEnabled(true);
		dockPanel.add(decoratedTabPanel, DockPanel.CENTER);
		decoratedTabPanel.setSize("1125px", "750px");

		SimplePanel simplePanel_1 = new SimplePanel();
		simplePanel_1.add(usp);
		decoratedTabPanel.add(simplePanel_1, "User Story Pile", false);

		SimplePanel simplePanel_2 = new SimplePanel();
		simplePanel_2.add(wb);
		decoratedTabPanel.add(simplePanel_2, "White Board", false);

		decoratedTabPanel.selectTab(0);
	}

}
