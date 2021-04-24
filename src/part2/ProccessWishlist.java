// -----------------------------------------------------
// Assignment 4
// COMP249
// Written by: Azal Al-Mashta, 40179492, Craig Kogan, 40175780
// Due Date 04/19/2021
// This program takes 2 files, a TVGuide.txt file and a Interest.txt file and determines whether the user can watch a certain show 
// or whether there is a conflicting timeline.
// -----------------------------------------------------
package part2;
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
 * Process Wishlist class, which is also the program driver.
 * contains the main method. There are no submethods. 
 */
public class ProccessWishlist {
	
	public static void main(String[] args) {
		ShowList list1 = new ShowList();
		ShowList list2 = new ShowList();
		ShowList list3 = new ShowList();
		ShowList list4 = new ShowList();
		String input;
		// end of a)
		System.out.println("Please enter the name of the TVGuide to use (full file name)");
		Scanner sysin= new Scanner(System.in);
		input= sysin.nextLine();
		File TVGuide = new File(input);
		try {
			Scanner in = new Scanner(TVGuide);
			while (in.hasNextLine()) {
				String first =in.nextLine();
				String showIDnName[]= first.split(" ");
				
				String second = in.nextLine();
				double startTime = Double.parseDouble(second.substring(2));
				
				String third = in.nextLine();
				double endTime = Double.parseDouble(third.substring(2));
				
				if(!list1.contains(showIDnName[0]))//if does not contain (check for duplicates)
					list1.addToStart(new TVShow(showIDnName[0],showIDnName[1],startTime,endTime));
				else
					System.out.println("Duplicate show");
				//skip empty line
				in.nextLine();
			}//end while
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}//end try-catch
		//end of b)
		System.out.println("Please enter the name of the Interest file to use (full file name)");
		input=sysin.nextLine();
		File interest = new File(input);
		ArrayList<String> watching = new ArrayList<String>();
		ArrayList<String> interests = new ArrayList<String>();
		try {
			Scanner in = new Scanner(interest);
			in.nextLine();
			String line=in.nextLine();
			while (!line.equals("Wishlist")) {
				if(!line.equals(""))
					watching.add(line); //adds interests to an ArrayList
				line=in.nextLine();
			}
			while(in.hasNextLine()) {
				//Skip first line
				line=in.nextLine();
				if(!line.equals(""))
					interests.add(line);
			}
			in.close();
			//System.out.println(watching);
			//System.out.println(interests);
		} catch (FileNotFoundException e) {
			System.out.println("error");
			e.printStackTrace();
		}//end try-catch
		TVShow wTVShow = null;
		ArrayList<TVShow> watchingShows = new ArrayList<TVShow>();
		for(String showID:watching) {	
			wTVShow = list1.find(showID).getTVShow();
			watchingShows.add(wTVShow);
			//System.out.println(watchingShows);
		}//end for(list1)
		TVShow iTVShow = null;
		ArrayList<TVShow> interestsShows = new ArrayList<TVShow>();
		for(String showID:interests) {
			iTVShow = list1.find(showID).getTVShow();
			interestsShows.add(iTVShow);
		}
		System.out.println("Watching: ");
		System.out.println(watchingShows);
		System.out.println("Interests: ");
		System.out.println(interestsShows);
		System.out.println();
		boolean canWatch;
		boolean isSameTime;
		for(TVShow TVShow:interestsShows) {
			canWatch=true;
			isSameTime=false;
			for(TVShow otherTVShow:watchingShows) {
				if(TVShow.isOnSameTime(otherTVShow).equals("Some overlap")) {
					canWatch=false;
					isSameTime=false;
				}//end if
				else if(TVShow.isOnSameTime(otherTVShow).equals("Same time")) {
					canWatch=false;
					isSameTime=true;
				}//end else if
			}//end for watchingShows
			if(canWatch)
				System.out.println("User can watch show " +TVShow.getShowID() +" as he/she is not watching anything else during that time");
			else if(!canWatch && isSameTime)
				System.out.println("User can't watch show " +TVShow.getShowID() +" as he/she will begin another show at the same time");
			else
				System.out.println("User can't watch show " +TVShow.getShowID() +" as he/she is not finished with a show he/she is watching");
			
		}//end for interests
		//end of c)
		System.out.println();
		boolean findShowID=true;
		String showIDToFind;
		int iterations;
		while(findShowID) {
			System.out.println("Please enter a show ID to find, press \"n\" to stop");
			showIDToFind=sysin.nextLine();
			if(showIDToFind.equals("n")) {
				findShowID=false;
				System.out.println("End of showID search");
			}
			else if(list1.contains(showIDToFind)) {
				iterations=0;
				for(iterations=0;iterations<list1.getSize();iterations++) {
					if(list1.traverse(iterations).getTVShow().getShowID().equals(showIDToFind)) {
						break;
					}
				}
				System.out.println("Found corresponding TVShow: " +list1.find(showIDToFind).getTVShow() +", for showID: " +showIDToFind +" after " +(iterations+1) +" iterations");
			}
			else {
				System.out.println("Inputted showID: " +showIDToFind +" does not exist withing the file: " +TVGuide);
			}
		}//end while findShowID
		sysin.close();
		
		// end of d)
		System.out.println("Testing methods:");
		System.out.println("Now cloning list of TVShows to another list");
		list1.clone(list2);
		System.out.println("List 1");
		System.out.println(list1);
		System.out.println("List 2 (clone of list 1, but reversed):");
		System.out.println(list2);
		list3.addToStart(new TVShow());
		list3.insertAtIndex(interestsShows.get(1), 1);
		System.out.println("List 3 (instert at index = size):");
		System.out.println(list3);
		list2.clone(list4);
		System.out.println("List 4 is equal to List 1:" +list1.equals(list4));
		System.out.println("List 4 is equal to List 2:" +list2.equals(list4));
		System.out.println("List 4 (delete at index=size-1 and index=0):");
		list4.deleteFromStart();
		list4.deleteFromIndex(list4.getSize()-1);
		System.out.println(list4);
		list4.replaceAtIndex(new TVShow(), 0);
		System.out.println("List 4 (Replace at index 0): ");
		System.out.println(list4);
		
		//end of e)
	}//end main method
}//end processWishlist class
