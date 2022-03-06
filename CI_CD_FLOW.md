# Notes

* Mermaid Flow Github:
  * https://github.com/mermaid-js/mermaid/blob/develop/docs/README.md

### CI Flow:
graph TD
    A[Source Code] -->|Build Trigger| B(Unit Test Execution)
    B --> C(Integration Test Execution)
    C --> D(Sonar Static Code Analysis & Code Coverage Stats)
    D --> E(Vulnerabilities Scan)
    E --> F(Create Image)
    F --> G(Push Image to Registry)

### CD Flow: 
- See this in Continuation to above flow

graph TD
      F(Start) --> G(Pull Image from Registry)
      G --> H(Deploy to Dev)
      H --> I(Run Automation Regression Tests)
      I --> J(Deploy to SIT/UAT)
      J --> K(Run Automation Regression Tests)
      K --> L(Run DAST tool-Wireshark for Security Testing)
      L --> M(Run Performance Tests)
      M --> N(Deploy to Production)