package edu.cis.CIS350.MovieDB;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import info.movito.themoviedbapi.model.MovieDb;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


/***************************************************************************
 * Handles the Main Screen for the user.
 *
 ***************************************************************************/
public class HomeController implements Initializable {
	
	/** Base URL String. **/
	private static final String URL =
			"https://image.tmdb.org/t/p/w500";
	
	/** Field for searching. **/
	@FXML private TextField search1;
	
	/** Field for searching. **/
	@FXML private TextField search2;
	
	/** Field for searching. **/
	@FXML private TextField search3;
	
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
	
	/** Label for the vote score of the movie. **/
	@FXML private javafx.scene.control.Label nameLabel;
	
	/** Label for the vote score of the movie. **/
	@FXML private javafx.scene.control.Label nameCharacterLabel;
	
	/** Label for the overview of the movie. **/
	@FXML private javafx.scene.control.Label overviewLabel;
	
	/** Our menu pane. **/
	@FXML private Pane menuPane;
	
	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView plus;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView favorite;
	
	/** The plus for watch list or favorites. **/
	@FXML private ImageView eye;
	
	/** the circle that allows the user to click on it. **/
	@FXML private Circle circle;
	
	/** how many times the user clicks on the circle. **/
	private int clickedOn = 0;
	
	/** Index of current Popular Movie. */
	private int currentPopularMovie = 0;
	
	/** Current Movie shown. **/
	private Movie currentMovie;
	
	/** Lower bounds for random id. **/
	private static final int MIN = 1;
	
	/** Higher bound for random id. **/
	private static final int MAX = 20000;
	
	/** handles the user account information. **/
	private APIManager account;
	
	/** stores our current movie ID. **/
	private int currentID;
	
	/** if the user is currently signed in or not. **/
	private boolean isSignedIn = false;
	
