Java Agent to trigger dumps when usage of memory(heap) reaches threshold

## To use agent
java -javaagent:gcDumpAgent.jar="-t 80 -d java,system -r <start>..<end>" -Xmx200m com.prakash.java.agent.test.AgentTest

###Where
-t -> Threshold,

-d -> Dump option

-r -> Range option
   <start> - from nth event onwards collect dump
   <end> - after nth event stop collecting dump

###Example

For generating heap and system dumps for max 4 times when the heap usage reaches 90% use the following option

<SDK_PATH>/bin/java -javaagent:gcDumpAgent.jar="-t 90 -d heap,system -r 1..4"

Note:
The project is not yet completed
