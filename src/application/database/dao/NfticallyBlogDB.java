package application.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.database.dbcontext.MySqlDB;
import application.database.model.NfticallyBlogModel;

public class NfticallyBlogDB extends MySqlDB implements IPostDB<NfticallyBlogModel> {

	@Override
	public List<NfticallyBlogModel> getAllPost() {
		 List<NfticallyBlogModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("SELECT * FROM blog ORDER BY date LIMIT 50;");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		    	    String acount   = rs.getString("author");			
		            String date     = rs.getString("date");				
		            String hashtag  = rs.getString("tag") ;		
		            String imageURL = rs.getString("blogImage");		
		            String postURL  = rs.getString("blogURL");		
		            String title    = rs.getString("title");					
		            String describe = rs.getString("describe");					
		            NfticallyBlogModel c = new NfticallyBlogModel(acount,date,hashtag,postURL,imageURL,title,describe);
		            list.add(c);
		        }
		      //  connection.close();
		     }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

	@Override
	public List<NfticallyBlogModel> getPostIn24H() {
		List<NfticallyBlogModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from blog WHERE STR_TO_DATE(date, '%M %e,%Y') >= DATE_SUB(CURDATE(), INTERVAL 1 day ) ;");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		    	    String acount   = rs.getString("author");			
		            String date     = rs.getString("date");				
		            String hashtag  = rs.getString("tag") ;		
		            String imageURL = rs.getString("blogImage");		
		            String postURL  = rs.getString("blogURL");		
		            String title    = rs.getString("title");					
		            String describe = rs.getString("describe");					
		            NfticallyBlogModel c = new NfticallyBlogModel(acount,date,hashtag,postURL,imageURL,title,describe);
		            list.add(c);
		        }
		      //  connection.close();
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

	@Override
	public List<NfticallyBlogModel> getPostIn1W() {
		List<NfticallyBlogModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from blog WHERE STR_TO_DATE(date, '%M %e,%Y') >= DATE_SUB(CURDATE(), INTERVAL 1 week ) ;");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		    	    String acount   = rs.getString("author");			
		            String date     = rs.getString("date");				
		            String hashtag  = rs.getString("tag") ;		
		            String imageURL = rs.getString("blogImage");		
		            String postURL  = rs.getString("blogURL");		
		            String title    = rs.getString("title");					
		            String describe = rs.getString("describe");				
		            NfticallyBlogModel c = new NfticallyBlogModel(acount,date,hashtag,postURL,imageURL,title,describe);
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
	public List<NfticallyBlogModel> getPostIn1M() {
		//SELECT * FROM blog WHERE STR_TO_DATE(date, '%M %e,%Y') >= DATE_SUB(CURDATE(), INTERVAL 200 DAY);
		List<NfticallyBlogModel> list = new ArrayList<>();
		 try {
			   Statement stmt=connection.createStatement();
			   System.out.println("added");
			   String sql=("select * from blog WHERE STR_TO_DATE(date, '%M %e,%Y') >= DATE_SUB(CURDATE(), INTERVAL 1 month ) ;");		   
		       ResultSet rs = stmt.executeQuery(sql);
		       while (rs.next()) {
		    	    String acount = rs.getString("author");			
		            String date = rs.getString("date");				
		            String hashtag = rs.getString("tag") ;		
		            String imageURL = rs.getString("blogImage");		
		            String postURL = rs.getString("blogURL");		
		            String title = rs.getString("title");					
		            String describe = rs.getString("describe");				
		            NfticallyBlogModel c = new NfticallyBlogModel(acount,date,hashtag,postURL,imageURL,title,describe);
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
	public List<NfticallyBlogModel> getPostInDayAgo(int dayAgo) {
				List<NfticallyBlogModel> list = new ArrayList<>();
				 try {
					   System.out.println("added");
					   String sql=("SELECT * FROM blog WHERE DATE(date) = DATE_SUB(curdate(), interval ? day) ;");		   
					   PreparedStatement st = connection.prepareStatement(sql);
				       st.setInt(1,dayAgo);
				       ResultSet rs = st.executeQuery();
				       while (rs.next()) {
				    	    String acount = rs.getString("author");			
				            String date = rs.getString("date");				
				            String hashtag = rs.getString("tag") ;		
				            String imageURL = rs.getString("blogImage");		
				            String postURL = rs.getString("blogURL");		
				            String title = rs.getString("title");					
				            String describe = rs.getString("describe");						
				            NfticallyBlogModel c = new NfticallyBlogModel(acount,date,hashtag,postURL,imageURL,title,describe);
				            list.add(c);
				        }
				        connection.close();
				   }
				  catch(SQLException e) {
					   System.out.println(e);
				  } 
				 return list;
	}

}
