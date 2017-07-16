package edu.cis.CIS350.MovieDB;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

/**
 * Controller class to control GUI.
 */
public class Controller {
	
	final String appendURL = "https://image.tmdb.org/t/p/w500";
	final int min =1;
	
	final int max = 20000;
	
	/** Choicebox on the quiz panel variable. **/
	@FXML private ImageView moviePoster;
	
	/** Choicebox on the quiz panel variable. **/
	@FXML private ChoiceBox quizChoiceBox;
	
	/** TextArea on the quiz panel variable. **/
	@FXML private TextArea quizQuestionBox;
	
	/** Login username text field on the main panel variable. **/
	@FXML private TextField loginUsername;
	
	/** Login password text field on the main panel variable. **/
	@FXML private TextField password;
	
	/** List field for movies on the main panel variable. **/
	@FXML private TextField randomMovieField;
	
	
	/** New Quiz for user. **/
	private Quiz userQuiz = new Quiz();
	
	/** Keeps track of what quiz question the user is on. **/
	private int timesClicked = 0;
	
	

	/**
	 * Controller function for login button.
	 */
	public void loginButtonClicked() {
		loginUsername.setText("Test");
	}
	
	/**
	 * Controller function for random movie button.
	 * 
	 */
	public void randomMovieButtonClicked(){
		boolean keepGoing = true;
		String imagePath = new String();
		
		//while(keepGoing) {
		
			//try {
		int movieId = ThreadLocalRandom.current().nextInt(min, max + 1);
		Movie randomMovie = new Movie(500);
		
		randomMovieField.setText(randomMovie.getTitle());
		
		imagePath = randomMovie.getPosterPath();
		
		keepGoing = false;
		imagePath = appendURL + imagePath;
		System.out.println(imagePath);
		
		Image image = new Image(imagePath);
		
		moviePoster.setImage(image);
		
			}
			//catch(Exception e){
				//keepGoing = true;
			//}
			
		//}
		
	//}
	
	/**
	 * Controller function for filter button.
	 */
	public void filterButtonClicked() {
		System.out.println("Getting filter information");
		System.out.println("Filter Data");
		System.out.println("Repopulating table");
	}
	
	/**
	 * Controller function for quiz start button.
	 */
	public void startButtonClicked() {
		quizQuestionBox.setText(userQuiz.getQuestion(0));
		timesClicked = 0;
		userQuiz.clearAnswers();
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
	 * Controller function for take quiz button on main window.
	 */
	public void quizButtonClicked() {
		initializeQuiz();
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
	 * Controller function for user interacting with table.
	 */
	public void tableSelection() {
		System.out.println("Get movie selected");
		System.out.println("Populate image with movie poster");
		//this might help:
		//https://stackoverflow.com/questions/
		//17388866/getting-selected-item-from-a-javafx-tableview
	}
	
	
	/** TO GET IMAGES FROM URL : https://image.tmdb.org/t/p/w500/
	 * hmOzkHlkGvi8x24fYpFSnXvjklv.jpg (GETS OBLIVION MOVIE POSTER) **/
	

}
