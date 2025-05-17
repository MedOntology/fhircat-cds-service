package com.medontology.fhircat.cds.hooks.km.thiopurine;

import org.fhircat.clingen.model.Concept;
import org.hl7.fhir.r5.model.Identifier;

public class IdentifierTransform {

    public static Concept transform(Identifier identifier) {
        Concept concept = null;
        if(identifier != null) {
            concept = new Concept(identifier.getValue(), identifier.getSystem(), identifier.getValue(), null);
        }
        return concept;
    }
}
