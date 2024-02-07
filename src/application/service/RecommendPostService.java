package application.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.DbDoc;

import application.database.dao.ICollectionDB;
import application.database.dao.IPostDB;
import application.database.model.ACollectionModel;
import application.database.model.APostModel;


public class RecommendPostService {
	private IPostDB<?> postDB     ;
	private ICollectionDB<?> marketDB ;
	
	private static final int NUMBEROFPLOT = 4 ;
	
	public RecommendPostService (IPostDB<?> postDB, ICollectionDB<?> marketDB ) {
		this.postDB = postDB ;
		this.marketDB = marketDB ;
	}
	
	public JSONArray getReccomendPost(String name) {
		JSONArray jsonArray = new JSONArray();
		List<Float> volumeList = new ArrayList<Float>();

		for(int i = 1 ; i <= NUMBEROFPLOT ; i++) {
			
			if(marketDB.getCollectionByNameInDayAgo(name, i).size()==0) volumeList.add(0.0F);
			else {
				ACollectionModel collection = marketDB.getCollectionByNameInDayAgo(name, i).get(0) ;
				volumeList.add(collection.calVolume()) ;
			}
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(name + " ");
		for (int i = 1; i < volumeList.size(); i++) {
            if      (volumeList.get(i) > volumeList.get(i-1))     queryBuilder.append("increase");
            else if (volumeList.get(i) < volumeList.get(i-1))     queryBuilder.append("decrease"); 
            else if (volumeList.get(i) == volumeList.get(i-1))    queryBuilder.append("unchanged");
        }

		try {
			FSDirectory indexDirectory = FSDirectory.open(Paths.get("E:\\MySql\\apacelucene"));
			IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
			IndexWriter       writer = new IndexWriter(indexDirectory, config);

			writer.deleteAll();
			
			for (APostModel pos : postDB.getAllPost()) {
			     Document doc = new Document();
			     doc.add(new TextField("url",    pos.getPostUrl()      , Field.Store.YES));
			     doc.add(new TextField("content",pos.getDetailSearch() , Field.Store.YES));
			     writer.addDocument(doc);
			 }

			writer.close();
			DirectoryReader reader = DirectoryReader.open(indexDirectory);
			IndexSearcher searcher = new IndexSearcher(reader);
		    QueryParser parser = new QueryParser("content", new StandardAnalyzer());
		    Query query = parser.parse(queryBuilder.toString().trim());
		    TopDocs topDocs = searcher.search(query, 5);
		    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			for (int i = 0; i < scoreDocs.length; i++) {
				JSONObject jsonObject = new JSONObject();
				ScoreDoc scoreDoc = scoreDocs[i];
				Document doc = searcher.getIndexReader().document(i);
				jsonObject.put("url",doc.get("url") );
				jsonArray.put(jsonObject);
			 }
			
		     reader.close();
		     indexDirectory.close();
		     for(APostModel pos : postDB.getAllPost() ) {
		    	 for(String iString : pos.tagList()) {
		    		 if(iString.equalsIgnoreCase(name.replaceAll("\\s+", ""))) {
		    			 JSONObject jsonObject = new JSONObject();
		    			 jsonObject.put("url",pos.getPostUrl() );
		    			 jsonArray.put(jsonObject);
		    			 break ;
		    		 }
		    	 }
		     }
		     Set<String> uniqueJsonStrings = new HashSet<>();
			  int length = jsonArray.length();
			
			  for (int i = 0; i < length; i++) {
			     JSONObject jsonObject = jsonArray.getJSONObject(i);
			     String jsonString = jsonObject.toString();
			     if (uniqueJsonStrings.contains(jsonString)) {
			            jsonArray.remove(i);
			            i--; 
			            length--; 
			      } 
			        else   uniqueJsonStrings.add(jsonString);
			     }	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  return  jsonArray;	
	}
	
}
