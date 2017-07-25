package edu.cis.CIS350.MovieDB;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/** Class to control the quiz window. **/
public class QuizController {

	/** Choice box on the quiz panel variable. **/
	@FXML private JFXComboBox<String> quizChoiceBox;
	
	/** TextArea on the quiz panel variable. **/
	@FXML private TextArea quizQuestionBox;
	
	/** New Quiz for user. **/
	private Quiz userQuiz = new Quiz();
	
	/** Keeps track of what quiz question the user is on. **/
	private int timesClicked = 0;
	
	
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
		if (5 == timesClicked) {
			quizQuestionBox.setText("Your genre is: " 
		    + userQuiz.returnGenre()
			+ "\nTry filtering for movies of your genre."); 
			} else {
			quizQuestionBox.setText(
					userQuiz.getQuestion(timesClicked));
			}
		}
	}
	
	
}
