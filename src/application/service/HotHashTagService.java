package application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

import application.database.dao.IPostDB;
import application.database.model.APostModel;

public class HotHashTagService  {
	
	private IPostDB<?> db ;
	public static final int DAY   = 1 ;
	public static final int WEEK  = 7 ;
	public static final int MONTH = 30 ;
	
	public HotHashTagService(IPostDB<?> db){ 
		this.db = db ;
	}

	public JSONArray getAll() {
		JSONArray jsonArray = new JSONArray();
		List<String> listTag = new ArrayList<>();
		for(APostModel  pos : db.getAllPost()) listTag.addAll(pos.tagList());
		int i = 1;
	    for (Map.Entry<String, Integer> entry : fromPostToHashtagOrder(listTag)) {
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("Hashtag", entry.getKey()) ;
	    	jsonObject.put("Number" , entry.getValue()) ;
	    	jsonObject.put("Rank"   , i) ;
	    	jsonArray.put(jsonObject);
	    	i++;
	    }
		return jsonArray ;
	}
	
	public JSONArray getByDay(){
		JSONArray jsonArray = new JSONArray();
		List<String> listTag = new ArrayList<>();
		for(APostModel  pos : db.getPostIn24H()) listTag.addAll(pos.tagList());
		int i = 1;
	    for (Map.Entry<String, Integer> entry : fromPostToHashtagOrder(listTag)) {
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("Hashtag", entry.getKey()) ;
	    	jsonObject.put("Number" ,entry.getValue()) ;
	    	jsonObject.put("Rank"   , i) ;
	    	jsonArray.put(jsonObject);
	    	i++;
	    }
		return jsonArray ;
	}
		
	public JSONArray getByWeek(){
		JSONArray jsonArray = new JSONArray();
		List<String> listTag = new ArrayList<>();
		for(APostModel  pos : db.getPostIn1W()) listTag.addAll(pos.tagList());
		int i = 1;
	    for (Map.Entry<String, Integer> entry : fromPostToHashtagOrder(listTag)) {
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("Hashtag", entry.getKey()) ;
	    	jsonObject.put("Number" , entry.getValue()) ;
	    	jsonObject.put("Rank"   , i) ;
	    	jsonArray.put(jsonObject);
	    	i++;
	    }
		return jsonArray ;
	}
		
	public JSONArray getByMonth(){
		JSONArray jsonArray = new JSONArray();
		List<String> listTag = new ArrayList<>();
		for(APostModel  pos : db.getPostIn1M()) listTag.addAll(pos.tagList());
		int i = 1;
	    for (Map.Entry<String, Integer> entry : fromPostToHashtagOrder(listTag)) {
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("Hashtag", entry.getKey()) ;
	    	jsonObject.put("Number",entry.getValue()) ;
	    	jsonObject.put("Rank"   , i) ;
	    	jsonArray.put(jsonObject);
	    	i++;
	    }
		return jsonArray ;
	}
	
	private List<Map.Entry<String, Integer>> fromPostToHashtagOrder(List<String> stringHashtag) {
		Map<String, Integer> hashtagCountMap = new HashMap<>();
        for (String hashtag : stringHashtag) {
            if (!hashtag.isEmpty()) {
                hashtagCountMap.put(hashtag, hashtagCountMap.getOrDefault(hashtag, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(hashtagCountMap.entrySet());
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		return entryList;	
	}
	
}
