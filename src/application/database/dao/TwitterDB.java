package application.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.database.dbcontext.MySqlDB;
import application.database.model.TwitterModel;

public class TwitterDB extends MySqlDB implements IPostDB<TwitterModel> {

	@Override
	public List<TwitterModel> getAllPost() {
		 List<TwitterModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("SELECT * FROM twitter ORDER BY date ;");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		    	   String acount = rs.getString("acount");			
		            String date = rs.getString("date");				
		            String hashtag = rs.getString("hashtag") ;		
		            String imageURL = rs.getString("imageUrl");		
		            String postURL = rs.getString("postlUrl");		
		            int views = rs.getInt("views");					
		            int likes = rs.getInt("likes");					
		            TwitterModel c = new TwitterModel(acount,date,hashtag,postURL,imageURL,views,likes);
		            list.add(c);
		        }

		       // connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}
	
	@Override
	public List<TwitterModel> getPostIn24H() {
		List<TwitterModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from twitter where date >= date_sub(curdate(),interval 1 day) and date <= curdate()");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		            String acount    = rs.getString("acount");			
		            String date      = rs.getString("date");				
		            String hashtag   = rs.getString("hashtag") ;		
		            String imageURL  = rs.getString("imageUrl");		
		            String postURL   = rs.getString("postlUrl");		
		            int view 		 = rs.getInt("views");					
		            int likes		 = rs.getInt("likes");					
		            TwitterModel c = new TwitterModel(acount,date,hashtag,postURL,imageURL,view,likes);
		            list.add(c);
		        }

		        connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

	@Override
	public List<TwitterModel> getPostIn1W() {
		List<TwitterModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from twitter where date >= date_sub(curdate(),interval 1 week) and date <= curdate()");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		            String acount    = rs.getString("acount");			
		            String date      = rs.getString("date");				
		            String hashtag   = rs.getString("hashtag") ;		
		            String imageURL  = rs.getString("imageUrl");		
		            String postURL   = rs.getString("postlUrl");		
		            int view 		 = rs.getInt("views");					
		            int likes		 = rs.getInt("likes");					
		            TwitterModel c = new TwitterModel(acount,date,hashtag,postURL,imageURL,view,likes);
		            list.add(c);
		        }

		        connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
		
	}

	@Override
	public List<TwitterModel> getPostIn1M() {
		List<TwitterModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from twitter where date >= date_sub(curdate(),interval 1 month) and date <= curdate()");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		            String acount    = rs.getString("acount");			
		            String date      = rs.getString("date");				
		            String hashtag   = rs.getString("hashtag") ;		
		            String imageURL  = rs.getString("imageUrl");		
		            String postURL   = rs.getString("postlUrl");		
		            int view 		 = rs.getInt("views");					
		            int likes		 = rs.getInt("likes");					
		            TwitterModel c = new TwitterModel(acount,date,hashtag,postURL,imageURL,view,likes);
		            list.add(c);
		        }

		        connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}
	
	@Override
	public List<TwitterModel> getPostInDayAgo(int dayAgo) {
		List<TwitterModel> list = new ArrayList<>();
		 try {
			   System.out.println("added");
			   String sql=("SELECT * FROM twitter WHERE DATE(date) = DATE_SUB(curdate(),interval ? day) ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
		       st.setInt(1,dayAgo);
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {
		            String acount    = rs.getString("acount");			
		            String date      = rs.getString("date");				
		            String hashtag   = rs.getString("hashtag") ;		
		            String imageURL  = rs.getString("imageUrl");		
		            String postURL   = rs.getString("postlUrl");		
		            int view 		 = rs.getInt("views");					
		            int likes		 = rs.getInt("likes");					
		            TwitterModel c = new TwitterModel(acount,date,hashtag,postURL,imageURL,view,likes);
		            list.add(c);
		        }

		       // connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}
}
