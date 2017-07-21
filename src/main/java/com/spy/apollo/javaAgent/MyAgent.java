package com.spy.apollo.javaAgent;

import com.spy.apollo.javaAgent.transformer.MyTransformer;

import java.lang.instrument.Instrumentation;

/**
 * 模块名
 *
 * @author shi.pengyan
 * @version 1.0 2017-07-21 14:17
 * @since 1.0
 */
public class MyAgent {

    public static void premain(String agentOps, Instrumentation inst) {
        System.out.println("----------------premain1");

        MyTransformer myTransformer = new MyTransformer();

        inst.addTransformer(myTransformer, true);

    }

    public static void premain(String agentOps) {
        System.out.println("----------------premain2");
    }

}
