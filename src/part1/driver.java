// -----------------------------------------------------
// Assignment 4
// COMP249
// Written by: Azal Al-Mashta, 40179492, Craig Kogan, 40175780
// Due Date 04/19/2021
// This program intakes a .txt file and parses through it, outputting three .txt files derived from the input. 
// -----------------------------------------------------

package part1;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Azal Al-Mashta - 40179492 & Craig Kogan - 40175780
 * <br> Class COMP249 Section QQ
 * <br> AssignemtnNb Assignment 4
 * <br> Due 04/19/2021
 * 
 * driver class
 * contains the main method. There are no submethods. 
 */
public class driver {

	//main must throw a FileNotFoundException in order to read in the file (line 82)
	/**
	 * main method. Contains all functions including the request for the txt file input as well as the printing of the three outputs. 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main (String[] args) throws FileNotFoundException {

		//variable initialization block - split by type
		boolean legalFil = false;
		boolean distinct = true;

		String txtTest;
		String filNam = "";
		String hold;
		String vowelTest;
		
		File temp;
		File obsessiveOFILE = new File("obsessive_o.txt");
		File vowelVerbiageFILE = new File("vowel_verbiage.txt");
		File distinctDataFILE = new File("distinct_data.txt");

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
		
		
		//printing of files
		PrintWriter OStreamO = new PrintWriter(obsessiveOFILE);
		for (int i = 0; i < obsessiveO.size(); i++) {
			if (i == 0)
				OStreamO.println("Word count: " + obsessiveO.size());
			OStreamO.println(obsessiveO.get(i));
		}
		OStreamO.close();
		
		
		PrintWriter OStreamV = new PrintWriter(vowelVerbiageFILE);
		for (int i = 0; i < vowelVerbiage.size(); i++) {
			if (i == 0)
				OStreamV.println("Word count: " + vowelVerbiage.size());
			OStreamV.println(vowelVerbiage.get(i));
		}
		OStreamV.close();
		
		
		PrintWriter OStreamD = new PrintWriter(distinctDataFILE);
		for (int i = 0; i < distinctData.size(); i++) {
			if (i == 0)
				OStreamD.println("Word count: " + distinctData.size());
			OStreamD.println(distinctData.get(i));
		}
		OStreamD.close();
		
		System.out.println("It's over!");
		
	}

}
