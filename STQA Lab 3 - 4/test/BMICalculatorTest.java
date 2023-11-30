import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    BMICalculator calculator;

    @BeforeEach
    void setUp(){
        calculator =  new BMICalculator();
    }
    @Test
    void calculateBMI() {
        double weight =  180;
        double height = 1;

        double result = calculator.calculateBMI(weight, height);

        assertEquals(180.0, result, "should return 80/1.75*2 = 22.86 rounded");
    }
    @DisplayName("Throw an exception WeightIsZero")
    @Test
    void calculateBMI_When_Weight_Is_Zero(){
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateBMI(0, 1.80);

        });
    }
    @DisplayName("Throw an exception HeightIsZero")
    @Test
    void calculateBMI_When_Height_Is_Zero(){
        assertThrows(IllegalArgumentException.class , ()->{
            calculator.calculateBMI(180, 0);
        });
    }



    @Test
    void classifyBMI() {
    }
}