	/**
	 * Method is called when the controller is initialized.
	 **/
	@Override
	public void initialize(final URL location,
			final ResourceBundle resources) {
		
		loadMovieInfo(currentPopularMovie);
		menuPane.setVisible(false);
		eye.setVisible(false);
		favorite.setVisible(false);
	}

	
	/**
	 * When the controller is created, it passed in the account information
	 * for us to this controller.
	 * 
	 * @param account API Manager that deals with the users account info.
	 * @param signedIn whether a user has successfully signed in.
	 **/
	public void setMyData(final APIManager account, final boolean signedIn) {
		this.account = account;
		isSignedIn = signedIn;
	}
	
	
	/**
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked() {
		System.out.println("Movie clicked");
		
		try {
			
			/** 
			 * obtains the current scene by selecting any element and gett
			 * their window.
			 */
			javafx.stage.Window source = circle.getScene().getWindow();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));
			
			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
			
			MovieDetailController movieDetail = loader.getController();
			movieDetail.setMyData(account, currentMovie, isSignedIn);
			
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
	 * When the previous is clicked, shows the previous popular movie.
	 **/
	public void previousMovieClicked() {
		
		if (currentPopularMovie - 1 >= 0) {
			loadMovieInfo(--currentPopularMovie);
		}
	}
	
	/**
	 * Shows the next popular movie.
	 **/
	public void nextMovieClicked() {
		
		if (currentPopularMovie + 1 < 20) {
			loadMovieInfo(++currentPopularMovie);
		}
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
	 * Search button.
	 **/
	public void searchButtonClicked() {
		String searchFieldOne;
		String searchFieldTwo;
		String searchFieldThree;
		
		searchFieldOne = search1.getText();
		searchFieldTwo = search2.getText();
		searchFieldThree = search3.getText();
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
	 * Top Rated Actor.
	 **/
	public void topRatedActorButtonClicked() {
		System.out.println("Top Rated Actors Clicked");
	}
	
	
	/**
	 * Tweet button.
	 **/
	public void twitterButtonClicked() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Tweet.fxml"));
		
		try {
			loader.load();
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		
		TweetController tweet = loader.getController();
		//movieDetail.setMyData(account, currentMovie, isSignedIn);
		
		Parent root = loader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.centerOnScreen();
		stage.show();
	}
	
	/**
	 * Cancel Button.
	 **/
	public void cancelButtonClicked() {
		System.out.println("Cancel Button Clicked");
		Platform.exit();
	}
	
	/**
	 * Log out button.
	 **/
	public void logOutButtonClicked() {
		System.out.println("Log Out Button Clicked");
		
		if (!isSignedIn) {
			javafx.stage.Window source = 
					circle.getScene().getWindow();
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource(
						"LogIn.fxml"));
				
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
	
	/**
	 * Quiz Button.
	 **/
	public void quizButtonClicked() {
		System.out.println("Quiz Button Clicked");
		initializeQuiz();
	}
	
	/**
	 * Favorites button.
	 **/
	public void myFavoritedClicked() {
		System.out.println("My Favorites Button Clicked");
	}
	
	/**
	 * WatchList button.
	 **/
	public void watchlistClicked() {
		System.out.println("WatchList Button Clicked");
		
	}
	
	/**
	 * Recommendations.
	 **/
	public void recommendationsClicked() {
		System.out.println("RecommendationsButton Clicked");
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
	 * Adds the current movie to the watch list of the account.
	 **/
	public void addToWatchListButtonClicked() {
		System.out.println("Added " + currentID + " to watchlist");
		account.addMovieToWatchList(currentID);
		
	}
	
	/**
	 * Adds the current movie to the favorites of the account.
	 **/
	public void addToFavoritesButtonClickd() {
		System.out.println("Added " + currentID + " to favorites");
		account.addMovieFavorite(currentID);
	}
	
	/**
	 * Loads the movie info to display to the user.
	 * 
	 * @param index the index of the movie in the array of popular movies.
	 **/
	private void loadMovieInfo(final int index) {
		
		Movie movie = new Movie();
		int runtime;
		String toRuntime;
		String imagePath;
		
		ArrayList<MovieDb> popularMovies = movie.getPopularMovies(1);
		
		titleLabel.setText(popularMovies.get(index).getTitle());
		overviewLabel.setText(popularMovies.get(index).getOverview());
	
		imagePath = popularMovies.get(index).getPosterPath();
		imagePath = URL + imagePath;
		
		Image image = new Image(imagePath, 650, 350, true, true, false);
		moviePoster.setImage(image);
		
		Movie movie2 = new Movie(popularMovies.get(index).getId());
		
		currentID = movie2.getID();
		currentMovie = movie2;
		
		runtime = movie2.getRuntime();
		System.out.println(runtime);
		toRuntime = runtime / 60 + " hr " + runtime % 60 + " min";
		runTimeLabel.setText(toRuntime);
		
		voteLabel.setText("" + movie2.getVoteAverage() + "/10");
		
		ArrayList<String> genres = 
				movie2.getGenres();
		
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

	
	/**
	 * Function to create and show quiz window.
	 */
	public void initializeQuiz() {
		try {
			Parent root = FXMLLoader.load(getClass()
					.getResource("Quiz.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root, 500, 300);
			primaryStage.setTitle("MovieDB Quiz");
			primaryStage.getIcons().add(
			new Image(Main.class.getResourceAsStream("MovieDBLogo@3x.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Controller function for random movie button.
	 * 
	 */
	public void randomMovieButtonClicked() {
		boolean keepGoing = true;
		String imagePath;
		String toRuntime;
		int runtime;
		
		while (keepGoing) {
			try {
		int movieId = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
		Movie randomMovie = new Movie(movieId);
		
		currentID = movieId;
		currentMovie = randomMovie;
		
		titleLabel.setText(randomMovie.getTitle());
		
		imagePath = randomMovie.getPosterPath();
		
		keepGoing = false;
		imagePath = URL + imagePath;
		
		overviewLabel.setText(randomMovie.getOverview());
		
		runtime = randomMovie.getRuntime();
		System.out.println(runtime);
		toRuntime = runtime / 60 + " hr " + runtime % 60 + " min";
		runTimeLabel.setText(toRuntime);
		
		voteLabel.setText("" + randomMovie.getVoteAverage() + "/10");
		
		ArrayList<String> genres = 
				randomMovie.getGenres();
		
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
		
		Image image = new Image(imagePath, 450, 350, true, true, false);
		moviePoster.setImage(image);
		
			} catch (RuntimeException e) {
				keepGoing = true;
			} catch (Exception InvocationTargetException) {
				keepGoing = true;
			}
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
			+ currentMovie.getVideos().get(0).getKey() + "?autoplay=1");
			
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
}
