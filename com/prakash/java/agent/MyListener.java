package com.prakash.java.agent;

import javax.management.NotificationListener;
import javax.management.Notification;
import java.lang.management.MemoryNotificationInfo;
import javax.management.openmbean.CompositeData;

class MyListener implements javax.management.NotificationListener {
    static int eventCnt = 0;

    public synchronized void handleNotification(Notification notification, Object cbData) {
        CompositeData cd = (CompositeData) notification.getUserData();
        MemoryNotificationInfo info = MemoryNotificationInfo.from(cd);

        String notifType = notification.getType();
        if (notifType.equals(MemoryNotificationInfo.MEMORY_THRESHOLD_EXCEEDED)) {
            System.out.println("Memory usage reached configured threshold " + info.getUsage());

	    eventCnt++;
	    if (Agent.start <= eventCnt && Agent.end >= eventCnt)
	    {
                for(String agent : Agent.dumpOptions) {
                    System.out.println("dumpOpt=" + agent + "xyz");
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
                System.gc();
            }
        }
    }
}
