package application.database.model;

public class OpenSeaModel extends ACollectionModel {
	
	private int sales ;

	public int getSales() {return sales;}
	
	public OpenSeaModel(int rank, String name, String volume, String percent, String floor, String date, int sale) {
		super(rank, name, volume, percent, floor, date);
		this.sales = sale;
	}

	@Override
	public float calVolume() throws NumberFormatException {
		String chuoi = volume.replaceAll(",", ".");
        chuoi = chuoi.replaceAll("[^\\d.]", "");
        float soThuc = Float.parseFloat(chuoi);
        return soThuc;
	}
	
}
