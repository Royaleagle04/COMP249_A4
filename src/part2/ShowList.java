// -----------------------------------------------------
// Assignment 4
// COMP249
// Written by: Azal Al-Mashta, 40179492, Craig Kogan, 40175780
// Due Date 04/19/2021
// This program takes 2 files, a TVGuide.txt file and a Interest.txt file and determines whether the user can watch a certain show 
// or whether there is a conflicting timeline.
// -----------------------------------------------------
package part2;
import java.util.NoSuchElementException;

/**
 * @author Azal Al-Mashta - 40179492 & Craig Kogan - 40175780
 * <br> Class COMP249 Section QQ
 * <br> AssignemtnNb Assignment 4
 * <br> Due 04/19/2021
 * 
 * Process Wishlist class, which is also the program driver.
 * contains the main method. There are no submethods. 
 */
public class ShowList{
	//attributes
	/**
	 * Internal ShowList class called ShowNode, implements Cloneable interface
	 *
	 */
	class ShowNode implements Cloneable{
		//attributes
		private TVShow TVShow;
		private ShowNode pointer;
		
		//constructors
		public ShowNode() {
			this.TVShow=null;
			this.pointer=null;
		}
		public ShowNode(TVShow TVShow, ShowNode pointer) {
			this.TVShow=TVShow;
			this.pointer=pointer;
		}
		public ShowNode(ShowNode otherShowNode) {
			this.TVShow=otherShowNode.TVShow;
			this.pointer=otherShowNode.pointer;
		}
		
		//methods
		public TVShow clone(String showID) throws CloneNotSupportedException {
	        return (TVShow) super.clone();
	    }
		
		//setters and getters
		public TVShow getTVShow() {
			return TVShow;
		}
		public void setTVShow(TVShow tVShow) {
			TVShow = tVShow;
		}
		public ShowNode getPointer() {
			return pointer;
		}
		public void setPointer(ShowNode pointer) {
			this.pointer = pointer;
		}
		
	}//end class ShowNode (inner)
	private ShowNode head;
	private int size;
	
	//constructors
	/**
	 * default constructor, creates an empty list of size 0
	 */
	public ShowList() {
		this.head=null;
		this.size=0;
	}
	//copy constructor
	/**
	 * Copy constructor, that creates a copy of a showList, however, because it is implemented using addToStart, the copy is reversed
	 * @param showList
	 * @see #addToStart(TVShow)
	 */
	public ShowList(ShowList showList) {
		ShowNode position=showList.head;
		while(position!=null) {
			this.addToStart(position.getTVShow());
			position=position.getPointer();
		}//end while
	}
	
