package ch.keystone;

import org.apache.camel.main.Main;

public class Application {
	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		System.out.println("application starting...");
		Main main = new Main();
		main.configure().addRoutesBuilder(new CsvToSalesforceRoute());
		main.bind("salesforce", SalesforceConnection.getSalesforceComponent());
		main.run(args);
		System.out.println("application shutdown");
	}
}