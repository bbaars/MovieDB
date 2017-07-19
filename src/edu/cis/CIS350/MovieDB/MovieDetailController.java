package edu.cis.CIS350.MovieDB;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.event.EventHandlerManager;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/** Detail of the movie the user wishes to view. **/
public class MovieDetailController implements Initializable {
	
	/** Base URL String. **/
	private static final String URL =
			"https://image.tmdb.org/t/p/w500";
	
	/** Base URL String. **/
	private static final String BACKDROP_URL =
			"https://image.tmdb.org/t/p/original";
	
	/** Our menu pane. **/
	@FXML private Pane menuPane;
	
	/** Our top cast pane. **/
	@FXML private Pane castPane;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView plus;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView favorite;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView eye;
	
	/** The header for our movie. **/
	@FXML private ImageView backdropImage;
	
	/** the poster of our movie. **/
	@FXML private ImageView posterImage;
	
	/** the circle that allows the user to click on it. **/
	@FXML private Circle circle;
	
	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label runTimeLabel;
	
	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel;
	
	/** Label for the genre of the movie. **/
	@FXML private javafx.scene.control.Label genre1Label;
	
	/** Label for the genre of the movie. **/
	@FXML private javafx.scene.control.Label genre2Label;
	
	/** Label for the genre of the movie. **/
	@FXML private javafx.scene.control.Label genre3Label;
	
	/** Label for the vote score of the movie. **/
	@FXML private javafx.scene.control.Label voteLabel;
	
	/** Label for the overview of the movie. **/
	@FXML private javafx.scene.control.Label overviewLabel;
	
	/** Label for the overview of the movie. **/
	@FXML private javafx.scene.control.Label titleOverviewLabel;
	
	/** handles the user account information. **/
	private APIManager account;
	
	/** if the user is currently signed in or not. **/
	private boolean isSignedIn;
	
	/** the movie that the page is going to present. **/
	private Movie movie;
	
	/** click events for our plus circle. **/
	private int clickedOn = 0;
	
	/** keeps track of our current tabbed index. **/
	private int tabbedIndex = 0;
	
	/**
	 * Method is called when the controller is initialized.
	 **/
	@Override
	public void initialize(final URL location,
			final ResourceBundle resources) {
		
		menuPane.setVisible(false);
		castPane.setVisible(false);
		eye.setVisible(false);
		favorite.setVisible(false);
		
	}
	
