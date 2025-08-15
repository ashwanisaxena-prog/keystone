package ch.keystone;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Application {
	public static void main(String[] args) throws Exception {
		CamelContext camelContext = new DefaultCamelContext();

		// Add our Route
		camelContext.addRoutes(new CsvToSalesforceRoute());

		// Add Salesforce Connection Config
		camelContext.addComponent("salesforce", SalesforceConnection.getSalesforceComponent());

		camelContext.start();
		Thread.sleep(10000); // keep running for 10 sec (or longer if needed)
		camelContext.stop();
	}
}