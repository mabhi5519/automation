package com.aff.frameworkweb;

public class DriverManagerFactory {

    public static DriverManager getDriverManager(DriverType driverType) {
        DriverManager driverManager = null;

        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;

            case FIREFOX:
                driverManager = new FirFoxDriverManager();
                break;

            case GRID_CHROME:
                driverManager = new RemoteDriverManager();
                break;

            default:
                // chrome is default driver manager
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}
