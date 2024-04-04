package com.example.faboncart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.faboncart.cache.UserProductClientCache;
import com.example.faboncart.clients.UserProductClient;
import com.example.faboncart.dao.FabonCartDao;
import com.example.faboncart.dto.FabonOrderProduct;
import com.example.faboncart.dto.FabonProduct;
import com.example.faboncart.dto.FabonUserCart;
import com.example.faboncart.dto.FabonUserCartForRedis;
import com.example.faboncart.dto.FallbackAttribute;
import com.example.faboncart.redis.dao.UserCartRedDao;
import com.example.faboncart.redis.dao.UserProductRedDao;
import com.example.faboncart.util.FabonCartUtil;

@Service
public class FabonCartService {

	private UserProductClient userProductClient;
	private UserProductClientCache userProductClientCache;
	private UserProductRedDao userProductRedDao;
	private UserCartRedDao userCartRedDao;

	@Autowired
	public FabonCartService(UserProductClient userProduct, UserProductClientCache userPrdClientCache,
			UserProductRedDao userPrdRedDao, UserCartRedDao userCartRDao) {
		userProductClient = userProduct;
		userProductClientCache = userPrdClientCache;
		userProductRedDao = userPrdRedDao;
		userCartRedDao = userCartRDao;
	}
	
	public static final Logger log = LoggerFactory.getLogger(FabonCartService.class);

	@Autowired
	FabonCartDao fabonCartDao;

	public void saveUserProductInCart(FabonUserCart userCartDetail) {
		fabonCartDao.save(userCartDetail);
		
		userCartRedDao.saveCart(userCartDetail);
	}

	public void deleteUserProductInCart(FabonUserCart userCartDetail) {
		fabonCartDao.deleteByCartIdAndCartProductId(userCartDetail.getCartId(), userCartDetail.getCartProductId());
	}

	public FabonUserCart getCartDetailsByCartId(String cartId, String cartProductId) {
		return fabonCartDao.findByCartIdAndCartProductId(cartId, cartProductId);
	}
	
	public List<FabonUserCart> getCartDetailsByCartId(String cartId) {
		return fabonCartDao.findByCartId(cartId);
	}

	public List<FabonOrderProduct> getOrderDetailsOfCart(List<FabonUserCart> cartDetails) {

		List<FabonOrderProduct> orderData = new ArrayList<>();

		cartDetails.forEach(cartDetail -> {
			FabonOrderProduct orderDet = new FabonOrderProduct();
			orderDet.setProductId(cartDetail.getCartProductId());
			orderDet.setQuantity(cartDetail.getQuantity());

			FabonProduct fabonProducts = getClientProductDetailsById(cartDetail.getCartProductId());
			//FabonProduct fabonProducts = userProductClient.getProductDetailsById(cartDetail.getCartProductId());
			orderDet.setProductDetail(fabonProducts);
			orderData.add(orderDet);

		});

		return orderData;
	}
	
	
    public FabonProduct getClientProductDetailsById(String productId) {
		FabonProduct prdDetailFromClient = userProductClient.getProductDetailsById(productId);
		System.out.println("data in prdDetailFromClient: " + prdDetailFromClient.toString());
		//save data in redis
		FabonProduct prdDetailFromRedis = userProductRedDao.saveProduct(prdDetailFromClient);
		System.out.println("data in prdDetailFromRedis: " + prdDetailFromRedis.toString());
		
        return prdDetailFromClient;
    }

	public List<FabonOrderProduct> getOrderDetailsOfCartFromCache(List<FabonUserCart> cartDetails) {
		List<FabonOrderProduct> orderData = new ArrayList<>();

		/*
		 * FallbackAttribute fallBackData = new
		 * FallbackAttribute("Product data from cache", "900"); FabonOrderProduct
		 * returnData = new
		 * FabonOrderProduct.Builder().fallbackAttribute(fallBackData).build();
		 * 
		 * orderData.add(returnData);
		 */

		cartDetails.forEach(cartDetail -> {
			FabonOrderProduct orderDet = new FabonOrderProduct();
			orderDet.setProductId(cartDetail.getCartProductId());
			orderDet.setQuantity(cartDetail.getQuantity());

			FabonProduct fabonProducts = userProductClientCache.callCachedProductDetailsById(cartDetail.getCartProductId());
			orderDet.setProductDetail(fabonProducts);

			FallbackAttribute fallBackData = new FallbackAttribute("Product data from cache", "900"); 
			//FabonOrderProduct returnData = new FabonOrderProduct.Builder().fallbackAttribute(fallBackData).build();

			orderDet.setFallbackAttribute(fallBackData);
			orderData.add(orderDet);

		});

		return orderData;
	}

	public void updateCartData(FabonUserCart userCartDetail) {
		//UUID orderId = FabonCartUtil.generateFabonOrderId();
		//userCartDetail.setOrderId(orderId.toString());
		
//		if(null != fabonCartDao.findByCartId(userCartDetail.getCartId())) {
//			List<FabonUserCart> cartList = fabonCartDao.findByCartId(userCartDetail.getCartId());
//			cartList.forEach(data -> {
//				data.set
//			});
//		}
		fabonCartDao.updateQuantityByCartId(userCartDetail.getQuantity(), userCartDetail.getCartId());
	}

}
