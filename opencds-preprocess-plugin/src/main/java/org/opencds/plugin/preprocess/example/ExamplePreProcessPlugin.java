package org.opencds.plugin.preprocess.example;

import org.opencds.plugin.api.PreProcessPlugin;
import org.opencds.plugin.api.PreProcessPluginContext;
import org.opencds.hooks.model.request.CdsRequest;

import java.util.List;
import java.util.Map;

public class ExamplePreProcessPlugin implements PreProcessPlugin {

    /**
     * The input {@link PreProcessPluginContext} contains the context of the execution.  Input elements are provided
     * in the context, as well as references to supporting data and the globals and named-objects maps.  These are all
     * in support of adding data to the input context for the rules engine.
     *
     * @param context
     */
    @Override
    public void execute(PreProcessPluginContext context) {

        /*
         * cache contains cached elements from the supporting data, including the cache to which the supporting data
         * packages may be cached.
         */
        context.getCache();
        context.getSupportingData();

        /*
         * allFactLists contains the CdsRequest input as well as other facts that may be provided through the request
         * (e.g., through HTTP headers).
         * globals is the map onto which globally available items, such as terminology services, may be placed.
         * namedObjects is map onto which elements of interest to this process may be interested.
         */
        Map<Class<?>, List<?>> facts = context.getAllFactLists();
        context.getGlobals();
        context.getNamedObjects();

        List<?> list = facts.get(CdsRequest.class);
    }
}
