package com.example.fabonproducts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fabonproducts.dto.FabonProducts;
import com.example.fabonproducts.service.FabonProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/products")
public class FabonProductController {

	public static final Logger log = LoggerFactory.getLogger(FabonProductController.class);

    @Autowired
    private JobLauncher jobLauncher;
    
	
	@Autowired
	@Qualifier("productBatchJob")
	Job productBatchJob;

	@Autowired
	FabonProductService fabonProductService;
	
	//later it is transferred to spring batch
	@PostMapping(value = "/addProducts")
	public void addProducts(@RequestBody List<FabonProducts> products) {
		fabonProductService.addProductToDB(products);

		// converting the request object to string
		/*
		 * String jsonUserProds = ""; try { jsonUserProds =
		 * mapper.writeValueAsString(products); } catch (JsonProcessingException e) { //
		 * Handle serialization error
		 * log.error("addProducts: API - /addProducts  Error: " + e); }
		 */

		//kafkaTemplate.send("PRODUCT-DETAIL", FabonUserProductStatusEnums.NEW_PRODUCT.toString(), jsonUserProds);
	}
	
	//At 01:00 on every 2nd day-of-week
	@Scheduled(cron="0 1 * * */2")
	@GetMapping(value = "/loadAllProducts")
	public String runProductBatchJob() {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();

			JobExecution jobExecution = jobLauncher.run(productBatchJob, jobParameters);
			
			fabonProductService.sendProductDetailsToKafka();
			fabonProductService.sendProductCategoryDetailsToKafka();
			fabonProductService.sendProductDivisionDetailsToKafka();

			return "Product Batch job started with ID: " + jobExecution.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error running product batch job";
		}
	}

}
