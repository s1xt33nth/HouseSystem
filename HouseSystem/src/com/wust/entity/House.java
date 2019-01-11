package com.wust.entity;

import java.util.Date;

public class House {
	private int houseId;
	private int userId;
	private String houseTitle;
	private String houseType;
	private double houseFloorage;
	private double housePrice;
	private Date houseDate;
	private String district;
	private String street;
	private String contact;
	private String description;
	private String pubDate;
	private String filePath;

	public House(int houseId, int userId, String houseTitle, String houseType, double houseFloorage, double housePrice,
			Date houseDate, String district, String street, String contact, String description, String pubDate, String filePath) {
		super();
		this.houseId = houseId;
		this.userId = userId;
		this.houseTitle = houseTitle;
		this.houseType = houseType;
		this.houseFloorage = houseFloorage;
		this.housePrice = housePrice;
		this.houseDate = houseDate;
		this.district = district;
		this.street = street;
		this.contact = contact;
		this.description = description;
		this.pubDate = pubDate;
		this.filePath = filePath;
	}

	public House(int userId, String houseTitle, String houseType, double houseFloorage, double housePrice,
			Date houseDate, String district, String street, String contact, String description, String pubDate, String filePath) {
		super();
		this.userId = userId;
		this.houseTitle = houseTitle;
		this.houseType = houseType;
		this.houseFloorage = houseFloorage;
		this.housePrice = housePrice;
		this.houseDate = houseDate;
		this.district = district;
		this.street = street;
		this.contact = contact;
		this.description = description;
		this.pubDate = pubDate;
		this.filePath = filePath;
	}

	public House() {
	}

	public int getHouseId() {
		return houseId;
	}

	public void setHouseId(int houseId) {
		this.houseId = houseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public double getHouseFloorage() {
		return houseFloorage;
	}

	public void setHouseFloorage(double houseFloorage) {
		this.houseFloorage = houseFloorage;
	}

	public double getHousePrice() {
		return housePrice;
	}

	public void setHousePrice(double housePrice) {
		this.housePrice = housePrice;
	}

	public Date getHouseDate() {
		return houseDate;
	}

	public void setHouseDate(Date houseDate) {
		this.houseDate = houseDate;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
