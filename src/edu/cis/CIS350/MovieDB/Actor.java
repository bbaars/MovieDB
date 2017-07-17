package edu.cis.CIS350.MovieDB;

//import static java.lang.System.out;
//import org.json.*;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;


/******************************************************************
* Class that gets the Actor ID and other stuff.
* Features:
* - 
*****************************************************************/
public class Actor {
	
	/** Name of the actor. **/
	private String actor;
	
	/** ID of actor. **/
	private int id;
	

   /******************************************************************
	 * A constructor that initializes the actor.
	 * 
	 * @param actor - genre of movie.
	 *****************************************************************/
	public Actor(final String actor) {
		this.actor = actor;
		id = 0;
	}
	
	/******************************************************************
	 *	Returns the person ID.
	 * @return genreID
	 * @throws Exception when URL can't be read
	 *****************************************************************/
	int getActorID() throws Exception {
		String[] splited = actor.split("\\s+");

	    String firstName = splited[0];
	    String lastName = splited[1];
	    
		String json = 
			readUrl("http://api.tmdb.org/3/search/person?api_key"
		+ "=6615c9824f812a6fb9b8b4ea5f49a285&query=" 
				+ firstName + "%20" + lastName);
		
		
		JSONObject obj = new JSONObject(json);
		//String n = obj.getString("page");

//		System.out.println("Page: " + a);  // prints "Page 1"
		
		JSONArray result = obj.getJSONArray("results");
		JSONObject firstResult = result.getJSONObject(0);
	    id = firstResult.getInt("id");
		
//		String str = "{ \"number\": [3, 4, 5, 6] }";
//		JSONObject obj = new JSONObject(str);
//		JSONArray arr = obj.getJSONArray("number");
//		for (int i = 0; i < arr.length(); i++)
//		    System.out.println(arr.getInt(i));
		
	    //Page page = gson.fromJson(json, Page.class);

	    return id;
	    
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
