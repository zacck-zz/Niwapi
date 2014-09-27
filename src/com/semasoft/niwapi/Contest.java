package com.semasoft.niwapi;

public class Contest {
	
	String Title, ImageLink, Id;
	int Votes, Tries; 
	
	public Contest()
	{
		
	}
	
	public Contest(String T, String I, String id)
	{
		this.Title = T;
		this.ImageLink = I;
		this.Id = id;

	}
	
	//getters
	public String getTitle()
	{
		return Title;
	}
	
	public String getImageLink()
	{
		return ImageLink;
	}
	
	public String getId()
	{
		return Id;
	}
	
	public int getVotes()
	{
		return Votes;
	}
	
	public int getTries()
	{
		return Tries;
	}
	
	//our setters 
	public 	void setTitle(String t)
	{
		this.Title = t;
		
	}
	public 	void setImageLink(String img)
	{
		this.ImageLink = img;
		
	}
	public 	void setID(String ids)
	{
		this.Id = ids;
		
	}
	public 	void setVotes(int v)
	{
		this.Votes = v;
		
	}
	public 	void setTries(int tries)
	{
		this.Tries = tries;
		
	}
	
	
	

}
