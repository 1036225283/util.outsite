package com.nitian.util.time;

import java.util.Timer;
import java.util.TimerTask;


import com.nitian.util.random.UtilRandom;

public class UtilTimer {

	public static void main(String[] args) {
		timer3();
	}
	
    public static void timer3() {  
        Timer timer = new Timer();  
        timer.scheduleAtFixedRate(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                System.out.println(UtilRandom.createUUID());
            }  
        }, 1000, 2000);  
    }  
}
