package org.fhircat.clingen.model;

import java.util.Objects;

public class Concept {
    public String code;
    public String system;
    public String display;
    public String version;

    public Concept(String code, String system, String display) {
        this(code, system, display, null);
    }

    public Concept(String code, String system, String display, String version) {
        this.code = code;
        this.system = system;
        this.display = display;
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public Concept setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSystem() {
        return system;
    }

    public Concept setSystem(String system) {
        this.system = system;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public Concept setDisplay(String display) {
        this.display = display;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Concept concept = (Concept) o;
        return Objects.equals(code, concept.code) && Objects.equals(system, concept.system);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, system);
    }
}
