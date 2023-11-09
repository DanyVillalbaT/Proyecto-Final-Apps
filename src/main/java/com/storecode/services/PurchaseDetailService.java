package com.storecode.services;

import com.storecode.models.Purchase;
import com.storecode.models.PurchaseDetail;
import com.storecode.repositories.IPurchaseDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailService {

    @Autowired
    private IPurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetail getById(Long id) {
        return purchaseDetailRepository.findById(id).orElse(null);
    }

    public PurchaseDetail save(PurchaseDetail purchaseDetail) {return purchaseDetailRepository.save(purchaseDetail);}

    public PurchaseDetail getByPurchase(Purchase purchase){
        return purchaseDetailRepository.getByPurchase(purchase);
    }
}
