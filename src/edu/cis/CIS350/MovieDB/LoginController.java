package edu.cis.CIS350.MovieDB;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;


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
	}
	
	public void cancelButtonClicked() {
		System.out.println("Cancel Button Clicked");
	}
}

