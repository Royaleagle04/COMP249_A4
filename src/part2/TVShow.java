// -----------------------------------------------------
// Assignment 4
// COMP249
// Written by: Azal Al-Mashta, 40179492, Craig Kogan, 40175780
// Due Date 04/19/2021
// This program takes 2 files, a TVGuide.txt file and a Interest.txt file and determines whether the user can watch a certain show 
// or whether there is a conflicting timeline.
// -----------------------------------------------------
package part2;
import java.util.Scanner;

/**
 * @author Azal Al-Mashta - 40179492 & Craig Kogan - 40175780
 * <br> Class COMP249 Section QQ
 * <br> AssignemtnNb Assignment 4
 * <br> Due 04/19/2021
 * 
 * TVShow class, backbone of the program. Creates a TVShow object. implements Cloneable and Watchable.
 */
public class TVShow implements Cloneable,Watchable{
	String showID;
	String showName;
	double startTime;
	double endTime;
	
	/**
	 * Default constructor that creates a TVShow object
	 */
	public TVShow() {
		this.showID="";
		this.showName="";
		this.startTime=-1;
		this.endTime=-1;

	}
	
	/**
	 * Parameterized constructor that creates a TVShow object using the passed parameters
	 * @param showID
	 * @param showName
	 * @param startTime
	 * @param endTime
	 */
	public TVShow(String showID,String showName, double startTime, double endTime) {
		this.showID=showID;
		this.showName=showName;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	/**
	 * Copy constructor that copies another TVShow, except the unique showID identifier, which is constructed from the passed parameter.
	 * @param otherShow
	 * @param showID
	 */
	public TVShow(TVShow otherShow, String showID) {
		this.showID=showID;
		this.showName=otherShow.showName;
		this.startTime=otherShow.startTime;
		this.endTime=otherShow.endTime;
	}
	
	 /**
	  * 
	 * @param newTVShow
	 * @throws CloneNotSupportedException
	 */
	public void clone(TVShow newTVShow) throws CloneNotSupportedException {
			Scanner in = new Scanner(System.in);
	        System.out.println("Please enter a unique showID");
	        newTVShow.showID=in.nextLine();
	        in.close();
	        newTVShow.endTime=this.endTime;
	        newTVShow.startTime=this.startTime;
	        newTVShow.showName=this.showName;
	    }
	
	/**
	 * @return String that represents a TVShow
	 */
	public String toString() {
		return ("ID: " +this.showID +", name: " +this.showName +", start: " +this.startTime +", end: " +this.endTime);
	}
	
	/**
	 * Check if two TVShows are equal by comparing their attributes, except the unique showID
	 * @param otherTVShow
	 * @return
	 */
	public boolean equals(TVShow otherTVShow) {
		if(this.getClass() ==otherTVShow.getClass() && otherTVShow!=null) {
			if(this.showName.equals(otherTVShow.showName) && this.startTime==otherTVShow.startTime && this.endTime==otherTVShow.endTime)
				return true;
		}
		return false;
	}
	
	/**
	 * checks if two TVShows are on at the same time, and returns a corresponding string.
	 *@return String that shows if two TVShows are on at the same time
	 */
	public String isOnSameTime(TVShow S) {
		if(this.startTime==S.startTime && this.endTime==S.endTime)
			return "Same time";
		else if(S.endTime<=this.startTime || this.endTime<=S.startTime)
			return "Different time";
		else
			return "Some overlap";
	}
	
	//setters and getters
	public String getShowID() {
		return showID;
	}
	public void setShowID(String showID) {
		this.showID = showID;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public double getStartTime() {
		return startTime;
	}
	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

}//end class TVShow
