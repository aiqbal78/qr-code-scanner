package com.example.qr_code_scanner.repository;

import com.example.qr_code_scanner.model.BuildingColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingColumnRepository extends JpaRepository<BuildingColumn, Long> {
    BuildingColumn findByQrCode(String qrCode);
}
