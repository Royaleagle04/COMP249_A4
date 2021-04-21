package part2;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ProccessWishlist {
	
	public static void main(String[] args) {
		ShowList list1 = new ShowList();
		ShowList list2 = new ShowList();
		File TVGuide = new File("TVGuide.txt");
		
		try {
			System.out.println("test");
			Scanner in = new Scanner(TVGuide);
			while (in.hasNextLine()) {
				System.out.println("loop");
				String first =in.nextLine();
				String showID[]= first.split(" ");
				
				String second = in.nextLine();
				double startTime = Double.parseDouble(second.substring(2));
				
				String third = in.nextLine();
				double endTime = Double.parseDouble(third.substring(2));
				
				if(!list1.contains(showID[0]))//if does not contain (check for duplicates)
					list1.addToStart(new TVShow(showID[0],showID[1],startTime,endTime));
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
		System.out.println("outside while");
		for(int i =0;i<list1.getSize();i++) {
			System.out.println(list1.getHead());
			list1.setHead(list1.getHead().getPointer());
		}//end for
	}//end main method
}//end processWishlist class
