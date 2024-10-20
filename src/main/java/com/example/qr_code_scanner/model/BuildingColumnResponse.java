package com.example.qr_code_scanner.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BuildingColumnResponse {
    private Long id;
    private String name;
    private double height;
    private double diameter;
    private String material;
    private double loadCapacity;
    private String reinforcementType;
    private String location;
    private LocalDate constructionDate;
    private String status;
    private String comments;
    private String qrCode; // Add QR code field if needed
    private LocalDateTime createdDate; // Timestamp for when the record was created
    private LocalDateTime lastModifiedDate; // Timestamp for when the record was last modified

    // Constructors
    public BuildingColumnResponse() {
    }

    public BuildingColumnResponse(Long id, String name, double height, double diameter, String material,
                                  double loadCapacity, String reinforcementType, String location,
                                  LocalDate constructionDate, String status, String comments,
                                  String qrCode, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.diameter = diameter;
        this.material = material;
        this.loadCapacity = loadCapacity;
        this.reinforcementType = reinforcementType;
        this.location = location;
        this.constructionDate = constructionDate;
        this.status = status;
        this.comments = comments;
        this.qrCode = qrCode;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public BuildingColumnResponse(Long id, String name, double height, double diameter, String material, String qrCode) {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public String getReinforcementType() {
        return reinforcementType;
    }

    public void setReinforcementType(String reinforcementType) {
        this.reinforcementType = reinforcementType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
