package application.controller.analysis;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import application.service.SeeGraphService;
import application.controller.AMyController;
import application.database.dao.BinanceDB;
import application.database.dao.OpenSeaDB;
import application.database.dao.TwitterDB;
import application.service.RecommendPostService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GraphController extends AMyController {
	private static final String URL = "application/view/Graph.fxml" ;
	private String marketOption ;
	private String nameCollection ;
	private JSONArray   data1 = new JSONArray();
	private JSONObject  data2 = new JSONObject();
	@FXML
	private VBox root;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label header;
    @FXML
    private VBox showArea;
    
    @FXML
    private LineChart<String,Float> chart ;
    
	public GraphController(String marketOption,String nameCollection) {
		this.marketOption      = marketOption ;
		this.nameCollection    = nameCollection ;
		loadView();	
    }
	
	@Override
	public VBox getRoot() {
		return root;
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
	     
	}
	
	@Override
	public void loadData() {	
		 if     (marketOption.equals("OpenSea"))   { 
			     RecommendPostService option1 = new RecommendPostService(new TwitterDB(), new OpenSeaDB()) ;
			     SeeGraphService	  option2 = new SeeGraphService(new TwitterDB(), new OpenSeaDB()) ;
			 	 data1 =  option1.getReccomendPost(nameCollection)  ; 
			 	 data2 =  option2.getDataDraw(nameCollection)       ;
		 }
		 else if (marketOption.equals("Binance"))   { 
			     RecommendPostService option1 = new RecommendPostService(new TwitterDB(), new BinanceDB()) ;
			     SeeGraphService	  option2 = new SeeGraphService(new TwitterDB(), new BinanceDB()) ;
			     data1 =  option1.getReccomendPost(nameCollection)  ; 
			 	 data2 =  option2.getDataDraw(nameCollection)       ;
				}
		 try {
			 header.setText(nameCollection);
			 yAxis.setAutoRanging(false);  // Tắt tự động điều chỉnh
		     yAxis.setLowerBound(0);       // Giá trị tối thiểu
		     yAxis.setUpperBound(400);     // Giá trị tối đa

		     yAxis.setTickUnit(10);       // Đơn vị là 10
		     XYChart.Series<String,Float> series1 = new XYChart.Series<>();
		     series1.setName("Volume of Collection");
		     for(int i = 4; i > 0 ; i--) {
		    	 series1.getData().add(new XYChart.Data<>(i + " day ago" , data2.getFloat("Volume Day"+i)));
		     }
		     XYChart.Series<String,Float> series2 = new XYChart.Series<>();
		     series2.setName("Number of Tweets");
		     for(int i = 4 ; i > 0  ; i--) {
				series2.getData().add(new XYChart.Data<>(i + " day ago" , data2.getFloat("Tag Day"+i)  ));
		     }
		     chart.getData().addAll(series1,series2);
		     Text text1 = new Text();
		     showArea.getChildren().add(text1) ;
		     StringBuilder urlBuilder = new StringBuilder();
		     for(int j = 0 ; j < data1.length() ; j++) urlBuilder.append(data1.getJSONObject(j).get("url")).append("\n") ;
		     text1.setText(urlBuilder.toString());
		  } catch (NullPointerException e) {
			e.printStackTrace();
		}
		 
	}
}