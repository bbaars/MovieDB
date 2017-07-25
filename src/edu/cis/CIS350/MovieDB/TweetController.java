package edu.cis.CIS350.MovieDB;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/** Class to control the twitter window. **/
public class TweetController {
	
	/** Tweet field on the tweet panel variable. **/
	@FXML private TextArea tweetContent;
	
	/** Name field on the tweet panel variable. **/
	@FXML private TextField nameField;
	
	/** Location field on the tweet panel variable. **/
	@FXML private TextField locationField;
	
	/** Location field on the tweet panel variable. **/
	@FXML private Button notNowButton;
	
	/** Makes sure person can only tweet once. **/
	private boolean hasTweeted = false;
	
	
	/**
	 * When the not now button is clicked, close window.
	 **/
	public void notNowButtonClicked() {
		Stage stage = (Stage) notNowButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	
	/**
	 * When the tweet button is clicked, tweet message.
	 * @throws TwitterException 
	 * @throws InterruptedException 
	 **/
	public void tweetButtonClicked() 
			throws TwitterException, InterruptedException {
		if (!hasTweeted) {
	   String tweet;
	   tweet = nameField.getText() + " from " + locationField.getText()
	   	+ " says " + tweetContent.getText();
	   
	   if (tweet.length() > 140) {
		   tweetContent.setText("Tweet longer than 140 characters");
	   } else {
		   ConfigurationBuilder cb = new ConfigurationBuilder();
		   cb.setDebugEnabled(true)
		     .setOAuthConsumerKey("FrWYlp6omUvU4IevJvORyei5e")
		     .setOAuthConsumerSecret("9Vvp4qoEltT1xx0i05"
		     		+ "NyCYj4aR9akq0nAzhaoItLWmMlg9lQvD")
		     .setOAuthAccessToken("889558386148339712-oA"
		     		+ "6LLvtOR4TuYmGsq26Y2gM3R8iRPA9")
		     .setOAuthAccessTokenSecret("SExdfQcX4YRw0jTsOI7X"
		     		+ "jbPc8coI9hgzUvWAhZGRf3TiC");
		   TwitterFactory tf = new TwitterFactory(cb.build());
		   Twitter twitter = tf.getInstance();
			twitter.updateStatus(tweet);
			
			 tweetContent.setText("Thanks for the review!");
			
			 hasTweeted = true;
	   }
	   } else {
		   tweetContent.setText("You already let a review!");
		}
	   }
	
}

