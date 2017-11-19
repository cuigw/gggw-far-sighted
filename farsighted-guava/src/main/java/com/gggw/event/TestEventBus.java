package com.gggw.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午4:24
 **/

public class TestEventBus {

    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());

    }

    public void testAysnc() throws Exception {

        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));

        EventListener listener = new EventListener();
        NewListener newListener = new NewListener();

        eventBus.register(listener);
        eventBus.register(newListener);

        eventBus.post(new TestEvent(200));
/*
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));
        eventBus.post(new TestNewEvent(20));
        eventBus.post(new TestNewEvent(30));
        eventBus.post(new TestNewEvent(40));
*/

        System.out.println("LastMessage:"+listener.getLastMessage());

    }

    public void testAysncRecord() throws Exception {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory);

        //AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));
        AsyncEventBus eventBus = new AsyncEventBus(pool);



        RecordLogEventListener recordLogEventListener = new RecordLogEventListener();

        eventBus.register(recordLogEventListener);

        for (int i = 0; i < 100; i++) {
            eventBus.post(new RecordLogEvent("第" + i + "单"));
        }


/*        eventBus.post(new RecordLogNewEvent("第二单"));
        eventBus.post(new RecordLogEvent("第三单"));
        eventBus.post(new RecordLogNewEvent("第四单"));*/
    }

    public static void main(String[] args) throws Exception {

        TestEventBus testEventBus = new TestEventBus();
        //testEventBus.testReceiveEvent();
        //System.out.println();
        //testEventBus.testAysnc();
        testEventBus.testAysncRecord();

    }
}