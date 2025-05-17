package com.medontology.fhircat.cds.hooks.km.thiopurine;

import org.fhircat.clingen.model.Genotype;
import org.fhircat.clingen.model.Haplotype;
import org.fhircat.clingen.model.Terminology;
import org.hl7.fhir.r5.model.MolecularDefinition;

import java.util.List;
import java.util.Map;

public class GenotypeTransform {

    public static Genotype transform(MolecularDefinition molecularDefinition, Map<String,Haplotype> members) {
        final Genotype genotype;
        if(molecularDefinition != null && molecularDefinition.getRepresentationFirstRep().getFocus().getText().equalsIgnoreCase("genotype")) {
            genotype = new Genotype();
            genotype.setCode(IdentifierTransform.transform(molecularDefinition.getIdentifierFirstRep()));
            genotype.setMoleculeType(Terminology.DNA.getCode());
            genotype.setDescription(molecularDefinition.getDescription().asStringValue());
            molecularDefinition.getMember().forEach(member -> {
                genotype.getMembers().add(members.get(member.getReference()));
            });
            genotype.setRepresentationCode(ConceptTransform.transform(molecularDefinition.getRepresentationFirstRep().getCode().get(0)));
            genotype.setAssociatedGene(ConceptTransform.transform(molecularDefinition.getExtensionByUrl("http://fhircat.org/haplotype-associated-gene").getValueCodeableConcept()));
        } else {
            genotype = null;
        }
        return genotype;
    }
}
