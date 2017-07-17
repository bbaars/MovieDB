package edu.cis.CIS350.MovieDB;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

/**
 * GUI creation function.
 */
public class Main extends Application {
	@Override
	public void start(final Stage primaryStage) {
		
		/** Tries to load our first FXML File. **/
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("LogIn.fxml"));
			Scene scene = new Scene(root, 1020, 550);
			
			primaryStage.getIcons().add(
			new Image(Main.class.getResourceAsStream(
					"MovieDBLogo@3x.png")));
			
			primaryStage.setTitle(" Log In MovieDB Application");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * GUI Class.
	 * @param args The arguments for the main program.
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
