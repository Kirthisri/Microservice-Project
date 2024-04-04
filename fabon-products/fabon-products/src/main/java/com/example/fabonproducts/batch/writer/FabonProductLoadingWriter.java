package com.example.fabonproducts.batch.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
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

	
	@Override
	public void write(Chunk<? extends FabonProductReaderObject> chunk) throws Exception {
		
		System.out.print("chunk size: "  + chunk.size());
		
		List<FabonProducts> productList = new ArrayList<>();
		List<FabonProductCategory> categoryList = new ArrayList<>();
		List<FabonProductDivision> divisionList = new ArrayList<>();
		
		for(FabonProductReaderObject readProduct : chunk) {
			FabonProducts p = new FabonProducts();
			FabonProductCategory c = new FabonProductCategory();
			FabonProductDivision d = new FabonProductDivision();
			
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
