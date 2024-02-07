package application.controller;

import java.io.IOException;

import application.controller.analysis.SeeCollectionController;
import application.controller.hashtag.HotHashtagController;
import application.controller.home.HomeController;
import application.controller.post.SeePostController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class BaseController  implements ICallUrl {
	
	private static final String URL = "application/view/Base.fxml" ;
	@FXML
    private VBox main;
	
	public VBox getRoot() {
		return main;
	}
	 public BaseController() {
		
	}
	 
	@FXML
	public void TrangChu(ActionEvent event) {
		call( new HomeController())     ;
	}
	
	@FXML
	public void XemDanhSach(ActionEvent event) throws IOException {
		call( new SeePostController())	 ;
	}
	
	@FXML
	public void TimHotHashtag(ActionEvent event) throws IOException {
		call( new HotHashtagController()) ;
	}
	
	@FXML
	public void PhanTich(ActionEvent event) throws IOException {
		call( new SeeCollectionController()) ;
	}
	
	@FXML
	public void Crawl(ActionEvent event) throws IOException {
		main.getChildren().clear();
		main.getChildren().add((new CrawlController()).getRoot()); 
	}
	
	@Override
	public void call(AMyController controller) {
		main.getChildren().clear();
		main.getChildren().add(controller.getRoot()); 
	}
}