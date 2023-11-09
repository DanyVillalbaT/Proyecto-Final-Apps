package com.storecode.services;

import com.storecode.models.ItemDetail;
import com.storecode.models.PurchaseDetail;
import com.storecode.repositories.IItemDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemDetailService {

    @Autowired
    private IItemDetailRepository itemDetailRepository;

    public ItemDetail getById(Long id) {
        return itemDetailRepository.findById(id).orElse(null);
    }

    public ItemDetail save(ItemDetail itemDetail) {return itemDetailRepository.save(itemDetail);}

    public List<ItemDetail> getByPurchaseDetail(PurchaseDetail purchaseDetail){
        return itemDetailRepository.getByPurchaseDetail(purchaseDetail);
    }
}
