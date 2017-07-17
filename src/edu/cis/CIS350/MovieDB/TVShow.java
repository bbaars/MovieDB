package edu.cis.CIS350.MovieDB;



import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbTV.TvMethod;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.tv.TvSeries;

import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;


/******************************************************************
* Class that holds and returns different attributes of a tv show.
* 
*****************************************************************/
public class TVShow extends APIManager {

	/** ID of a tv show. **/
	private static int id;

	/** Title of the TV Show. **/
	private String title;

	/** holds The Movie Database api key. **/
	private static TmdbApi tmdbApi;

	/** API Manager object that allows the tmdb api to be used. **/
	private APIManager api;


	/******************************************************************
	 * Constructor call that accepts a TV show title.
	 * 
	 * @param title - The title of the TV show.
	 *****************************************************************/
	public TVShow(final String title) {
		this.title = title;
		api = new APIManager();
		tmdbApi = api.getApiObject();
		id = getIDfromString(title);
	}

	/******************************************************************
	 * Get the tv show ID from the database given the title of the tv
	 * show.
	 * 
	 * @param query - The title of the TV show.
	 * @return id the ID of the tv show
	 *****************************************************************/
	public int getIDfromString(final String query) {
		TvResultsPage result = 
				tmdbApi.getSearch().searchTv(query, "en", 0);

		int id = -1;

		if (!result.getResults().isEmpty()) {
			id = result.getResults().get(0).getId();
		}

		return id;
	}


	/******************************************************************
	 * Get the tv show ID. 
	 * 
	 * @return id the ID of the tv show
	 *****************************************************************/
	public int getID() {
		return id;
	}

	/******************************************************************
	 * Get the back drop path of images for tv shows.
	 * 
	 * @return backDropPath the backdrop path for tv show
	 *****************************************************************/
	public String getBackDropPath() {
		try {
		String backDropPath;
		TvSeries tvShow = tmdbApi.getTvSeries().getSeries(id, "en");
		backDropPath = tvShow.getBackdropPath();
		
		return backDropPath;
		
		} catch (Exception e) {
		return "null";
		}
	}

	/******************************************************************
	 * Get the tv show ID. 
	 * 
	 * @return id the ID of the tv show
	 *****************************************************************/
	public String getTitle() {
		return title;
	}

	/******************************************************************
	 * Get the director of the tv show.
	 * 
	 * @return director director of the tv show
	 *****************************************************************/
	public String getDirector() {
		try {
		String director;	
		TvSeries tvShow = tmdbApi.getTvSeries().getSeries(id, "en");
		
		director = tvShow.getCreatedBy().get(0).toString();
		return director;
		
		} catch (Exception e) {
		return "null";
		}

	}

	/******************************************************************
	 * Get the tv show rating.
	 * 
	 * @return rating rating of the tv show
	 *****************************************************************/
	public String getRating() {
		
		try {
		String rating;
		TvSeries tvShow = tmdbApi.getTvSeries().getSeries(id, "en");
		
		rating = String.valueOf(tvShow.getVoteAverage());
		
		return rating;
		} catch (Exception e) {
			return "null";
		}

	}

	/******************************************************************
	 * Get the lead actor of tv show.
	 * 
	 * @return leadActor lead actor of the tv show
	 *****************************************************************/
	public String getLeadActor() {
		
		try {
			String leadActor;
			TvSeries tvShow = tmdbApi.getTvSeries()
					.getSeries(id, "en", TvMethod.credits);
			
			leadActor = 
				tvShow.getCredits().getAll().get(0).toString();
			
			return leadActor;
		} catch (Exception e) {
			return "null";
		}
	}
	
	/******************************************************************
	 * Get the tv show air date.
	 * 
	 * @return airDate air date of the tv show
	 *****************************************************************/
	public String getAirDate() {
		try {
			String airDate;
			TvSeries tvShow =
				tmdbApi.getTvSeries().getSeries(id, "en");
			airDate = tvShow.getFirstAirDate();
			
			return airDate;
		} catch (Exception e) {
			return "null";
		}
	}
	
	/******************************************************************
	 * Get the genres of the tv show.
	 * 
	 * @return genres genres of the tv show
	 *****************************************************************/
	public List<String> getGenres() {
		List<String> genres = new ArrayList<String>();
		
		try {
		TvSeries tvShow = tmdbApi.getTvSeries().getSeries(id, "en");

		for (int i = 0; i < tvShow.getGenres().size(); i++) {
			genres.add(tvShow.getGenres().get(i).toString());
		}
		
		} catch (Exception e) {
		}
		
		return genres;
		
		
	}
	
	/******************************************************************
	 * Get the results of the tv shows that the user entered.
	 * 
	 * @param query title of the tv show
	 * @return series results of tv shows
	 *****************************************************************/
	public List<TvSeries> searchTVShows(final String query) {
		
		List<TvSeries> series = new ArrayList<TvSeries>();
		TvResultsPage result = 
			tmdbApi.getSearch().searchTv(query, "en", 0);

		series = result.getResults();
		
		return series;
	}




	/** 
	 * 	Test out Actor class.	
	 *  @param args For running main function. 
	 *  **/
	public static void main(final String[] args) {

		TVShow show = new TVShow("Doctor Who");

		out.println("ID: " + show.getID());
		out.println("Backdrop Path: " + show.getBackDropPath());
		
		List<String> list = show.getGenres();

		for (String n: list) {
			out.println("Genre:" + n);
		}

		out.println("Director: " + show.getDirector());
		out.println("Rating: " + show.getRating());
		out.println("Lead Actor: " + show.getLeadActor());
		out.println("Air Date: " + show.getAirDate());

	}
}
