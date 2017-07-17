package edu.cis.CIS350.MovieDB;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/***************************************************************************
 * Handles the Log In by the user.
 *
 ***************************************************************************/
public class LoginController {
	
	
	/** Login username text field on the main panel variable. **/
	@FXML private TextField loginUsername;
	
	/** Login password text field on the main panel variable. **/
	@FXML private TextField password;
	
	/**
	 * Controller function for login button.
	 */
	public void loginButtonClicked() {
		System.out.println("Log In Button Clicked");
		
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("Home.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root, 1166, 741);
			primaryStage.setTitle("MovieDB Application");
			
			/* Stupid Line Length */
			primaryStage.getIcons().add(
			new Image(Main.class.
			getResourceAsStream("MovieDBLogo@3x.png")));
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/* Hide our window when they log in FOR NOW */
			javafx.stage.Window source = 
					loginUsername.getScene().getWindow();
			source.hide();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * When the X is clicked, it closes the program.
	 **/
	public void cancelButtonClicked() {
		System.out.println("Cancel Button Clicked");
		Platform.exit();
	}
}

