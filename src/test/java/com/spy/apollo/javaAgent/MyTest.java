package com.spy.apollo.javaAgent;

import org.junit.Test;

/**
 * 模块名
 * vm options:-javaagent:D:/gitlab/codi-product/apollo-java-agent/target/apollo-java-agent.jar
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-21 14:22
 * @since 1.0
 */
public class MyTest {

    public static void main(String[] args) {
        System.out.println("---------main");

        MyTest myTest = new MyTest();
        myTest.test();
    }

    public void test() {
        System.out.println("test method");
    }

    @Test
    public void test2() {
        System.out.println("---test methods");
    }

}
