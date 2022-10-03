package de.vj.mb.backend.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import de.vj.mb.backend.dto.Feedback;

@Component
public class ApiV1RestRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		this.restConfiguration().component("servlet").bindingMode(RestBindingMode.json)//
				.dataFormatProperty("prettyPrint", "true")//
				.contextPath("/").port(8080)//
				.apiContextPath("/api-doc")//
				.apiProperty("api.title", "Meeting Barometer API").apiProperty("api.version", "1")//
				.apiProperty("swagger.version", "2").apiProperty("cors", "true").enableCORS(true).corsAllowCredentials(true).corsHeaderProperty("Access-Control-Allow-Headers",
						"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization, Authorization-User");

		this.rest("/api/v1").description("Meeting Barometer Backend").consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE);

		this.rest("/api/v1/feedbacks/{meetingId}").get("/").description("List all Feedbacks for Meeting").outType(Feedback[].class) //
				.to("direct:listFeedbacksByMeetingId");

		this.rest("/api/v1/reports/{meetingId}").get("/").description("List all Feedbacks for Meeting").outType(Feedback[].class) //
				.to("direct:listFeedbacksByMeetingId");

		this.from("direct:listFeedbacksByMeetingId").to("bean:feedbackService?method=getFeedbackList(${headers.meetingId})");

	}
}
