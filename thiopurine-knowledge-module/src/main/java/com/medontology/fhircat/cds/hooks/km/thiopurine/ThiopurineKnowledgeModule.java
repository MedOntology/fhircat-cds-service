package com.medontology.fhircat.cds.hooks.km.thiopurine;

import org.fhircat.clingen.kb.engine.ClinGenKb;
import org.fhircat.clingen.model.Concept;
import org.fhircat.clingen.model.Genotype;
import org.fhircat.clingen.model.MedicationDosageRecommendation;
import org.fhircat.clingen.model.MetabolizerStatus;
import org.hl7.fhir.r5.model.*;
import org.opencds.hooks.engine.api.CdsHooksEvaluationContext;
import org.opencds.hooks.engine.api.CdsHooksExecutionEngine;
import org.opencds.hooks.model.r5.request.prefetch.R5PrefetchHelper;
import org.opencds.hooks.model.request.CdsRequest;
import org.opencds.hooks.model.request.prefetch.PrefetchResult;
import org.opencds.hooks.model.response.Card;
import org.opencds.hooks.model.response.CdsResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class is the main knowledge module (Java-based) that can be used within OpenCDS.
 */
public class ThiopurineKnowledgeModule implements CdsHooksExecutionEngine {

    private static final ClinGenKb clinGenKb = new ClinGenKb();

    static {
        clinGenKb.populate(MolDefIndex.getGenotypes());
    }

    private R5PrefetchHelper prefetchHelper = new R5PrefetchHelper();


    /**
     * OpenCDS will adapt the input received from the CDS Request, and provide the request and the
     * {@link CdsHooksEvaluationContext} to this method.
     * <p>
     * The CdsRequest is specified at:
     * https://cds-hooks.hl7.org (HL7 Standard site)
     * https://cds-hooks.org (Development site)
     * <p>
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
     * <p>
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

        //1. Get resources from the payload. These should include the medication request, the patient, and a list of genotypes potentially.
        PrefetchResult<OperationOutcome, Resource> prefetchResult1 = cdsRequest.getPrefetchResource("patient", prefetchHelper);
        Patient patient = prefetchResult1.getResource(Patient.class);

        PrefetchResult<OperationOutcome, Resource> prefetchResult2 = cdsRequest.getPrefetchResource("medicationRequest", prefetchHelper);
        MedicationRequest medicationRequest = (MedicationRequest) prefetchResult2.getResource(Bundle.class).getEntryFirstRep().getResource();

        PrefetchResult<OperationOutcome, Resource> labPrefetchResult = cdsRequest.getPrefetchResource("geneticLabResults", prefetchHelper);
        Bundle geneticLabResults = labPrefetchResult.getResource(Bundle.class);

        //2. Get the medication id (we assume here that it is a code)
        CodeableConcept medicationCode = medicationRequest.getMedication().getConcept();

        //3. Get the patient's genotypes
        List<Genotype> genotypes = getPatientGenotypes(patient, geneticLabResults);

        //4. Get genes for drug
        List<Concept> genes = clinGenKb.getGenesForRxNormIngredient(ConceptTransform.transform(medicationCode));

        //5. Get metabolizer status for genotype
        List<MetabolizerStatus> metabolizerStatuses = clinGenKb.getMetabolizerStatuses(ConceptTransform.transform(medicationCode), genes, genotypes);

        //6. Get drug recommendation for the metabolizer status.
        MedicationDosageRecommendation recommendation = clinGenKb.getDoseRecommendation(ConceptTransform.transform(medicationCode), metabolizerStatuses);

        //7. For now, simply return message from guideline as card. We can elaborate later.

        CdsResponse response = new CdsResponse();
        Card card = new Card();
        if (recommendation != null) {
            card.setDetail(recommendation.getTherapeuticRecommendation());
        } else {
            card.setDetail("Continue with normal thiopurine dosage");
        }
        response.addCard(card);
        return response;
    }

    private List<Genotype> getPatientGenotypes(Patient patient, Bundle geneticLabResults) {
        List<Genotype> genotypes = new ArrayList<>();
        Map<String, Genotype> genotypeIndex = MolDefIndex.getGenotypes();
        for (Bundle.BundleEntryComponent entry : geneticLabResults.getEntry()) {
            if (entry.getResource() != null && entry.getResource() instanceof Observation) {
                Observation obs = (Observation) entry.getResource();
                String reference = obs.getValueReference().getReference();
                Genotype genotype = MolDefIndex.getGenotypes().get(reference);
                genotypes.add(genotype);
            }
        }
        return genotypes;
    }
}
