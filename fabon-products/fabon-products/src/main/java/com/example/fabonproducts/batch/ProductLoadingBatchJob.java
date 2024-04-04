package com.example.fabonproducts.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.fabonproducts.batch.processor.FabonProductLoadingProcessor;
import com.example.fabonproducts.batch.writer.FabonProductLoadingWriter;
import com.example.fabonproducts.dto.FabonProductReaderObject;

@Configuration
@EnableBatchProcessing
@ComponentScan({"com.example.fabonproducts.dao","com.example.fabonproducts.service","com.example.fabonproducts.batch.writer"})
public class ProductLoadingBatchJob {
	
	@Value("${product.file.source}")
	String productFileSource;
	
	@Value("${product.file.columns}")
	String productFileColumns;
	
	@Value("${product.file.delimiter}")
	String productFileDelimiter;
	    
    @Autowired
    JobRepository repo;
    
    @Autowired
    PlatformTransactionManager transactionManager;

	@Bean
    public Job productBatchJob() {
        return new JobBuilder("productBatchJob",repo)
            .start(productLoadingStep())
            .build();
    }

    @Bean
    public Step productLoadingStep() {
        return new StepBuilder("productLoadingStep", repo)
            .<FabonProductReaderObject, FabonProductReaderObject>chunk(10, transactionManager)
            .reader(productLoadingReader())
            .processor(productLoadingProcessor())
            .writer(productLoadingWriter())
            .build();
    }

	private FlatFileItemReader<? extends FabonProductReaderObject> productLoadingReader() {
		
		String[] columns = productFileColumns.split(",");
		
		FlatFileItemReader<FabonProductReaderObject> reader = new FlatFileItemReader<>();
	    reader.setResource(new FileSystemResource(productFileSource));
	    reader.setLineMapper(new DefaultLineMapper<FabonProductReaderObject>() {{
	        setLineTokenizer(new DelimitedLineTokenizer() {{
	            setNames(columns);
	            setDelimiter("|");
	            setStrict(false);
	        }});
	        setFieldSetMapper(new BeanWrapperFieldSetMapper<FabonProductReaderObject>() {{
	            setTargetType(FabonProductReaderObject.class);
	        }});
	    }});
		return reader;
	}

	@Bean
	@Lazy
	public FabonProductLoadingProcessor productLoadingProcessor() {
		return new FabonProductLoadingProcessor();
	}
	@Bean
	@Lazy
	public FabonProductLoadingWriter productLoadingWriter() {
		return new FabonProductLoadingWriter();
	}
}
