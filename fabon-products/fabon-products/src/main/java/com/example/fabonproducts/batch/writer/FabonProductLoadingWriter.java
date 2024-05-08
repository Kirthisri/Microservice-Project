package com.example.fabonproducts.batch.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.example.fabonproducts.dto.FabonProductCategory;
import com.example.fabonproducts.dto.FabonProductDivision;
import com.example.fabonproducts.dto.FabonProductReaderObject;
import com.example.fabonproducts.dto.FabonProducts;
import com.example.fabonproducts.service.FabonProductService;

@Component
public class FabonProductLoadingWriter implements ItemWriter<FabonProductReaderObject>{
	
	 @Autowired
	 private FabonProductService fabonProductService;
	 
	 @Autowired
	 @Qualifier("getExistingProductsInCache")
	 List<FabonProducts> productCache;
		
	 @Autowired
	 @Qualifier("getExistingCategoryInCache")
	 List<FabonProductCategory> categoryCache;
		
	 @Autowired
	 @Qualifier("getExistingDivisionInCache")
	 List<FabonProductDivision> divisionCache;

	
	@Override
	public void write(Chunk<? extends FabonProductReaderObject> chunk) throws Exception {
		
		System.out.print("chunk size: "  + chunk.size());
		
		List<FabonProducts> productList = new ArrayList<>();
		List<FabonProductCategory> categoryList = new ArrayList<>();
		List<FabonProductDivision> divisionList = new ArrayList<>();
		
		//Map<String, FabonProducts> productByProductId = productCache.stream().collect(Collectors.groupingBy(FabonProducts::getProductId));
		
		for(FabonProductReaderObject readProduct : chunk) {	
			
			FabonProducts p = productCache.stream()
	                   .filter(product -> product.getProductId().equals(readProduct.getProductId()))
	                   .findFirst()
	                   .orElse(new FabonProducts());
			FabonProductCategory c = categoryCache.stream()
	                   .filter(cat -> cat.getCategoryId().equals(readProduct.getProductCategoryId()))
	                   .findFirst()
	                   .orElse(new FabonProductCategory());
			FabonProductDivision d = divisionCache.stream()
	                   .filter(div -> div.getDivisionId().equals(readProduct.getProductDivisionId()))
	                   .findFirst()
	                   .orElse(new FabonProductDivision());
			
			//adding product details
			p.setProductId(readProduct.getProductId());
			p.setProductName(readProduct.getProductName());
			p.setProductCost(readProduct.getProductCost());
			p.setProductDiscount(readProduct.getProductDiscount());
			p.setProductPrice(readProduct.getProductPrice());
			p.setProductUnits(readProduct.getProductUnits());
			p.setProductSalePrice(readProduct.getProductSalePrice());
			p.setCategoryId(readProduct.getProductCategoryId());
			p.setDivisionId(readProduct.getProductDivisionId());
			
			productList.add(p);
			
			//adding category details
			c.setCategoryId(readProduct.getProductCategoryId());
			c.setCategoryName(readProduct.getProductCategoryName());
			
			categoryList.add(c);
			
			//adding division details
			d.setDivisionId(readProduct.getProductDivisionId());
			d.setDivisionName(readProduct.getProductDivisionName());
			d.setCategoryId(readProduct.getProductCategoryId());
			
			divisionList.add(d);
		}
		
		if(productList.size() > 0) {
			System.out.println("# in productList: "+productList.size());
			fabonProductService.saveAllProducts(productList);
		}
		if(divisionList.size() > 0) {
			System.out.println("# in divisionList: "+divisionList.size());
			fabonProductService.saveAllProductDivisions(divisionList);
		}
		if(categoryList.size() > 0) {
			System.out.println("# in categoryList: "+categoryList.size());
			fabonProductService.saveAllProductCategories(categoryList);
		}
	}

}
