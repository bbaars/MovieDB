package edu.cis.CIS350.MovieDB;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import info.movito.themoviedbapi.model.MovieDb;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


/***************************************************************************
 * Handles the Log In by the user.
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
	
	/** Label for the overview of the movie. **/
	@FXML private javafx.scene.control.Label overviewLabel;
	
	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster;
	
	/** Choicebox on the quiz panel variable. **/
	@FXML private ChoiceBox<String> quizChoiceBox;
	
	/** TextArea on the quiz panel variable. **/
	@FXML private TextArea quizQuestionBox;
	
	/** New Quiz for user. **/
	private Quiz userQuiz = new Quiz();
	
	/** Keeps track of what quiz question the user is on. **/
	private int timesClicked = 0;
	
	/** Index of current Popular Movie. */
	private int currentPopularMovie = 0;
	
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
	}
	
	/**
	 * When the controller is created, it passed in the account information
	 * for us to this controller.
	 * 
	 * @param account API Manager that deals with the users account info.
	 **/
	public void setMyData(final APIManager account) {
		this.account = account;
		System.out.println(account);
		isSignedIn = true;
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
	}
	
	/**
	 * Search button.
	 **/
	public void searchButtonClicked() {
		System.out.println("Search Button Clicked");
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
	public void addToWatchListButtonClicked() {
		System.out.println("Add to WatchList Button Clicked");
		System.out.println(isSignedIn);
		
		if (isSignedIn) {
			account.addMovieToWatchList(currentID);
			System.out.println(
					"Added " + currentID + " to watchlist");
		} else {
			System.out.println("Must log in for this");
			// TO DO POP UP THAT TELLS THE USER THEY NEED 
			//TO LOG IN FOR THAT FEATURE
		}
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
			Scene scene = new Scene(root, 600, 300);
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
	 * Controller function for quiz next button.
	 */
	public void nextButtonClicked() {
		int choiceSelected;
		if (timesClicked < 5) {
		choiceSelected = 
		    (Integer.parseInt((String) quizChoiceBox.getValue()) - 1);
		userQuiz.setAnswer(choiceSelected);
		timesClicked++;
		if (5 <= timesClicked) {
			quizQuestionBox.setText("Your genre is: " 
		    + userQuiz.returnGenre()
			+ "\nTry filtering for movies of your genre."); 
			} else {
		quizQuestionBox.setText(userQuiz.getQuestion(timesClicked));
		}
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
}
