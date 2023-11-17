package com.storecode.repositories;

import com.storecode.models.ItemDetail;
import com.storecode.models.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemDetailRepository  extends JpaRepository<ItemDetail, Long> {

    List<ItemDetail> getByPurchaseDetail(PurchaseDetail purchaseDetail);
}
