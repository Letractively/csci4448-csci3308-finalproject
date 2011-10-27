package com.csci.finalproject.agileassistant.server;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Task {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private long ID;
	
	@Persistent
	private String title;
	
	@Persistent
	private int task_numb; //is this task 1, 2, 3,....
	
	@Persistent
	private int condition; // 0=To Do 1=In Prof 2=In Veriification 3= Complete

	@Persistent
	private User user;
	
	public Task(User user, String title,int condition,int task_numb) {
		super();
		this.user = user;
		this.title = title;
		this.condition = condition;
		this.task_numb = task_numb;
		
	}
	
}
