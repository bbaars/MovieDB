
package edu.cis.CIS350.MovieDB;

import info.movito.themoviedbapi.TmdbApi;
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

	/***********************************************************************
	* Constructor for initializing the API and Session Tokens.
	***********************************************************************/
	public APIManager() {

		sessionString = "598b5da9fced4ed17e54f6956875b1198dff82ff";

		 tmdbApi = new TmdbApi("ee032dd5fbbc85b55298a6d20a7f2e10");
		 sessionToken = new 
		SessionToken("598b5da9fced4ed17e54f6956875b1198dff82ff");
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
