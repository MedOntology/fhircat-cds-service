package org.fhircat.clingen.model;

import java.util.Objects;

public class Haplotype {
    private Concept code;
    private String description;
    private Concept moleculeType;
    private Concept representationFocus;
    private Concept representationCode;
    private Concept associatedGene;

    public Haplotype() {
    }

    public Haplotype(Concept code, String description, Concept moleculeType, Concept representationFocus, Concept representationCode, Concept associatedGene) {
        this.code = code;
        this.description = description;
        this.moleculeType = moleculeType;
        this.representationFocus = representationFocus;
        this.representationCode = representationCode;
        this.associatedGene = associatedGene;
    }

    public Concept getCode() {
        return code;
    }

    public void setCode(Concept code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Concept getMoleculeType() {
        return moleculeType;
    }

    public void setMoleculeType(Concept moleculeType) {
        this.moleculeType = moleculeType;
    }

    public Concept getRepresentationFocus() {
        return representationFocus;
    }

    public void setRepresentationFocus(Concept representationFocus) {
        this.representationFocus = representationFocus;
    }

    public Concept getRepresentationCode() {
        return representationCode;
    }

    public void setRepresentationCode(Concept representationCode) {
        this.representationCode = representationCode;
    }

    public Concept getAssociatedGene() {
        return associatedGene;
    }

    public void setAssociatedGene(Concept associatedGene) {
        this.associatedGene = associatedGene;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Haplotype haplotype = (Haplotype) o;
        return Objects.equals(code, haplotype.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
