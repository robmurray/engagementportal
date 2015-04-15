package com.ys.eportal.util;

import com.ys.eportal.model.ImportOracleObi;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

// Configuration
public class SalesOrderImportConfiguration {
    // tag::readerwriterprocessor[]
    @Bean
    public ItemReader<ImportOracleObi> reader() {

        FlatFileItemReader<ImportOracleObi> reader = new FlatFileItemReader<ImportOracleObi>();

        // Todo pull from paramter
        reader.setResource(new ClassPathResource("sample-data.csv"));

        reader.setLineMapper(new DefaultLineMapper<ImportOracleObi>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{

                setNames(new String[]{"firstName", "lastName"});


            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ImportOracleObi>() {{
                setTargetType(ImportOracleObi.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<ImportOracleObi, ImportOracleObi> processor() {
        return new OracleOBISalesOrderProcessor();
    }

    @Bean
    public ItemWriter<ImportOracleObi> writer(DataSource dataSource) {

        JdbcBatchItemWriter<ImportOracleObi> writer = new JdbcBatchItemWriter<ImportOracleObi>();
        /*    writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ImportOracleObi>());
            writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
            writer.setDataSource(dataSource);
            */
        return writer;
    }


    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<ImportOracleObi> reader,
                      ItemWriter<ImportOracleObi> writer, ItemProcessor<ImportOracleObi, ImportOracleObi> processor) {
        return stepBuilderFactory.get("step1")
                .<ImportOracleObi, ImportOracleObi>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
