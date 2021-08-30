package com.it.boot;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@DisplayName("Junit5测试")
public class JUnit5Test {

    /*
    * 测试前置条件
    * */
    @DisplayName("测试前置条件")
    @Test
    void testAssumptions(){
        Assumptions.assumeTrue(false,"结果不是true");
        System.out.println("1111111111");
    }


    /*
    * 断言：当断言失败，后面的代码都不会执行
    * */
    @DisplayName("测试简单断言")
    @Test
    void testAssertions(){
        int call = call(2, 4);
        Assertions.assertEquals(6,call,"业务逻辑计算失败");
        Object o1 = new Object();
        Object o2 = new Object();
        Assertions.assertSame(o1,o2,"两个对象不一致");
    }

    int call(int i, int j){
        return i+j;
    }


    @DisplayName("测试displayname")
    @Test
    void testDisplayName(){
        System.out.println(1);
    }

    @Disabled
    @DisplayName("测试2")
    @Test
    void test2(){
        System.out.println(2);
    }

    /*
    * 超出规定时间，就会出异常
    * */
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeOut() throws InterruptedException {
        Thread.sleep(500);
    }


    @RepeatedTest(5)
    @Test
    void test3(){
        System.out.println(5);
    }

    @BeforeEach
    void testBeforeEach(){
        System.out.println("测试就要开始了");
    }

    @AfterEach
    void testAfterEach(){
        System.out.println("测试结束了");
    }

    @BeforeAll
    static void testBeforeAll(){
        System.out.println("所有测试就要开始了");
    }

    @AfterAll
    static void testAfterAll(){
        System.out.println("所有测试都要结束了");
    }

}
