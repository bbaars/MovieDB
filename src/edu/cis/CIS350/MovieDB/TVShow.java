package edu.cis.CIS350.MovieDB;



import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvSeries;

import static java.lang.System.out;
import java.util.List;


public class TVShow extends APIManager{
	
	/** Title of the TV Show **/
    private String title;
	
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
        this.title = title;
        api = new APIManager();
        
        tmdbApi = api.getApiObject();
        sessionToken = api.getSessionToken();
    }
    
    /*
     * Searches for a TV show by name
     */
    public int searchTVShow() {
    		TvResultsPage result = tmdbApi.getSearch().searchTv(title, "en", 0);
    		
    		int id = -1;
    		
    		if (!result.getResults().isEmpty())
    			id = result.getResults().get(0).getId();
    	
    		
    		return id;
    }
    
    public List<TvSeries> searchTVShows(String query)
    {
    	TvResultsPage result = tmdbApi.getSearch().searchTv(query, "en", 0);
    	
    	return result.getResults();
    }
    
    
    
    
    //Test out the TVshow class
    public static void main(String[] args) {

        TVShow show = new TVShow("Doctor Who");

        
        out.println("ID: " + show.searchTVShow());
    }
    
    
    
    
}
