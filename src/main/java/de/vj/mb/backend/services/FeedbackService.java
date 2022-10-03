package de.vj.mb.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import de.vj.mb.backend.dto.Feedback;

@Component
public class FeedbackService {

	public List<Feedback> getFeedbackList(String meetingId) {
		List<Feedback> result = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			Feedback f = new Feedback();
			f.setMeetingId(meetingId);
			f.setUserId(UUID.randomUUID().toString());
			f.setRating(Double.valueOf(Math.random()).intValue());
			result.add(f);
		}
		return result;
	}

}
