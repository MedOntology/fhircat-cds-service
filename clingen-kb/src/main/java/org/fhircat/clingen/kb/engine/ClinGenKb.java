package org.fhircat.clingen.kb.engine;

import org.fhircat.clingen.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClinGenKb {

    /**
     * Represents a potential data store where one searches by drug ingredient
     * and gets back a list of genes that impact that drug's metabolism.
     */
    private Map<Concept, List<Concept>> drugToGeneIndex = new HashMap<>();

    /**
     * Represents a potential data store or logic unit where a genotype is mapped to metabolizer status.
     */
    private Map<Concept, MetabolizerStatus> genotypeToMetabolizerStatusIndex = new HashMap<>();

    public ClinGenKb() {
    }

    public void populate(Map<String, Genotype> genotypeIndex) {
        List<Concept> impactedDrugs = new ArrayList<>();
        impactedDrugs.add(Terminology.AZATHIOPRINE.getCode());
        List<Genotype> genotypes = new ArrayList<>();
        genotypes.add(genotypeIndex.get("mygeno:100"));
        genotypes.add(genotypeIndex.get("mygeno:200"));
        MetabolizerStatus geno100_azathiaprine = new MetabolizerStatus(Terminology.GENOTYPE100.getCode(), Terminology.INTERMEDIATE_METABOLIZER.getCode(), "Moderate to high concentrations of TGN metabolites; low concentrations of MeTIMP. Increased risk of thiopurine-related leukopenia, neutropenia, myelosuppression.", impactedDrugs);
        MetabolizerStatus geno200_azathiaprine = new MetabolizerStatus(Terminology.GENOTYPE200.getCode(), Terminology.INTERMEDIATE_METABOLIZER.getCode(), "Increased risk of thiopurine-related leukopenia, neutropenia, myelosuppression.", impactedDrugs);
        this.genotypeToMetabolizerStatusIndex.put(Terminology.GENOTYPE100.getCode(), geno100_azathiaprine); //TODO: one-to-many
        this.genotypeToMetabolizerStatusIndex.put(Terminology.GENOTYPE200.getCode(), geno200_azathiaprine);
        List<Concept> genes = new ArrayList<>();
        genes.add(Terminology.HGNC12014.getCode());
        genes.add(Terminology.HGNC23063.getCode());
        drugToGeneIndex.put(Terminology.AZATHIOPRINE.getCode(), genes);
    }

    public List<Concept> getGenesForRxNormIngredient(Concept rxNormIngredient) {
        if (drugToGeneIndex.containsKey(rxNormIngredient)) {
            return this.drugToGeneIndex.get(rxNormIngredient);
        } else {
            return new ArrayList<>();
        }
    }

    public List<MetabolizerStatus> getMetabolizerStatuses(Concept medicationCode, List<Concept> genes, List<Genotype> genotypes) {
        List<MetabolizerStatus> statuses = new ArrayList<>();
        if (genotypes != null) {
            for (Genotype genotype : genotypes) {
                if (genotypeToMetabolizerStatusIndex.containsKey(genotype.getRepresentationCode())) {
                    statuses.add(genotypeToMetabolizerStatusIndex.get(genotype.getRepresentationCode()));
                }
            }
        }

        if (genes != null) {
            //TODO: Implement at some point. Question for Bob: No guarantee patient has any of these genes. How to get the intersection of genes associated with drug and genes patient has for tailored response.
        }
        return statuses;
    }

    public MedicationDosageRecommendation getDoseRecommendation(Concept
                                                                        rxCuiIngredient, List<MetabolizerStatus> metabolizerStatuses) {
        List<MetabolizerStatus> relevantStatuses = metabolizerStatuses.stream().filter(s -> s.getImpactedDrugs().contains(rxCuiIngredient)).collect(Collectors.toUnmodifiableList());
        if (!relevantStatuses.isEmpty()) {
            MedicationDosageRecommendation recommendation = new MedicationDosageRecommendation(relevantStatuses, "Start with reduced starting doses (20%-50% of normal dose) if normal starting dose is 2-3 mg/kg/day (e.g., 0.4 – 1.5 mg/kg/day), and adjust doses of azathioprine based on degree of myelosuppression and disease-specific guidelines. Allow 2-4 weeks to reach steady-state after each dose adjustment (PMID 20354201, 11302950, 15606506, 16530532).", Terminology.STRENGTH_OF_RECOMMENDATION_STRONG.getCode(), "Normal starting doses vary by race/ethnicity and treatment regimens. If standard dose is below normal recommended dose, dose reduction might not be recommended for intermediate metabolizers.");
            return recommendation;
        } else {
            return null;
        }
    }

}
