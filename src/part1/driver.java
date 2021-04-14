package part1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class driver {

	//main must throw a FileNotFoundException in order to read in the file (line 55)
	public static void main (String[] args) throws FileNotFoundException {

		//variable initialization block - split by type
		boolean legalFil = false;
		boolean distinct = true;

		String txtTest;
		String filNam = "";
		String hold;
		String vowelTest;
		
		File temp;
		
		ArrayList<String> obsessiveO = new ArrayList<String>(0);
		ArrayList<String> vowelVerbiage = new ArrayList<String>(0);
		ArrayList<String> distinctData = new ArrayList<String>(0);
		
		int vowelCount = 0;

		Scanner userInput = new Scanner(System.in); //Scanner for all of the user inputs

		
		//C:\Users\craig\OneDrive\Documents\Comp249_W21_Assg4_Files\history_of_java.txt
		while (!legalFil) {
			System.out.println("Input the path of the input file, must be a .txt file:");
			filNam = userInput.nextLine();
			temp = new File(filNam);

			try (Scanner IStream = new Scanner(temp)) {
				txtTest = filNam.substring(filNam.length()-4,filNam.length());
				if(txtTest.compareTo(".txt") == 0)
					legalFil = true;
			}
			catch (FileNotFoundException e) {
				System.out.println("Invalid file input.");

			}


		} //end of WHILE (legalFil)
		userInput.close();
		
		Scanner IStream = new Scanner(new File(filNam));
		//since it is possible for an input file to have 0 elements, a WHILE is more appropriate than a DO-WHILE
		
		while(IStream.hasNext() == true) {
			
			//read in the next token, remove all non alpha-numerics
			hold = IStream.next();
			hold = hold.replaceAll("[^a-zA-Z0-9]", "");
			
			//run through the conditions to check the token belongs in any of the outputs/arraylists. 

			//obsessiveO
			try {
				if (hold.substring(0,1).compareToIgnoreCase("O") == 0) {
					obsessiveO.ensureCapacity(obsessiveO.size() + 1);
					obsessiveO.add(hold);
				}
			}
			catch (StringIndexOutOfBoundsException e) {

			}

			//vowelVerbiage
			for (int i = 0; i < hold.length(); i++) {
				vowelTest = hold.substring(i, i+1);
				//does Y count as a vowel??
				if (vowelTest.compareToIgnoreCase("a") == 0 || vowelTest.compareToIgnoreCase("e") == 0 || vowelTest.compareToIgnoreCase("i") == 0 || vowelTest.compareToIgnoreCase("o") == 0 || vowelTest.compareToIgnoreCase("u") == 0)
					vowelCount++;
				//A small optimization
				if (vowelCount == 4)
					break;
			}
			if (vowelCount > 3) {
				vowelVerbiage.ensureCapacity(vowelVerbiage.size() + 1);
				vowelVerbiage.add(hold);
			}
			vowelCount = 0;
			
			//distinctData
			//This should be changed to make it not take extremely long times with gib files
			for (int j = 0; j < distinctData.size(); j++) {
				if (hold.compareTo(distinctData.get(j)) == 0) {
					distinct  = false;
					break;
				}
			}
			if (distinct) {
				distinctData.ensureCapacity(distinctData.size() + 1);
				distinctData.add(hold);
			}
			distinct = true;

		} //end of WHILE - IStream.hasNext
		
		//just some debug helpers
		int x = 0;
		int y = 0;
		
		System.out.println("Obsessive O's, not to be confused with orange julep: " + obsessiveO.size());
		System.out.println("Vowel on like you ain't gonna stop: " + vowelVerbiage.size());
		System.out.println("Distinct, clean like the spring waters: " + distinctData.size());
		
		System.out.println("It's over!");
		
	}

}
