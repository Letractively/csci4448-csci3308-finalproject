package com.csci.finalproject.agileassistant.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The entry point module for the Project Manager application.
 * 
 * @author Jacob
 */
public class ProjectManager implements EntryPoint {
	// Google Login/Logout stuff
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access your Project Board.");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");

	// Remote Services
	UserStoryServiceAsync userStoryServ = GWT.create(UserStoryService.class);

	// Components
	private AbstractProject project;

	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				// Make sure we don't loose any data
				if( project != null ) {
					project.saveProjectState();
				}
				
				Window.alert("The current state of the program has been saved!" +
						"\n\nUncaught: " + e.getMessage());
				String s = buildStackTrace(e, "Runtime Exception:\n");
				Window.alert(s);
				e.printStackTrace();
			}
		});


		// Check login status using login service.
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {}
			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if(loginInfo.isLoggedIn()) {
					// If we are logged in, load and launch the project
					loadLogout();
					loadProject();
				} else {
					// If not logged in, show the login link
					loadLogin();
				}
			}
		});
	}


	/*
	 * Page Loads and Component Initializations
	 */
	private void loadLogin() {
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("loginDiv").add(loginPanel);
	}

	private void loadLogout() {
		signOutLink.setHref(loginInfo.getLogoutUrl());
		RootPanel.get("loginDiv").add(signOutLink);
	}


	/*
	 * RPC service methods
	 */
	public void loadProject() {
		userStoryServ.loadProjectData(new AsyncCallback<ProjectData>() {
			@Override
			public void onFailure(Throwable caught) {
				handleError( caught );
			}

			@Override
			public void onSuccess(ProjectData pd) {
				project = pd.genAbstractProject(loginInfo);
				project.launch();
			}
		});
	}


	/*
	 * HELPER METHODS
	 */
	protected void handleError(Throwable error) {
		Window.alert("handleError says:\n\n"+error.getMessage());
		if (error instanceof NotLoggedInException) {
			Window.Location.replace(loginInfo.getLogoutUrl());
		}
	}

	private String buildStackTrace(Throwable t, String log) {

		if (t != null) {
			log += t.getClass().toString();
			log += t.getMessage();
			log += "\n";
			
			StackTraceElement[] stackTrace = t.getStackTrace();
			if (stackTrace != null) {
				StringBuffer trace = new StringBuffer();

				for (int i = 0; i < stackTrace.length; i++) {
					trace.append("\n" + stackTrace[i].getClassName() + "." + stackTrace[i].getMethodName() + "(" + stackTrace[i].getFileName() + ":" + stackTrace[i].getLineNumber() + ")");
				}

				log += trace.toString();
			}
			//
			Throwable cause = t.getCause();
			if (cause != null && cause != t) {

				log += buildStackTrace(cause, "CausedBy:\n");

			}
		}
		return log;
	}

}
