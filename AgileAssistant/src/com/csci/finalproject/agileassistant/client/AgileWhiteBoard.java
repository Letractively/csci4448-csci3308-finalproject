package com.csci.finalproject.agileassistant.client;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Widget;

public class AgileWhiteBoard extends AbstractWhiteBoard {
	
	private final WhiteBoardColumn userStoryCol = new WhiteBoardColumn("User Stories", this,
			new OnDropBehavior_UserStories( this ));
	private final WhiteBoardColumn toDoCol = new WhiteBoardColumn("To Do", this,
			new OnDropBehavior_ToDo( this ));
	private final WhiteBoardColumn inProgressCol = new WhiteBoardColumn("In Progress", this,
			new OnDropBehavior_ToDo( this ));
	private final WhiteBoardColumn inVerificationCol = new WhiteBoardColumn("In Verification", this,
			new OnDropBehavior_InVerification( this ));
	private final WhiteBoardColumn completeCol  = new WhiteBoardColumn("Complete", this,
			new OnDropBehavior_Complete( this ));

	public AgileWhiteBoard(AbstractProject project) {
		super(project);

		whiteBoardWrapper.add(userStoryCol);
		userStoryCol.setWidth("175px");
		whiteBoardWrapper.add(toDoCol);
		whiteBoardWrapper.add(inProgressCol);
		whiteBoardWrapper.add(inVerificationCol);
		whiteBoardWrapper.add(completeCol);
		completeCol.setStyleName("WhiteBoardColumn-WrapperRight");
	}

	@Override
	public void add(Widget w) {
		if( w.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) w;
			if( nc.getCondition() != 2 ) {
				nc.setCondition(2);
				for( Postit p : nc.getPostits() ) {
					this.add(p);
				}
			}
			userStoryCol.getDragDropPanel().add(w);
		}
		else if( w.getClass() == Postit.class ) {
			Postit p = (Postit) w;
			switch( p.getCondition() ) {
				case 1:
					inProgressCol.getDragDropPanel().add(p);
					break;
				case 2:
					inVerificationCol.getDragDropPanel().add(p);
					break;
				case 3:
					completeCol.getDragDropPanel().add(p);
					break;
				default:
					toDoCol.getDragDropPanel().add(p);				
			}
		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDropControllers() {
		project.getDragCon_notecard().registerDropController(userStoryCol.getDropController());
		project.getDragCon_postit().registerDropController(toDoCol.getDropController());
		project.getDragCon_postit().registerDropController(inProgressCol.getDropController());
		project.getDragCon_postit().registerDropController(inVerificationCol.getDropController());
		project.getDragCon_postit().registerDropController(completeCol.getDropController());

	}

}
