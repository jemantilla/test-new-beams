package com.newbeams.business;

import java.util.Calendar;
import java.util.Date;

public class ThreadImpl extends Thread {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    private static ThreadImpl instance = new ThreadImpl();
    private Date now = new Date();

    private ThreadImpl(){
        this.start();
    }
    
    public static ThreadImpl getInstance(){ return instance; }

    public Date getNow(){ return now;}

    public void run() {
        for (;;) {
            try{
                now = Calendar.getInstance().getTime();
                Thread.sleep(60000);
            }catch(Exception e){
                System.out.println("from ThreadImpl");
                e.printStackTrace();
            }
        }
    }
}
