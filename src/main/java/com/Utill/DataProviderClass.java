package com.Utill;

import org.testng.annotations.DataProvider;

/**
 * Provides test data to TestNG test methods using the @DataProvider annotation.
 * Data is fetched dynamically from Excel sheets via the ReadDataFromExcel utility.
 */
public class DataProviderClass {

    /**
     * Data provider for categories.
     * Retrieves data from the "Cat" sheet in the Excel file.
     *
     * @return A two-dimensional Object array containing category data.
     */
    @DataProvider(name = "Cat")
    public Object[][] getCatData() {
        return ReadDataFromExcel.readDataExcel("Cat");
    }

    /**
     * Data provider for electronics products.
     * Retrieves data from the "Electronics" sheet in the Excel file.
     *
     * @return A two-dimensional Object array containing electronics data.
     */
    @DataProvider(name = "Electronics")
    public Object[][] getElectronicsData() {
        return ReadDataFromExcel.readDataExcel("Electronics");
    }
}
