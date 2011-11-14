package com.csci.finalproject.agileassistant.client;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Widget;

/**
 * Extension of {@link AbstractWhiteBoard} that generates
 * a white board specific to an {@link AgileProject}.
 * @author Jacob
 *
 */
public class AgileWhiteBoard extends AbstractWhiteBoard {
	
	/*
	 * WHTE BOARD COLUMNS
	 */
	private WhiteBoardColumn userStoryCol = new WhiteBoardColumn
			("User Stories", new OnDropBehavior_UserStories( this ));
	
	private WhiteBoardColumn toDoCol = new WhiteBoardColumn
			("To Do", new OnDropBehavior_ToDo( this ));
	
	private WhiteBoardColumn inProgressCol = new WhiteBoardColumn
			("In Progress", new OnDropBehavior_InProgress( this ));
	
	private WhiteBoardColumn inVerificationCol = new WhiteBoardColumn
			("In Verification", new OnDropBehavior_InVerification( this ));
	
	private WhiteBoardColumn completeCol = new WhiteBoardColumn
			("Complete", new OnDropBehavior_Complete( this ));

	
	/**
	 * An extension of {@link AbstractWhiteBoard} that is specific to an
	 * {@link AgileProject}. It has 5 {@link WhiteBoardColumn}'s: 'User Stories',
	 * 'To Do', 'In Progress', 'In Verification', and 'Complete'. 
	 * @param project
	 */
	public AgileWhiteBoard(AbstractProject project) {
		super(project);
		
		userStoryCol.setWidth("175px");
		completeCol.setStyleName("WhiteBoardColumn-WrapperRight");
		
		whiteBoardWrapper.add(userStoryCol);
		whiteBoardWrapper.add(toDoCol);
		whiteBoardWrapper.add(inProgressCol);
		whiteBoardWrapper.add(inVerificationCol);
		whiteBoardWrapper.add(completeCol);
		
		registerDropControllers();
	}

	@Override
	public void add(Widget w) {
		if( w.getClass() == Notecard.class ) {
			Notecard nc = (Notecard) w;
			if( nc.getCondition() != UserStoryCondition.WB ) {
				nc.setCondition(UserStoryCondition.WB);
			}
			for( Postit p : nc.getPostits() ) {
				add(p);
			}
			
			userStoryCol.getDragDropPanel().add(nc);
		}
		else if( w.getClass() == Postit.class ) {
			if( w.isAttached() ) return;

			Postit p = (Postit) w;
			switch( p.getCondition() ) {
				case IN_PROGRESS:
					inProgressCol.getDragDropPanel().add(p);
					break;
				case IN_VERIFICATION:
					inVerificationCol.getDragDropPanel().add(p);
					break;
				case COMPLETE:
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
		if( w.isAttached() ) {
			w.removeFromParent();
			return true;
		}
		return false;
	}

	@Override
	public void registerDropControllers() {
		// Notecard Columns
		project.getDragCon_notecard().registerDropController(userStoryCol.getDropController());
		
		// Postit Columns
		project.getDragCon_postit().registerDropController(toDoCol.getDropController());
		project.getDragCon_postit().registerDropController(inProgressCol.getDropController());
		project.getDragCon_postit().registerDropController(inVerificationCol.getDropController());
		project.getDragCon_postit().registerDropController(completeCol.getDropController());
	}

}
