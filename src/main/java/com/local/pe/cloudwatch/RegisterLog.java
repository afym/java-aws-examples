package com.local.pe.cloudwatch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AWSLogsClient;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.PutLogEventsRequest;
import com.amazonaws.services.logs.model.PutLogEventsResult;
import com.local.pe.base.CredentialBuilder;
import com.amazonaws.services.logs.model.LogStream; 

public class RegisterLog {
	private static String LOG_GROUP = "demo1";
	private static String LOG_STREAM = "stream1";
	public static String LOG_TOKEN = null;

	public static void main(String[] args) throws ParseException {
		// building my credential and calendar instances
		AWSCredentials credential = CredentialBuilder.getCredential();
		Calendar calendar = Calendar.getInstance();
		// building a cloud watch log client
		AWSLogsClient cloudWatchlog = new AWSLogsClient(credential);
		cloudWatchlog.setRegion(Region.getRegion(Regions.US_WEST_1));
		// building a put request log
		DescribeLogStreamsRequest describer = new DescribeLogStreamsRequest(LOG_GROUP);
		PutLogEventsRequest request = new PutLogEventsRequest();
		request.setLogGroupName(LOG_GROUP);
		request.setLogStreamName(LOG_STREAM);

		List<LogStream> streams = cloudWatchlog.describeLogStreams(describer).getLogStreams();

		for (LogStream logStream: streams) {
	         if (logStream.getLogStreamName().equals(LOG_STREAM)) {
	        	 LOG_TOKEN = logStream.getUploadSequenceToken(); 
	         }
	     }  

		if (LOG_STREAM != null) {
			request.setSequenceToken(LOG_TOKEN);
		}

		// building my log event
		InputLogEvent log = new InputLogEvent();
		log.setMessage("INFO(test): Una prueba desde pokemon go!");
		log.setTimestamp(calendar.getTimeInMillis());
		// building the array list log event
		ArrayList<InputLogEvent> logEvents = new ArrayList<InputLogEvent>();
		logEvents.add(log);
		// setting the error array list
		request.setLogEvents(logEvents);
		// make the request
		PutLogEventsResult response = cloudWatchlog.putLogEvents(request);
		String token = response.getNextSequenceToken();
		System.out.println("done!");
	}
}