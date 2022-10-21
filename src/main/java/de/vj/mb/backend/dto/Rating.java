package de.vj.mb.backend.dto;

import com.google.cloud.firestore.annotation.DocumentId;

public class Rating {
	@DocumentId
	private String id;
	private String userId;
	private String meetingId;
	private int ratingOverall;
	private int ratingInterest;
	private int ratingUnderstandability;
	private int ratingCostBenefit;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public int getRatingOverall() {
		return this.ratingOverall;
	}

	public void setRatingOverall(int ratingOverall) {
		this.ratingOverall = ratingOverall;
	}

	public int getRatingInterest() {
		return this.ratingInterest;
	}

	public void setRatingInterest(int ratingInterest) {
		this.ratingInterest = ratingInterest;
	}

	public int getRatingUnderstandability() {
		return this.ratingUnderstandability;
	}

	public void setRatingUnderstandability(int ratingUnderstandability) {
		this.ratingUnderstandability = ratingUnderstandability;
	}

	public int getRatingCostBenefit() {
		return this.ratingCostBenefit;
	}

	public void setRatingCostBenefit(int ratingCostBenefit) {
		this.ratingCostBenefit = ratingCostBenefit;
	}

}
