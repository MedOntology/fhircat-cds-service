package org.opencds.hooks.example.service

import spock.lang.Specification

class OpenCDSHooksServicesFunctionalSpec extends Specification {
    private static final URL_R4 = 'http://localhost:38580/opencds-hooks/r4/hooks/cds-services'
    private static final URL_STU3 = 'http://localhost:38580/opencds-hooks/stu3/hooks/cds-services'

    def 'test r4 hooks'() {
        when:
        var services = new URL(URL_R4).text

        then:
        noExceptionThrown()
        services
        services == '{"services":[{"hook":"patient-view","title":"Example Knowledge Module for FHIR R4","description":"Example Description","id":"example-knowledge-module-r4","prefetch":{"Patient":"Patient/{{context.patientId}}"}}]}'
    }

    def 'test stu3 hooks'() {
        when:
        var services = new URL(URL_STU3).text

        then:
        noExceptionThrown()
        services
        services == '{"services":[{"hook":"patient-view","title":"Example Knowledge Module","description":"Example Description","id":"example-knowledge-module","prefetch":{"Patient":"Patient/{{context.patientId}}"}}]}'
    }
}
