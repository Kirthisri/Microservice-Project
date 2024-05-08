package com.example.fabonproducts.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.fabonproducts.dto.FabonProducts;

@Configuration
@EnableBatchProcessing
public class ProductBatchJob {
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

	@Bean
    public Job productBatchJob() {
        return jobBuilderFactory.get("productBatchJob")
            .start(productLoadingStep())
            .build();
    }

    @Bean
    public Step productLoadingStep() {
        return stepBuilderFactory.get("productLoadingStep")
            .<FabonProducts, FabonProducts>chunk(10)
            .reader(productLoadingReader())
            .processor(productLoadingProcessor())
            .writer(productLoadingWriter())
            .build();
    }

	private ItemReader<? extends FabonProducts> productLoadingReader() {
		// TODO Auto-generated method stub
		return null;
	}

	private ItemProcessor<? super FabonProducts, ? extends FabonProducts> productLoadingProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

    private ItemWriter<? super FabonProducts> productLoadingWriter() {
		// TODO Auto-generated method stub
		return null;
	}
}
