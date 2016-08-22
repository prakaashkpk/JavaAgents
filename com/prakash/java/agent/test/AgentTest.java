package com.prakash.java.agent.test;

import java.util.ArrayList;
import java.util.List;

class AgentTest {
	/*	
	public static void main(String[] args) {
		System.out.println("Main");
	}
	*/

  private static final long MEGABYTE = 1024L * 1024L;

  public static long bytesToMegabytes(long bytes) {
    return bytes / MEGABYTE;
  }

  public static void main(String[] args) {
    AgentTest pt = new AgentTest();
    pt.start();
  }

  public void printMemStats() {
    // Calculate the used memory
    Runtime runtime = Runtime.getRuntime();
    long memory = runtime.totalMemory() - runtime.freeMemory();
    System.out.println("Used memory is bytes: " + memory);
    System.out.println("Used memory is megabytes: "
        + bytesToMegabytes(memory));


  }

  public void start() {
    // I assume you will know how to create a object Person yourself...
    List<Person> list = new ArrayList<Person>();
    for (int i = 0; i <= 9999999; i++) {
      Person per = new Person("Jim", "Knopf"); 
      list.add(per);
    }
    printMemStats();
    // Get the Java runtime
    Runtime runtime = Runtime.getRuntime();
    // Run the garbage collector
    runtime.gc();
    printMemStats();
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
