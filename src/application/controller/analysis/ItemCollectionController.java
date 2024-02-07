package application.controller.analysis;


import java.io.IOException;
import org.json.JSONObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ItemCollectionController   {

	private static final String URL = "application/view/ItemList.fxml" ;
	private JSONObject data ;
	@FXML
	private VBox itemList;
	@FXML
	private VBox detail;
	@FXML
	private HBox popular;
	@FXML
	private Text text1;
	@FXML
	private Text text2;
	@FXML
	private Text text3;
	@FXML
	private Text postUrl ;
	@FXML 
	private Text imgUrl ;
	@FXML
	private Text tag ;
	@FXML
	private Text view ;
	@FXML
	private Text like ;
	
	public ItemCollectionController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(URL));
		fxmlLoader.setController(this);
	      try {
	          itemList = fxmlLoader.load();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	    detail.setVisible(false);
	    detail.setManaged(false); 
	}
	
	public VBox getRoot() {
		return itemList;
	} 
	
	public void loadData(JSONObject a) {
		data = a ;
		try {
			text1.setText(data.getString("name"));
			text2.setText(data.getString("volume"));
			text3.setText(Integer.toString(data.getInt("rank")));
		}  catch (NumberFormatException e2) {
				System.out.println("Can't parse to int!");
			}
		}
	
	public void addError(String error) {
		text1.setText(error);
    }	
}
