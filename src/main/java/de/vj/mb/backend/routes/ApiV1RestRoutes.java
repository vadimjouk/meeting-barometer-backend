package de.vj.mb.backend.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import de.vj.mb.backend.dto.Meeting;
import de.vj.mb.backend.dto.Rating;

@Component
public class ApiV1RestRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		this.restConfiguration().component("servlet").bindingMode(RestBindingMode.json)//
				.dataFormatProperty("prettyPrint", "true")//
				.contextPath("/").port(8080)//
				.apiContextPath("/api-doc")//
				.apiProperty("api.title", "Meeting Barometer API").apiProperty("api.version", "1")//
				.apiProperty("swagger.version", "2").apiProperty("cors", "true").enableCORS(true).corsAllowCredentials(true)
				.corsHeaderProperty("Access-Control-Allow-Headers",
						"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, Authorization-User");

		this.rest("/api/v1").description("Meeting Barometer Backend").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE);

		this.rest("/api/v1/meetings/").get().description("List all Meetings").outType(Meeting[].class)
				.to("direct:listMeetings");

		this.rest("/api/v1/meetings/").post().description("Store Meeting").type(Meeting.class)
				.param().name("body").type(RestParamType.body).description("Meeting to update or create").endParam()
				.outType(String.class)
				.to("direct:storeMeeting");

		this.rest("/api/v1/meetings/{meetingId}").get().description("Get Meeting details").outType(Rating[].class)
				.param().name("meetingId").type(RestParamType.path).description("The id of the Meeting").dataType("String").endParam() //
				.to("direct:getMeetingById");

		this.rest("/api/v1/ratings").post("/").description("Update Rating").type(Rating.class).outType(String.class)
				.param().name("body").type(RestParamType.body).description("Rating to update or create").endParam()
				.to("direct:storeRating");

		this.rest("/api/v1/meetings/{meetingId}").get("/ratings").description("List all Ratings for Meeting").outType(Rating[].class)
				.param().name("meetingId").type(RestParamType.path).description("The id of the Meeting").dataType("String").endParam() //
				.to("direct:listRatingsByMeetingId");

		this.from("direct:listMeetings").to("bean:meetingService?method=getMeetings");
		this.from("direct:storeMeeting").to("bean:meetingService?method=storeMeeting(${body})");
		this.from("direct:getMeetingById").to("bean:meetingService?method=getMeeting(${headers.meetingId})");

		this.from("direct:listRatingsByMeetingId").to("bean:meetingService?method=getRatings(${headers.meetingId})");
		this.from("direct:storeRating").to("bean:meetingService?method=storeRating(${body})");

	}
}
