package edu.cis.CIS350.MovieDB;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;

/** Movie class that handles the the data associated with a movie. **/
public class Movie {

    /** Title of the movie that is wanted to obtain information on. **/
    private String title;
    
    /** String of the picture of the movies backdrop path. **/
    private String backdropPath;
    
    /** Budget for the movie. **/
    private int budget;
    
    /** Id for the movie. **/
    private int id;
    
    /** The language for the movie. **/
    private String originalLanguage;
    
    /** Overview description. **/
    private String overview;
    
    /** Popularity of the movie. **/
    private double popularity; 
    
    /** Release date of the movie. **/
    private String releaseDate;
    
    /** The total revenue of the given movie. **/
    private int revenue;
    
    /** The runtime in total minutes. **/
    private int runtime;
    
    /** A one-liner tagline that the movie is known for.**/
    private String tagline;
    
    /** The average of all the votes cast for the movie. **/
    private double voteAverage;
    
    /** The total number of votes counted. **/
    private int voteCount;
    
    /** holds our tmbd api key. **/
    private static TmdbApi tmdbApi;
    
    /** Holds our SessionToken. */
    private static SessionToken sessionToken;
    	
    /** our API Manager object to hold our tmdp api key. **/
    	private APIManager api;
    
    /** Constructor call that accepts a movie title.
    * @param title The title of the movie.
    **/
    public Movie(final String title) {
        this.title = title;
        tmdbApi = api.getApiObject();
        sessionToken = api.getSessionToken();
    }
    
    /**
     * Searches the API for the movie to obtain relevant information. 
     **/
    private void searchMovie() {
    		MovieResultsPage results = 
    		tmdbApi.getSearch().searchMovie(title, 0, "en", false, 0);
    		
    }
    
    
    
    
}
