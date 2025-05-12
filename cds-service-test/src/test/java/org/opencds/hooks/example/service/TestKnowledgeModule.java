package org.opencds.hooks.example.service;

import org.junit.Test;
import org.opencds.hooks.model.r5.util.R5JsonUtil;
import org.opencds.hooks.model.response.CdsResponse;
import org.fhircat.cds.utils.ResourceFileReader;

import java.net.URL;
import java.net.http.HttpResponse;

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
        cdsHooksResponse.getCards().forEach(card -> System.out.println(card.toString()));
    }
}
