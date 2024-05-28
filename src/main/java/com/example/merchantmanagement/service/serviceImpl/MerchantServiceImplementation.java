package com.example.merchantmanagement.service.serviceImpl;

import com.example.merchantmanagement.dto.MerchantRequest;
import com.example.merchantmanagement.dto.MerchantResponse;
import com.example.merchantmanagement.entity.Category;
import com.example.merchantmanagement.entity.Merchant;
import com.example.merchantmanagement.repository.CategoryRepository;
import com.example.merchantmanagement.repository.MerchantRepository;
import com.example.merchantmanagement.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MerchantServiceImplementation implements MerchantService {
    private final CategoryRepository categoryRepository;
    private final MerchantRepository merchantRepository;

    public MerchantServiceImplementation(CategoryRepository categoryRepository, MerchantRepository merchantRepository) {
        this.categoryRepository = categoryRepository;
        this.merchantRepository = merchantRepository;
    }

    @Override
    public HttpStatus saveMerchant(MerchantRequest merchantRequest) {
        try{
            Merchant merchant = new Merchant();
            merchant.setOwnerName(merchant.getOwnerName());
            merchant.setShopName(merchant.getShopName());
            merchant.setPhoneNumber(merchant.getPhoneNumber());
            Category category=categoryRepository.findByCategoryName(merchantRequest.getCategoryName()).get();
            merchant.setCategory(category);
            merchant.setRegistrationNumber(UUID.randomUUID()+merchantRequest.getOwnerName());

            merchantRepository.save(merchant);
            return HttpStatus.CREATED;
        }catch (Exception e){

        }

    }

    @Override
    public ResponseEntity<MerchantResponse> getMerchant(String shopName) {
        try{
            Merchant merchant= merchantRepository.findByShopName(shopName).get();
        }catch (Exception e){

        }
    }

    @Override
    public List<ResponseEntity<MerchantResponse>> getAllMerchants() {
        try{

        }catch (Exception e){

        }
    }

    @Override
    public HttpStatus deleteMerchant(String shopName) {
        try{
            merchantRepository.deleteByShopName(shopName);
            return HttpStatus.ACCEPTED;
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
