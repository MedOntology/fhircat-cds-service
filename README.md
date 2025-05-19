# fhircat-cds-service
A OpenCDS Service for the FHIRCat ClinGen demonstration project.

This project consists of 7 modules and is a work in progress.

To compile this project, you will need to run:

```aiignore
> mvn clean install -DskipTests
```
Failing tests will be fixed in a subsequent iteration.

The project consists of the following modules:

* fhircat-cds-utils: cross-cutting utilities such as a file reader.
* clingen-kb: A mock knowledge base and logical model to be eventually replaced with an actual knowledge base service and logical model library.
* thiopurine-knowledge-module: The thiopurine CDS knowledge module (CDS Hooks endpoint)
* cds-service: the CDS server, an OpenCDS server implementation
* opencds-postprocess-plugin: a currently no-op post-processing plugin. At some point, this plugin will transform logical model payloads back to an IG-compliant CDS Hooks card.
* opencds-preprocess-plugin: a currently no-op pre-processing plugin. At some point, this plugin will transform FHIR payloads to a logical model payload.

Please note the following TODOs:

* Code documentation - code is currently poorly documented.
* Replacement of in-memory indexes with calls to a knowledge repository or FHIR server - currently much is hard-coded to support this example.
* Better separation of concerns: knowledge module currently operates on FHIR and logical models by handling transforms. Decoupling of FHIR and logical model will be done in the future.
* Hard-coded transformations of FHIR-logical-model. We will make these declarative over time.
* Standing up of FHIR server when R6 is available and of knowledge base.
* Use of a real terminology server rather than a static Terminology class.

