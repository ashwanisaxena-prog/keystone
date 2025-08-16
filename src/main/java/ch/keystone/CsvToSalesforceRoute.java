package ch.keystone;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CsvToSalesforceRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

	    // Global error handler
	    onException(Exception.class)
	        .handled(true)
	        .process(exchange -> {
	            // Restore original CSV content before moving to failed folder
	            String originalCsv = exchange.getIn().getHeader("originalFileBody", String.class);
	            if (originalCsv != null) {
	                exchange.getIn().setBody(originalCsv);
	            }
	        })
	        .log("Error occurred: ${exception.message}")
	        .toD("file:data/failed?fileName=${file:name}");

	    from("file:data/input?fileName=images.csv&moveFailed=../failed")
	        .routeId("csv-to-salesforce")
	        // Save original CSV in header to restore later on success or failure
	        .process(exchange -> {
	            String originalCsv = exchange.getIn().getBody(String.class);
	            exchange.getIn().setHeader("originalFileBody", originalCsv);
	        })
	        .log("Received CSV file: ${file:name}")
	        .unmarshal().bindy(BindyType.Csv, ImageRecord.class)
	        // your existing processing logic here...
	        .process(exchange -> {
	            ImageRecord record = exchange.getIn().getBody(ImageRecord.class);
	            Map<String, Object> payload = new HashMap<>();
	            payload.put("Byline__c", record.getImageByline());
                payload.put("Headline__c", record.getImageHeadline());
                payload.put("Reference__c", record.getReference());
                payload.put("cur_Price__c", record.getPrice());
                if (record.getPurchaseDate() != null && !record.getPurchaseDate().isEmpty()) {
                    LocalDate localDate = LocalDate.parse(record.getPurchaseDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    payload.put("dt_DownloadDate__c", localDate.toString());
                }
                payload.put("pkl_BillingStatus__c", "Bezogen");
                payload.put("pkl_Delivery__c", "Bild");
                payload.put("pkl_LicenseType__c", record.getLicenceInformation());
                payload.put("pkl_SubscriptionID__c", record.getSubscriptionInformation());
                payload.put("recordTypeid", "012bW000000G9KIQA0");
                payload.put("txta_Caption__c", record.getImageCaption());
                payload.put("txt_Contact_UUID__c", record.getUserUuid());
                payload.put("txt_Customer_UUID__c", record.getCompanyUuid());
                payload.put("txt_ImageNumber__c", record.getImageOid());
                payload.put("txt_ImageTitle__c", record.getImageObjectName());
                payload.put("txt_LoginName__c", record.getUserLoginName());
                payload.put("txt_Partner_Photo_Code__c", record.getSourcePhotoCode());
                payload.put("txt_Price_Calculation_UUID__c", record.getConditionUuid());
                //payload.put("txt_UUID__c", record.getUuid());
	            exchange.getIn().setBody(payload);
	        })
	        .marshal().json()
	        .to("salesforce:createSObject?sObjectName=SingleUse__c&rawPayload=true")
	        .log("Uploaded record with response: ${body}")
	        // restore original CSV for success file move
	        .process(exchange -> {
	            String originalCsv = exchange.getIn().getHeader("originalFileBody", String.class);
	            exchange.getIn().setBody(originalCsv);
	        })
	        .toD("file:data/success?fileName=${file:name}");
	}

}