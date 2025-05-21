
# Amazon Add To Cart Test Automation

This is a Selenium TestNG automation framework for testing the **Amazon Add To Cart** functionality.
 The framework uses the Page Object Model (POM) design pattern and supports data-driven testing using Excel.

---

## 📁 Project Structure

```
AmazonAddToCartTest
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com.BaseTest
│   │   │   ├── com.POM
│   │   │   └── com.Util
│   │   └── resources
│   │       ├── config.properties
│   │       └── TestData
│   │
│   ├── test
│   │   ├── java
│   │   │   └── com.Test
│   │   └── resources
│
├── testng.xml
├── pom.xml
├── ExtentReport.html
```

---

## ✅ Features

- TestNG based test framework.
- Selenium WebDriver for browser automation.
- Page Object Model (POM) design pattern.
- Extent Reports integration for detailed reports.
- Data-driven testing using Excel and Apache POI.
- Environment configuration via `config.properties`.

---

## 🔧 Technologies Used

- Java
- Maven
- TestNG
- Selenium WebDriver
- Apache POI (Excel integration)
- Extent Reports

---

## 🔌 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/AmazonAddToCartTest.git
cd AmazonAddToCartTest
```

### 2. Import into IDE

- Open IntelliJ or Eclipse
- Import as a Maven project

### 3. Install Dependencies

```bash
mvn clean install
```

### 4. Configure Properties

Edit the `config.properties` file located at:

```
src/main/resources/config.properties
```

### 5. Update Test Data

Place your Excel files in:

```
src/main/resources/TestData
```

---

## 🚀 Running the Tests

### Using Maven & TestNG Suite:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### From IDE:

- Right-click `AmazonAddToCartTest.java` or `testng.xml` > Run as > TestNG Suite

---

## 📊 Reports

After test execution, open the `ExtentReport.html` file from the project root in your browser to view detailed test results with screenshots.

---

## 📁 Key Packages

### `com.BaseTest`
- `BaseTest.java`: Base class for initializing and quitting WebDriver.
- `DriverFactory.java`: Driver manager for different browsers.
- `ExtentReportListener.java`, `ExtentTestManager.java`: Hooks for Extent Reports.
- `TestListener.java`: Implements ITestListener for test events.

### `com.POM`
- `HomePage.java`: Amazon home page interactions.
- `LeftSideNavBarPage.java`: Interactions with side navigation.
- `LoginPage.java`: User login actions.

### `com.Util`
- `ReadDataFromExcel.java`: Read Excel files.
- `DataProviderClass.java`: Supplies test data to test methods.
- `ReadProperties.java`: Reads properties from config file.
- `ReusableMethods.java`: Common helper functions.
- `BrowserEventHandler.java`: Custom WebDriver event handling.

### `com.Test`
- `AmazonAddToCartTest.java`: Test case for adding items to cart.
- `AmazonDropDownTest.java`: Test case for dropdown interaction.

---

## 👨‍💻 Author

Shashikumar S  


---
