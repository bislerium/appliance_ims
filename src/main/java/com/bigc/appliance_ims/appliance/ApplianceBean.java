/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigc.appliance_ims.appliance;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author bisha
 */
public class ApplianceBean implements Serializable{

    private static final long serialVersionUID = -427890823421L;
    
    private int applianceID;              // Appliance ID is set automatically based on appliancesData list
    private String applianceName;               // Name of the Appliance
    private String category;              // appliance category. Cannot be changed once done  
    private String subCategory;           // appliance sub-category. Cannot be changed once done 
    private String modelNumber;                 // Model number of the appliance item
    private String weight;                      // Weight of the Appliance Item only, Attachments or optional items came within the box is excluded
    private String brand;                       // Brand or manufacturer of the Product    
    private String service;                     // Services like Guarantee/waranty/repairibilty & time period
    private double price;                       // price of the appliance item
    private boolean availability;               // Avaibility of the appliance item for customer
    private int stocks;                         // Total stock of the appliance item
    private String applianceSKU;                // Stock Keeping Unit number generated automatically & assigned to product 
    private boolean discontinued;               // Whether the Manufacturer has stoped manufacturing the Appliance item
    private Timestamp regDateTime;                    // The date & time of appliance got stock in the Inventory.
    private String description;                 // Detail Discription or Specification of Appliance
    private String comment;                     // Comment (optional) works as remainder or notes for a given appliance 
    private int addedBy;

    public int getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(int applianceID) {
        this.applianceID = applianceID;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getApplianceSKU() {
        return applianceSKU;
    }

    public void setApplianceSKU(String applianceSKU) {
        this.applianceSKU = applianceSKU;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public Timestamp getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(Timestamp regDateTime) {
        this.regDateTime = regDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(int addedBy) {
        this.addedBy = addedBy;
    }
    
    public Object[] toObjectArray() {                    //forJTable;
        return new Object[] {this.applianceID, this.applianceName, this.category, this.subCategory,
            this.modelNumber, this.weight, this.brand, this.service, this.price, this.stocks,
            this.availability, this.applianceSKU, this.discontinued, this.regDateTime};
    }

    @Override
    public String toString() {
        return "ApplianceBean{" + "applianceID=" + applianceID + ", applianceName=" 
                + applianceName + ", category=" + category + ", subCategory=" 
                + subCategory + ", modelNumber=" + modelNumber + ", weight=" 
                + weight + ", brand=" + brand + ", service=" + service + ", price=" 
                + price + ", availability=" + availability + ", stocks=" + stocks 
                + ", applianceSKU=" + applianceSKU + ", discontinued=" + discontinued 
                + ", dateTime=" + regDateTime + ", description=" + description 
                + ", comment=" + comment + '}';
    }    
}
