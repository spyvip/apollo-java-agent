package com.spy.apollo.javaAgent.transformer;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-21 14:37
 * @since 1.0
 */
public class MyTransformer implements ClassFileTransformer {

    private static ClassPool classPool = ClassPool.getDefault();

    private static byte[] EMPTY_ARRAY = {};

    private static final String PROJECT_DEFAULT_PACKAGE = "com/spy";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className == null) {
            return EMPTY_ARRAY;
        }

        if (className.startsWith(PROJECT_DEFAULT_PACKAGE)) {
            System.out.println(className); //格式如下
            //com/spy/apollo/javaAgent/MyTest

            if (className.indexOf("/") != -1) {
                className = className.replaceAll("/", ".");
            }

            try {
                CtClass ctClass = classPool.get(className);

                CtMethod ctMethod = ctClass.getDeclaredMethod("test");

                ctMethod.insertBefore("{ System.out.println(\"test method begin\"); }");
                //TODO 不支持insertAfter中访问insertBefore的变量
                ctMethod.insertAfter("{ System.out.println(\"test method end\"); }");

                return ctClass.toBytecode();// 转成字节码

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }


        return classfileBuffer;
    }


}

