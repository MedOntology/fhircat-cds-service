package org.fhircat.clingen.model;

public enum Terminology {
    HGNC12014(new Concept("HGNC:12014", "http://www.genenames.org", "HGNC:12014")),
    HGNC23063(new Concept("HGNC:23063", "http://www.genenames.org", "HGNC:23063")),
    TPMT1(new Concept("TPMT*1", "https://liu.se/en/research/tpmt-nomenclature-committee", "TPMT*1")),
    TPMT3C(new Concept("TPMT*3C", "https://liu.se/en/research/tpmt-nomenclature-committee", "TPMT*3C")),
    NUDT15_1(new Concept("PV01597", "http://pharmvar.org", "NUDT15*1")),
    NUDT15_3C(new Concept("PV01599", "http://pharmvar.org", "NUDT15*3C")),
    GENOTYPE100(new Concept("mygeno:100", "http://mygenotypes.org", "TPMT*1/*3C")),
    GENOTYPE200(new Concept("mygeno:200", "http://mygenotypes.org", "NUDT15*1/*3")),
    DNA(new Concept("DNA", "https://fhircat.org", "DNA", null)),
    AZATHIOPRINE(new Concept("1256", "http://loinc.org", "azathioprine")),
    INTERMEDIATE_METABOLIZER(new Concept("1000", "http://fhircat.org/terminology", "intermediate metabolizer")),
    POOR_METABOLIZER(new Concept("1001", "http://fhircat.org/terminology", "poor metabolizer")),
    STRENGTH_OF_RECOMMENDATION_STRONG(new Concept("3001", "http://fhircat.org/terminology", "Strong"));

    private final Concept code;

    Terminology(Concept code) {
        this.code = code;
    }

    public Concept getCode() {
        return code;
    }
}
