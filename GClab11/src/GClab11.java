import java.util.Scanner;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class GClab11 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String userCont = "y";
		List<Movie> movieList = new ArrayList<>();
	
		// It puts value in our list with the movies packet on information.
		for (int d = 1; d < 101; d++) {
			movieList.add(MovieIO.getMovie(d));
		}

		// gets a set of all of the categories
		Set<String> catSet = new HashSet<>();
		for (int i = 0; i < movieList.size(); i++) {
			String tempCat;
			Movie tempMov;
			tempMov = movieList.get(i);
			tempCat = tempMov.getCategory();
			catSet.add(tempCat);
		}

		
		HashMap<Integer, String> catChoices = new HashMap<>();
		int i = 1;
		for (String cat : catSet) {
			catChoices.put(i, cat);
			i++;
		}
		//prompt that welcomes the user.
		System.out.println("Welcome to the Movie List Application!");

		do {
			printCatChoices(catChoices);
			//uses the validator class to check the users input within a set of parameters.
			int userSelection = Validator.getInt(scnr, "What category are you interested in?");
			//if the user selects the correct parameter input then give them their selection.
			if (catChoices.containsKey(userSelection)) {
				//This TreeSet is used with the command alphaList 
				TreeSet<String> alphList = new TreeSet<>(); 
				//For loop calls for the movie list and categories from our MovieIO class 
				//It uses the users Input to call to a certain category they selected.
				for (int k = 0; k < movieList.size(); k++) {
					Movie tempMov;
					tempMov = movieList.get(k);
					String tempCat = tempMov.getCategory();
					//Then if all the values meet the parameters 
					//put the genre the user selected in alphabetical order
					if (tempCat.equals(catChoices.get(userSelection))) {
						alphList.add(tempMov.getTitle());
					}
					
				}
				for(String titleSelection : alphList) {
					System.out.println(titleSelection);
				}
				
				//if the user selects something outside of our parameters.
				//This prompts up that they have selected outside of the parameters given.
				//Tells them to select a valid one.
			} else {
				System.out.println("Your selection is INVALID! Please choose a Valid Number.");
			}
			//using our validator class, we are asking the user if they want to keep going or to stop the program.
			//It calls to it and sees if they either enter a y to keep going or anything else to exit.
			userCont = Validator.getString(scnr, "Press \"y\" to continue, any other key to exit.");
			//while is here after the user selects y to keep going to move onto the next line/prompt
		} while (userCont.equalsIgnoreCase("y"));

		scnr.close();
	}
	//prompt of a movie category they want to see and showing them the proper parameters for input.
	public static void printCatChoices(HashMap<Integer, String> catPrint) {
		System.out.println("Please enter a number of a movie category you would like to see. Here are your options:");
		for (int j = 1; j < catPrint.size() + 1; j++) {
			System.out.printf("%-3d", j);
			System.out.printf("%-15s", catPrint.get(j));
			System.out.println();
		}
	}

}
