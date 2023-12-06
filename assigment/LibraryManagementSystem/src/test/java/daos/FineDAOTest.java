package daos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import models.Fine;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FineDAOTest {

    private FineDAO fineDAO;
    private Fine testFine;

    @BeforeEach
    void setUp() {
        fineDAO = new FineDAO();
        testFine = new Fine(1, 10.0, "Late return", LocalDate.now(), null);
        assertTrue(fineDAO.addFine(testFine), "Fine should be added successfully");
    }

    @Test
    void testAddAndFetchFine() {
        // Fetch the fines and find the test fine
        List<Fine> fines = fineDAO.fetchAllFines();
        boolean found = fines.stream().anyMatch(f -> f.getMemberId() == testFine.getMemberId() && f.getAmount() == testFine.getAmount());
        assertTrue(found, "Test fine should be found in the database");
    }

    @Test
    void testDeleteFine() {
        // Assuming you have a way to get the fineId of the testFine after adding it
        assertTrue(fineDAO.deleteFine(testFine.getFineId()), "Fine should be deleted successfully");
        // Verify that the fine is no longer in the database
        List<Fine> fines = fineDAO.fetchAllFines();
        boolean found = fines.stream().anyMatch(f -> f.getFineId() == testFine.getFineId());
        assertFalse(found, "Test fine should not be found after deletion");
    }
    @Test
    void testCalculateFine() {
        Fine fine = new Fine();
        LocalDate dueDate = LocalDate.now().minusDays(10);
        LocalDate returnDate = LocalDate.now();

        double calculatedFine = fine.calculateFine(dueDate, returnDate);
        assertEquals(5.0, calculatedFine, "Fine calculation for 10 days late should be correct");
    }


    @AfterEach
    void tearDown() {
        // Clean up - delete the test fine
        fineDAO.deleteFine(testFine.getFineId());
    }
}
