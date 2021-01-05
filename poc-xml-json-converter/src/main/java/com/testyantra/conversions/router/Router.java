package com.testyantra.conversions.router;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import org.json.JSONObject;
import org.json.XML;

/**
 * This is class is to convert the xml file to json file using the apache camel
 * dependency and json dependency
 * 
 * @author Jalaj kumar
 *
 */
@Component
public class Router extends RouteBuilder {

	/**
	 * 
	 * This is override method of to configure the start point and end point using
	 * "From" and "To" methods
	 */
	@Override
	public void configure() throws Exception {
		System.err.println("Runing RouteBuilder");
		from("file:D:/inputFolder").process(new Processor() {

			/**
			 * This is the actual converting the formates from xml to json and setting to "To" point 
			 * 
			 */
			@Override
			public void process(Exchange exchange) throws Exception {
				System.err.println("Runing processor");
				Message inmessage = exchange.getIn();
				String xmldata = inmessage.getBody(String.class);

				JSONObject xmlJSONObj = XML.toJSONObject(xmldata);
				Message outmessage = exchange.getMessage();
				outmessage.setBody(xmlJSONObj.toString());

			}
		}).to("file:D:/outputFolder?fileName=pom.json");
	}

}
