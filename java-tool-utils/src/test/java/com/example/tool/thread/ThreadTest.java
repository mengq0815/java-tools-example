package com.example.tool.thread;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * @author mengqiang
 */
public class ThreadTest {

    @Test
    public void testInteger() {
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            WorkThreadUtil2.getInstance().execute(() -> doSomeThing(finalI, 1000));
        }

        sleep(12000);
    }


    private void doSomeThing(int time, long millis) {
        Thread thread = Thread.currentThread();
        long threadId = thread.getId();
        String threadName = thread.getName();
        System.out.println(MessageFormat.format("[{0}] >> [{1}] I am  do some thing , currentTimeMillis : {1}",
                time, threadName,
                String.valueOf(System.currentTimeMillis())));
        //测试 demo 休眠以下
        sleep(millis);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("休眠异常了");
        }
    }
}