	/**
	 * When the controller is created, it passed in the account information
	 * for us to this controller.
	 * 
	 * @param account API Manager that deals with the users account info.
	 * @param movie Movie that the detail will show
	 * @param isSignedIn whether or not the user can add to favorites/watchlist.
	 **/
	public void setMyData(final APIManager account, final Movie movie, final
			Boolean isSignedIn) {
		this.account = account;
		this.movie = movie;
		this.isSignedIn = isSignedIn;
		loadGUIComponents();
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
	 * Adds to watchlist.
	 **/
	public void plusButtonClicked() {
		
		if (isSignedIn) {
			
			clickedOn++;
			ScaleTransition st =
					new ScaleTransition(Duration.millis(300), circle);
			
			RotateTransition rotate =
					new RotateTransition(Duration.millis(300), plus);
			
			if (clickedOn % 2 != 0) {
				
				st.setByX(1.5f);
				st.setByY(1.5f);
				st.setCycleCount(1);
				st.play();
			
				eye.setVisible(true);
				favorite.setVisible(true);
				
				rotate.setFromAngle(0);
				rotate.setToAngle(45);
				rotate.play();
				
			} else {
				st.setByX(-1.5f);
				st.setByY(-1.5f);
				st.setCycleCount(1);
				st.play();
				
				rotate.setFromAngle(45);
				rotate.setToAngle(0);
				rotate.play();
				
				eye.setVisible(false);
				favorite.setVisible(false);
			}
		} else {
			System.out.println("Must log in for this");
			// TO DO POP UP THAT TELLS THE USER THEY NEED 
			//TO LOG IN FOR THAT FEATURE
		}
	}
	
	/** 
	 * Handles the event of when the trailer is clicked.
	 */
	public void trailerClicked() {
		System.out.println("Trailer Clicked");
		
		try {
			WebView webView = new WebView();
			WebEngine webEngine = webView.getEngine();
			webEngine.load("https://www.youtube.com/embed/"
			+ movie.getVideos().get(0).getKey() + "?autoplay=1");
			
			Scene scene = new Scene(webView, 640, 390);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				 @Override
		            public void handle(final WindowEvent event) {
		              webView.getEngine().load(null);
		            }
				
			});
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Whoops");
			alert.setHeaderText(null);
			alert.setContentText("Could not load trailer.");
			alert.showAndWait();
		}
	}
	
	/**
	 * Adds the current movie to the watch list of the account.
	 **/
	public void addToWatchListButtonClicked() {
		System.out.println("Added to watchlist");
		account.addMovieToWatchList(movie.getID());
		
	}
	
	/**
	 * Adds the current movie to the favorites of the account.
	 **/
	public void addToFavoritesButtonClickd() {
		System.out.println("Added to favorites");
		account.addMovieFavorite(movie.getID());
	}
	
	/**
	 * Top Rated Actor.
	 **/
	public void topRatedActorButtonClicked() {
		System.out.println("Top Rated Actors Clicked");
	}
	
	/**
	 * Popular movie.
	 **/
	public void popularMovieButtonClicked() {
		System.out.println("Popular Movie Button Clicked");
	}
	
	/**
	 * Top Rated Movie.
	 **/
	public void topRatedMovieButtonClicked() {
		System.out.println("Top Rated Movie Clicked");
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
	try {
			
			/** 
			 * obtains the current scene by selecting any element and get
			 * their window.
			 */
			javafx.stage.Window source = circle.getScene().getWindow();
			
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
	
	/**
	 * Loads the Movie Details and applies them to the screen.
	 **/
	private void loadGUIComponents() {
		
		int runtime;
		String toRuntime;
		String imagePath;
		String backdropPath;
				
		titleLabel.setText(movie.getTitle());
		overviewLabel.setText(movie.getOverview());
		titleOverviewLabel.setText("Overview");
	
		imagePath = movie.getPosterPath();
		imagePath = URL + imagePath;
		
		backdropPath = movie.getBackdropPath();
		backdropPath = BACKDROP_URL + backdropPath;
		
		Image image = new Image(imagePath, 650, 350, true, true, false);
		posterImage.setImage(image);
		
		Image backdrop = new Image(backdropPath, 1900, 1280, true, true, false);
		backdropImage.setImage(backdrop);
				
		runtime = movie.getRuntime();
		System.out.println(runtime);
		toRuntime = runtime / 60 + " hr " + runtime % 60 + " min";
		runTimeLabel.setText(toRuntime);
		
		voteLabel.setText("" + movie.getVoteAverage() + "/10");
		
		ArrayList<String> genres = 
				movie.getGenres();
				
		if (genres == null) {
			genre1Label.setText("");
			genre2Label.setText("");
			genre3Label.setText("");
		} else {
			
			int count = genres.size();
			
			if (count >= 3) {
				genre1Label.setText(genres.get(0));
				genre2Label.setText(genres.get(1));
				genre3Label.setText(genres.get(2));
			} else if (count >= 2) {
				genre1Label.setText(genres.get(0));
				genre2Label.setText(genres.get(1));
			} else if (count >= 1) {
				genre1Label.setText(genres.get(0));
			}
		}
	}
	
	
	public void infoTabPressed() {
		
		
	}
	
	public void castTabPressed() {
		
		
	}
	
	public void reviewTabPressed() {
		
		
	}
	
	
}
