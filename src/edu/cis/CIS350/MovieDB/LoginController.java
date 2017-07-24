package edu.cis.CIS350.MovieDB;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/***************************************************************************
 * Handles the Log In by the user.
 *
 ***************************************************************************/
public class LoginController {


	/** Login user name text field on the main panel variable. **/
	@FXML private TextField loginUsername;

	/** Login password text field on the main panel variable. **/
	@FXML private TextField password;

	/**
	 * Controller function for login button.
	 */
	public void loginButtonClicked() {
		System.out.println("Log In Button Clicked");
		
		try {

			APIManager account = new APIManager(
					loginUsername.getText(),
					password.getText());

			if (account.logIn() == 1) {

				javafx.stage.Window source = 
					loginUsername.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(
						"Home.fxml"));
				
				try {
					loader.load();
				} catch (IOException ex) {
					System.out.println(ex.toString());
				}
					
				HomeController home = loader.getController();
				home.setMyData(account, true);
				
				
				Parent root = loader.getRoot();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.centerOnScreen();
				stage.show();
				
				source.hide();

			} else {
				
				// TO DO : POP UP THAT SAYS INVALID LOGIN
				System.out.println("Invalid Login");
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Whoops");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username or password");
				alert.showAndWait();
			}

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
	
	/**
	 * If the user wants to skip the login, just show the next scene.
	 */
	public void skipButtonPressed() {
		
		javafx.stage.Window source = 
				loginUsername.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(
					"Home.fxml"));
			
			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
							
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.centerOnScreen();
			stage.show();
			
			source.hide();
	}
}

