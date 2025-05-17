package org.opencds.hooks.example.service;

import org.junit.Test;
import org.opencds.hooks.model.r5.util.R5JsonUtil;
import org.opencds.hooks.model.response.CdsResponse;
import org.fhircat.cds.utils.ResourceFileReader;

import org.opencds.hooks.model.response.Card;
import java.net.URL;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestKnowledgeModule {

    @Test
    public void test() {
        ResourceFileReader resourceFileReader = new ResourceFileReader();
        URL url = java.lang.ClassLoader.getSystemResource("payloads/test1.json");
        System.out.println(url.getPath());
        String cdsHooksPayload = resourceFileReader.getFileContentAsString("payloads/test1.json");

        RestClient client = new RestClient();
        HttpResponse<String> response = client.invokeService("http://localhost:8080/clingen-cds-service/r5/hooks/cds-services/thiopurine-knowledge-module", cdsHooksPayload);
        String s = response.body();
        System.out.println(s);
        R5JsonUtil r5JsonUtil = new R5JsonUtil();
        CdsResponse cdsHooksResponse = r5JsonUtil.fromJson(s, CdsResponse.class);
        Card card = cdsHooksResponse.getCards().get(0);
        assertEquals("Start with reduced starting doses (20%-50% of normal dose) if normal starting dose is 2-3 mg/kg/day (e.g., 0.4 – 1.5 mg/kg/day), and adjust doses of azathioprine based on degree of myelosuppression and disease-specific guidelines. Allow 2-4 weeks to reach steady-state after each dose adjustment (PMID 20354201, 11302950, 15606506, 16530532).", card.getDetail());
    }
}
