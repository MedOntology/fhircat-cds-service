package com.medontology.fhircat.cds.hooks.km.thiopurine;

import ca.uhn.fhir.context.FhirContext;
import org.fhircat.cds.utils.ResourceFileReader;
import org.hl7.fhir.r5.model.MolecularDefinition;

import java.util.HashMap;
import java.util.Map;



public class MolDefIndex {


    private FhirContext ctx = FhirContext.forR5();
    private Map<String, MolecularDefinition> index = new HashMap<>();

    public MolDefIndex() {
    }

    public void add(MolecularDefinition def) {
        index.put(def.getId(), def);
    }

    public MolecularDefinition get(String id) {
        return index.get(id);
    }

    public void populate() {
        ResourceFileReader reader = new ResourceFileReader();
        String gt100 = reader.getFileContentAsString("payloads/gt100.json");
        MolecularDefinition gt100MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, gt100);
        add(gt100MolDef);

        String gt200 = reader.getFileContentAsString("payloads/gt200.json");
        MolecularDefinition gt200MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, gt200);
        add(gt200MolDef);

        String md1 = reader.getFileContentAsString("payloads/md1.json");
        MolecularDefinition md1MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, md1);
        add(md1MolDef);

        String md2 = reader.getFileContentAsString("payloads/md2.json");
        MolecularDefinition md2MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, md2);
        add(md2MolDef);

        String md3 = reader.getFileContentAsString("payloads/md3.json");
        MolecularDefinition md3MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, md3);
        add(md3MolDef);

        String md4 = reader.getFileContentAsString("payloads/md4.json");
        MolecularDefinition md4MolDef = ctx.newJsonParser().parseResource(MolecularDefinition.class, md4);
        add(md4MolDef);
    }
}
