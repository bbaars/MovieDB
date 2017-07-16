package edu.cis.CIS350.MovieDB;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Controller {
	
	@FXML private ChoiceBox quizChoiceBox;
	@FXML private TextArea quizQuestionBox;
	@FXML private TextField longinUsername;
	Quiz userQuiz = new Quiz();
	int timesClicked = 0;


	public void loginButtonClicked() {
		//longinUsername.setText("Test");
       
	}
	
	public void filterButtonClicked() {
		System.out.println("Getting filter information");
		System.out.println("Filter Data");
		System.out.println("Repopulating table");
	}
	
	public void startButtonClicked() {
		quizQuestionBox.setText(userQuiz.getQuestion(0));
		timesClicked = 0;
		userQuiz.clearAnswers();
	}
	
	public void nextButtonClicked() {
		int choiceSelected;
		if (timesClicked < 5) {
		choiceSelected = 
		    (Integer.parseInt((String) quizChoiceBox.getValue()) - 1);
		userQuiz.setAnswer(choiceSelected);
		timesClicked++;
		if (5 <= timesClicked) {
			quizQuestionBox.setText("Your genre is: " + userQuiz.returnGenre()
			+ "\nTry filtering for movies of your genre."); 
			}
		else {
		quizQuestionBox.setText(userQuiz.getQuestion(timesClicked));
		}
		}

	}
	
	public void quizButtonClicked() {

		initializeQuiz();
	 
	}
	
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
	
	public void tableSelection() {
		System.out.println("Get movie selected");
		System.out.println("Populate image with movie poster");
		//this might help:
		//https://stackoverflow.com/questions/17388866/getting-selected-item-from-a-javafx-tableview
	}
	
	
	/** TO GET IMAGES FROM URL : https://image.tmdb.org/t/p/w500/hmOzkHlkGvi8x24fYpFSnXvjklv.jpg (GETS OBLIVION MOVIE POSTER) **/
	
}
