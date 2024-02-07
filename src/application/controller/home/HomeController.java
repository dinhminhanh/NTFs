package application.controller.home;


import java.io.IOException;

import application.controller.AMyController;
import application.controller.ICallUrl;
import application.controller.analysis.SeeCollectionController;
import application.controller.hashtag.HotHashtagController;
import application.controller.post.SeePostController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeController extends AMyController implements ICallUrl {
	
	private static final String URL           = "application/view/HomePage.fxml" ;
	private static final String HEADER_STRING = "Trang chủ" ;
	
	@FXML
	private VBox root;
	@FXML
	private Label header;
	
	public HomeController() {
		loadView();
	}
	
	@Override
	public VBox getRoot() {
		return root ;
	}
	
	@FXML
	void showButtonPressed(ActionEvent event) {
		AnchorPane parent = (AnchorPane) ((Button) event.getSource()).getParent();
		for (Node child : parent.getChildren()) {
			if (child instanceof Text) {
				String title = ((Text) child).getText(); 
				call(title);
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
	}
	
	@Override
	public void call(AMyController controller) {
		root.getChildren().clear();
		root.getChildren().add(controller.getRoot()); 
	}
	
	private void call(String name) {
		switch (name) {
		case "Danh sách bài viết"	: call(new SeePostController())	      ; break ;
		case "Tìm hashtag hot nhất"	: call(new HotHashtagController())	  ; break ;
		case "Phân tích tương quan"	: call(new SeeCollectionController()) ; break ;
		default:
			throw new IllegalArgumentException("Unexpected value: " + name);
		}	
	}
	
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
	}
}
