package org.opencds.hooks.example.service

import org.junit.jupiter.api.extension.ExtendWith
import org.opencds.config.api.ConfigurationService
import org.opencds.config.api.model.impl.KMIdImpl
import org.opencds.config.api.service.ExecutionEngineService
import org.opencds.config.api.service.KnowledgeModuleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfiguration.class)
class OpenCDSSpringContextSpec extends Specification {

    @Autowired
    ConfigurationService configurationService

    KnowledgeModuleService knowledgeModuleService
    ExecutionEngineService executionEngineService

    def setup() {
        assert configurationService
        knowledgeModuleService = configurationService.getKnowledgeRepository().getKnowledgeModuleService()
        assert knowledgeModuleService
        executionEngineService = configurationService.getKnowledgeRepository().getExecutionEngineService()
        assert executionEngineService
    }

    def "test me"() {
        when:
        var km = knowledgeModuleService.find(KMIdImpl.create('org.opencds.example', 'example-knowledge-module-r4', '1.0'))

        then:
        notThrown(Exception)
        km

        when:
        km = knowledgeModuleService.find(KMIdImpl.create('org.opencds.example', 'example-knowledge-module', '1.0'))

        then:
        notThrown(Exception)
        km

        when:
        var ee = executionEngineService.find(km.getExecutionEngine())

        then:
        notThrown(Exception)
        ee

        when:
        var adapter = executionEngineService.getExecutionEngineAdapter(ee)

        then:
        notThrown(Exception)
        adapter
    }
}
