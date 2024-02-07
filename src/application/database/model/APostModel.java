package application.database.model;

import java.util.List;

public abstract class APostModel {		
	private String author ;
	private String date ;
	private String tags ;
	private String postUrl ;
	private String imageUrl;
	
	public APostModel(String author, String date , String tags , String postUrl , String imageUrl) {
		this.author		= author ;
		this.date 		= date	 ;
		this.tags		= tags	 ;
		this.postUrl 	= postUrl;
		this.imageUrl	= imageUrl;
	}

	public String getAuthor()  {  return author; }
	public String getDate()    {  return date  ; }
	public String getTags()	   {  return tags  ; }
	public String getPostUrl() {  return postUrl;}
	public String getImageUrl(){  return imageUrl;}
	
	public abstract List<String> tagList() ;	
	public abstract String getDetailSearch() ;
}
