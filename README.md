# Selenium Test Automation for Qubika Website

This project automates a series of tests on the [Qubika website](https://www.qubika.com) using Selenium WebDriver and TestNG. The goal is to validate the behavior of the website, specifically focusing on the contact form, following a predefined set of actions.

## Prerequisites

To run this project, you need to have the following installed:

- **Java JDK** (version 11 or higher)
- **Maven** (to manage dependencies)
- **Selenium WebDriver**
- **TestNG** (for test execution)
- **Allure** (for test reporting)
- **WebDriver executable** (e.g., ChromeDriver for Google Chrome)

### Dependencies

The project uses Maven to manage dependencies. The following dependencies are included in the `pom.xml` file.


## Test Steps

- Navigate to the Qubika Website

- Validate Website Display

- Check that the URL is correct.
- Verify that the Qubika logo is displayed.
- Click on the 'Contact us' button to navigate to the contact form.

- Ensure the 'Name' field is displayed.
- Ensure the 'Email' field is displayed.
- Ensure the 'Get in touch' button is displayed.
- Click the 'Get in touch' button without entering any information in the fields.

- Ensure that all mandatory fields display an error message.

- Validate Red Color for ‘Name’ Field error

- Enter 'Test name' in the 'Name' field.

- Click the 'Get in touch' button with the 'Name' field filled in.

- Ensure that all mandatory fields display an error message except for the 'Name' field.

- Ensure that only the 'Email' field error is marked with a red color.

### Running the Tests
Clone the repository:

git clone <repository-url>
cd <repository-directory>

Run the tests with Maven:

mvn clean test

The test results will be displayed in the console. For detailed execution reports, TestNG will generate an emailable-report.html report in the /test-output directory.

Also Allure Reports are included but I didn't had time to setup properly the @Step to generate a better report, but anyways, it's included and the files are generated under the directory /target/allure-results
