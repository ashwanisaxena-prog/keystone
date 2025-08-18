package ch.keystone;

import org.apache.camel.component.salesforce.SalesforceComponent;
import org.apache.camel.component.salesforce.SalesforceLoginConfig;
import org.apache.camel.support.jsse.KeyStoreParameters;

public class SalesforceConnection {

	public static SalesforceComponent getSalesforceComponent() {
		SalesforceComponent salesforce = new SalesforceComponent();

		SalesforceLoginConfig loginConfig = new SalesforceLoginConfig();
		loginConfig.setLoginUrl(ConfigLoader.get("salesforce.loginUrl"));
		loginConfig.setClientId(ConfigLoader.get("salesforce.clientId"));
		loginConfig.setUserName(ConfigLoader.get("salesforce.username"));

		KeyStoreParameters ksp = new KeyStoreParameters();
		ksp.setResource("keystore.jks");
		ksp.setPassword(ConfigLoader.get("salesforce.keystorePassword")); // password for the keystore

		loginConfig.setKeystore(ksp);

		salesforce.setLoginConfig(loginConfig);

		return salesforce;
	}
}