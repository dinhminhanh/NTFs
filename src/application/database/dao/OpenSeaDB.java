package application.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.database.dbcontext.MySqlDB;
import application.database.model.OpenSeaModel;

public class OpenSeaDB extends MySqlDB implements ICollectionDB<OpenSeaModel> {
	
	@Override
	public List<OpenSeaModel> getAllColectionInDayAgo(int dayago) {
		List<OpenSeaModel> list = new ArrayList<OpenSeaModel>();
		 try {
			   String sql=("SELECT * FROM opensea where date(date) = date_sub(curdate(),interval ? day) and date <= curdate() ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
		       st.setInt(1,dayago );
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	   int rank  = rs.getInt("rank");			
		            String name      = rs.getString("collection");				
		            String volume   = rs.getString("volume") ;		
		            String percentChange  = rs.getString("percentChange");		
		            String floorPrice   = rs.getString("floorPrice");					
		            String date		 = rs.getString("date");	
		            int sales		 = rs.getInt("sales");		
		            OpenSeaModel c = new OpenSeaModel(rank, name, volume, percentChange, floorPrice, date, sales) ;
		            list.add(c);
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

	@Override
	public List<OpenSeaModel> getCollectionByNameInDayAgo(String name, int day) {
		List<OpenSeaModel> list = new ArrayList<OpenSeaModel>();
		 try {
			   System.out.println("added");
			   String sql=("select * from opensea where collection like ? and date(date) = date_sub(curdate(),interval ? day) and date <= curdate() ");
		       PreparedStatement st = connection.prepareStatement(sql);
		       st.setString(1,name);
		       st.setInt(2,day );
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {
		            int rank  = rs.getInt("rank");			
		            String nameCollection      = rs.getString("collection");				
		            String volume              = rs.getString("volume") ;		
		            String percentChange       = rs.getString("percentChange");		
		            String floorPrice          = rs.getString("floorPrice");					
		            String date		           = rs.getString("date");	
		            int sales		            = rs.getInt("sales");		
		            OpenSeaModel c = new OpenSeaModel(rank, nameCollection , volume, percentChange, floorPrice, date, sales) ;
		            list.add(c);
		            
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

}
