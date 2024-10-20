package com.example.qr_code_scanner.service;
import com.example.qr_code_scanner.model.BuildingColumn;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRCodeService {
    public String generateQRCode(BuildingColumn buildingColumn) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            // Generate QR code with relevant building column details
            String qrData = String.format("Building Column ID: %d\nName: %s\nHeight: %.2f\nDiameter: %.2f\nMaterial: %s",
                    buildingColumn.getId(), buildingColumn.getName(), buildingColumn.getHeight(),
                    buildingColumn.getDiameter(), buildingColumn.getMaterial());

            BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 200, 200);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR Code", e);
        }
    }
}
