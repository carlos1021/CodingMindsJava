import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.

        assertEquals(0, 0); */
        assertEquals(3, CompoundInterest.numYears(2024));
        assertEquals(4, CompoundInterest.numYears(2025));
        assertEquals(0, CompoundInterest.numYears(2021));
        assertEquals(1, CompoundInterest.numYears(2022));
        assertEquals(100, CompoundInterest.numYears(2121));
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        assertEquals(12.544, CompoundInterest.futureValue(10, 12, 2023),tolerance);
        assertEquals(189.8208, CompoundInterest.futureValue(50, 56, 2024),tolerance);
        assertEquals(21.2336664, CompoundInterest.futureValue(25, -4, 2025),tolerance);
    }



    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
    assertEquals(11.80264966, CompoundInterest.futureValueReal(10, 12, 2023, 3), tolerance);

    }


    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000, 2023, 10), tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
         assertEquals(15571.895,CompoundInterest.totalSavingsReal(5000,2023,10, 3), tolerance);
         assertEquals(202832.5231,CompoundInterest.totalSavingsReal(50000, 2024, 5, 2), tolerance);
    }

}