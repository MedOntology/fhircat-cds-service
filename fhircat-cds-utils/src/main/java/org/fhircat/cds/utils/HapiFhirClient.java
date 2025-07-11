package org.fhircat.cds.utils;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Patient;

public class HapiFhirClient {

    private IGenericClient client;
    private String fhirBase;

    public String getFhirBase() {
        if( fhirBase == null ) {
            PropertyReader propertyReader = new PropertyReader();
            fhirBase = propertyReader.loadProperties().getProperty("fhir.base");
        }
        return fhirBase;
    }

    public IGenericClient getClient() {
        if (client == null) {
            FhirContext context = FhirContext.forR5();
            client = context.newRestfulGenericClient(getFhirBase());
        }
        return client;
    }

    public static void main(String[] args) {

        HapiFhirClient hapiFhirClient = new HapiFhirClient();

        // Create a client to interact with the FHIR server
        IGenericClient client = hapiFhirClient.getClient();

        Bundle result = client
                .search()
                .forResource(Patient.class)
                .returnBundle(Bundle.class)
                .execute();


        System.out.println(result.getEntry().size());
    }
}
