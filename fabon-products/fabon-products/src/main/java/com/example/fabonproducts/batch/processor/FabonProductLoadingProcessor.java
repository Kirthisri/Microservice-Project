package com.example.fabonproducts.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.fabonproducts.dto.FabonProductReaderObject;

@Component
public class FabonProductLoadingProcessor implements ItemProcessor<FabonProductReaderObject, FabonProductReaderObject>{

	@Override
	public FabonProductReaderObject process(FabonProductReaderObject readerProduct) throws Exception {
				
		return readerProduct;
	}

}
