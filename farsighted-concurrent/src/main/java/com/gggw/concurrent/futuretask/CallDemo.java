package com.gggw.concurrent.futuretask;

import java.util.concurrent.*;

/**
 * Future FutureTask Callable
 * Created by cuigaowei on 2017/8/29.
 */
public class CallDemo {


    public static void main(String[] args) throws Exception {

        /**
         * 第一种方式:Future + ExecutorService
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(new Task());
        System.out.println(future.get());
        //executorService.shutdown();   关闭之后线程池无法使用

        /**
         * 第二种方式: FutureTask + ExecutorService
         */
        //ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
        //executorService.shutdown();

        /**
         * 第三种方式:FutureTask + Thread
         */
        // 1.新建FutureTask,需要一个实现了Callable接口的类的实例作为构造函数参数
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Task());
        // 2. 新建Thread对象并启动
        Thread thread = new Thread(futureTask2);
        thread.setName("futureTask2 Thread");
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
        // 3. 调用isDone()判断任务是否结束
        if (!futureTask2.isDone()) {
            System.out.println("Task is not done");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = 0;
        try{
            result = futureTask2.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("result is " + result);

        /**
         * 第四种方式:FutureTask(Runnable runnable, V result)
         */
        FutureTask<Integer> futureTask3 = new FutureTask<Integer>(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
                int result = 0;
                for (int i =0; i < 100; ++i) {
                    result += i ;
                }
            }
        }, 333);
        executorService.submit(futureTask3);
        System.out.println(futureTask3.get());
    }




    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
            int result = 0;
            for (int i =0; i < 100; ++i) {
                result += i ;
            }
            Thread.sleep(3000);
            return result;
        }
    }
}
