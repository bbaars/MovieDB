package edu.cis.CIS350.MovieDB;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.people.PersonCast;
import info.movito.themoviedbapi.model.people.PersonPeople;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ActorController implements Initializable {
	
	/** Base URL String. **/
	private static final String URL =
			"https://image.tmdb.org/t/p/w500";
	
	/** Our menu pane. **/
	@FXML private Pane menuPane;
	
	/** the poster of our actor. **/
	@FXML private ImageView actorImage;
	
	/** Label for the name of the actor. **/
	@FXML private javafx.scene.control.Label actorName;
	
	/** Label for the bio of the actor. **/
	@FXML private javafx.scene.control.Label actorBio;
	
	private int actor;
	
	/** handles the user account information. **/
	private APIManager account;
	
	/** if the user is currently signed in or not. **/
	private boolean isSignedIn;
	
	
	/**
	 * Method is called when the controller is initialized.
	 **/
	@Override
	public void initialize(final URL location,
			final ResourceBundle resources) {
		
		menuPane.setVisible(false);
	}
	
	/**
	 * When the controller is created, it passed in the account information
	 * for us to this controller.
	 * 
	 * @param account API Manager that deals with the users account info.
	 * @param movie Movie that the detail will show
	 * @param isSignedIn whether or not the user can add to favorites/watchlist.
	 **/
	public void setMyData(final int actorID, 
			APIManager account, boolean isSignedIn) {
		this.account = account;
		this.isSignedIn = isSignedIn;
		this.actor = actorID;
		loadGUIComponents();
	}
	
	private void loadGUIComponents() {
		
		String imagePath;
		
		APIManager apiManager = new APIManager();
		TmdbApi tmdbApi = apiManager.getApiObject();
		
		PersonPeople person = 
				tmdbApi.getPeople().getPersonInfo(actor);
		
		actorName.setText(person.getName());
		actorBio.setText(person.getBiography());
		
		imagePath = person.getProfilePath();
		imagePath = URL + imagePath;
		Image image = new Image(imagePath, 650, 350, true, true, false);
		actorImage.setImage(image);
	}
	
	/**
	 * Dimisses the popup menu.
	 **/
	public void menuDismissButtonClicked() {
		System.out.print("Dismiss Menu Clicked");
	
		FadeTransition 
			fTransition = new FadeTransition(Duration.millis(400), menuPane);
		fTransition.setFromValue(1.0);
		fTransition.setToValue(0);
		fTransition.setCycleCount(1);
		fTransition.play();	
		
		fTransition.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(final ActionEvent event) {
				menuPane.setVisible(false);
			}
		});
	}
	
	/**
	 * For menu button.
	 **/
	public void menuButtonClicked() {
		System.out.println("Menu Button Clicked");
		menuPane.setVisible(true);
		
		FadeTransition 
			fTransition = new FadeTransition(Duration.millis(400), menuPane);
		fTransition.setFromValue(0);
		fTransition.setToValue(1.0);
		fTransition.setCycleCount(1);
		fTransition.play();
	}
	
	/**
	 * Log out button.
	 **/
	public void logOutButtonClicked() {
		System.out.println("Log Out Button Clicked");
	}
	
	/**
	 * Takes the user to the home screen.
	 **/
	public void homeButtonClicked() {
		System.out.println("Home Button Clicked");
		try {

			/** 
			 * obtains the current scene by selecting any element and get
			 * their window.
			 */
			javafx.stage.Window source = actorImage.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Home.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

			HomeController movieDetail = loader.getController();
			movieDetail.setMyData(account, isSignedIn);

			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.centerOnScreen();
			stage.show();

			source.hide();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
