package com.example.merchantmanagement.controller;

import com.example.merchantmanagement.dto.CategoryRequest;
import com.example.merchantmanagement.dto.MerchantRequest;
import com.example.merchantmanagement.dto.MerchantResponse;
import com.example.merchantmanagement.service.CategoryService;
import com.example.merchantmanagement.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    MerchantService merchantService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/addMerchants")
    public HttpStatus addMerchants(@RequestBody MerchantRequest merchantRequest) {
        try {
            return merchantService.saveMerchant(merchantRequest);
        } catch (Exception e) {
            log.error("Error occurred while adding merchant {}", e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("/addCategories")
    public HttpStatus addCategory(@RequestBody CategoryRequest categoryRequest) {
        try {
            return categoryService.saveCategory(categoryRequest);
        } catch (Exception e) {
            log.error("Error occurred while adding category {}", e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/getAllMerchants")
    public ResponseEntity<List<MerchantResponse>> getAllMerchants() {
        try {
            return merchantService.getAllMerchants();
        } catch (Exception e) {
            log.error("Error occurred while fetching merchants {}", e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException();
        }
    }

    @GetMapping("/getMerchant/{shopName}")
    public ResponseEntity<MerchantResponse> getMerchant(@PathVariable String shopName) {
        try {
            return merchantService.getMerchant(shopName);
        } catch (Exception e) {
            log.error("Error occurred while fetching merchant {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMerchant/{shopName}")
    public HttpStatus deleteMerchant(@PathVariable String shopName) {
        try {
            return merchantService.deleteMerchant(shopName);
        } catch (Exception e) {
            log.error("Error occurred while delete merchant {}", e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
