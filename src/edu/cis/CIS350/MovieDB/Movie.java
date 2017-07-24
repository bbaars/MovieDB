 package edu.cis.CIS350.MovieDB;


import java.util.ArrayList;
 import java.util.Iterator;
 import info.movito.themoviedbapi.TmdbApi;
 import info.movito.themoviedbapi.TmdbMovies;
 import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.model.Artwork;
 import info.movito.themoviedbapi.model.ArtworkType;
import info.movito.themoviedbapi.model.Genre;
 import info.movito.themoviedbapi.model.MovieDb;
 import info.movito.themoviedbapi.model.Reviews;
 import info.movito.themoviedbapi.model.Video;
 import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;
 
 /** Movie class that handles the the data associated with a movie. **/
 public class Movie {

     /** holds our tmbd api key. **/
     private TmdbApi tmdbApi;

     /** Holds the ID of a movie. **/
     private int id;

     /** Whether or not the movie is an adult film. **/
     private boolean adult;

     /** Backdrop path to the request movie. **/
     private String backdropPath;

     /** Budget of the movie. **/
     private long budget;

     /** List of genres the movie can be classified as. **/
     private ArrayList<String> genres;

     /** Original language of the movie. **/
     private String originalLanguage;

     /** Original Title (Not always the same as the title). **/
     private String originalTitle;

     /** Brief overview of the movie. **/
     private String overview;

     /** Popularity of the movie, compared to other movies. **/
     private float popularity;

     /** Path to the poster for the movie. **/
     private String posterPath;

     /** Release date of the movie, past and future. **/
     private String releaseDate;

     /** Revenue the movie has gotten up to now. **/
     private long revenue;

     /** Run time of the movie in minutes. **/
     private int runtime;

     /** A one line of a 'catch phrase' from the movie. **/
     private String tagline;

     /** The title of the movie. **/
     private String title;

     /** Average amount of votes 1 - 10. **/
     private float voteAverage;

     /** The amount of people who have cast a vote. **/
     private int voteCount;

     /** Holds an object of type review. */
     private ArrayList<Reviews> reviews;

     /** Holds an object of type video. */
     private ArrayList<Video> videos;

     /** Holds an image. */
     private ArrayList<Artwork> images;

     /** Holds the cast of our movie we're trying to find. **/
     private ArrayList<PersonCast> cast;
     
     /** our API Manager object to hold our tmdp api key. **/
     private APIManager api;

     /** ArrayList of similar movies to the specified movie. **/
     private ArrayList<MovieDb> similarMovies;

     /*************************************************************************
      * Movie Constructor call that accepts a movie title.
      *
      * @param title The title of the movie.
      *************************************************************************/
     public Movie(final String title) {
     	this.title = title;
     	api = new APIManager();
        tmdbApi = api.getApiObject();
     }

     /**************************************************************************
      * Movie Constructor.
      *
      * @param id The id of the movie you'd like to obtain
      *************************************************************************/
     public Movie(final int id) {
     	this.id = id;
     	api = new APIManager();
        tmdbApi = api.getApiObject();
     	this.getMovieFromID();
     }

     /**************************************************************************
      * Empty constructor to access things like movies by genre, popular movies,
      * top rated movies. Things that are not movie specific.
      *
      * What you can get back is a Array List of movies with all of the data
      * needed.
      *************************************************************************/
     public Movie() {
     	api = new APIManager();
        tmdbApi = api.getApiObject();
     }


     /**************************************************************************
      * Searches the API for the movie to obtain relevant information.
      *
      * @return MovieResults returns an ArrayList of individual movies where you
      * can access every property about each movie
      *************************************************************************/
     public ArrayList<Movie> getMoviesFromTitle() {

     	ArrayList<Movie> movies = new ArrayList<Movie>();

     	MovieResultsPage results =
     		tmdbApi.getSearch().searchMovie(title, 0, "en", false, 0);

     	Iterator<MovieDb> iterator = results.iterator();

     	while (iterator.hasNext()) {
     		MovieDb movie = iterator.next();
     		movies.add(new Movie(movie.getId()));
     	}

     	return movies;
     }

     /**************************************************************************
      * Get the Popular Movies .
      * @param numOfPages The amount of pages you want to obtain top movies from
      *
      * @return ArrayList of movies where each is a top movie (1 page)
      *************************************************************************/
     public ArrayList<MovieDb> getPopularMovies(final int numOfPages) {

     	TmdbMovies tmdbMovies = tmdbApi.getMovies();

     	ArrayList<MovieDb> popularMovies = new ArrayList<MovieDb>();
     	
     	/* Loops through multiple pages of movies and appends to our array. */
     	for (int i = 1; i <= numOfPages; i++) {
     		MovieResultsPage results = tmdbMovies.getPopularMovies("en", i);

     		for (MovieDb mov : results) {
         		popularMovies.add(mov);
         	}
     	}

     	return popularMovies;
     }
     
     /**************************************************************************
      * Get the Popular Movies .
      * @param numOfPages The amount of pages you want to obtain top movies from
      *
      * @return ArrayList of movies where each is a top movie (1 page)
      *************************************************************************/
     public ArrayList<MovieDb> getTopRatedMovies(final int numOfPages) {

     	TmdbMovies tmdbMovies = tmdbApi.getMovies();

     	ArrayList<MovieDb> topRatedMovies = new ArrayList<MovieDb>();

     	/* Loops through multiple pages of movies and appends to our array. */
     	for (int i = 1; i <= numOfPages; i++) {
     		MovieResultsPage results = tmdbMovies.getPopularMovies("en", i);

     		for (MovieDb mov : results) {
     			topRatedMovies.add(mov);
         	}
     	}

     	return topRatedMovies;
     }

     /*************************************************************************
      * Gets a movie from the ID.
      *************************************************************************/
     private void getMovieFromID() {
    	 
     	TmdbMovies tmdbMovies = tmdbApi.getMovies();
     	genres = new ArrayList<String>();

     	MovieDb movie = tmdbMovies.getMovie(this.id, "en", MovieMethod.reviews,
     		MovieMethod.videos, MovieMethod.images, MovieMethod.credits);
     	
     	backdropPath = movie.getBackdropPath();
     	budget = movie.getBudget();
     	originalLanguage = movie.getOriginalLanguage();
     	originalTitle = movie.getOriginalTitle();
     	overview = movie.getOverview();
     	popularity = movie.getPopularity();
     	posterPath = movie.getPosterPath();
     	releaseDate = movie.getReleaseDate();
     	revenue = movie.getRevenue();
     	runtime = movie.getRuntime();
     	tagline = movie.getTagline();
     	title = movie.getTitle();
     	videos = (ArrayList<Video>) movie.getVideos();
     	voteAverage = movie.getVoteAverage();
     	voteCount = movie.getVoteCount();
     	cast = (ArrayList<PersonCast>) movie.getCast();
     	reviews = (ArrayList<Reviews>) movie.getReviews();
     	images = (ArrayList<Artwork>) movie.getImages(ArtworkType.POSTER);

     	ArrayList<Genre> genresList = (ArrayList<Genre>) movie.getGenres();

     	for (Genre genre: genresList) {
     		genres.add(genre.getName());
     	}
     }

     /*************************************************************************
      * Get similar movies to the one that was passed.
      *
      * @return ArrayList of Similar movies
      ************************************************************************/
     public ArrayList<MovieDb> getSimilarMovie() {
    	 
    	TmdbMovies tmdbMovies = tmdbApi.getMovies();
    	 
      	MovieResultsPage similar =
      			tmdbMovies.getSimilarMovies(this.id, "en", 0);
      	Iterator<MovieDb> iterator = similar.iterator();
      	
      	if (iterator == null) {
      		System.out.println("No similar Movies");
      	} else {
      		
      		while (iterator.hasNext()) {
      			similarMovies.add(iterator.next());
      		}
      	}
     	return similarMovies;
     }
     
     /*************************************************************************
      * Get the movies unique identifier.
      *
      * @return long integer of the movies unique identifier
      *************************************************************************/
     public int getID() {
     	return id;
     }
     
     /*************************************************************************
      * Get the genres associated with the movie.
      *
      * @return ArrayList of strings of the genres.
      *************************************************************************/
     public ArrayList<String> getGenres() {
     	return genres;
     }

     /*************************************************************************
      * Get the artwork associated with the movie.
      *
      * @return ArrayList of artwork for the movie
      *************************************************************************/
     public ArrayList<Artwork> getImages() {
     	return images;
     }

     /*************************************************************************
      * Get the Reviews from the movie.
      *
      * @return ArrayList of reviews of what people are saying about the movie.
      *************************************************************************/
     public ArrayList<Reviews> getReviews() {
     	return reviews;
     }

     /*************************************************************************
      * Get the cast from the associated movie.
      *
      * @return ArrayList of the cast of the movie.
      *************************************************************************/
     public ArrayList<PersonCast> getCast() {
     	return cast;
     }

     /*************************************************************************
      * Get the vote count of the movie.
      *
      * @return VoteCount as an integer of how many people voted.
      *************************************************************************/
     public int getVoteCount() {
     	return voteCount;
     }

     /*************************************************************************
      * Get the vote average 1 - 10 for what the movie received.
      *
      * @return float of the vote Average.
      *************************************************************************/
     public float getVoteAverage() {
     	return voteAverage;
     }

     /*************************************************************************
      * Get the videos from the movie.
      *
      * @return Videos associated with the movie
      *************************************************************************/
     public ArrayList<Video> getVideos() {
     	return videos;
     }

     /*************************************************************************
      * Get the tag line associated with the movie.
      *
      * @return String of the tag line of the movie.
      *************************************************************************/
     public String getTagline() {
     	return tagline;
     }

     /*************************************************************************
      * Get the runtime of the movie.
      *
      * @return int of the runtime of the movie in minutes.
      *************************************************************************/
     public int getRuntime() {
     	return runtime;
     }

     /*************************************************************************
      * Get the poster path of the movie.
      *
      * @return String for the poster path of the movie.
      *************************************************************************/
     public String getPosterPath() {
     	return posterPath;
     }

     /*************************************************************************
      * Get the revenue of the movie.
      *
      * @return long of the revenue of what the movie made.
      *************************************************************************/
     public long getRevenue() {
     	return revenue;
     }

     /*************************************************************************
      * Get the release date of the movie.
      *
      * @return String of the release date.
      *************************************************************************/
     public String getReleaseDate() {
     	return releaseDate;
     }

     /*************************************************************************
      * Get the popularity of the movie compared to every other one.
      *
      * @return float of the popularity of the movie.
      *************************************************************************/
     public float getPopularity() {
     	return popularity;
     }

     /*************************************************************************
      * Get the overview of the movie.
      *
      * @return String of the overview of the move.
      *************************************************************************/
     public String getOverview() {
     	return overview;
     }

     /*************************************************************************
      * Get the original title of the movie.
      *
      * @return Original title of the movie.
      *************************************************************************/
     public String getOriginalTitle() {
     	return originalTitle;
     }

     /*************************************************************************
      * Get the original language for the movie.
      *
      * @return the original language of the movie.
      *************************************************************************/
     public String getOriginalLanguage() {
     	return originalLanguage;
     }

     /*************************************************************************
      * Get the budget for the movie.
      *
      * @return The budget for the movie.
      *************************************************************************/
     public long getBudget() {
     	return budget;
     }

     /*************************************************************************
      * Get the backdrop path of the movie.
      *
      * @return String of the backdrop path of the movie
      *************************************************************************/
     public String getBackdropPath() {
     	return backdropPath;
     }

     /*************************************************************************
      * Get the title of the movie.
      *
      * @return title Title of the movie.
      *************************************************************************/
     public String getTitle() {
     	return title;
     }

     /*************************************************************************
      * Get whether the film is an adult film.
      *
      * @return adult Title of the movie.
      *************************************************************************/
     public Boolean getAdult() {
     	return adult;
     }
 }
