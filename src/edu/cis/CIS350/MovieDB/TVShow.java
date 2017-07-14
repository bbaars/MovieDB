package edu.cis.CIS350.MovieDB;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;

public class TVShow{
	
	/** Title of the TV Show **/
    private String _title;
	
    /** holds The Movie Database api key. **/
    private static TmdbApi tmdbApi;
    
    /** Holds the current sessionToken. */
    private static SessionToken sessionToken;
    
    /** API Manager object that allows the tmdb api to be used **/
	private APIManager api;
	
	
    /** Constructor call that accepts a TV show title.
    * @param title: The title of the TV show.
    **/
    public TVShow(final String title) {
        this._title = title;
        tmdbApi = api.getApiObject();
        sessionToken = api.getSessionToken();
    }
    
    /*
     * Searches for a TV show by name
     */
    private TvResultsPage searchTVShow() {
    		TvResultsPage result = tmdbApi.getSearch().searchTv(_title, "en", 0);
    		return result;
    }
    
    
}
