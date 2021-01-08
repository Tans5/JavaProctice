package com.tans.annotationprocessor.processors;

import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import com.tans.annotationprocessor.annotations.*;

// @SupportedAnnotationTypes({"com.example.processors.SqlTable", "com.example.processors.SqlDao"})
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SqlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!annotations.isEmpty()) {
            // logs
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
                writer.append("RoundEnv Query: ").append(roundEnv.getElementsAnnotatedWith(Query.class).toString()).append("\n");

                writer.append("----------------End--------------").append("\n");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            Set<? extends Element> daoElements = roundEnv.getElementsAnnotatedWith(SqlDao.class);
            Set<? extends Element> queryElements = roundEnv.getElementsAnnotatedWith(Query.class);
            for (Element daoE: daoElements) {
                if (daoE.getKind().equals(ElementKind.INTERFACE)) {
                    TypeElement daoTE = (TypeElement) daoE;
                    List<ExecutableElement> queryEEs = new ArrayList<>();
                    for (Element queryE: queryElements) {
                        if (queryE.getKind().equals(ElementKind.METHOD)) {
                            ExecutableElement queryEE = (ExecutableElement) queryE;
                            if (queryEE.getEnclosingElement() == daoTE) {
                                queryEEs.add(queryEE);
                            }
                        }
                    }
                    generateDaoClass(daoTE, queryEEs);
                }
            }


            // Generate Code.
            File genCodeDir = new File("build/generated/sources/tans-processors/java/com/tans/processors/gen/");
            if (!genCodeDir.exists()) {
                if (!genCodeDir.mkdirs()) {
                    return false;
                }
            }
            File genJava = new File(genCodeDir, "GenTest.java");
            if (!genJava.exists()) {
                try {
                    if (!genJava.createNewFile()) {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            try {
                FileWriter genJavaWriter = new FileWriter(genJava);
                genJavaWriter.append("package com.tans.processors.gen; \n");
                genJavaWriter.append("public class GenTest { \n");
                genJavaWriter.append("public static String getGenMessage() { \n");
                genJavaWriter.append("return \"Hello, this is a GenCode..\"; \n");
                genJavaWriter.append("}\n");
                genJavaWriter.append("}\n");
                genJavaWriter.flush();
                genJavaWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
                genJava.delete();
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> result = new HashSet<>();
        result.add(SqlDao.class.getCanonicalName());
        return result;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    private void generateDaoClass(TypeElement daoTe, List<ExecutableElement> daoMethods) {
        File genCodeDir = new File("build/generated/sources/tans-processors/java/com/tans/processors/gen/");
        if (!genCodeDir.exists()) {
            if (!genCodeDir.mkdirs()) {
                return;
            }
        }
        String classSimpleName = daoTe.getSimpleName().toString() + "Impl";
        File genJava = new File(genCodeDir, classSimpleName + ".java");
        try {
            FileWriter genJavaWriter = new FileWriter(genJava);
            genJavaWriter.append("package com.tans.processors.gen; \n");
            genJavaWriter.append("public class GenTest { \n");
            genJavaWriter.append("public static String getGenMessage() { \n");
            genJavaWriter.append("return \"Hello, this is a GenCode..\"; \n");
            genJavaWriter.append("}\n");
            genJavaWriter.append("}\n");
            genJavaWriter.flush();
            genJavaWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            genJava.delete();
        }
    }
}
