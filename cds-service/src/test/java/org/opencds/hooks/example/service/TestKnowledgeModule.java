package org.opencds.hooks.example.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.opencds.hooks.evaluation.service.HookEvaluation;
import org.opencds.hooks.model.r5.util.R5JsonUtil;
import org.opencds.hooks.model.response.CdsResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.http.HttpResponse;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = AppConfiguration.class)
public class TestKnowledgeModule {

    @Test
    public void test() {

        String cdsHooksPayload =  """
                    {
                      "hookInstance": "d1577c69-dfbe-44ad-ba6d-3e05e953b2ea",
                      "fhirServer": "http://fhircat.org:8080",
                      "hook": "patient-view",
                      "fhirAuthorization": {
                        "access_token": "some-opaque-fhir-access-token",
                        "token_type": "Bearer",
                        "expires_in": 300,
                        "scope": "user/Patient.read user/Observation.read",
                        "subject": "cds-service4"
                      },
                      "context": {
                        "userId": "Practitioner/example",
                        "patientId": "1288992",
                        "encounterId": "89284"
                      },
                      "prefetch": {
                        "patientToGreet": {
                          "resourceType": "Patient",
                          "gender": "male",
                          "birthDate": "1925-12-23",
                          "id": "1288992",
                          "active": true
                        }
                      }
                    }
                    """;

        RestClient client = new RestClient();
        HttpResponse<String> response = client.invokeService("http://localhost:8080/clingen-cds-service/r5/hooks/cds-services/thiopurine-knowledge-module", cdsHooksPayload);
        String s = response.body();
        System.out.println(s);
        R5JsonUtil r5JsonUtil = new R5JsonUtil();
        CdsResponse cdsHooksResponse = r5JsonUtil.fromJson(s, CdsResponse.class);
        cdsHooksResponse.getCards().forEach(card -> System.out.println(card.toString()));

    }
}
