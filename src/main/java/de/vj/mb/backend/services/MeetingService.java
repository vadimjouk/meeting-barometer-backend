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

	public String storeMeeting(Meeting meeting) throws Exception {
		return FirestoreService.storeObject("meetings", meeting);
	}

	public String storeRating(Rating rating) throws Exception {
		return FirestoreService.storeObject("ratings", rating);
	}

	public List<Meeting> getMeetings() throws Exception {
		return FirestoreService.findAll("meetings", 100, Meeting.class);
	}

	public Meeting getMeeting(String meetingId) throws Exception {
		Optional<Meeting> result = FirestoreService.findObject("meetings", meetingId, Meeting.class);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new NoSuchElementException();
		}
	}

	public List<Rating> getRatings(String meetingId) throws Exception {
		return FirestoreService.findObjects("ratings", "meetingId", meetingId, Rating.class);
	}

}
