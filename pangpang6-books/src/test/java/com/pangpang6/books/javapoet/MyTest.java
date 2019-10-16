package com.pangpang6.books.javapoet;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Date;

public class MyTest {

    @Test
    public void test02() throws IOException {
        MethodSpec.Builder result = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addException(Exception.class);

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                //.addMethod(computeRange("multiply10to20", 10, 20, "*"))
                //.addMethod(whatsMyName("jiangjiguang"))
                .addMethod(result.build())
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();

        javaFile.writeTo(System.out);
    }


    @Test
    public void test01() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addStatement("long now = $T.currentTimeMillis()", System.class)
                .beginControlFlow("if ($T.currentTimeMillis() < now)", System.class)
                .addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
                .nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class)
                .addStatement("$T.out.println($S)", System.class, "Time stood still!")
                .nextControlFlow("else")
                .addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
                .endControlFlow()
                .build();
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(computeRange("multiply10to20", 10, 20, "*"))
                //.addMethod(whatsMyName("jiangjiguang"))
                //.addMethod(testT02())
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
                .build();
        StringBuilder sb = new StringBuilder();
        javaFile.writeTo(sb);
        System.out.println(sb);
    }

    private MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
                .addStatement("result = result $L i", op)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    private static MethodSpec whatsMyName(String name) {
        return MethodSpec.methodBuilder(name)
                .returns(String.class)
                .addStatement("return $S", name)
                .build();
    }

    private MethodSpec testT() {
        MethodSpec today = MethodSpec.methodBuilder("today")
                .returns(Date.class)
                .addStatement("return new $T()", Date.class)
                .build();
        return today;
    }

    private MethodSpec testT02() {
        ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");

        MethodSpec today = MethodSpec.methodBuilder("tomorrow")
                .returns(hoverboard)
                .addStatement("return new $T()", hoverboard)
                .build();
        return today;
    }
}
