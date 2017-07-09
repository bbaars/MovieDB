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
	public static SessionToken sessionToken;
	
	/**
	* Constructor for initializing the API and Session Tokens.
	*/
	public APIManager() {
		 tmdbApi = new TmdbApi("6615c9824f812a6fb9b8b4ea5f49a285");
		 sessionToken = new SessionToken("21b1aa674bced210f3b99836956d47ecb1ee13e6");
	}
}
