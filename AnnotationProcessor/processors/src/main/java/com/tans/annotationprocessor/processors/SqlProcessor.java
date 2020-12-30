package com.tans.annotationprocessor.processors;

import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.tans.annotationprocessor.annotations.*;

// @SupportedAnnotationTypes({"com.example.processors.SqlTable", "com.example.processors.SqlDao"})
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SqlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        File file = new File("processors.log");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        FileWriter writer;
        try {
            writer = new FileWriter(file, true);
            writer.append("----------------Start------------\n");
            writer.append("Annotations: ").append(annotations.toString()).append("\n");
            writer.append("RoundEnv SqlDao: ").append(roundEnv.getElementsAnnotatedWith(SqlDao.class).toString()).append("\n");
            writer.append("RoundEnv SqlTable: ").append(roundEnv.getElementsAnnotatedWith(SqlTable.class).toString()).append("\n");
            writer.append("----------------End--------------").append("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add(SqlTable.class.getCanonicalName());
        result.add(SqlDao.class.getCanonicalName());
        return result;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
