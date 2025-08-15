package ch.keystone;

import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.component.salesforce.SalesforceLoginConfig;
import org.apache.camel.support.jsse.KeyStoreParameters;

public class SalesforceConnection {

	public static SalesforceComponent getSalesforceComponent() {
		SalesforceComponent salesforce = new SalesforceComponent();

		SalesforceLoginConfig loginConfig = new SalesforceLoginConfig();
		loginConfig.setLoginUrl("https://test.salesforce.com");
		loginConfig
				.setClientId("3MVG9VwL6uEwP_uTvojRCpFFuAAWYfVXOQrHbjUepFugdba1XF0t_4yKNUEHwkLmByESJEcM4obBUvKWJjMFH");
		loginConfig.setUserName("sfintegration@keystone-sda.ch.partialsc");

		KeyStoreParameters ksp = new KeyStoreParameters();
		ksp.setResource("file:data/keystore.jks");
		ksp.setPassword("ai11"); // password for the keystore

		loginConfig.setKeystore(ksp);

		salesforce.setLoginConfig(loginConfig);

		return salesforce;
	}
}