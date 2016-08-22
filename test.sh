#!/bin/bash
# TODO: Add configuration to set the SDK_PATH

echo ">> Cleaning existing agent"
rm gcDumpAgent.jar

echo ">> Compiling agent"
javac com/prakash/java/agent/*.java

echo ">> Compiling test"
javac com/prakash/java/agent/test/AgentTest.java

echo ">> Making agent jar"
jar cmf manifest.txt gcDumpAgent.jar com/prakash/java/agent/Agent.class

echo ">> Running test program"
echo " "
java -javaagent:gcDumpAgent.jar="-t 80 -d java,system" -Xmx200m com.prakash.java.agent.test.AgentTest
