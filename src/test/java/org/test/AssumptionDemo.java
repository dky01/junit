package org.test;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperties;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class AssumptionDemo {
    private boolean isProductionEnvironment() {
        // Simulate a condition check for the environment
        return System.getenv("ENVIRONMENT") != null && System.getenv("ENVIRONMENT").equals("production");
    }

    @BeforeEach
    void setup() {
        System.setProperty("env", "prod");
    }

    @Test
    void testOnlyInProduction() {
        // Use assumption to skip the test if not in production environment
        // Assumptions.assumeTrue(isProductionEnvironment(), "Test skipped because not in production environment");

        // Test code that should only run in production environment
        System.out.println("Running test in production environment.");
        // Add assertions or test logic here
    }

    @Test
    void testAlways() {
        // This test will run regardless of assumptions
        System.out.println("Running test that always executes.");
        // Add assertions or test logic here
    }

    @Test
    void testOnProd() {
        boolean flag = true;
        Assumptions.assumingThat(
//                isProductionEnvironment(),
                flag,
                () -> {
                    System.out.println("Running test only in production environment.");
                    // Add assertions or test logic here
                }
        );
    }

    @Disabled
    @DisabledOnOs(OS.MAC)
    @DisabledOnJre(JRE.JAVA_8)
    @Test
    void disabled() {
        // This test will be disabled
        System.out.println("Running disabled test.");
        // Add assertions or test logic here
    }

    // @DisabledIfEnvironmentVariable(named = "env", matches = "dev")
    @DisabledIfSystemProperty(named = "env", matches = "prod")
    @Test
    void disabled2() {
        // This test will be disabled
        System.out.println("Running disabled test. " + System.getProperty("env"));
        // Add assertions or test logic here
    }
}
