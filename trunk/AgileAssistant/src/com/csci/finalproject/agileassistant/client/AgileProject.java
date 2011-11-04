package com.csci.finalproject.agileassistant.client;

import java.util.List;

import com.google.gwt.user.client.Window;

public class AgileProject extends AbstractProject {
	private final AbstractUserStoryPile usp = new AgileUserStoryPile( this );
	private final AbstractBacklog bl = new AgileBacklog( this );
	private final AbstractWhiteBoard wb = new AgileWhiteBoard( this );

	public AgileProject(String title, Long iD, LoginInfo loginInfo,
			List<Notecard> notecards) {
		super(title, iD, loginInfo, notecards);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addNotecard(Notecard nc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPostitToNotecard(Long usID, Postit postit) {
		Window.alert("Postit condition:\t"+postit.getCondition());
		for( Notecard nc : notecards ) {
			if( nc.getID() == usID ) {
				dragCon_postit.makeDraggable(postit);
				nc.addPostit(postit);
				if(nc.getCondition() == 2) {
					wb.addPostit(postit);
				}
			}
		}
	}

}
