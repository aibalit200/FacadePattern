import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// I keep the tests fast and self validating (Chapter 9: Unit Tests) so they run instantly and give clear boolean results.
public class SmartCarFacadeTest{

    private static final int FULL_BATTERY = 80;
    private static final int LOW_BATTERY = 10;
    private static final String DESTINATION = "Downtown";

    @Test
    public void testBeginJourneyWithFullBatterySucceeds(){
        SmartCarFacade myCar = new SmartCarFacade(FULL_BATTERY);

        assertDoesNotThrow(() -> {
            myCar.beginAutopilotJourney(DESTINATION);
        });
    }

    @Test
    public void testBeginJourneyWithLowBatteryThrowsException(){
        SmartCarFacade myCar = new SmartCarFacade(LOW_BATTERY);

        Exception exception = assertThrows(CarSystemException.class, () -> {
            myCar.beginAutopilotJourney(DESTINATION);
        });

        // I check the exact exception message (Chapter 7: Error Handling) to ensure the error provides proper context to the caller.
        assertEquals("battery too low to start", exception.getMessage());
    }

    @Test
    public void testAutoParkWorksAfterEmergencyStop(){
        SmartCarFacade myCar = new SmartCarFacade(FULL_BATTERY);

        assertDoesNotThrow(() -> {
            myCar.beginAutopilotJourney(DESTINATION);
            myCar.emergencyStop();
            myCar.autoPark();
        });
    }
    public static void main(String[] args){
        SmartCarFacadeTest test = new SmartCarFacadeTest();

        System.out.println("Running tests...");

        try{
            test.testBeginJourneyWithFullBatterySucceeds();
            System.out.println("Test 1 passed.");
        } catch(Throwable error){
            System.out.println("Test 1 failed: " + error.getMessage());
        }

        try{
            test.testBeginJourneyWithLowBatteryThrowsException();
            System.out.println("Test 2 passed.");
        } catch(Throwable error){
            System.out.println("Test 2 failed: " + error.getMessage());
        }

        try{
            test.testAutoParkWorksAfterEmergencyStop();
            System.out.println("Test 3 passed.");
        } catch(Throwable error){
            System.out.println("Test 3 failed: " + error.getMessage());
        }
    }
}