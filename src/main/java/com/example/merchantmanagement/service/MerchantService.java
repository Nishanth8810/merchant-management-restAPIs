package com.example.merchantmanagement.service;

import com.example.merchantmanagement.dto.MerchantRequest;
import com.example.merchantmanagement.dto.MerchantResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MerchantService {
    HttpStatus saveMerchant(MerchantRequest merchantRequest);

    ResponseEntity<MerchantResponse> getMerchant(String shopName);

    List<ResponseEntity<MerchantResponse>> getAllMerchants();

    HttpStatus deleteMerchant(String merchantName);
}
