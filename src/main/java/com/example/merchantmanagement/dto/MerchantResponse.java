package com.example.merchantmanagement.dto;

import com.example.merchantmanagement.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantResponse {
    private String shopName;
    private String phoneNumber;
    private String ownerName;
    private String registrationNumber;
    private String categoryName;

}
