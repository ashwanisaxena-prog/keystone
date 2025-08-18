package ch.keystone;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true)
public class ImageRecord {

	@DataField(pos = 1)
	private String reference;
	@DataField(pos = 2)
	private String price;
	@DataField(pos = 3)
	private String purchaseDate;
	@DataField(pos = 4)
	private String licenceInformation;
	@DataField(pos = 5)
	private String subscriptionInformation;
	@DataField(pos = 6)
	private String imageByline;
	@DataField(pos = 7)
	private String imageHeadline;
	@DataField(pos = 8)
	private String imageCaption;
	@DataField(pos = 9)
	private String imageOid;
	@DataField(pos = 10)
	private String imageObjectName;
	@DataField(pos = 11)
	private String userUuid;
	@DataField(pos = 12)
	private String userLoginName;
	@DataField(pos = 13)
	private String companyUuid;
	@DataField(pos = 14)
	private String sourcePhotoCode;
	@DataField(pos = 15)
	private String conditionUuid;

	// Getters
	public String getReference() {
		return reference;
	}

	public String getPrice() {
		return price;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public String getLicenceInformation() {
		return licenceInformation;
	}

	public String getSubscriptionInformation() {
		return subscriptionInformation;
	}

	public String getImageByline() {
		return imageByline;
	}

	public String getImageHeadline() {
		return imageHeadline;
	}

	public String getImageCaption() {
		return imageCaption;
	}

	public String getImageOid() {
		return imageOid;
	}

	public String getImageObjectName() {
		return imageObjectName;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public String getUserLoginName() {
		return userLoginName;
	}

	public String getCompanyUuid() {
		return companyUuid;
	}

	public String getSourcePhotoCode() {
		return sourcePhotoCode;
	}

	public String getConditionUuid() {
		return conditionUuid;
	}
}
