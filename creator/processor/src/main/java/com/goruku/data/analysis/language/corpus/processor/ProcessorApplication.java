package com.goruku.data.analysis.language.corpus.processor;

import com.goruku.data.analysis.language.corpus.processor.domain.model.corpus.CorpusConfigurationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

}
