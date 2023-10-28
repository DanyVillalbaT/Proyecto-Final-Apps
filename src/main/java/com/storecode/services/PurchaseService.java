package com.storecode.services;

import com.storecode.models.Purchase;
import com.storecode.models.User;
import com.storecode.repositories.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    public Purchase getById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }

    public List<Purchase> findByUser(User user){
    	return purchaseRepository.findByUser(user);
    }
}
