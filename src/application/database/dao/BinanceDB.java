package application.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.database.dbcontext.MySqlDB;
import application.database.model.BinanceModel;

public class BinanceDB  extends MySqlDB implements ICollectionDB<BinanceModel> {

	@Override
	public List<BinanceModel> getAllColectionInDayAgo(int dayAgo) {
		List<BinanceModel> list = new ArrayList<BinanceModel>();
		 try {
			   String sql=("SELECT * FROM binance where date(date) = date_sub(curdate() , interval ? day) and date <= curdate() ;");		   
			   PreparedStatement st = connection.prepareStatement(sql);
		       st.setInt(1, dayAgo );
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {	
		    	    int rank                   = rs.getInt("rank");			
		            String nameCollection      = rs.getString("collection");				
		            String volume              = rs.getString("volume") ;		
		            String percentChange       = rs.getString("percentChange");		
		            String floorPrice          = rs.getString("floorPrice");					
		            int owners		           = rs.getInt("owners");	
		            int items		           = rs.getInt("items");
		            String date				   = rs.getString("date") ;
		            BinanceModel c = new BinanceModel(rank, nameCollection , volume, percentChange, floorPrice, date, owners,items ) ;
		            list.add(c);
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		  } 
		 return list;
	}

	@Override
	public List<BinanceModel> getCollectionByNameInDayAgo(String name, int day) {
		List<BinanceModel> list = new ArrayList<BinanceModel>();
		 try {
			   System.out.println("added");
			   String sql=("select * from binance where collection like ? and date(date) = date_sub(curdate(),interval ? day) and date <= curdate() ");
		       PreparedStatement st = connection.prepareStatement(sql);
		       st.setString(1,name);
		       st.setInt(2,day );
		       ResultSet rs = st.executeQuery();
		       while (rs.next()) {
		            int rank                   = rs.getInt("rank");			
		            String nameCollection      = rs.getString("collection");				
		            String volume              = rs.getString("volume") ;		
		            String percentChange       = rs.getString("percentChange");		
		            String floorPrice          = rs.getString("floorPrice");					
		            int owners		           = rs.getInt("owners");	
		            int items		           = rs.getInt("items");
		            String date				   = rs.getString("date") ;
		            BinanceModel c = new BinanceModel(rank, nameCollection , volume, percentChange, floorPrice, date, owners , items ) ;
		            list.add(c);
		        }
		   }
		  catch(SQLException e) {
			   System.out.println(e);
		   } 
		  return list;
	}

}
