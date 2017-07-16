package edu.cis.CIS350.MovieDB;



import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbTV.TvMethod;
import info.movito.themoviedbapi.TvResultsPage;
import info.movito.themoviedbapi.model.core.SessionToken;
import info.movito.themoviedbapi.model.tv.TvSeries;

import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;



public class TVShow extends APIManager{

	private static int id;

	/** Title of the TV Show **/
	private static String title;

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

		id = getID_fromString(title);
	}

	public int getID_fromString(String query) {
		TvResultsPage result = tmdbApi.getSearch().searchTv(query, "en", 0);

		int id = -1;

		if (!result.getResults().isEmpty())
			id = result.getResults().get(0).getId();

		return id;
	}


	public int getID()
	{
		return id;
	}

	public String getBackDropPath()
	{
		try{
		TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en");
		return tv_show.getBackdropPath();
		}
		
		catch (Exception e){
		return "null";
		}
	}

	public String getTitle()
	{
		return title;
	}

	public String getDirector()
	{
		try{
		TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en");
		return tv_show.getCreatedBy().get(0).toString();
		}
		
		catch (Exception e){
		return "null";
		}

	}

	public String getRating()
	{
		try{
		TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en");
		return String.valueOf(tv_show.getVoteAverage());
		}
		
		catch (Exception e){
			return "null";
		}

	}

	public String getLeadActor()
	{
		try{
			TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en", TvMethod.credits);
			return tv_show.getCredits().getAll().get(0).toString();
		}
		
		catch (Exception e){
			return "null";
		}
	}
	
	public String getAirDate()
	{
		try{
			TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en");
			return tv_show.getFirstAirDate();
		}
		
		catch (Exception e){
			return "null";
		}
	}





	public List<String> getGenres()
	{
		List<String> genres = new ArrayList<String>();
		
		try {
		TvSeries tv_show = tmdbApi.getTvSeries().getSeries(id, "en");

		for (int i = 0; i < tv_show.getGenres().size(); i++)
		{
			genres.add(tv_show.getGenres().get(i).toString());
		}
		}
		catch (Exception e)
		{
		}
		
		return genres;
		
		
	}



	public List<TvSeries> searchTVShows(String query)
	{
		TvResultsPage result = tmdbApi.getSearch().searchTv(query, "en", 0);

		return result.getResults();
	}




	//Test out the TVshow class
	public static void main(String[] args) {

		TVShow show = new TVShow("Doctor Who");

		out.println("ID: " + show.getID());
		out.println("Backdrop Path: " + show.getBackDropPath());
		
		List<String> list = show.getGenres();

		for (String n: list)
		{
			out.println("Genre:" + n);
		}

		out.println("Director: " + show.getDirector());
		out.println("Rating: " + show.getRating());
		out.println("Lead Actor: " + show.getLeadActor());
		out.println("Air Date: " + show.getAirDate());

	}
}
