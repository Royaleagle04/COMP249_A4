// -----------------------------------------------------
// Assignment 4
// COMP249
// Written by: Azal Al-Mashta, 40179492, Craig Kogan, 40175780
// Due Date 04/19/2021
// This program takes 2 files, a TVGuide.txt file and a Interest.txt file and determines whether the user can watch a certain show 
// or whether there is a conflicting timeline.
// -----------------------------------------------------
package part2;

/**
 * @author Azal Al-Mashta - 40179492 & Craig Kogan - 40175780
 * <br> Class COMP249 Section QQ
 * <br> AssignemtnNb Assignment 4
 * <br> Due 04/19/2021
 * 
 * Watchable interface. 
 */
public interface Watchable {
	/**
	 * isOnSameTime method
	 * @param S
	 * @return
	 */
	public String isOnSameTime (TVShow S);
}
