package application.controller.post;

import java.io.IOException;
import org.json.JSONObject;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ItemPostController {
	
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
	private Text postUrl ;
	@FXML 
	private Text imgUrl ;
	@FXML
	private Text tag ;
	@FXML
	private Text view ;
	@FXML
	private Text like ;
	
	public ItemPostController() {
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
	
	public void addAction() {
		itemList.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			private static int count = 0 ;
			@Override
			public void handle(MouseEvent arg0) {
				if(count == 0) {
					detail.setManaged(true);
					detail.setVisible(true);
					count ++ ;	
				} else {
					detail.setVisible(false);
					detail.setManaged(false);
					count = 0 ;
				}
				if(view.getText().isEmpty() || like.getText().isEmpty()) {popular.setVisible(false); popular.setManaged(false);}
			}
		});
	}

	public void loadData(JSONObject a) {
		data = a ;
		try {
			text1.setText(data.getString("author"));
			text2.setText(data.getString("date"));
			tag.setText(data.getString("tags"));
			imgUrl.setText(data.getString("imageUrl"));
			postUrl.setText(data.getString("postUrl"));
			if(data.has("views") && data.has("likes")) {
				view.setText(Integer.toString(data.getInt("views")));
				like.setText(Integer.toString(data.getInt("likes")));
			}
		} catch (Exception e) {
			 System.out.println("Json data not found!");
		}
		
	 }
	public void addError(String error) {
		text1.setText(error);
	}	
}
