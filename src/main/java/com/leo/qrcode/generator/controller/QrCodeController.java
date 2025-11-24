package com.leo.qrcode.generator.controller;

import com.google.zxing.WriterException;
import com.leo.qrcode.generator.dto.QrCodeGenerateRequest;
import com.leo.qrcode.generator.dto.QrCodeGenerateResponse;
import com.leo.qrcode.generator.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QrCodeController(QrCodeGeneratorService qrCodeGeneratorService){
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request) throws IOException, WriterException {
        try {
          QrCodeGenerateResponse response = this.qrCodeGeneratorService.generateAndUploadQrCode(request.text());
          return ResponseEntity.ok(response);
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
