package de.vj.mb.backend.dto;

import com.google.cloud.firestore.annotation.DocumentId;

public class Meeting {
	@DocumentId
	private String id;

	private String name;

	private int ratingOverallCount = 0;
	private int ratingInterestCount = 0;
	private int ratingUnderstandabilityCount = 0;
	private int ratingCostBenefitCount = 0;

	private int ratingOverallAvg = 0;
	private int ratingInterestAvg = 0;
	private int ratingUnderstandabilityAvg = 0;
	private int ratingCostBenefitAvg = 0;

	private int ratingOverall1 = 0;
	private int ratingOverall2 = 0;
	private int ratingOverall3 = 0;
	private int ratingOverall4 = 0;
	private int ratingOverall5 = 0;

	private int ratingInterest1 = 0;
	private int ratingInterest2 = 0;
	private int ratingInterest3 = 0;
	private int ratingInterest4 = 0;
	private int ratingInterest5 = 0;

	private int ratingUnderstandability1 = 0;
	private int ratingUnderstandability2 = 0;
	private int ratingUnderstandability3 = 0;
	private int ratingUnderstandability4 = 0;
	private int ratingUnderstandability5 = 0;

	private int ratingCostBenefit1 = 0;
	private int ratingCostBenefit2 = 0;
	private int ratingCostBenefit3 = 0;
	private int ratingCostBenefit4 = 0;
	private int ratingCostBenefit5 = 0;

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

	public int getRatingOverallCount() {
		return this.ratingOverallCount;
	}

	public void setRatingOverallCount(int ratingOverallCount) {
		this.ratingOverallCount = ratingOverallCount;
	}

	public int getRatingInterestCount() {
		return this.ratingInterestCount;
	}

	public void setRatingInterestCount(int ratingInterestCount) {
		this.ratingInterestCount = ratingInterestCount;
	}

	public int getRatingUnderstandabilityCount() {
		return this.ratingUnderstandabilityCount;
	}

	public void setRatingUnderstandabilityCount(int ratingUnderstandabilityCount) {
		this.ratingUnderstandabilityCount = ratingUnderstandabilityCount;
	}

	public int getRatingCostBenefitCount() {
		return this.ratingCostBenefitCount;
	}

	public void setRatingCostBenefitCount(int ratingCostBenefitCount) {
		this.ratingCostBenefitCount = ratingCostBenefitCount;
	}

	public int getRatingOverallAvg() {
		return this.ratingOverallAvg;
	}

	public void setRatingOverallAvg(int ratingOverallAvg) {
		this.ratingOverallAvg = ratingOverallAvg;
	}

	public int getRatingInterestAvg() {
		return this.ratingInterestAvg;
	}

	public void setRatingInterestAvg(int ratingInterestAvg) {
		this.ratingInterestAvg = ratingInterestAvg;
	}

	public int getRatingUnderstandabilityAvg() {
		return this.ratingUnderstandabilityAvg;
	}

	public void setRatingUnderstandabilityAvg(int ratingUnderstandabilityAvg) {
		this.ratingUnderstandabilityAvg = ratingUnderstandabilityAvg;
	}

	public int getRatingCostBenefitAvg() {
		return this.ratingCostBenefitAvg;
	}

	public void setRatingCostBenefitAvg(int ratingCostBenefitAvg) {
		this.ratingCostBenefitAvg = ratingCostBenefitAvg;
	}

	public int getRatingOverall1() {
		return this.ratingOverall1;
	}

	public void setRatingOverall1(int ratingOverall1) {
		this.ratingOverall1 = ratingOverall1;
	}

	public int getRatingOverall2() {
		return this.ratingOverall2;
	}

	public void setRatingOverall2(int ratingOverall2) {
		this.ratingOverall2 = ratingOverall2;
	}

	public int getRatingOverall3() {
		return this.ratingOverall3;
	}

	public void setRatingOverall3(int ratingOverall3) {
		this.ratingOverall3 = ratingOverall3;
	}

	public int getRatingOverall4() {
		return this.ratingOverall4;
	}

	public void setRatingOverall4(int ratingOverall4) {
		this.ratingOverall4 = ratingOverall4;
	}

	public int getRatingOverall5() {
		return this.ratingOverall5;
	}

	public void setRatingOverall5(int ratingOverall5) {
		this.ratingOverall5 = ratingOverall5;
	}

	public int getRatingInterest1() {
		return this.ratingInterest1;
	}

	public void setRatingInterest1(int ratingInterest1) {
		this.ratingInterest1 = ratingInterest1;
	}

	public int getRatingInterest2() {
		return this.ratingInterest2;
	}

	public void setRatingInterest2(int ratingInterest2) {
		this.ratingInterest2 = ratingInterest2;
	}

	public int getRatingInterest3() {
		return this.ratingInterest3;
	}

	public void setRatingInterest3(int ratingInterest3) {
		this.ratingInterest3 = ratingInterest3;
	}

	public int getRatingInterest4() {
		return this.ratingInterest4;
	}

	public void setRatingInterest4(int ratingInterest4) {
		this.ratingInterest4 = ratingInterest4;
	}

	public int getRatingInterest5() {
		return this.ratingInterest5;
	}

	public void setRatingInterest5(int ratingInterest5) {
		this.ratingInterest5 = ratingInterest5;
	}

	public int getRatingUnderstandability1() {
		return this.ratingUnderstandability1;
	}

	public void setRatingUnderstandability1(int ratingUnderstandability1) {
		this.ratingUnderstandability1 = ratingUnderstandability1;
	}

	public int getRatingUnderstandability2() {
		return this.ratingUnderstandability2;
	}

	public void setRatingUnderstandability2(int ratingUnderstandability2) {
		this.ratingUnderstandability2 = ratingUnderstandability2;
	}

	public int getRatingUnderstandability3() {
		return this.ratingUnderstandability3;
	}

	public void setRatingUnderstandability3(int ratingUnderstandability3) {
		this.ratingUnderstandability3 = ratingUnderstandability3;
	}

	public int getRatingUnderstandability4() {
		return this.ratingUnderstandability4;
	}

	public void setRatingUnderstandability4(int ratingUnderstandability4) {
		this.ratingUnderstandability4 = ratingUnderstandability4;
	}

	public int getRatingUnderstandability5() {
		return this.ratingUnderstandability5;
	}

	public void setRatingUnderstandability5(int ratingUnderstandability5) {
		this.ratingUnderstandability5 = ratingUnderstandability5;
	}

	public int getRatingCostBenefit1() {
		return this.ratingCostBenefit1;
	}

	public void setRatingCostBenefit1(int ratingCostBenefit1) {
		this.ratingCostBenefit1 = ratingCostBenefit1;
	}

	public int getRatingCostBenefit2() {
		return this.ratingCostBenefit2;
	}

	public void setRatingCostBenefit2(int ratingCostBenefit2) {
		this.ratingCostBenefit2 = ratingCostBenefit2;
	}

	public int getRatingCostBenefit3() {
		return this.ratingCostBenefit3;
	}

	public void setRatingCostBenefit3(int ratingCostBenefit3) {
		this.ratingCostBenefit3 = ratingCostBenefit3;
	}

	public int getRatingCostBenefit4() {
		return this.ratingCostBenefit4;
	}

	public void setRatingCostBenefit4(int ratingCostBenefit4) {
		this.ratingCostBenefit4 = ratingCostBenefit4;
	}

	public int getRatingCostBenefit5() {
		return this.ratingCostBenefit5;
	}

	public void setRatingCostBenefit5(int ratingCostBenefit5) {
		this.ratingCostBenefit5 = ratingCostBenefit5;
	}

}
