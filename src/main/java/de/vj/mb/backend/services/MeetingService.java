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
		int ratingCount = ratings.size();
		final Meeting meeting = this.getMeeting(rating.getMeetingId());
		meeting.setRating1(0);
		meeting.setRating2(0);
		meeting.setRating3(0);
		meeting.setRating4(0);
		meeting.setRating5(0);

		ratings.forEach(r -> {
			switch (r.getRating()) {
				case 1:
					meeting.setRating1(meeting.getRating1() + 1);
					break;
				case 2:
					meeting.setRating2(meeting.getRating2() + 1);
					break;
				case 3:
					meeting.setRating3(meeting.getRating3() + 1);
					break;
				case 4:
					meeting.setRating4(meeting.getRating4() + 1);
					break;
				case 5:
					meeting.setRating5(meeting.getRating5() + 1);
					break;
				default:
					break;
			}
		});

		Double ratingAvg = ratings.stream().mapToInt(r -> r.getRating() * 20).average().orElse(0);
		meeting.setRatingCount(ratingCount);
		meeting.setRatingAvg(ratingAvg.intValue());
		this.storeMeeting(meeting);
		return rating.getId();
	}
}
