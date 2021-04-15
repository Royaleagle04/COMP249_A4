package part2;

public class TVShow implements Cloneable,Watchable{
	String showID;
	String showName;
	double startTime;
	double endTime;
	
	public TVShow() {
		this.showID="";
		this.showName="";
		this.startTime=-1;
		this.endTime=-1;

	}
	
	public TVShow(String showID,String showName, double startTime, double endTime) {
		this.showID=showID;
		this.showName=showName;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	public TVShow(TVShow otherShow, String showID) {
		this.showID=showID;
		this.showName=otherShow.showName;
		this.startTime=otherShow.startTime;
		this.endTime=otherShow.endTime;
	}
	
	 public TVShow clone(String showID) throws CloneNotSupportedException {
	        TVShow clonedObj = (TVShow) super.clone();
	        clonedObj.showID = showID;
	        return clonedObj;
	    }
	
	public String toString() {
		return ("ID: " +this.showID +", name: " +this.showName +", start: " +this.startTime +", end: " +this.endTime);
	}
	
	public boolean equals(TVShow otherTVShow) {
		if(this.getClass() ==otherTVShow.getClass() && otherTVShow!=null) {
			if(this.showName.equals(otherTVShow.showName) && this.startTime==otherTVShow.startTime && this.endTime==otherTVShow.endTime)
				return true;
		}
		return false;
	}
	
	public String isOnSameTime(TVShow S) {
		if(this.startTime==S.startTime && this.endTime==S.endTime)
			return "Same time";
		else if (this.startTime<S.endTime || this.endTime>S.startTime || S.startTime<this.endTime || S.endTime>this.startTime )
			return "Some overlap";
		else
			return "Different time";
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
