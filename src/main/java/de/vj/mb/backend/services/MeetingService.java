package de.vj.mb.backend.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import de.vj.mb.backend.dto.Meeting;
import de.vj.mb.backend.dto.Rating;
import de.vj.mb.backend.firestore.FirestoreService;

@Component
public class MeetingService {

	private static final String FS_MEETINGS = "meetings";
	private static final String FS_RATINGS = "ratings";

	public String storeMeeting(Meeting meeting) throws Exception {
		return FirestoreService.storeObject(FS_MEETINGS, meeting.getId(), meeting);
	}

	public Rating storeRating(Rating rating) throws Exception {
		Optional<Rating> result = FirestoreService.findObject(FS_RATINGS, "userId", rating.getUserId(), Rating.class);
		if (result.isPresent()) {
			rating.setId(result.get().getId());
		}
		FirestoreService.storeObject(FS_RATINGS, rating.getId(), rating);
		return rating;
	}

	public List<Meeting> getMeetings() throws Exception {
		return FirestoreService.findAll(FS_MEETINGS, 100, Meeting.class);
	}

	public Meeting getMeeting(String meetingId) throws Exception {
		Optional<Meeting> result = FirestoreService.findObject(FS_MEETINGS, meetingId, Meeting.class);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new NoSuchElementException();
		}
	}

	public List<Rating> getRatings(String meetingId) throws Exception {
		return FirestoreService.findObjects(FS_RATINGS, "meetingId", meetingId, Rating.class);
	}

	public String updateMeeting(Rating rating) throws Exception {
		List<Rating> ratings = this.getRatings(rating.getMeetingId());

		long ratingOverallCount = ratings.stream().filter(t -> t.getRatingOverall() > 0).count();
		long ratingInterestCount = ratings.stream().filter(t -> t.getRatingInterest() > 0).count();
		long ratingUnderstandabilityCount = ratings.stream().filter(t -> t.getRatingUnderstandability() > 0).count();
		long ratingCostBenefitCount = ratings.stream().filter(t -> t.getRatingCostBenefit() > 0).count();

		final Meeting meeting = this.getMeeting(rating.getMeetingId());

		this.updateOverallRating(ratings, ratingOverallCount, meeting, rating);
		this.updateInterestRating(ratings, ratingInterestCount, meeting, rating);
		this.updatUnderstandabilityRating(ratings, ratingUnderstandabilityCount, meeting, rating);
		this.updateCostBenefitRating(ratings, ratingCostBenefitCount, meeting, rating);

		this.storeMeeting(meeting);
		return rating.getId();
	}

	private void updateOverallRating(List<Rating> ratings, long ratingOverallCount, final Meeting meeting, final Rating rating) {
		meeting.setRatingOverall1(0);
		meeting.setRatingOverall2(0);
		meeting.setRatingOverall3(0);
		meeting.setRatingOverall4(0);
		meeting.setRatingOverall5(0);

		ratings.forEach(r -> {
			switch (r.getRatingOverall()) {
				case 1:
					meeting.setRatingOverall1(meeting.getRatingOverall1() + 1);

					break;
				case 2:
					meeting.setRatingOverall2(meeting.getRatingOverall2() + 1);
					break;
				case 3:
					meeting.setRatingOverall3(meeting.getRatingOverall3() + 1);
					break;
				case 4:
					meeting.setRatingOverall4(meeting.getRatingOverall4() + 1);
					break;
				case 5:
					meeting.setRatingOverall5(meeting.getRatingOverall5() + 1);
					break;
				default:
					break;
			}
		});

		Double ratingOverallAvg = ratings.stream().mapToInt(r -> r.getRatingOverall() * 20).average().orElse(0);
		meeting.setRatingOverallCount(Long.valueOf(ratingOverallCount).intValue());
		meeting.setRatingOverallAvg(ratingOverallAvg.intValue());
	}

	private void updateInterestRating(List<Rating> ratings, long ratingInterestCount, final Meeting meeting, final Rating rating) {
		meeting.setRatingInterest1(0);
		meeting.setRatingOverall2(0);
		meeting.setRatingInterest3(0);
		meeting.setRatingInterest4(0);
		meeting.setRatingInterest5(0);

		ratings.forEach(r -> {
			switch (r.getRatingInterest()) {
				case 1:
					meeting.setRatingInterest1(meeting.getRatingInterest1() + 1);
					break;
				case 2:
					meeting.setRatingInterest2(meeting.getRatingInterest2() + 1);
					break;
				case 3:
					meeting.setRatingInterest3(meeting.getRatingInterest3() + 1);
					break;
				case 4:
					meeting.setRatingInterest4(meeting.getRatingInterest4() + 1);
					break;
				case 5:
					meeting.setRatingInterest5(meeting.getRatingInterest5() + 1);
					break;
				default:
					break;
			}
		});

		Double ratingInterestAvg = ratings.stream().mapToInt(r -> r.getRatingInterest() * 20).average().orElse(0);
		meeting.setRatingInterestCount(Long.valueOf(ratingInterestCount).intValue());
		meeting.setRatingInterestAvg(ratingInterestAvg.intValue());
	}

	private void updatUnderstandabilityRating(List<Rating> ratings, long ratingUnderstandabilityCount, final Meeting meeting, final Rating rating) {
		meeting.setRatingUnderstandability1(0);
		meeting.setRatingUnderstandability2(0);
		meeting.setRatingUnderstandability3(0);
		meeting.setRatingUnderstandability4(0);
		meeting.setRatingUnderstandability5(0);

		ratings.forEach(r -> {
			switch (r.getRatingUnderstandability()) {
				case 1:
					meeting.setRatingUnderstandability1(meeting.getRatingUnderstandability1() + 1);
					break;
				case 2:
					meeting.setRatingUnderstandability2(meeting.getRatingUnderstandability2() + 1);
					break;
				case 3:
					meeting.setRatingUnderstandability3(meeting.getRatingUnderstandability3() + 1);
					break;
				case 4:
					meeting.setRatingUnderstandability4(meeting.getRatingUnderstandability4() + 1);
					break;
				case 5:
					meeting.setRatingUnderstandability5(meeting.getRatingUnderstandability5() + 1);
					break;
				default:
					break;
			}
		});

		Double ratingUnderstandabilityAvg = ratings.stream().mapToInt(r -> r.getRatingUnderstandability() * 20).average().orElse(0);
		meeting.setRatingUnderstandabilityCount(Long.valueOf(ratingUnderstandabilityCount).intValue());
		meeting.setRatingUnderstandabilityAvg(ratingUnderstandabilityAvg.intValue());
	}

	private void updateCostBenefitRating(List<Rating> ratings, long ratingCostBenefitCount, final Meeting meeting, final Rating rating) {
		meeting.setRatingCostBenefit1(0);
		meeting.setRatingCostBenefit2(0);
		meeting.setRatingCostBenefit3(0);
		meeting.setRatingCostBenefit4(0);
		meeting.setRatingCostBenefit5(0);

		ratings.forEach(r -> {
			switch (r.getRatingCostBenefit()) {
				case 1:
					meeting.setRatingCostBenefit1(meeting.getRatingCostBenefit1() + 1);
					break;
				case 2:
					meeting.setRatingCostBenefit2(meeting.getRatingCostBenefit2() + 1);
					break;
				case 3:
					meeting.setRatingCostBenefit3(meeting.getRatingCostBenefit3() + 1);
					break;
				case 4:
					meeting.setRatingCostBenefit4(meeting.getRatingCostBenefit4() + 1);
					break;
				case 5:
					meeting.setRatingCostBenefit5(meeting.getRatingCostBenefit5() + 1);
					break;
				default:
					break;
			}
		});

		Double ratingCostBenefitAvg = ratings.stream().mapToInt(r -> r.getRatingCostBenefit() * 20).average().orElse(0);
		meeting.setRatingCostBenefitCount(Long.valueOf(ratingCostBenefitCount).intValue());
		meeting.setRatingCostBenefitAvg(ratingCostBenefitAvg.intValue());
	}
}
