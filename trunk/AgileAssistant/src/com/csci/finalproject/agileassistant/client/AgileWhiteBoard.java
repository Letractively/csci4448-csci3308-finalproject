package com.csci.finalproject.agileassistant.client;

public class AgileWhiteBoard extends AbstractWhiteBoard {
	/*
	 * FIELDS
	 */
	private WhiteBoardColumn userStoryColumn;
	private WhiteBoardColumn toDoColumn;
	private WhiteBoardColumn inProgressColumn;
	private WhiteBoardColumn inVerificationColumn;
	private WhiteBoardColumn completeColumn;

	public AgileWhiteBoard(AbstractProject project) {
		super(project);
		
		
	}

	/*
	 * PUBLIC METHODS
	 */
	@Override
	public void addNotecard(Notecard nc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPostit(Postit postit) {
		// TODO Auto-generated method stub
		
	}

}
