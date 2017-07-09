package edu.cis.CIS350.MovieDB;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;

/** Movie class that handles the the data associated with a movie. **/
public class Movie {

    /** Title of the movie that is wanted to obtain information on. **/
    private String _title;
    
    /** String of the picture of the movies backdrop path. **/
    private String _backdrop_Path;
    
    /** Budget for the movie. **/
    private int _budget;
    
    /** Id for the movie. **/
    private int _id;
    
    /** The language for the movie. **/
    private String _originalLanguage;
    
    /** Overview description. **/
    private String _overview;
    
    /** Popularity of the movie. **/
    private double _popularity; 
    
    /** Release date of the movie **/
    private String _releaseDate;
    
    /** The total revenue of the given movie **/
    private int _revenue;
    
    /** The runtime in total minutes **/
    private int _runtime;
    
    /** A one-liner tagline that the movie is known for**/
    private String _tagline;
    
    /** The average of all the votes cast for the movie **/
    private double _voteAverage;
    
    /** The total number of votes counted **/
    private int _voteCount;
    
    /** holds our tmbd api key. **/
    private static TmdbApi tmdbApi;
    
    /** Holds our SessionToken. */
    private static SessionToken sessionToken;
    	
    /** our API Manager object to hold our tmdp api key **/
    	private APIManager api;
    
    /** Constructor call that accepts a movie title.
    * @param title: The title of the movie.
    **/
    public Movie(final String title) {
        this._title = title;
        tmdbApi = api.getApiObject();
        sessionToken = api.getSessionToken();
    }
    
    /*
     * Searches the API for the movie to obtain relevant information 
     */
    private void searchMovie() {
    		MovieResultsPage results = tmdbApi.getSearch().searchMovie(_title, 0, "en", false, 0);
    		
    }
    
    
    
    
}
