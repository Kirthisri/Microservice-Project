package com.example.fabonreader.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import com.example.fabonreader.dto.FabonUserCartDetails;

@Repository
public interface FabonUserCartDetailDao extends MongoRepository<FabonUserCartDetails, Long>{

	void deleteByEmailId(String emailId);
	
	FabonUserCartDetails findByEmailId(String emailId);
	
	@Query("{'emailId':?0, 'orderPlaced':true}")
	FabonUserCartDetails findByEmailIdAndOrderPlaced(String emailId);

	@Query("{'orderId':?0, 'emailId':?1}")
	void updateOrderIdbyEmailId(String orderId, String emailId);
	
    //@Modifying
    @Query("{emailId: ?0}")
    void updateCartProductIdsByEmailId(String emailId, List<String> newCartProductIds);
    
    @Query("{emailId: ?0}")
    void updateOrderPlacedByEmailId(String emailId, boolean orderPlaced);

    @Aggregation("{$match:{emailId:?0}},"
            + "  {$unwind: \"$cartProductIds\"},"
            + "  {$lookup: {"
            + "    from: 'Products',"
            + "    localField: 'cartProductIds',"
            + "    foreignField: 'productId',"
            + "    as: 'ProductDetail'"
            + "  }},"
            + "  {$unwind: \"$ProductDetail\"},"
            + "  {$group: {"
            + "    _id: null,"
            + "    emailId: { $first: '$emailId' },"
            + "    cartProductIds: { $push: '$cartProductIds' },"
            + "    orderPlaced: { $first: '$orderPlaced' },"
            + "    proceedPayment: { $first: '$proceedPayment' },"
            + "    orderId: { $first: '$orderId' },"
            + "    totalAmountPayable: {"
            + "      $sum: { $toDouble: '$ProductDetail.productPrice' }"
            + "    }"
            + "  }},"
            + "  {$project: {"
            + "    _id: 0"
            + "  }}")
    FabonUserCartDetails calculateTotalAmountPayableByEmailId(String emailId);


}
