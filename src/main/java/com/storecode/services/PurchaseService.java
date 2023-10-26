package com.storecode.services;

import com.storecode.models.Purchase;
import com.storecode.repositories.IPurchaseRepository;
import org.springframework.stereotype.Service;
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
}
