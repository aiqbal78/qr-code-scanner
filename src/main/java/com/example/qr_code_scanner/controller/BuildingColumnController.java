package com.example.qr_code_scanner.controller;


import com.example.qr_code_scanner.model.BuildingColumn;
import com.example.qr_code_scanner.model.BuildingColumnRequest;

import com.example.qr_code_scanner.model.BuildingColumnResponse;
import com.example.qr_code_scanner.service.BuildingColumnService;

import com.example.qr_code_scanner.service.QRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/items")
public class BuildingColumnController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingColumnController.class);

    @Autowired
    private BuildingColumnService buildingColumnService;

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<BuildingColumnResponse> createBuildingColumn(@RequestBody BuildingColumnRequest buildingColumnRequest) {
        logger.info("Received request to create item: {}", buildingColumnRequest);

        // Validate required fields
        if (buildingColumnRequest.getName() == null || buildingColumnRequest.getName().isEmpty()) {
            logger.error("Name is required.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getHeight() <= 0) {
            logger.error("Height must be greater than zero.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getDiameter() <= 0) {
            logger.error("Diameter must be greater than zero.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getMaterial() == null || buildingColumnRequest.getMaterial().isEmpty()) {
            logger.error("Material is required.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getLoadCapacity() <= 0) {  // Assuming load capacity must be greater than zero
            logger.error("Load capacity must be greater than zero.");
            return ResponseEntity.badRequest().body(null);
        }

        // Optionally validate additional fields if they exist in ItemRequest
        if (buildingColumnRequest.getReinforcementType() != null && buildingColumnRequest.getReinforcementType().isEmpty()) {
            logger.error("Reinforcement type cannot be empty.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getLocation() != null && buildingColumnRequest.getLocation().isEmpty()) {
            logger.error("Location cannot be empty.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getConstructionDate() == null) {
            logger.error("Construction date is required.");
            return ResponseEntity.badRequest().body(null);
        }

        if (buildingColumnRequest.getStatus() == null || buildingColumnRequest.getStatus().isEmpty()) {
            logger.error("Status is required.");
            return ResponseEntity.badRequest().body(null);
        }

        // Validate comments if needed
        if (buildingColumnRequest.getComments() != null && buildingColumnRequest.getComments().length() > 500) { // Example validation
            logger.error("Comments cannot exceed 500 characters.");
            return ResponseEntity.badRequest().body(null);
        }

        try {
            // Perform item creation logic
            BuildingColumn savedItem = buildingColumnService.saveItem(buildingColumnRequest);
            logger.debug("Item saved successfully: {}", savedItem);

            // Generate QR code
            String qrCode = qrCodeService.generateQRCode(savedItem);
            logger.debug("QR code generated for item: {}", savedItem.getName());

            // Build response
            BuildingColumnResponse response = new BuildingColumnResponse(
                    savedItem.getId(),
                    savedItem.getName(),
                    savedItem.getHeight(),
                    savedItem.getDiameter(),
                    savedItem.getMaterial(),
                    savedItem.getLoadCapacity(),
                    savedItem.getReinforcementType(),
                    savedItem.getLocation(),
                    savedItem.getConstructionDate(),
                    savedItem.getStatus(),
                    savedItem.getComments(),
                    qrCode,
                    savedItem.getCreatedDate(),  // Assuming this is set in your item entity
                    savedItem.getLastModifiedDate()  // Assuming this is set in your item entity
            );

            logger.info("Item created successfully with ID: {}", savedItem.getId());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while creating item", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingColumnResponse> getBuildingColumnById(@PathVariable Long id) {
        logger.info("Received request to retrieve building column with ID: {}", id);
        Optional<BuildingColumn> buildingColumnOptional = buildingColumnService.findBuildingColumnById(id);

        if (buildingColumnOptional.isPresent()) {
            BuildingColumn buildingColumn = buildingColumnOptional.get();
            // Generate QR code if it hasn't been created yet
            String qrCode = buildingColumn.getQrCode() != null ? buildingColumn.getQrCode() : qrCodeService.generateQRCode(buildingColumn);

            // Create response object
            // Build response
            BuildingColumnResponse response = new BuildingColumnResponse(
                    buildingColumn.getId(),
                    buildingColumn.getName(),
                    buildingColumn.getHeight(),
                    buildingColumn.getDiameter(),
                    buildingColumn.getMaterial(),
                    buildingColumn.getLoadCapacity(),
                    buildingColumn.getReinforcementType(),
                    buildingColumn.getLocation(),
                    buildingColumn.getConstructionDate(),
                    buildingColumn.getStatus(),
                    buildingColumn.getComments(),
                    qrCode,
                    buildingColumn.getCreatedDate(),
                    buildingColumn.getLastModifiedDate()
            );
            logger.info("Building column retrieved successfully with ID: {}", id);
            return ResponseEntity.ok(response);
        } else {
            logger.warn("Building column not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<BuildingColumnResponse> getBuildingColumnByQRCode(@RequestParam String qrCode) {
        logger.info("Received request to search building column by QR code: {}", qrCode);
        try {
            BuildingColumn buildingColumn = buildingColumnService.findByQRCode(qrCode);
            if (buildingColumn == null) {
                logger.warn("No building column found for QR code: {}", qrCode);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // Generate QR code for the building column
            String generatedQRCode = qrCodeService.generateQRCode(buildingColumn);

            // Create response object
            BuildingColumnResponse response = new BuildingColumnResponse(
                    buildingColumn.getId(),
                    buildingColumn.getName(),
                    buildingColumn.getHeight(),
                    buildingColumn.getDiameter(),
                    buildingColumn.getMaterial(),
                    generatedQRCode
            );
            logger.info("Building column found with ID: {}", buildingColumn.getId());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error occurred while searching building column", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
