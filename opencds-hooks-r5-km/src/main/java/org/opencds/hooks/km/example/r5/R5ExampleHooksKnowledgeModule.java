package org.opencds.hooks.km.example.r5;

import org.hl7.fhir.r5.model.OperationOutcome;
import org.hl7.fhir.r5.model.Patient;
import org.hl7.fhir.r5.model.Resource;
import org.opencds.hooks.engine.api.CdsHooksEvaluationContext;
import org.opencds.hooks.engine.api.CdsHooksExecutionEngine;
import org.opencds.hooks.model.r5.request.prefetch.R5PrefetchHelper;
import org.opencds.hooks.model.request.CdsRequest;
import org.opencds.hooks.model.request.prefetch.PrefetchResult;
import org.opencds.hooks.model.response.Card;
import org.opencds.hooks.model.response.CdsResponse;

import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * This class is the main knowledge module (Java-based) that can be used within OpenCDS.
 */
public class R5ExampleHooksKnowledgeModule implements CdsHooksExecutionEngine {

    private R5PrefetchHelper prefetchHelper = new R5PrefetchHelper();

    /**
     * OpenCDS will adapt the input received from the CDS Request, and provide the request and the
     * {@link CdsHooksEvaluationContext} to this method.
     *
     * The CdsRequest is specified at:
     *      https://cds-hooks.hl7.org (HL7 Standard site)
     *      https://cds-hooks.org (Development site)
     *
     * The {@link CdsHooksEvaluationContext} provides:
     * <ul>
     *     <li>the evalTime (which is typically provided as a header
     *     (eval-time, or Eval-Time; case-insensitive) to the CDS Hooks HTTP Request.</li>
     *     <li>a map of globals, which are additional resources that may be useful for rule execution.
     *     globals are often provided by pre-process plugins, and may include Terminology APIs or any other
     *     custom pre-processors.</li>
     *     <li>serverBaseUri, which may be useful for creating links to content hosted by the CDS Hooks
     *     implementation in OpenCDS</li>
     * </ul>
     *
     * Examples of an execution engine include KIE/Drools, CQL Engine, and plain Java.  Most often, exising execution
     * engines such as Drools or CQL Engine require only an Adapter to be built in order to adapt the CDS Hooks input
     * to the format expected by the rules engine.  However, this class is an example of a Java adapter that may be
     * extended to support custom rules engines or other engines that require more custom processing than the adapter
     * provide.
     *
     * @param cdsRequest
     * @param cdsHooksEvaluationContext
     * @return cdsResponse
     */
    public CdsResponse evaluate(CdsRequest cdsRequest, CdsHooksEvaluationContext cdsHooksEvaluationContext) {
        /*
         * time of evaluation.  This can be set to a past or future date on request.
         */
        Date evalTime = cdsHooksEvaluationContext.getEvalTime();
        /*
         * globals is the location for additional resources that may be useful for rule execution
         */
        Map<String, Object> globals = cdsHooksEvaluationContext.getGlobals();
        /*
         * serverBaseUri is the base location of the hooks service, e.g., https://localhost:8443/opencds/hooks
         */
        URI serverBaseUri = cdsHooksEvaluationContext.getServerBaseUri();

        /*
         * Execution engine logic.
         *
         * Once the rules execution logic has completed, a CdsResponse must be built to return to OpenCDS for additional
         * processing.
         */

        PrefetchResult<OperationOutcome, Resource> prefetchResult = cdsRequest.getPrefetchResource("Patient", prefetchHelper);

        Patient patient = prefetchResult.getResource(Patient.class);

        CdsResponse response = new CdsResponse();
        Card card = new Card();
        card.setDetail("example response card");
        response.addCard(card);
        return response;
    }
}
