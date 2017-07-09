package edu.cis.CIS350.MovieDB;

public class Genre {
	private String genre;
	
	public Genre (String genre)
	{
		this.genre = genre;
	}
	
	private class GenreID{
		/**An integer to hold an x coordinate**/
		String ID;


		GenreID(String ID){
			this.ID = ID;
		}

		String getID(){ 
			return ID;
		}
	}
}
