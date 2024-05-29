package com.se.ecostruxure_mmirzakhani.utils;

import com.se.ecostruxure_mmirzakhani.be.entities.Change;
import com.se.ecostruxure_mmirzakhani.be.entities.Contract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectServiceTest {

    @Test
    @DisplayName("Test compare with equal objects")
    public void testCompare_withEqualObjects() {
        // Create two equal objects for comparison

        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setAnnualSalary(100_000);
        contract1.setFixedAnnualAmount(5_000);

        Contract contract2 = new Contract();
        contract2.setId(1);
        contract2.setAnnualSalary(100_000);
        contract2.setFixedAnnualAmount(5_000);


        // Compare the two objects
        List<Change> changes = ObjectService.compare(contract1, contract2);

        // Assert that no changes are detected
        assertEquals(0, changes.size());
    }

    @Test
    @DisplayName("Test compare a contract when salary increased")
    public void testCompare_withPartiallyDifferentObjects() {
        // Create two partially different objects for comparison
        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setAnnualSalary(100_000);
        contract1.setFixedAnnualAmount(5_000);

        Contract contract2 = new Contract();
        contract2.setId(1); // Same contract
        contract2.setAnnualSalary(120_000);
        contract2.setFixedAnnualAmount(5_000);

        // Compare the two objects
        List<Change> changes = ObjectService.compare(contract1, contract2);

        // Assert that changes are detected only for the annual salary property
        assertEquals(1, changes.size());
        assertEquals("100000.0", changes.get(0).getPreviousState());
        assertEquals("120000.0", changes.get(0).getCurrentState());
    }


    @Test
    @DisplayName("Test compare with different objects")
    public void testCompare_withDifferentObjects() {
        // Create two different objects for comparison
        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setAnnualSalary(100_000);
        contract1.setFixedAnnualAmount(5_000);

        Contract contract2 = new Contract();
        contract2.setId(2);
        contract2.setAnnualSalary(120_000);
        contract2.setFixedAnnualAmount(6_000);

        // Compare the two objects
        List<Change> changes = ObjectService.compare(contract1, contract2);

        // Assert that changes are detected for each property
        assertEquals(3, changes.size());
    }
}