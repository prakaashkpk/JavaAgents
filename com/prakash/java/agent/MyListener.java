package com.prakash.java.agent;

import javax.management.NotificationListener;
import javax.management.Notification;
import java.lang.management.MemoryNotificationInfo;
import javax.management.openmbean.CompositeData; 

class MyListener implements javax.management.NotificationListener {
    public synchronized void handleNotification(Notification notification, Object cbData) {
        CompositeData cd = (CompositeData) notification.getUserData();
        MemoryNotificationInfo info = MemoryNotificationInfo.from(cd); 

        String notifType = notification.getType();
        if (notifType.equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED)) {
            System.out.println("Memory usage reached configured threshold " + info.getUsage());

            /* 
            TODO: Execute you own task here such as Loadbalancing, take dumps or whatever you wish. 
            The handleNotification method should be designed to do a very minimal amount of work and return without delay to avoid causing delay in delivering subsequent notifications

            Ref: https://docs.oracle.com/javase/7/docs/api/java/lang/management/MemoryPoolMXBean.html
            */
            for(String agent : Agent.dumpOptions) {
                if(agent.toLowerCase().contentEquals("system")) {
                    System.out.println("Generating SystemDump...");
                    com.ibm.jvm.Dump.SystemDump();
                } else if(agent.toLowerCase().contentEquals("java")) {
                    System.out.println("Generating JavaDump...");
                    com.ibm.jvm.Dump.JavaDump();
                } else if(agent.toLowerCase().contentEquals("heap")) {
                    System.out.println("Generating HeapDump...");
                    com.ibm.jvm.Dump.HeapDump();
                } else {
                    System.err.println("Invalid Agent configured");
                }
            }
        }
    }
}
