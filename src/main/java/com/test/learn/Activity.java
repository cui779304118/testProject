package com.test.learn;

public class Activity {
	private int id;
	private String activityId;
	private String activityName;
	private long startTime;
	private long endTime;
	private long awardTime;
	private int awardType;
	private String remark;
	private int type;
	
	public String toString(){
		return "["+id+activityId+activityName+startTime+endTime+awardTime+awardType+remark+type+"]";
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getAwardTime() {
		return awardTime;
	}
	public void setAwardTime(long awardTime) {
		this.awardTime = awardTime;
	}
	public int getAwardType() {
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
