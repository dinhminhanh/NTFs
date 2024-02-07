package application.service;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import application.database.dao.IPostDB;
import application.database.model.APostModel;

public class SeePostService {
	
	private IPostDB<?> db ;
	
	public SeePostService(IPostDB<?> db ){
		this.db = db ;
	}
	
	public  JSONArray getAllPost(){
		JSONArray jsonArray = new JSONArray();
		for(APostModel post : db.getAllPost()) 	{
			JSONObject jsonObject = new JSONObject(post);
 	        jsonArray.put(jsonObject);
		}
		return jsonArray ;
	}
	
	public JSONArray getPostByHashtag(String hashtag){
		JSONArray jsonArray = new JSONArray();
		for(APostModel post : db.getAllPost()) 	{
			List<String> hashtags = post.tagList() ;
			for (String tag : hashtags) {
				System.out.println(tag);
				if(tag.equalsIgnoreCase(hashtag))  {
					jsonArray.put(new JSONObject(post));
					break;
				}
			}
		}
		return jsonArray ;
	}
	
}
