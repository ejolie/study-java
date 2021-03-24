package org.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

// AutoService를 이용해 애노테이션 프로세서 등록
// cf. ServiceProvider 개념
@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // element: 소스코드의 구성요소 (예: 패키지, 클래스, 메소드)
    // round: 애노테이션 프로세싱이 round 별로 처리됨
    // 리턴 값 true: 이 애노테이션 타입의 처리가 끝난 상태. 다음 프로세서들한테 더 처리하라고 부탁하지 X
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        for (Element element : elements) {
            Name elementName = element.getSimpleName();
            if (element.getKind() != ElementKind.INTERFACE) { // 애노테이션을 interface 에만 적용하고 싶을 때
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotation cannot be used on " + elementName);
            } else {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + elementName);
            }

            TypeElement typeElement = (TypeElement) element;
            ClassName className = ClassName.get(typeElement);

            MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", "Rabbit!")
                    .build();

            TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(className) // ex: Moja (interface)
                    .addMethod(pullOut)
                    .build();

            Filer filer = processingEnv.getFiler();
            try {
                JavaFile.builder(className.packageName(), magicMoja)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Fatal Error: " + e);
            }
        }
        return true;
    }
}
