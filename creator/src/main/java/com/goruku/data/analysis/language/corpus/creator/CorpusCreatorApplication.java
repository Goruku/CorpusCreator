package com.goruku.data.analysis.language.corpus.creator;

import com.goruku.data.analysis.language.corpus.creator.domain.model.corpus.CorpusImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorpusCreatorApplication {

	public static void main(String[] args) {
		CorpusImpl corpusImpl = new CorpusImpl();
		SpringApplication.run(CorpusCreatorApplication.class, args);
	}

}
