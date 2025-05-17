package org.fhircat.clingen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Genotype {
    private Concept code;
    private String description;
    private Concept moleculeType;
    private Concept associatedGene;
    private List<Haplotype> members = new ArrayList<>();
    private Concept representationFocus;
    private Concept representationCode;

    public Genotype() {
    }

    public Genotype(Concept code, String description, Concept moleculeType, Concept associatedGene, List<Haplotype> members, Concept representationFocus, Concept representationCode) {
        this.code = code;
        this.description = description;
        this.moleculeType = moleculeType;
        this.associatedGene = associatedGene;
        this.members = members;
        this.representationFocus = representationFocus;
        this.representationCode = representationCode;
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

    public Concept getAssociatedGene() {
        return associatedGene;
    }

    public void setAssociatedGene(Concept associatedGene) {
        this.associatedGene = associatedGene;
    }

    public List<Haplotype> getMembers() {
        return members;
    }

    public void setMembers(List<Haplotype> members) {
        this.members = members;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genotype genotype = (Genotype) o;
        return Objects.equals(code, genotype.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
