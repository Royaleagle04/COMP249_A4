package part2;

import java.util.NoSuchElementException;

public class ShowList{
	class ShowNode implements Cloneable{
		private TVShow TVShow;
		private ShowNode pointer;
		
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
	
	public ShowList() {
		this.head=null;
		this.size=0;
	}
	
	//copy constructor
	public ShowList(ShowList otherShowList) {
		this.head=otherShowList.head;
		this.size=otherShowList.size;
	}
	
	public void addToStart(TVShow newTVShow) {
		head=new ShowNode(newTVShow,head);
		size++;
	}
	
	public void insertAtIndex(TVShow newTVShow, int index) {
		if(index<0 || index>size-1)
			throw new NoSuchElementException();
		else {
			ShowNode position=head;
			ShowNode newNode = new ShowNode();
			newNode.setTVShow(newTVShow);
			//traverse the list up to index
			for(int i=0;i<index;i++) {
				position=position.getPointer();
			}
			position.setPointer(newNode);
	
			if(index+1<size)//if newNode is not last in list
				newNode.setPointer(position.getPointer());
			size++;
		}
	}//end of method insert at index
	public void deleteFromIndex(int index) {
		if(index<0 || index>size-1)
			throw new NoSuchElementException();
		else {
			ShowNode position=head;
			ShowNode before = position;
			for(int i=0;i<index;i++) {
				before=position;
				position=position.getPointer();
			}
			before.setPointer(position.getPointer());
			size--;
		}
	}
	
	public void deleteFromStart() {
		if(size==0) {
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
	}
	
	public void replaceAtIndex(TVShow newTVShow, int index) {
		if(index<0 || index>size-1) {
			System.out.println("Invalid index, did not replace");
			return;
		}
			
		else {
			ShowNode position=head;
			ShowNode before = position;
			ShowNode newNode=new ShowNode(newTVShow, null);
			for(int i=0;i<index;i++) {
				before=position;
				position=position.getPointer();
			}
			before.setPointer(newNode);
			newNode.setPointer(position.getPointer());
		}
	}

	public ShowNode find(String showID) {
		ShowNode position=head;
		while(position!=null) {
			if (showID.equals(position.TVShow.getShowID()))
				return position;
		}
		return null;
	}
	
	public boolean contains(String showID) {
		return (find(showID) != null);
	}
	
	public boolean equals(ShowList otherShowList) {
		if(this.getClass() ==otherShowList.getClass() && otherShowList!=null && this.size==otherShowList.size) {
		ShowNode thisPosition=this.head;
		ShowNode otherPosition=otherShowList.head;
			while(thisPosition!=null){
				if(!thisPosition.getTVShow().equals(otherPosition.getTVShow())) 
					return false;
			}//end while
			return true;
		}
		return false;
	}
}//end class ShowList(outer)
