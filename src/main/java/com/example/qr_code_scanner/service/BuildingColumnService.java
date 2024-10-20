package com.example.qr_code_scanner.service;

import com.example.qr_code_scanner.model.BuildingColumn;
import com.example.qr_code_scanner.model.BuildingColumnRequest;

import com.example.qr_code_scanner.repository.BuildingColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BuildingColumnService {
    @Autowired
    private BuildingColumnRepository buildingColumnRepository;
    @Autowired
    private QRCodeService qrCodeService;

    public BuildingColumn saveItem(BuildingColumnRequest buildingColumnRequest) {
        BuildingColumn buildingColumn = new BuildingColumn();
        buildingColumn.setName(buildingColumnRequest.getName());
        buildingColumn.setHeight(buildingColumnRequest.getHeight());
        buildingColumn.setDiameter(buildingColumnRequest.getDiameter());
        buildingColumn.setMaterial(buildingColumnRequest.getMaterial());
        buildingColumn.setLoadCapacity(buildingColumnRequest.getLoadCapacity());

        // Optionally set other fields if they are included in the request
        buildingColumn.setReinforcementType(buildingColumnRequest.getReinforcementType());
        buildingColumn.setLocation(buildingColumnRequest.getLocation());
        buildingColumn.setConstructionDate(buildingColumnRequest.getConstructionDate());
        buildingColumn.setStatus(buildingColumnRequest.getStatus());
        buildingColumn.setComments(buildingColumnRequest.getComments());
        buildingColumn.setQrCode("3");
        //String qrCode = qrCodeService.generateQRCode(buildingColumn); // Generate QR code with item ID
        //buildingColumn.setQrCode(qrCode);

        // Set created date and last modified date
        buildingColumn.setCreatedDate(LocalDateTime.now());
        buildingColumn.setLastModifiedDate(LocalDateTime.now());

        return buildingColumnRepository.save(buildingColumn);
    }

    public Optional<BuildingColumn> findBuildingColumnById(Long id) {
        return buildingColumnRepository.findById(id);
    }

    public BuildingColumn findByQRCode(String qrCode) {
        return buildingColumnRepository.findByQrCode(qrCode);
    }
}
