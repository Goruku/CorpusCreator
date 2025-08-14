package com.goruku.data.analysis.language.corpus.processor.domain.model.corpus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface CorpusEntity {
    int contexSize() default 10;
    String wordPrefix() default "w";
    String tableName() default "";
}
