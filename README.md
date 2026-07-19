<a id="readme-top"></a>



<div align="center">

&#x20; <h3 align="center">Secure Coding Solutions: British Airways Case Study</h3>



&#x20; <p align="center">

&#x20;   Executable Java implementations to mitigate critical security vulnerabilities.

&#x20; </p>

</div>



<details>

&#x20; <summary>Table of Contents</summary>

&#x20; <ol>

&#x20;   <li><a href="#about-the-project">About The Project</a></li>

&#x20;   <li><a href="#built-with">Built With</a></li>

&#x20;   <li><a href="#getting-started">Getting Started</a></li>

&#x20;   <li><a href="#secure-coding-solutions">Secure Coding Solutions</a>

&#x20;     <ul>

&#x20;       <li><a href="#1-secure-credential-hashing">Secure Credential Hashing</a></li>

&#x20;     </ul>

&#x20;   </li>

&#x20;   <li><a href="#license">License</a></li>

&#x20;   <li><a href="#team">Team</a></li>

&#x20;   <li><a href="#acknowledgments">Acknowledgments</a></li>

&#x20; </ol>

</details>



\## About The Project



This repository contains proof-of-concept (PoC) Java solutions designed to remediate the vulnerabilities identified in the 2018 British Airways data breach. The project demonstrates the implementation of industry-standard security controls, including cryptographic hashing and secure data handling, to prevent privilege escalation and credential compromise.



\## Built With



\* Java (Standard Edition)

\* NetBeans IDE

\* javax.crypto (PBKDF2/HMAC-SHA256)



\## Getting Started



To run these solutions, ensure you have the Java Development Kit (JDK) installed.



\### Prerequisites



\* JDK 8 or higher

\* NetBeans IDE (or any Java-compatible IDE)



\### Installation



1\. Clone the repo

&#x20;  ```sh

&#x20;  git clone https://github.com/TingRongYou/Y3S1SoftwareSecurityAndSafety.git

&#x20;  ```

2\. Open the project folder in NetBeans

3\. Build and run the `SecureCredentialHashing.java` (or subsequent solution) file to see the PoC output.



<!-- Secure Coding Solutions -->

\## Secure Coding Solutions



\### 1. Secure Credential Hashing

This solution addresses the \*\*Plaintext Storage of Credentials\*\* vulnerability. It uses the `PBKDF2WithHmacSHA256` algorithm to perform iterative, salted hashing on administrator passwords.



\*   \*\*Key Security Features:\*\*

&#x20;   \*   \*\*Cryptographic Salt:\*\* Uses `SecureRandom` (16-byte) to defend against offline dictionary attacks.

&#x20;   \*   \*\*Work Factor:\*\* Implements 600,000 iterations to act as a computational bottleneck against brute-force attempts.

&#x20;   \*   \*\*Irreversibility:\*\* Produces a 256-bit hash, ensuring credentials cannot be reversed even if the database is compromised.



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->

\## License



Distributed under the Unlicense License.



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- TEAM -->

\## Team



\* Ting Rong You

\* Yong Chong Xin

\* Lim Wen Liang

\* Anson

\* Wan Zi Kang

\* Nur Aina Lee



<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->

\## Acknowledgments



\* \[NIST SP 800-63B Digital Identity Guidelines](https://doi.org/10.6028/NIST.SP.800-63b)

\* \[OWASP Password Storage Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Password\_Storage\_Cheat\_Sheet.html)

\* \[OWASP Top 10:2021 - Cryptographic Failures](https://owasp.org/Top10/A02\_2021-Cryptographic\_Failures/)



<p align="right">(<a href="#readme-top">back to top</a>)</p>

