package application.start;


import application.controller.BaseController;
import application.controller.home.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("application/view/Base.fxml"));
			BaseController controller = new BaseController();
            loader.setController(controller);
            Parent root = loader.load();
            
			primaryStage.setTitle("GROUP 19");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			
			controller.call(new HomeController()) ;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
       launch(args);
     }

}
