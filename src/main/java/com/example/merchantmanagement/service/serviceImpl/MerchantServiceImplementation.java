package com.example.merchantmanagement.service.serviceImpl;

import com.example.merchantmanagement.dto.MerchantRequest;
import com.example.merchantmanagement.dto.MerchantResponse;
import com.example.merchantmanagement.entity.Category;
import com.example.merchantmanagement.entity.Merchant;
import com.example.merchantmanagement.repository.CategoryRepository;
import com.example.merchantmanagement.repository.MerchantRepository;
import com.example.merchantmanagement.service.MerchantService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MerchantServiceImplementation implements MerchantService {
    private final CategoryRepository categoryRepository;
    private final MerchantRepository merchantRepository;

    public MerchantServiceImplementation(CategoryRepository categoryRepository, MerchantRepository merchantRepository) {
        this.categoryRepository = categoryRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public HttpStatus saveMerchant(MerchantRequest merchantRequest) {
        try {
            Merchant merchant = new Merchant();
            merchant.setOwnerName(merchantRequest.getOwnerName());
            merchant.setShopName(merchantRequest.getShopName());
            merchant.setPhoneNumber(merchantRequest.getPhoneNumber());
            Category category = categoryRepository.findByCategoryName(merchantRequest.getCategoryName()).get();
            merchant.setCategory(category);
            merchant.setRegistrationNumber(UUID.randomUUID() + merchantRequest.getOwnerName());

            merchantRepository.save(merchant);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public ResponseEntity<MerchantResponse> getMerchant(String shopName) {
        try {
            Merchant merchant = merchantRepository.findByShopName(shopName).get();
            MerchantResponse merchantResponse= new MerchantResponse();
            merchantResponse.setOwnerName(merchant.getOwnerName());
            merchantResponse.setPhoneNumber(merchant.getPhoneNumber());
            merchantResponse.setRegistrationNumber(merchant.getRegistrationNumber());
            merchantResponse.setCategoryName(merchant.getCategory().getCategoryName());
            merchantResponse.setShopName(merchant.getShopName());
            return new ResponseEntity<>(merchantResponse,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<MerchantResponse>> getAllMerchants() {
        try {
            List<Merchant> merchants=merchantRepository.findAll();
            return toMerchantListDTO(merchants);
        } catch (Exception e) {
        throw new RuntimeException();
        }
    }

    private ResponseEntity<List<MerchantResponse>> toMerchantListDTO(List<Merchant> merchants) {
        List<MerchantResponse> merchantList=new ArrayList<>();
        for (Merchant merchant:merchants){
            MerchantResponse merchantResponse= new MerchantResponse();
            merchantResponse.setOwnerName(merchant.getOwnerName());
            merchantResponse.setPhoneNumber(merchant.getPhoneNumber());
            merchantResponse.setRegistrationNumber(merchant.getRegistrationNumber());
            merchantResponse.setCategoryName(merchant.getCategory().getCategoryName());
            merchantResponse.setShopName(merchant.getShopName());
            merchantList.add(merchantResponse);
        }
        return new ResponseEntity<>(merchantList,HttpStatus.OK);
    }

    @Override
    @Transactional
    public HttpStatus deleteMerchant(String shopName) {
        try {
            merchantRepository.deleteByShopName(shopName);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            log.error("Error deleting merchant with shop name {}: {}", shopName, e.getMessage());
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
