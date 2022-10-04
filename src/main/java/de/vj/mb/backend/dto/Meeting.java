package de.vj.mb.backend.dto;

import com.google.cloud.firestore.annotation.DocumentId;

public class Meeting {
	@DocumentId
	private String id;
	private String name;
	private int rating1;
	private int rating2;
	private int rating3;
	private int rating4;
	private int rating5;
	private int ratingCount;
	private int ratingAvg;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating1() {
		return this.rating1;
	}

	public void setRating1(int rating1) {
		this.rating1 = rating1;
	}

	public int getRating2() {
		return this.rating2;
	}

	public void setRating2(int rating2) {
		this.rating2 = rating2;
	}

	public int getRating3() {
		return this.rating3;
	}

	public void setRating3(int rating3) {
		this.rating3 = rating3;
	}

	public int getRating4() {
		return this.rating4;
	}

	public void setRating4(int rating4) {
		this.rating4 = rating4;
	}

	public int getRating5() {
		return this.rating5;
	}

	public void setRating5(int rating5) {
		this.rating5 = rating5;
	}

	public int getRatingCount() {
		return this.ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getRatingAvg() {
		return this.ratingAvg;
	}

	public void setRatingAvg(int ratingAvg) {
		this.ratingAvg = ratingAvg;
	}
}
