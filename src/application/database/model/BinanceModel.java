package application.database.model;

public class BinanceModel  extends ACollectionModel{
	
	private int owners ;
	private int items ;
	
	public BinanceModel(int rank, String name, String volume, String percentChange, String floorPrice, String date, int owners, int items) {
		super(rank, name, volume, percentChange, floorPrice, date);
		this.owners = owners ;
		this.items  = items;
	}
	
	public int getOwners() 			  { return owners;}
	public int getItems() 			  { return items; }

	@Override
	public float calVolume() throws NumberFormatException {
		String chuoi = volume.replaceAll(",", ".");
        chuoi = chuoi.replaceAll("[^\\d.]", "");
        float volFloat = Float.parseFloat(chuoi);
        return volFloat;

	}
		
}
