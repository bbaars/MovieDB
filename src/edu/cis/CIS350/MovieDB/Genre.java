package edu.cis.CIS350.MovieDB;

import java.util.HashMap;


/******************************************************************
 * Class that gets the Genre ID for a certain genre.
 * 
 *****************************************************************/
public class Genre {
	
	/** Movie Genre that user entered. **/
	private String genre;
	
	/******************************************************************
	 * A constructor that initializes the genre.
	 * 
	 * @param genre - genre of movie.
	 *****************************************************************/
	public Genre(final String genre) {
		this.genre = genre;
	}
	
	/******************************************************************
	 *	Returns the Genre ID of the Genre.
	 * @return genreID
	 *****************************************************************/
	int getGenreID() {
		GenreID genreID = new GenreID(genre);
		return genreID.getID();
	}
	
	/******************************************************************
	 * Class that gets the Genre ID for a certain genre.
	 * 
	 *****************************************************************/
	private class GenreID {
		/**An integer that holds the genre ID. **/
		private int id;
		
		/**A string that holds the genre type. **/
		private String genre;

		/**A hashmap that holds string and number ID. **/
	    private HashMap<String, Integer> hmap = new HashMap<String, Integer>();

	    /******************************************************************
		 * A constructor that initializes the genre, genre ID, and hashmap.
		 * 
		 * @param genre - genre of movie.
		 *****************************************************************/
		GenreID(final String genre) {
			this.genre = genre;
			this.id = 0;
			
			/*Adding elements to HashMap*/
		      hmap.put("Action", 28);
		      hmap.put("Adventure", 12);
		      hmap.put("Animation", 16);
		      hmap.put("Comedy", 35);
		      hmap.put("Crime", 80);
		      hmap.put("Documentary", 99);
		      hmap.put("Drama", 18);
		      hmap.put("Family", 10751);
		      hmap.put("Fantasy", 14);
		      hmap.put("History", 36);
		      hmap.put("Horror", 27);
		      hmap.put("Music", 10402);
		      hmap.put("Mystery", 9648);
		      hmap.put("Romance", 10749);
		      hmap.put("Science Fiction", 878);
		      hmap.put("TV Movie", 10770);
		      hmap.put("Thriller", 53);
		      hmap.put("War", 10752);
		      hmap.put("Western", 37);
		}

		/******************************************************************
		 *	Returns the Genre ID of the Genre.
		 * @return id
		 *****************************************************************/
		int getID() { 
			id = hmap.get(genre);
		    return id;
		}
	}
}
