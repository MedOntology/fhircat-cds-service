package com.medontology.fhircat.cds.hooks.km.thiopurine;

import org.fhircat.clingen.model.Haplotype;
import org.fhircat.clingen.model.Terminology;
import org.hl7.fhir.r5.model.MolecularDefinition;

public class HaplotypeTransform {

    public static Haplotype transform(MolecularDefinition molecularDefinition) {
        Haplotype haplotype = null;
        if(molecularDefinition != null && molecularDefinition.getRepresentationFirstRep().getFocus().getText().equalsIgnoreCase("haplotype")) {
            haplotype = new Haplotype();
            haplotype.setCode(IdentifierTransform.transform(molecularDefinition.getIdentifierFirstRep()));
            haplotype.setMoleculeType(Terminology.DNA.getCode());
            haplotype.setDescription(molecularDefinition.getDescription().asStringValue());
            haplotype.setRepresentationCode(ConceptTransform.transform(molecularDefinition.getRepresentationFirstRep().getCode().get(0)));
            haplotype.setAssociatedGene(ConceptTransform.transform(molecularDefinition.getExtensionByUrl("http://fhircat.org/haplotype-associated-gene").getValueCodeableConcept()));
        }
        return haplotype;
    }
}
