package application.controller.hashtag;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import application.controller.AMyController;
import application.database.dao.NfticallyBlogDB;
import application.database.dao.TwitterDB;
import application.service.HotHashTagService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HotHashtagController extends AMyController{
	
	private static final String URL              = "application/view/HotHashtag.fxml" ;
	private static final String HEADER_STRING    = "Tìm hashtag hot nhất" ;
	private JSONArray data = new JSONArray();
	
	@FXML
	private VBox root;
	@FXML
	private Label header;
	@FXML
	private VBox showArea;
	@FXML
	private ComboBox<String> socialOption;
	@FXML
	private ComboBox<String> timeOption;
	
	public HotHashtagController() {
		loadView();
	}
	
	@Override
	public VBox getRoot() {
		return root ;
	}
	
	@FXML
	void searchButtonPressed(ActionEvent event) throws IOException {
		if(socialOption.getValue() == null) {
			ItemTagController aController = new ItemTagController() ;
			aController.addError("Xin lỗi bạn hãy chọn nền tảng bài viết");
			showArea.getChildren().add(aController.getRoot());
		} else {
			loadData() ;
			showArea.getChildren().clear();		
			for(int i = 0; i < data.length() ;i++) {	
				JSONObject jsonObject = data.getJSONObject(i);
				ItemTagController itemTagController = new ItemTagController() ;
				itemTagController.loadData(jsonObject);
				showArea.getChildren().add(itemTagController.getRoot());
			}				
		}
		
	}
	
	@Override
	public void loadView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(URL));
		fxmlLoader.setController(this);
	      try {
	          root = fxmlLoader.load();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	     header.setText(HEADER_STRING);
	     ObservableList<String> options = FXCollections.observableArrayList("Twitter","Blog");
		 socialOption.setItems(options);
		      
		 ObservableList<String>    time = FXCollections.observableArrayList("24H","1W","1M");
		 timeOption.setItems(time);
	}
	
	@Override
	public void loadData() {
		HotHashTagService service ;
		if     (socialOption.getValue().equals("Twitter"))    service = new HotHashTagService(new TwitterDB()) ;
		else if(socialOption.getValue().equals("Blog"))       service = new HotHashTagService(new NfticallyBlogDB()) ;
		else 												  service = new HotHashTagService(new TwitterDB()) ;
		
		if     (timeOption.getValue() == null)				  data = service.getAll()	  ;
		else {
			switch (timeOption.getValue()) {
			case "24H" :  data = service.getByDay()   ; break ;
			case "1W" :   data = service.getByWeek()  ; break ;
			case "1M" :   data = service.getByMonth() ; break ;
			default   :   data = service.getAll()	  ; break ;
		  }
		}
	} 
 }
