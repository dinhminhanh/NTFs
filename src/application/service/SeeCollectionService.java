package application.service;

import org.json.JSONArray;
import org.json.JSONObject;

import application.database.dao.ICollectionDB;
import application.database.model.ACollectionModel;


public class SeeCollectionService {

	private ICollectionDB<?> db;
	
	public SeeCollectionService(ICollectionDB<?> db ){
		this.db = db ;
	}
	
	public  JSONArray getAllCollectionInPreviousDay(){
		JSONArray jsonArray = new JSONArray();
		for(ACollectionModel item : db.getAllColectionInDayAgo(1)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", item.getName());
			jsonObject.put("volume", item.getVolume());
			jsonObject.put("rank", item.getRank());
 	        jsonArray.put(jsonObject);
		}
		return jsonArray ;
	}
	
	public JSONArray getCollectionByNameInPreviousDay(String name){
		JSONArray jsonArray = new JSONArray();
		for(ACollectionModel item : db.getCollectionByNameInDayAgo(name, 1)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", item.getName());
			jsonObject.put("volume", item.getVolume());
			jsonObject.put("rank", item.getRank());
 	        jsonArray.put(jsonObject);
		}
		return jsonArray ;
	}
	
}
