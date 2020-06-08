package com.pk.batch.BatchDemo.configuration;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.pk.batch.BatchDemo.model.User;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<User> itemReader, ItemProcessor<User, User> itemPocessor, ItemWriter<User> itemWriter) {

		Step csvToDB = stepBuilderFactory.get("Csv-DB")
				.<User, User>chunk(100).reader(itemReader)
				.processor(itemPocessor)
				.writer(itemWriter)
				.build();
//if only one step is there use start, else use f flow
		
		Job job = jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(csvToDB).build();

		return job;

	}

	@Bean
	public FlatFileItemReader<User> itemReader() throws IOException {
		FlatFileItemReader<User> fileReader = new FlatFileItemReader<>();
		fileReader.setName("CSV-Reader");
		FileSystemResource fileSystemResource = new FileSystemResource("src/main/resources/user.csv");
		fileReader.setResource(fileSystemResource);
		fileReader.setLinesToSkip(2);
		fileReader.setStrict(false);
		fileReader.setLineMapper(getLineMapper());
		System.out.println(fileSystemResource.getInputStream().readAllBytes().toString());
		return fileReader;
	}

	@Bean
	public LineMapper<User> getLineMapper() {
		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setStrict(false);
		tokenizer.setNames(new String[] { "Id", "Name", "Department", "Salary" }); //Column Names in the CSV

		BeanWrapperFieldSetMapper<User> filedSetMapper = new BeanWrapperFieldSetMapper<>();
		filedSetMapper.setTargetType(User.class);

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(filedSetMapper);
		return lineMapper;
	}
}
