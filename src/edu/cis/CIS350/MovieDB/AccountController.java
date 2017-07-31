package edu.cis.CIS350.MovieDB;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import info.movito.themoviedbapi.model.MovieDb;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/***************************************************************************
 * Handles the account screen for user.
 *
 ***************************************************************************/
public class AccountController implements Initializable {

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster1;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster2;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster3;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster4;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster5;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster6;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster7;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster8;

	/** Label for the genre of the movie. **/
	@FXML private ImageView moviePoster9;
	
	/** Go back pane **/
	@FXML private Pane goBack;

	/** handles the user account information. **/
	private APIManager account;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel1;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel2;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel3;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel4;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel5;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel6;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel7;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel8;

	/** Label for the runtime of the movie. **/
	@FXML private javafx.scene.control.Label titleLabel9;

	/** Base URL String. **/
	private static final String URL =
			"https://image.tmdb.org/t/p/w500";

	/** Page the user is on. **/
	private int page;

	/** Watchlist Page the user is on. **/
	private int page2;

	/** Page the user is on. **/
	private ArrayList<MovieDb> favoriteMovies;

	/** Page the user is on. **/
	private ArrayList<MovieDb> watchList;

	/** favorite flag. **/
	private boolean isFull;

	/** Watchlist flag . **/
	private boolean isFull2;

	/** index of favorite list . **/
	private int index;

	/** index of watchlist list . **/
	private int index2;
	
	/** index of movie in element 0 in favorites. **/
	private int index_0;
	
	/** index of movie in element 1 in favorites. **/
	private int index_1;
	
	/** index of movie in element 2 in favorites. **/
	private int index_2;
	
	/** index of movie in element 3 in favorites. **/
	private int index_3;
	
	/** index of movie in element 4 in favorites. **/
	private int index_4;
	
	/** index of movie in element 5 in watchlist. **/
	private int index_5;
	
	/** index of movie in element 6 in watchlist. **/
	private int index_6;
	
	/** index of movie in element 7 in watchlist. **/
	private int index_7;
	
	/** index of movie in element 8 in watchlist. **/
	private int index_8;
	
	/** index of movie in element 9 in watchlist. **/
	private int index_9;


	/**
	 * Method is called when the controller is initialized.
	 **/
	@Override
	public void initialize(final URL location,
			final ResourceBundle resources) {

	}

	/**
	 * When the controller is created, it passed in the account information
	 * for us to this controller.
	 *
	 * @param account API Manager that deals with the users account info.
	 **/
	public void setMyData(final APIManager account) {
		this.account = account;
		page = 0;
		index = 0;
		index2 = 0;
		index_0 = 0;
		index_1 = 0;
		index_2 = 0;
		index_3 = 0;
		index_4 = 0;
		index_5 = 0;
		index_6 = 0;
		index_7 = 0;
		index_8 = 0;
		index_9 = 0;
		favoriteMovies = this.account.getFavoriteMovies();
		watchList = this.account.getMovieWatchList();
		isFull = false;
	}

