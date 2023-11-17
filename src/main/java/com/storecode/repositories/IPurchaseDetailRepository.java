package com.storecode.repositories;

import com.storecode.models.Purchase;
import com.storecode.models.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

    PurchaseDetail getByPurchase(Purchase purchase);
}
