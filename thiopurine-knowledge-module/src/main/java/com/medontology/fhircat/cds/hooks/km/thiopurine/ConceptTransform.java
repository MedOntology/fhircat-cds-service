package com.medontology.fhircat.cds.hooks.km.thiopurine;

import org.fhircat.clingen.model.Concept;
import org.hl7.fhir.r5.model.CodeableConcept;
import org.hl7.fhir.r5.model.Coding;

public class ConceptTransform {

    public static Concept transform(CodeableConcept codeableConcept) {
        Concept concept = null;
        if(codeableConcept != null && !codeableConcept.getCoding().isEmpty()) {
            Coding coding = codeableConcept.getCodingFirstRep();
            concept = new Concept(coding.getCode(), coding.getSystem(), coding.getDisplay(), coding.getVersion());
        }
        return concept;
    }

    public static Concept transform(Coding coding) {
        Concept concept = null;
        if(coding != null) {
            concept = new Concept(coding.getCode(), coding.getSystem(), coding.getDisplay(), coding.getVersion());
        }
        return concept;
    }
}
