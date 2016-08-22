package com.prakash.java.agent;

import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import javax.management.NotificationListener;
import javax.management.NotificationEmitter;
import javax.management.Notification;
import java.lang.management.MemoryType;

class Agent {
    private static boolean init;
    private static int threshold;
    public static String [] dumpOptions;

    // Agent command line args: -javaagent:gcDumpAgent.jar="-t 80 -d java,system"
    public static void premain(String args, Instrumentation instrumentation) {
        parseCommandLineArgs(args.trim());        

        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        NotificationEmitter emitter = (NotificationEmitter) mbean;
        MyListener listener = new MyListener();
        emitter.addNotificationListener(listener, null, null);
        init();
    }

    private static void parseCommandLineArgs(String args) {
        String arguments = args;
        String [] tokens = arguments.split("-");
        for(String token : tokens) {
            if(token.length() > 0) {
                switch(token.charAt(0)) {
                    case 't':
                        threshold = Integer.parseInt(token.substring(2, token.trim().length()));
                        break;
                    case 'd':
                        String dumpOption = token.substring(2, token.length());
                        dumpOptions = dumpOption.split(",");
                        break;
                    default:
                        // TODO: Nothing to do here. Handle error if required
                        break;
                }
            }
        }
    }

    public synchronized static void init() {        
        if(init) 
            return;

        for(MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getType() == MemoryType.HEAP && pool.isUsageThresholdSupported()) {
                long maxMemory = pool.getUsage().getMax();
                long warningThreshold = (long) (maxMemory * ((double) threshold/100) );
                System.out.println("MaxMemory: " + maxMemory + ", WarningThreshold: " + warningThreshold);

                pool.setUsageThreshold(warningThreshold);
            }
        }
        init = true;
    }
}