	/**
	 * Displays the users favorites list
	 *
	 **/
	public void displayMovie() {
		int favSize;
		String imagePath;
		index = 0;

		favSize = favoriteMovies.size();

		if (favSize > 5 && page == 0) {

			if (favSize > 0) {

				if (index >= favSize) {
					isFull = true;
				}
				titleLabel.setVisible(true);
				titleLabel.setText(favoriteMovies.get(index).getTitle());
				imagePath = favoriteMovies.get(index).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster.setVisible(true);
				moviePoster.setImage(image);
				index_0 = index;
				index++;

				}
			else {
				moviePoster.setVisible(false);
				titleLabel.setVisible(false);
			}

			if (!isFull && index < favSize) {

				if (index >= favSize) {
					isFull = true;
				}
				titleLabel1.setVisible(true);
				titleLabel1.setText(favoriteMovies.get(index).getTitle());
				imagePath = favoriteMovies.get(index).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster1.setVisible(true);
				moviePoster1.setImage(image);
				index_1 = index;
				index++;

				}
			else {
				moviePoster1.setVisible(false);
				titleLabel1.setVisible(false);
			}

			if (!isFull && index < favSize) {

				if (index >= favSize) {
					isFull = true;
				}
				titleLabel2.setVisible(true);
				titleLabel2.setText(favoriteMovies.get(index).getTitle());
				imagePath = favoriteMovies.get(index).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster2.setVisible(true);
				moviePoster2.setImage(image);
				index_2 = index;
				index++;

				}
			else {
				moviePoster2.setVisible(false);
				titleLabel2.setVisible(false);
			}

			if (!isFull && index < favSize) {

				if (index >= favSize) {
					isFull = true;
				}
				titleLabel3.setVisible(true);
				titleLabel3.setText(favoriteMovies.get(index).getTitle());
				imagePath = favoriteMovies.get(index).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster3.setVisible(true);
				moviePoster3.setImage(image);
				index_3 = index;
				index++;

				}
			else {
				moviePoster3.setVisible(false);
				titleLabel3.setVisible(false);
			}

			if (!isFull && index < favSize) {

				if (index >= favSize) {
					isFull = true;
				}
				titleLabel4.setVisible(true);
				titleLabel4.setText(favoriteMovies.get(index).getTitle());
				imagePath = favoriteMovies.get(index).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster4.setVisible(true);
				moviePoster4.setImage(image);
				index_4 = index;
				index++;

				}
			else {
				moviePoster4.setVisible(false);
				titleLabel4.setVisible(false);
			}

			} else if (favSize >= 5 && page > 0) {

			if (!isFull && (index + (page * 5)) < favSize) {

				if ((index + (page * 5)) >= favSize) {
					isFull = true;
				}
				else {

				titleLabel.setVisible(true);
				titleLabel.setText(favoriteMovies.get(index
						+ (page * 5)).getTitle());
				imagePath = favoriteMovies.get(index + (page * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster.setVisible(true);
				moviePoster.setImage(image);
				index_0 = index;
				index++;
				}
				}

			else {
				moviePoster.setVisible(false);
				titleLabel.setVisible(false);
			}

			if (!isFull && (index + (page * 5)) < favSize) {

				if ((index + (page * 5)) >= favSize) {
					isFull = true;
				} else {

				titleLabel1.setVisible(true);
				titleLabel1.setText(favoriteMovies.get(index
						+ (page * 5)).getTitle());
				imagePath = favoriteMovies.get(index + (page * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster1.setVisible(true);
				moviePoster1.setImage(image);
				index_1 = index;
				index++;
				}
				}
			else {
				moviePoster1.setVisible(false);
				titleLabel1.setVisible(false);
			}

			if (!isFull && (index + (page * 5)) < favSize) {

				if ((index + (page * 5)) >= favSize) {
					isFull = true;
				} else {
				titleLabel2.setVisible(true);
				titleLabel2.setText(favoriteMovies.get(index
						+ (page * 5)).getTitle());
				imagePath = favoriteMovies.get(index + (page * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster2.setVisible(true);
				moviePoster2.setImage(image);
				index_2 = index;
				index++;
				}
				}
			else {
				moviePoster2.setVisible(false);
				titleLabel2.setVisible(false);
			}

			if (!isFull && (index + (page * 5)) < favSize) {

				if ((index + (page * 5)) >= favSize) {
					isFull = true;
				}
				else {
				titleLabel3.setVisible(true);
				titleLabel3.setText(favoriteMovies.get(index
						+ (page * 5)).getTitle());
				imagePath = favoriteMovies.get(index + (page * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster3.setVisible(true);
				moviePoster3.setImage(image);
				index_3 = index;
				index++;
				}
				}
			else {
				moviePoster3.setVisible(false);
				titleLabel3.setVisible(false);
			}

			if (!isFull && (index + (page * 5)) < favSize) {

				if ((index + (page * 5)) >= favSize) {
					isFull = true;
				} else {
				titleLabel4.setVisible(true);
				titleLabel4.setText(favoriteMovies.get(index
						+ (page * 5)).getTitle());
				imagePath = favoriteMovies.get(index + (page * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster4.setVisible(true);
				moviePoster4.setImage(image);
				index_4 = index;
				index++;
				}
				}
			else {
				moviePoster4.setVisible(false);
				titleLabel4.setVisible(false);
			}

			}
	}

	/******************************************************************
	* displays the user's watchlist
	*
	*****************************************************************/
	public void displayWatchList() {
		int watchSize;
		index2 = 0;
		String imagePath;

		watchSize = watchList.size();

		if (watchSize > 5 && page2 == 0) {

			if (watchSize > 0) {

				titleLabel5.setVisible(true);
				titleLabel5.setText(watchList.get(index2).getTitle());
				imagePath = watchList.get(index2).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster5.setVisible(true);
				moviePoster5.setImage(image);
				index_5 = index2;
				index2++;

				}
			else {
				moviePoster5.setVisible(false);
				titleLabel5.setVisible(false);
			}

			if (!isFull2 && index2 < watchSize) {

				titleLabel6.setVisible(true);
				titleLabel6.setText(watchList.get(index2).getTitle());
				imagePath = watchList.get(index2).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster6.setVisible(true);
				moviePoster6.setImage(image);
				index_6 = index2;
				index2++;

				}
			else {
				moviePoster6.setVisible(false);
				titleLabel6.setVisible(false);
			}

			if (!isFull2 && index2 < watchSize) {

				titleLabel7.setVisible(true);
				titleLabel7.setText(watchList.get(index2).getTitle());
				imagePath = watchList.get(index2).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster7.setVisible(true);
				moviePoster7.setImage(image);
				index_7 = index2;
				index2++;

				}
			else {
				moviePoster7.setVisible(false);
				titleLabel7.setVisible(false);
			}

			if (!isFull2 && index2 < watchSize) {

				titleLabel8.setVisible(true);
				titleLabel8.setText(watchList.get(index2).getTitle());
				imagePath = watchList.get(index2).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster8.setVisible(true);
				moviePoster8.setImage(image);
				index_8 = index2;
				index2++;

				}
			else {
				moviePoster8.setVisible(false);
				titleLabel8.setVisible(false);
			}

			if (!isFull2 && index2 < watchSize) {

				titleLabel9.setVisible(true);
				titleLabel9.setText(watchList.get(index2).getTitle());
				imagePath = watchList.get(index2).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster9.setVisible(true);
				moviePoster9.setImage(image);
				index_9 = index2;
				index2++;

				}
			else {
				moviePoster9.setVisible(false);
				titleLabel9.setVisible(false);
			}

			} else if (watchSize >= 5 && page2 > 0) {

			if (!isFull2 && (index2 + (page2 * 5)) < watchSize) {

				if ((index2 + (page2 * 5)) >= watchSize) {
					isFull2 = true;
				}
				else {
				titleLabel5.setVisible(true);
				titleLabel5.setText(watchList.get(index2
						+ (page2 * 5)).getTitle());
				imagePath = watchList.get(index2 + (page2 * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster5.setVisible(true);
				moviePoster5.setImage(image);
				index_5 = index2;
				index2++;
				}
				}
			else {
				moviePoster5.setVisible(false);
				titleLabel5.setVisible(false);
			}

			if (!isFull2 && (index2 + (page2 * 5)) < watchSize) {

				if ((index2 + (page2 * 5)) >= watchSize) {
					isFull2 = true;
				}

				else {
				titleLabel6.setVisible(true);
				titleLabel6.setText(watchList.get(index2
						+ (page2 * 5)).getTitle());
				imagePath = watchList.get(index2 + (page2 * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster6.setVisible(true);
				moviePoster6.setImage(image);
				index_6 = index2;
				index2++;
				}
				}
			else {
				moviePoster6.setVisible(false);
				titleLabel6.setVisible(false);
			}

			if (!isFull2 && (index2 + (page2 * 5)) < watchSize) {

				if ((index2 + (page2 * 5)) >= watchSize) {
					isFull2 = true;
				}

				else {
				titleLabel7.setVisible(true);
				titleLabel7.setText(watchList.get(index2
						+ (page2 * 5)).getTitle());
				imagePath = watchList.get(index2 + (page2 * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster7.setVisible(true);
				moviePoster7.setImage(image);
				index_7 = index2;
				index2++;
				}
				}
			else {
				moviePoster7.setVisible(false);
				titleLabel7.setVisible(false);
			}

			if (!isFull2 && (index2 + (page2 * 5)) < watchSize) {

				if ((index2 + (page2 * 5)) >= watchSize) {
					isFull2 = true;
				}

				else {
				titleLabel8.setVisible(true);
				titleLabel8.setText(watchList.get(index2
						+ (page2 * 5)).getTitle());
				imagePath = watchList.get(index2 + (page2 * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster8.setVisible(true);
				moviePoster8.setImage(image);
				index_8 = index2;
				index2++;
				}
				}
			else {
				moviePoster8.setVisible(false);
				titleLabel8.setVisible(false);
			}

			if (!isFull2 && (index2 + (page2 * 5)) < watchSize) {

				if ((index2 + (page2 * 5)) >= watchSize) {
					isFull2 = true;
				}

				else {
				titleLabel9.setVisible(true);
				titleLabel9.setText(watchList.get(index2
						+ (page2 * 5)).getTitle());
				imagePath = watchList.get(index2 + (page2 * 5)).getPosterPath();
				imagePath = URL + imagePath;
				Image image = new Image(imagePath, 650, 350, true, true, false);
				moviePoster9.setVisible(true);
				moviePoster9.setImage(image);
				index_9 = index2;
				index2++;
				}
				}
			else {
				moviePoster9.setVisible(false);
				titleLabel9.setVisible(false);
			}

			}
	}

	/**
	 * When next button is clicked then go to next page.
	 *
	 **/
	public void nextButtonClicked() {
		if (!isFull) {
		page++;
		displayMovie();
		}
	}

	/**
	 * When previous button is clicked then go to previous page.
	 *
	 **/
	public void previousButtonClicked() {
		if (page > 0) {
		page--;
		}
		displayMovie();
		isFull = false;
	}

	/**
	 * When next button for watch list is clicked then go to next page.
	 *
	 **/
	public void nextButtonClicked2() {
		if (!isFull2) {
		page2++;
		displayWatchList();
		}
	}

	/**
	 * When previous button for watch list is clicked then go to previous page.
	 *
	 **/
	public void previousButtonClicked2() {
		if (page2 > 0) {
		page2--;
		}
		displayWatchList();
		isFull = false;
	}


	/**
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (favoriteMovies.get(index_0 + (page * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked1() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (favoriteMovies.get(index_1 + (page * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked2() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (favoriteMovies.get(index_2 + (page * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked3() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (favoriteMovies.get(index_3 + (page * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked4() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (favoriteMovies.get(index_4 + (page * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked5() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (watchList.get(index_5 + (page2 * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked6() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (watchList.get(index_6 + (page2 * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked7() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (watchList.get(index_7 + (page2 * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked8() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (watchList.get(index_8 + (page2 * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Obtains the current movie and transitions to the next screen.
	 **/
	public void movieClicked9() {
		System.out.println("Movie clicked");

		try {

			/**
			 * obtains the current scene by selecting any element
			 * and gets their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MovieDetail.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

		   MovieDetailController movieDetail = loader.getController();
		   Movie movie = new Movie (watchList.get(index_9 + (page2 * 5)).getId());
		   movieDetail.setMyData(account, movie, true);

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
	 * Takes the user to the home screen.
	 **/
	public void homeButtonClicked() {
		try {

			/** 
			 * obtains the current scene by selecting any element and get
			 * their window.
			 */
			javafx.stage.Window source = titleLabel.getScene().getWindow();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Home.fxml"));

			try {
				loader.load();
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}

			HomeController movieDetail = loader.getController();
			movieDetail.setMyData(account, true);

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
