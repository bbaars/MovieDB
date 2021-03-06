package edu.cis.CIS350.MovieDB;

//import static java.lang.System.out;
//import org.json.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbPeople.PersonResultsPage;
import info.movito.themoviedbapi.model.people.Person;


/******************************************************************
* Class that gets the Actor ID and other stuff.
* Features:
* - 
*****************************************************************/
public class Actor {
	
	/** Tmdb api objects. **/
	 private TmdbApi tmdbApi;
	 
     /** our API Manager object to hold our tmdp api key. **/
     private APIManager api;
	
	/** Name of the actor. **/
	private String name;
	
	/** ID of actor. **/
	private int id;
	

   /******************************************************************
	 * A constructor that initializes the actor.
	 * 
	 * @param name - name of actor.
	 *****************************************************************/
	public Actor(final String name) {
		this.name = name;
     	api = new APIManager();
        tmdbApi = api.getApiObject();
		id = 0;
	}
	
	/******************************************************************
	 *	Sets the person ID.
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	void setActorID() throws Exception {
		String[] splited = name.split("\\s+");

	    String firstName = splited[0];
	    String lastName = splited[1];
	    
		String json = 
			readUrl("http://api.tmdb.org/3/search/person?api_key"
		+ "=6615c9824f812a6fb9b8b4ea5f49a285&query=" 
				+ firstName + "%20" + lastName);
		
		
		JSONObject obj = new JSONObject(json);
				
		JSONArray result = obj.getJSONArray("results");
		JSONObject firstResult = result.getJSONObject(0);
		
		
	    id = firstResult.getInt("id");
		
	    
	}
	
	/******************************************************************
	 *	Returns actors matching search criteria.
	 *	@return arraylist of matching actors
	 *****************************************************************/
    public ArrayList<Actor> getActorsFromName() {

     	ArrayList<Actor> actors = new ArrayList<Actor>();

     	PersonResultsPage results =
     		tmdbApi.getSearch().searchPerson(name, false, 0);

     	Iterator<Person> iterator = results.iterator();

     	while (iterator.hasNext()) {
     		Person actor = iterator.next();
     		actors.add(new Actor(actor.getName()));
     	}

     	return actors;
     }
	
	/******************************************************************
	 *	Returns the person name.
	 * @return actor
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	public String getName() {
	    return name;
	}
	
	/******************************************************************
	 *	Returns the person ID.
	 * @return genreID
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	int getActorID() {
	    return id;
	}
	
	/******************************************************************
	 *	Returns the person's birthday.
	 * @return birthday
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	String getBirthday() throws Exception {
		String ids = Integer.toString(id);
		String birthday;
		
		String json = 
		readUrl("https://api.themoviedb.org/3/person/" + ids 
		+ "?api_key=6615c9824f812a6fb9b8b4ea5f49a285&language=en-US");
		
		JSONObject obj = new JSONObject(json);
		
		birthday = obj.getString("birthday");
		
		return birthday;

	}
	
	/******************************************************************
	 *	Returns the person's birth place.
	 * @return birthPlace
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	String getBirthPlace() throws Exception {
		String ids = Integer.toString(id);
		String birthPlace;
		
		String json =
		readUrl("https://api.themoviedb.org/3/person/" + ids 
		+ "?api_key=6615c9824f812a6fb9b8b4ea5f49a285&language=en-US");
		
		JSONObject obj = new JSONObject(json);
		
		birthPlace = obj.getString("place_of_birth");
		
		return birthPlace;

	}
	
	/******************************************************************
	 *	Returns the person's biography.
	 * @return biography
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	String getBiography() throws Exception {
		String ids = Integer.toString(id);
		String biography;
		
		String json = 
		readUrl("https://api.themoviedb.org/3/person/" + ids 
		+  "?api_key=6615c9824f812a6fb9b8b4ea5f49a285&language=en-US");
		
		JSONObject obj = new JSONObject(json);
		
		biography = obj.getString("biography");
		
		return biography;

	}
	
	/******************************************************************
	 *	Returns the person profile picture.
	 * @return urlPath
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	String getPic() throws Exception {
		String ids = Integer.toString(id);
		String urlPath;
		
		String json = 
		readUrl("https://api.themoviedb.org/3/person/" + ids
		+ "?api_key=6615c9824f812a6fb9b8b4ea5f49a285&language=en-US");
		
		JSONObject obj = new JSONObject(json);
		
		urlPath = obj.getString("profile_path");
		
		return urlPath;
		
	}
	
	
   /******************************************************************
	 *	Returns the URL of JSON object.
	 * @return buffer.toString()
	 * @param urlString the URL that has the JSON object
	 * @throws Exception when JSON object can't be retrieved
	 *****************************************************************/
   private static String readUrl(final String urlString) throws Exception {
       BufferedReader reader = null;
       try {
           URL url = new URL(urlString);
           reader = new BufferedReader(new InputStreamReader(url.openStream()));
           StringBuffer buffer = new StringBuffer();
           int read;
           char[] chars = new char[1024];
           while ((read = reader.read(chars)) != -1) {
               buffer.append(chars, 0, read); 
           }

           return buffer.toString();
       } finally {
           if (reader != null) {
               reader.close();
           }
       }
   }

}
