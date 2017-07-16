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
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("application.fxml"));
			Scene scene = new Scene(root, 900, 700);
		//	scene.getStylesheets().add(getClass()
		//	.getResource("application.css").toExternalForm());
			primaryStage.setTitle("MovieDB Application");
			primaryStage.getIcons().add(
			new Image(Main.class.getResourceAsStream("logo.png")));
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
