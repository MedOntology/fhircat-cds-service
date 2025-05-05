package org.opencds.plugin.postprocess.example;

import org.opencds.plugin.api.PostProcessPlugin;
import org.opencds.plugin.api.PostProcessPluginContext;

public class ExamplePostProcessPlugin implements PostProcessPlugin {
    /**
     * The input {@link PostProcessPluginContext} contains the result context from execution.  Both input and output
     * elements are provided in the context.
     *
     * @param context
     */
    @Override
    public void execute(PostProcessPluginContext context) {
        /*
         * Typical inputs.
         * allFactLists contains any fact lists that were built before rules engine execution
         * cache contains cached elements from the supporting data, including the cache to which the supporting data
         * packages may be cached.
         */
        context.getAllFactLists();
        context.getCache();
        context.getSupportingData();

        /*
         * Output may include assertions and/or named-objects made within the rules engine, which may be useful
         * when manipulating the output (resultFactLists) from the execution.
         *
         * In a typical CDS Hooks implementation, the resultFactLists, which is a map of simple class name to a list of
         * instances of that class, contains a single entry where the key is "CdsResponse" (the name of CDS Hooks
         * response class) and a list of one CdsResponse (which is as an output to the execution engine..
         */
        context.getAssertions();
        context.getNamedObjects();
        context.getResultFactLists();

    }
}
