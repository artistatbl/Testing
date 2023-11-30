import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void beforeEachTestMethod(){
        calculator =  new Calculator();
    }

    @DisplayName("Division by zero")
    @Test
    void testIntegerVision_WhenDivideByZero_ShouldThrowArithmeticException() {

        int dividend = 9;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        ArithmeticException actualException =  assertThrows(ArithmeticException.class, () ->{
            calculator.integerDivision(dividend, divisor);
        }, "Division by zero should have throw an Arithmetic exception");
        assertEquals(expectedExceptionMessage, actualException.getMessage(),
        "Unexpected exception message");
    }
    @DisplayName("should return 3")
    @Test
    void testIntegerDivision(){
        int result = calculator.integerDivision(6,2);

        assertEquals(3, result, "6/2 did not produce 3");
    }
}