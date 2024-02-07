package application.controller;

import java.io.IOException;
import application.crawl.crawler.BinanceCrawler;
import application.crawl.crawler.NfticallyBlogCrawler;
import application.crawl.crawler.OpenSeaCrawler;
import application.crawl.crawler.TwitterCrawler;
import application.crawl.driver.EdgeDriverContext;
import application.service.CrawlService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CrawlController  {
	private static final String URL           = "application/view/Crawl.fxml" ;
	private static final String HEADER_STRING = "Thu thập dữ liệu" ;
	private CrawlService service ;
	@FXML
	private VBox root;
	@FXML
	private Label header;
	@FXML
	private VBox showArea;
	@FXML
	private ComboBox<String> socialOption;
	@FXML
	private Label finishText;

	public CrawlController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(URL));
		fxmlLoader.setController(this);
	      try {
	          root = fxmlLoader.load();
	      } catch (IOException e) {
	    	  e.printStackTrace();
	      }
	      header.setText(HEADER_STRING);
	      ObservableList<String> options = FXCollections.observableArrayList("Twitter","NFTicallyBlog","Binance","OpenSea");
		  socialOption.setItems(options);
		  finishText.setVisible(false);
	}
	
	public VBox getRoot() {
		return root ;
	}
	
	@FXML
	void crawlButtonPressed(ActionEvent event) throws IOException {
		if(socialOption.getValue() == null)  service = new CrawlService(new TwitterCrawler(new EdgeDriverContext())) ; 
		else {
			switch(socialOption.getValue()){
				case "Twitter"       : service = new CrawlService(new TwitterCrawler(new EdgeDriverContext()))            ;  break ;
				case "NFTicallyBlog" : service = new CrawlService(new NfticallyBlogCrawler(new EdgeDriverContext()))      ;  break ;
				case "Binance"       : service = new CrawlService(new BinanceCrawler(new EdgeDriverContext()))            ;  break ;         
				case "OpenSea"       : service = new CrawlService(new OpenSeaCrawler(new EdgeDriverContext()))            ;  break ;
				default              : service = new CrawlService(new TwitterCrawler(new EdgeDriverContext()))            ;  break ;
		      }
		}
		if( service.crawlData()) {
				finishText.setVisible(true);
				finishText.setText("Crawl Success!");
		} else {
				finishText.setVisible(true);
				finishText.setText("Crawl Fail!");
		}
	}
}