	//methods
	/**
	 * addToStart creates a new ShowNode by using the passed TVShow, and adds it to the ShowList. Increments size.
	 * @param newTVShow, TVShow to add to the ShowList.
	 */
	public void addToStart(TVShow newTVShow) {
		head=new ShowNode(newTVShow,head);
		size++;
	}	
	/**
	 * Inserts a new TVShow at a specific index. 
	 * @param newTVShow
	 * @param index
	 * @throws NoSuchElementException() if the index is invalid
	 */
	public void insertAtIndex(TVShow newTVShow, int index) {
		try {
			if(index<0 || index>size)
				throw new NoSuchElementException("Index is invalid");
			ShowNode position=head;
			ShowNode newNode = new ShowNode();
			newNode.setTVShow(newTVShow);
			if(index==size) {
				for(int i=0;i<index-1;i++) {
					position=position.getPointer();
				}
				position.setPointer(newNode);
			}
			else {
			//traverse the list up to index
			for(int i=0;i<index;i++) {
				position=position.getPointer();
			}
			position.setPointer(newNode);
	
			if(index<size)//if newNode is not last in list
				newNode.setPointer(position.getPointer());
			}
			size++;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}//end of method insert at index
	/**
	 * Deletes a TVShow at a specific index. 
	 * @param index
	 * @throws NoSuchElementException() if the index is invalid, or if the list is empty or null
	 */
	public void deleteFromIndex(int index) {
		try {
			if(index<0 || index>=size || size==0 || this==null)
				throw new NoSuchElementException("Index is invalid");
			ShowNode position=head;
			ShowNode before = position;
			if(index==0) {
				this.deleteFromStart();
			}
			else {
				for(int i=0;i<index;i++) {
					before=position;
					position=position.getPointer();
				}
				before.setPointer(position.getPointer());
			}
			size--;
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}
	/**
	 * Deletes a TVShow at the start of the list
	 * @throws NoSuchElementException() if the list is empty or null
	 */
	public void deleteFromStart() {
		try {
			if(size==0 || this==null) {
				throw new NoSuchElementException();
			}
			else if(size==1) {
				head=null;
			size--;
			}
			else {
				head=head.getPointer();
				size--;
			}
		}catch(NoSuchElementException e) {
			e.printStackTrace();
			System.exit(1);;
		}
	}
	/**
	 * replaceAtIndex method that replaces a TVShow at an index with a new TVShow. If the index is invalid the replacement is cancelled.
	 * @param newTVShow
	 * @param index
	 */
	public void replaceAtIndex(TVShow newTVShow, int index) {
		if(index<0 || index>size-1 || this==null) {
			System.out.println("Invalid index, did not replace");
			return;
		}	
		else {
			ShowNode position=this.head;
			ShowNode newNode=new ShowNode(newTVShow, null);
			if(index==0) {
				newNode.setPointer(head.getPointer());
				this.head=newNode;
			}
			else {
				ShowNode before = position;
				for(int i=0;i<index;i++) {
					before=position;
					position=position.getPointer();
				}
				before.setPointer(newNode);
				newNode.setPointer(position.getPointer());
			}
		}
	}
	/**
	 * Finds a TVShow in the list by showID, and returns it. If it is not found, returns null.
	 * @param showID
	 * @return position
	 */
	public ShowNode find(String showID) {
		ShowNode position=head;
		while(position!=null) {
			if (showID.equals(position.TVShow.getShowID()))
				return position;
			else
				position=position.getPointer();
		}
		return null;
	}
	/**
	 * Method that checks if a list contains a TVShow by showID
	 * @param showID
	 * @return boolean, true if showID exists in list, else false
	 */
	public boolean contains(String showID) {
		return (find(showID) != null);
	}
	/**
	 * Method that checks if two ShowLists are equal
	 * @param otherShowList
	 * @return boolean true if two ShowLists are equal, else returns false
	 */
	public Boolean equals(ShowList otherShowList) {
		if(this.getClass() ==otherShowList.getClass() && otherShowList!=null && this.size==otherShowList.size) {
		ShowNode thisPosition=this.head;
		ShowNode otherPosition=otherShowList.head;
			while(thisPosition!=null){
				if(! thisPosition.getTVShow().equals(otherPosition.getTVShow())) 
					return false;
				thisPosition=thisPosition.getPointer();
				otherPosition=otherPosition.getPointer();
			}//end while
			return true;
		}
		return false;
	}//end equals method
	/**
	 * traverses the ShowList (obsolete)
	 */
	public void traverse() {
		ShowNode position = head;
		while(position!= null) {
		position = position.getPointer();
		}
	}
	/**
	 * traverses the ShowList  by a certain index (obsolete)
	 * @param index
	 * @return ShowNode position
	 */
	public ShowNode traverse(int index) {
		ShowNode position = head;
		for(int i =0;i<index;i++) {
		position = position.getPointer();
		}
		return position;
	}
	
	
	public ShowNode getHead() {
		return head;
	}
	public void setHead(ShowNode head) {
		this.head = head;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * clones a ShowList to a passed newShowList. However, because it is implemented using addToStart, the order is reversed.
	 * @param newShowList
	 * @see #addToStart(TVShow)
	 */
	public void clone(ShowList newShowList) {
		//empty the showList if it had nodes
		ShowNode position=this.head;
		while(position!=null) {
			newShowList.addToStart(position.getTVShow());
			position=position.getPointer();
		}
	}
	/**
	 * Converts a ShowList into a readable form and returns it as a String
	 *@return string showing the ShowList
	 */
	@Override
	public String toString() {
		ShowNode position=head;
		String output= "[";
		while(position!=null) {
			output+=position.getTVShow().toString() +" ";
			position=position.getPointer();
		}
		return output +"]";
	}
}//end class ShowList(outer)
