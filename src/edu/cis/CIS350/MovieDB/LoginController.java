package edu.cis.CIS350.MovieDB;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

			APIManager account = new APIManager(
					loginUsername.getText(),
					password.getText());

			if (account.logIn() == 1) {
//				Parent root = FXMLLoader.load(getClass()
//						.getResource("Home.fxml"));
//				Stage primaryStage = new Stage();
//				Scene scene = new Scene(root, 1166, 741);
//				primaryStage.setTitle("MovieDB Application");
//
//				/* Stupid Line Length */
//				primaryStage.getIcons().add(
//						new Image(Main.class.
//				getResourceAsStream("MovieDBLogo@3x.png")));
//
//				primaryStage.setScene(scene);
//				primaryStage.show();
//
//				/* Hide our window when they log in FOR NOW */
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
				home.setMyData(account);
				
				Parent root = loader.getRoot();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.centerOnScreen();
				stage.show();
				
				source.hide();
			} else {
				
				// TO DO : POP UP THAT SAYS INVALID LOGIN
				System.out.println("Invalid Login");
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
}

