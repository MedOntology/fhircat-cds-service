package org.fhircat.clingen.model;

import java.util.List;

public class MetabolizerStatus {
    private Concept genotype;
    private Concept metabolizerStatus;
    private String implications;
    private List<Concept> impactedDrugs;

    public MetabolizerStatus() {
    }

    public MetabolizerStatus(Concept genotype, Concept metabolizerStatus, String implications, List<Concept> impactedDrugs) {
        this.genotype = genotype;
        this.metabolizerStatus = metabolizerStatus;
        this.implications = implications;
        this.impactedDrugs = impactedDrugs;
    }

    public Concept getGenotype() {
        return genotype;
    }

    public void setGenotype(Concept genotype) {
        this.genotype = genotype;
    }

    public Concept getMetabolizerStatus() {
        return metabolizerStatus;
    }

    public void setMetabolizerStatus(Concept metabolizerStatus) {
        this.metabolizerStatus = metabolizerStatus;
    }

    public String getImplications() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications = implications;
    }

    public List<Concept> getImpactedDrugs() {
        return impactedDrugs;
    }

    public void setImpactedDrugs(List<Concept> impactedDrugs) {
        this.impactedDrugs = impactedDrugs;
    }
}
