package com.example.merchantmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantRequest {
    private String shopName;
    private String phoneNumber;
    private String ownerName;
    private String registrationNumber;
    private String categoryName;
}
