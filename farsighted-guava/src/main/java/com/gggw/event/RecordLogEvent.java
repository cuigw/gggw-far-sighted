package com.gggw.event;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午11:26
 **/

public class RecordLogEvent implements Event {

    private final String record;

    public RecordLogEvent(String record) {
        this.record = record;
        System.out.println(Thread.currentThread() + "本条日志记录为:" + record);
    }

    @Override
    public String recordInToDB() {
        System.out.println(Thread.currentThread() + "正在往db中插入日志........." + record);

        try {
            //Thread.sleep(3000);
        } catch (Exception e) {

        }

        System.out.println(Thread.currentThread() + "往db中插入日志成功........." + record);
        return record;
    }

}
