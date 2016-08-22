Java Agent to trigger dumps when usage of memory(heap) reaches threshold

## To use agent
java -javaagent:gcDumpAgent.jar="-t 80 -d java,system" -Xmx200m com.prakash.java.agent.test.AgentTest

###Where
-t -> Threshold,

-d -> Dump option

Note:
The project is not yet completed
