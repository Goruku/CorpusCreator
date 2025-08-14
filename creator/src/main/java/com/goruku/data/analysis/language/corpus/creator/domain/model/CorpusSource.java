package com.goruku.data.analysis.language.corpus.creator.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

@Entity
@Getter
@Setter
public class CorpusSource {

    @Id
    private Long id;

    @Column
    private String path;

    @Column
    private String encoding;


    public String getText() throws FileNotFoundException {
        try {
            return Files.readString(Path.of(path), Charset.forName(encoding));
        } catch (IOException e) {
            throw new FileNotFoundException(
                    String.format("Source %s exists in the dataset, but its associated source file %s is missing from your storage medium",
                            id, path));
        }
    }
}
