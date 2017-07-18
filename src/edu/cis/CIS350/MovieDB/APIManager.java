
package edu.cis.CIS350.MovieDB;

import java.util.ArrayList;
import java.util.Iterator;

import info.movito.themoviedbapi.TmdbAccount;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbAuthentication;
import info.movito.themoviedbapi.TmdbAccount.MediaType;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.config.Account;
import info.movito.themoviedbapi.model.config.TokenSession;
import info.movito.themoviedbapi.model.core.AccountID;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;

/***************************************************************************
 * API manager for classes to use.
 **************************************************************************/
public class APIManager {

	/** Handles the API Manager. **/
	private static TmdbApi tmdbApi;

	/** Session token for one session. **/
	private static SessionToken sessionToken;

	/** Session string. **/
	private String sessionString;
	
	/** Username String. **/
	private String username;
	
	/** Password string. **/
	private String password;
		
	/***********************************************************************
	* Constructor for initializing the API and Session Tokens.
	***********************************************************************/
	public APIManager() {

		/** BRANDONS CURRENT SESSION AND API KEY **/
		sessionString = "720e0b015cfd60b7ad5ab0f12f448f7b9acd35e6";

		 tmdbApi = new TmdbApi("6615c9824f812a6fb9b8b4ea5f49a285");
		 sessionToken = new 
		SessionToken("720e0b015cfd60b7ad5ab0f12f448f7b9acd35e6");
	}
	
	/***********************************************************************
	*	Constructor that initializes an account for a user.
	*
	*  @param username The user name of the user.
	*  @param password The password of the user.
	***********************************************************************/
	public APIManager(final String username, final String password) {
		
		this.username = username;
		this.password = password;
	}
	
	/***********************************************************************
	*	Attempts to login to the users account.
	*
	*  @return Int of whether login was successful, 1 for yes, -1 for not.
	***********************************************************************/
	public int logIn() {
		
		tmdbApi = new TmdbApi("ee032dd5fbbc85b55298a6d20a7f2e10");
		
		TmdbAuthentication tmdbAuth = 
				tmdbApi.getAuthentication();
		
		try {
			TokenSession tokenSession = 
					tmdbAuth.getSessionLogin(username, password);
			
			sessionToken = new SessionToken(tokenSession.getSessionId());
			
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}
	
	/***********************************************************************
	*	Get the users favorite movies.
	*
	*  @return ArrayList of the users current favorite movies.
	***********************************************************************/
	public ArrayList<MovieDb> getFavoriteMovies() {
		
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		Account act = tmdbAccount.getAccount(sessionToken);
		AccountID actId = new AccountID(act.getId());
		ArrayList<MovieDb> favorites = new ArrayList<MovieDb>();
		MovieResultsPage resultsPage = 
			tmdbAccount.getFavoriteMovies(sessionToken, actId);
		
		Iterator<MovieDb> iterator = resultsPage.iterator();
		
		if (iterator.next() == null) {
			System.out.println("The user currently has no favorites");
		} else {
			
			while (iterator.hasNext()) {
				favorites.add(iterator.next());
			}
		}
		
		return favorites;
	}
	
	/***********************************************************************
	*	Get the users watch list.
	*
	*  @return ArrayList of the users current Watchlist.
	***********************************************************************/
	public ArrayList<MovieDb> getMovieWatchList() {
		
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		Account act = tmdbAccount.getAccount(sessionToken);
		AccountID actId = new AccountID(act.getId());
		ArrayList<MovieDb> watchList = new ArrayList<MovieDb>();
		MovieResultsPage resultsPage = 
			tmdbAccount.getWatchListMovies(sessionToken, actId, 0);
		
		Iterator<MovieDb> iterator = resultsPage.iterator();
		
		if (iterator.next() == null) {
			System.out.println("The user currently has no favorites");
		} else {
			
			while (iterator.hasNext()) {
				watchList.add(iterator.next());
			}
		}
		
		return watchList;
	}
	
	/***********************************************************************
	*	Add a movie Favorite.
	*
	*  @param movieID The current id of the movie you'd wish to add to the user.
	***********************************************************************/
	public void addMovieFavorite(final int movieID) {
		
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		Account act = tmdbAccount.getAccount(sessionToken);
		AccountID actId = new AccountID(act.getId());
		tmdbAccount.addFavorite(sessionToken, actId, movieID, MediaType.MOVIE);
	}
	
	/***********************************************************************
	*	Add a movie Favorite.
	*
	*  @param movieID The current id of the movie you'd wish to add to the user.
	***********************************************************************/
	public void addMovieToWatchList(final int movieID) {
		
		TmdbAccount tmdbAccount = tmdbApi.getAccount();
		Account act = tmdbAccount.getAccount(sessionToken);
		AccountID actId = new AccountID(act.getId());
		tmdbAccount.addToWatchList(sessionToken, 
				actId, movieID, MediaType.MOVIE);
	}

	/**********************************************************************
	 * Returns the tmdbapi object.
	 * @return TmdbApi The api key that allows us to make requests.
	 **********************************************************************/
	public TmdbApi getApiObject() {
		return tmdbApi;
	}

	/**********************************************************************
	 * Returns the SessionToken object.
	 * @return SessionToken for each session.
	 **********************************************************************/
	public SessionToken getSessionToken() {
		return sessionToken;
	}

	/**********************************************************************
	 * Returns the SessionString.
	 * @return Session String
	 **********************************************************************/
	public String getSessionString() {
		return sessionString;
	}
}
