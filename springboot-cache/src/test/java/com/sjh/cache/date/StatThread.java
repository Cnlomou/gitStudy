package com.sjh.cache.date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class StatThread extends Thread {
    private static Logger log= LoggerFactory.getLogger(StatThread.class);
    private static final long DEFAULT_PERIOD=3000l;
    private final Calendar calendar;
    private boolean status=true;
    private  long period;
    public StatThread(Date date){
        this(date,DEFAULT_PERIOD);
    }
    public StatThread(Calendar calendar){
        this(calendar.getTime(),DEFAULT_PERIOD);
    }
    public StatThread(long timeMillis){
        this(new Date(timeMillis),DEFAULT_PERIOD);
    }
    private StatThread(Date date,Long period){
        this.calendar=Calendar.getInstance();
        calendar.setTime(date);
        this.period=period;
    }
    @Override
    public void run() {
        while(status){
            try {
                Thread.sleep(period);
                log.info("执行了一次查询");
                Calendar calendar1=Calendar.getInstance();
                if(calendar.before(calendar1)) {
                    log.info("检测到超时，将抹去数据");
                    calendar.add(Calendar.MINUTE,3);
                }
                else
                    log.info("未对数据做出任何修改");
            } catch (InterruptedException e) {
            }
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
