package com.csci.finalproject.agileassistant.client;

/**
 * @author Jacob
 *
 */
public enum PermissionType_Agile {

	/**
	 * Scrum Masters are the uppermost level technical possition. They are in 
	 * charge of creating User Stories and sorting them into the Backlog. They 
	 * are the overseers of the entire project and are ultimately held 
	 * responsible for the end product. This is the highest permission level. 
	 */
	SCRUM_MASTER,
	
	/**
	 * Product Owners are the team leaders for an individual Scrum Teams. They 
	 * are responsible for the code produced by their team. They are in charge 
	 * of pulling User Stories from the backlog and placing them onto their 
	 * teams White Board. They are also able to move User Stories and their 
	 * Tasks around on the White Board. This is the second highest permission 
	 * level.
	 */
	PRODUCT_OWNER,
	
	/**
	 * Developers are the ones actually writing code and producing the product. 
	 * They are assigned to a team and work with that team on the User Stories 
	 * that are claimed by their teams Product Owner. They are able to claim 
	 * Tasks as their own and move User Stories and Tasks around their White 
	 * Board.
	 */
	DEVELOPER;
}
