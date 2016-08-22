package com.prakash.java.agent;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import javax.management.NotificationListener;
import javax.management.NotificationEmitter;
import javax.management.Notification;
import java.lang.management.MemoryType;

// import com.ibm.lang.management.*;

class Agent {
    private static boolean init;

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Agent Pre-Main, Args: " + args);

        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        NotificationEmitter emitter = (NotificationEmitter) mbean;
        MyListener listener = new MyListener();
        emitter.addNotificationListener(listener, null, null);
        init();
    }

    public synchronized static void init() {        
        if(init) 
            return;

        for(MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getType() == MemoryType.HEAP && pool.isUsageThresholdSupported()) {
                System.out.println("Pool name: " + pool.getName() + ", Pool type: " + pool.getType());
                
                long maxMemory = pool.getUsage().getMax();
                long warningThreshold = (long) (maxMemory * 0.3);
                System.out.println("MaxMemory: " + maxMemory + ", WarningThreshold: " + warningThreshold);

                pool.setUsageThreshold(warningThreshold);
            }
        }
        init = true;
    }
}
