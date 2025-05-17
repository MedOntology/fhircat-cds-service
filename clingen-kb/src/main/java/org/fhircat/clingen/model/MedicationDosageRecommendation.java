package org.fhircat.clingen.model;

import java.util.List;

public class MedicationDosageRecommendation {
    private List<MetabolizerStatus> metabolizerStatuses;
    private String therapeuticRecommendation;
    private Concept strengthOfRecommendation;
    private String comments;

    public MedicationDosageRecommendation() {
    }

    public MedicationDosageRecommendation(List<MetabolizerStatus> metabolizerStatuses, String therapeuticRecommendation, Concept strengthOfRecommendation, String comments) {
        this.metabolizerStatuses = metabolizerStatuses;
        this.therapeuticRecommendation = therapeuticRecommendation;
        this.strengthOfRecommendation = strengthOfRecommendation;
        this.comments = comments;
    }

    public List<MetabolizerStatus> getMetabolizerStatuses() {
        return metabolizerStatuses;
    }

    public void setMetabolizerStatuses(List<MetabolizerStatus> metabolizerStatuses) {
        this.metabolizerStatuses = metabolizerStatuses;
    }

    public String getTherapeuticRecommendation() {
        return therapeuticRecommendation;
    }

    public void setTherapeuticRecommendation(String therapeuticRecommendation) {
        this.therapeuticRecommendation = therapeuticRecommendation;
    }

    public Concept getStrengthOfRecommendation() {
        return strengthOfRecommendation;
    }

    public void setStrengthOfRecommendation(Concept strengthOfRecommendation) {
        this.strengthOfRecommendation = strengthOfRecommendation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
