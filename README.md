Java Agent to trigger dumps when usage of memory(heap) reaches threshold

## To use agent
java -javaagent:gcDumpAgent.jar="-t 80 -d java,system" -Xmx200m com.prakash.java.agent.test.AgentTest

###Where
-t -> Threshold,

-d -> Dump option

###Example

To generate heap, system dumps when the heap usage reaches 90%, use the following option

<SDK_PATH>/bin/java -javaagent:gcDumpAgent.jar="-t 90 -d heap,system"

Note:
The project is not yet completed
