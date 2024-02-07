package application.database.model;

public abstract class ACollectionModel {
	protected int    rank ;
	protected String name ;
	protected String volume ;
	protected String percentChange ;
	protected String floorPrice ;
	protected String date ;
	
	public ACollectionModel(int rank, String name, String volume, String percentChange, String floorPrice, String date) {
		this.rank 			= rank ;
		this.name 			= name;
		this.volume 		= volume ;
		this.percentChange 	= percentChange ;
		this.floorPrice 	= floorPrice ;
		this.date 			= date ;
	}
	
	public abstract float calVolume() ; 	
	public int getRank() 			 { return rank; }
	public String getName() 		 { return name;}
	public String getVolume() 		 { return volume;}
	public String getPercentChange() { return percentChange;}
	public String getFloorPrice() 	 { return floorPrice;}
	public String getDate() 		 { return date; }
	
}
