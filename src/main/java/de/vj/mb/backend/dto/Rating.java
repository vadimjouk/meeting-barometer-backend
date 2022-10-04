package de.vj.mb.backend.dto;

import com.google.cloud.firestore.annotation.DocumentId;

public class Rating {
	@DocumentId
	private String id;
	private String userId;
	private String meetingId;
	private int rating;

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

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
