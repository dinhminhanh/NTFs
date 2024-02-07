package application.controller.analysis;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import application.controller.AMyController;
import application.controller.ICallUrl;
import application.database.dao.BinanceDB;
import application.database.dao.OpenSeaDB;
import application.service.SeeCollectionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SeeCollectionController extends AMyController implements ICallUrl{
	
	private static final String URL             = "application/view/Analysis.fxml" ;
	private static final String HEADER_STRING   = "Phân tích tương quan" ;
	private JSONArray data = new JSONArray(); 
	private SeeCollectionService option ;
	
	@FXML
	private VBox root;
	@FXML
	private Label header;
	@FXML
	private VBox showArea;
	@FXML
	private TextField search ;
	@FXML
	private ComboBox<String> marketplace;
	
	public SeeCollectionController() {
		loadView(); 
	}
	
	@Override
	public VBox getRoot() {
		return root ;
	}
	
	@FXML
	void searchButtonPressed(ActionEvent event) {
		showArea.getChildren().clear();	
		if(marketplace.getValue()   == null) {
			ItemCollectionController addInform = new ItemCollectionController() ;
			addInform.addError("Hãy chọn sàn NFT");
			showArea.getChildren().add(addInform.getRoot());
		} 
		else {
		  loadData();
		  if(data.length()==0) {
			  ItemCollectionController addInform = new ItemCollectionController() ;
			  addInform.addError("Không tìm thấy kết quả");
			  showArea.getChildren().add(addInform.getRoot());
		  } 
		  else {
		  for(int i = 0; i < data.length() ;i++) {	
				
				JSONObject jsonObject = data.getJSONObject(i);
				ItemCollectionController addList = new ItemCollectionController() ;
				addList.loadData(jsonObject);
				addList.getRoot().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent arg0) {
						GraphController graph = new GraphController(marketplace.getValue(),jsonObject.getString("name"));
						call(graph);
						graph.loadData();
					}
				});
				showArea.getChildren().add(addList.getRoot());
			}
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
	     ObservableList<String> options = FXCollections.observableArrayList("OpenSea","Binance");
	     marketplace.setItems(options);   
	}
	
	@Override
	public void loadData() {
		if      (search.getText().isEmpty() && (marketplace.getValue().equals("OpenSea")))  { 
			option = new SeeCollectionService(new OpenSeaDB()) ;
			data   = option.getAllCollectionInPreviousDay() ;
		}	
		else if (search.getText().isEmpty() && (marketplace.getValue().equals("Binance"))) {
			 option = new SeeCollectionService(new BinanceDB()) ;
			 data   = option.getAllCollectionInPreviousDay() ;	
		}
		else if(!search.getText().isEmpty() && (marketplace.getValue().equals("OpenSea"))) {
			 option = new SeeCollectionService(new OpenSeaDB()) ;
			 data   = option.getCollectionByNameInPreviousDay(search.getText()) ;
		}
		else if(!search.getText().isEmpty() && (marketplace.getValue().equals("Binance"))) {
			 option = new SeeCollectionService(new BinanceDB()) ;
			 data   = option.getCollectionByNameInPreviousDay(search.getText()) ;
		}
		
	 }

	@Override
	public void call(AMyController controller) {
		root.getChildren().clear();
		root.getChildren().add(controller.getRoot()); 
	}	
}
