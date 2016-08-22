package com.prakash.java.agent.test;

import java.util.ArrayList;
import java.util.List;

class AgentTest {
    private static final long MEGABYTE = 1024L * 1024L;
    public static void main(String[] args) {
        AgentTest pt = new AgentTest();
        pt.start();
    }

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
    }

    public void start() {
        List < Person > list = new ArrayList < Person > ();
        for (int i = 0; i <= 9999999; i++) {
        Person per = new Person("John", "Mchlaren");
            list.add(per);
        }
    }

    class Person {
        String first_name;
        String last_name;
        public Person(String firstname, String lastname) {
            first_name = firstname;
            last_name = lastname;
        }
    }
}
