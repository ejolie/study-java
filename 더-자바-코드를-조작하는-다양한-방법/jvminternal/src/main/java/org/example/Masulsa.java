package org.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Masulsa {

    public static void main(String[] args) {
        pullRabbitUsingJavaAgent();
    }

    private static void pullRabbitUsingByteBuddy() {
        // 1. 바이트코드 조작 코드 실행: 코드를 컴파일 하듯이 기존의 클래스를 재정의함
        try {
            new ByteBuddy().redefine(Moja.class) // 여기서 이미 클래스 로딩 완료 (조작 전 상태)
                    .method(named("pullOut"))
                    .intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Users/eunjeong/Developer/GitHub/study-java/더-자바-코드를-조작하는-다양한-방법/jvminternal/target/classes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 기존 코드 실행
        // - 1번과 2번 코드는 동시에 실행 불가 X, 번갈아 주석 처리하고 실행해야 함
        // - Why? Masulsa를 실행하면 Masulsa.class와 Moja.class 파일이 로딩되고 실행되므로
        //   그 이후에 ByteBuddy로 클래스 파일을 조작해도 이미 로딩된 클래스 정보(@메소드 영역)를 보고 있으므로 적용 불가
//        System.out.println(new Moja().pullOut()); // Rabbit!
    }

    private static void pullRabbitUsingClassLoader() {
        ClassLoader classLoader = Masulsa.class.getClassLoader();
        TypePool typePool = TypePool.Default.of(classLoader);

        try {
            new ByteBuddy().redefine(typePool.describe("org.example.Moja").resolve(),
                    ClassFileLocator.ForClassLoader.of(classLoader))
                    .method(named("pullOut"))
                    .intercept(FixedValue.value("Rabbit!"))
                    .make().saveIn(new File("/Users/eunjeong/Developer/GitHub/study-java/더-자바-코드를-조작하는-다양한-방법/jvminternal/target/classes/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Moja를 여기 와서야 읽음
        System.out.println(new Moja().pullOut());
    }

    private static void pullRabbitUsingJavaAgent() {
        // java -javaagent:/Users/eunjeong/Developer/GitHub/study-java/더-자바-코드를-조작하는-다양한-방법/jvminternal
        System.out.println(new Moja().pullOut());
    }
}
