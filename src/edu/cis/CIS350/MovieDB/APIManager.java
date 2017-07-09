package edu.cis.CIS350.MovieDB;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.SessionToken;

/**
 * Api manager for classes to use.
 */
public class APIManager {
	
	/** handles the API Manager. **/
	public static TmdbApi tmdbApi;
	
	/** Session token for one session. **/
	private static SessionToken sessionToken;
	
	/**
	* Constructor for initializing the API and Session Tokens.
	*/
	public APIManager() {
		 tmdbApi = new TmdbApi("6615c9824f812a6fb9b8b4ea5f49a285");
		 sessionToken = new SessionToken("21b1aa674bced210f3b99836956d47ecb1ee13e6");
	}
	
	/*
	 * Returns the tmdbapi object.
	 * @return TmdbApi The api key that allows us to make requests.
	 */
	public TmdbApi getApiObject() {
		return tmdbApi;
	}
	
	/*
	 * Returns the SessionToken object.
	 * @return SessionToken for each session.
	 */
	public SessionToken getSessionToken() {
		return sessionToken;
	}
}
