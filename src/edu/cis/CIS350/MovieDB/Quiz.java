package edu.cis.CIS350.MovieDB;

/** Quiz class that handles the the data associated with a quiz. **/
public class Quiz {

	/** Array of strings to hold questions. **/
	private String[] questions = new String[5];
	
	/** Array of ints to hold user answers.**/
	private int[] totals = new int[4];
	
    /**
     * Populates questions array.
     **/
	public Quiz() {
	questions[0] = "Which word most applies to you:\n1.Romantic\n2.Curious"
				+ "\n3.Funny\n4.Ambitious";
	questions[1] = "What is your ideal night out:\n1.Dinner with SO\n"
			+ "2.Doing an Escape Room\n3.Night in with friends\n"
				+ "4.Hiking and seeing the stars";
	questions[2] = "Who is your favorite actor:\n1.Ryan Gosling\n"
			+ "2.Mark Harmon\n3.Will Ferrell\n4.Tom Cruise";
	questions[3] = "What was your subject in school:\n1.Art\n2.History\n"
				+ "3.School is for nerds\n4.Chemistry";
	questions[4] = "Where are you in 5 years:\n1.Married with kids\n"
		    + "2.Still in school\n3.Las Vegas\n4.In another country";
	}
	
    /**
     * Getter method to return specific question.
     * @param questionNumber Number of desired question.
     * @return String or desired question
     **/
	public String getQuestion(final int questionNumber)	{
		return this.questions[questionNumber];
	}
	
    /**
     * Setter method to record user answer.
     * @param answer Answer of user.
     **/
	public void setAnswer(final int answer)	{
		this.totals[answer]++;
	}
	
	/**
     * Method to clear answer array if quiz is rerun.
     **/
	public void clearAnswers()	{
		for (int i = 0; i < 4; i++) {
			this.totals[i] = 0;
			}
	}
	
	/**
     * Method to go through answer aray and return the highest category.
     * @return String of genre the user "is"
     **/
	public String returnGenre() {
		int index = 0;
		int max = 0;
		String genreString = " ";
		for (int i = 0; i < this.totals.length; i++) {
			if (this.totals[i] > max) {
				max = this.totals[i];
				index = i;
			}
		}
		
		switch (index) {
		case 0:
			genreString = "Romance";
			break;
		case 1:
			genreString = "Mystery";
			break;
		case 2:
			genreString = "Comedy";
			break;
		case 3:
			genreString = "Adventure";
			break;
		 default: 
         break;
		}
		return genreString;					
	}
}



