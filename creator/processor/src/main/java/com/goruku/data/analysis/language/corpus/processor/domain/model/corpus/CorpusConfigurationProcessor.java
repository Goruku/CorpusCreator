package com.goruku.data.analysis.language.corpus.processor.domain.model.corpus;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Set;

@SupportedAnnotationTypes("com.goruku.data.analysis.language.corpus.processor.domain.model.corpus.CorpusEntity")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
@AutoService(Processor.class)
public class CorpusConfigurationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(CorpusEntity.class);
        try {
            for (Element element : elements) {
                consumeCorpusEntityData(element);
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    private void consumeCorpusEntityData(Element element) throws IOException {
        System.out.println("WE ARE PROCESSING CUSTOM ANNOTATIONS");
        System.out.println(element);

        String elementString = element.toString();

        String packageName = null;

        int lastDot = elementString.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = elementString.substring(0, lastDot);
        }

        int contextSize = element.getAnnotation(CorpusEntity.class).contexSize();
        String wordPrefix = element.getAnnotation(CorpusEntity.class).wordPrefix();
        String tableName =  element.getAnnotation(CorpusEntity.class).tableName();
        int corpusWordCount = contextSize*2 + 1;

        String implName = element.getSimpleName()+"Impl";
        System.out.println(implName);

        JavaFileObject file = processingEnv.getFiler().createSourceFile(implName);

        try (PrintWriter out = new PrintWriter(file.openWriter())) {
            if (packageName != null) {
                out.println(String.format("package %s;", packageName) );
                out.println();
            }
            out.println("import jakarta.persistence.Entity;");
            out.println("import jakarta.persistence.Id;");
            out.println("import jakarta.persistence.Table;");
            out.println();
            out.println("@Entity");
            out.println(String.format("@Table%s", Objects.equals(tableName, "") ? "" : String.format("(name=\"%s\")", tableName)));
            out.println(String.format("public class %s implements %s {", implName, element.getSimpleName()));
            out.println("    @Id");
            out.println("    long id;");
            out.println("    long textId;");
            for (int columnIndex = 1; columnIndex <= corpusWordCount; columnIndex++) {
                out.println(String.format("    long %s;", wordPrefix+columnIndex));
            }
            out.println("}");
        }
    }
}
