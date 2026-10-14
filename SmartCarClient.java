// I keep the client ignorant of inner parts (Chapter 8: Boundaries) by interacting only with the facade.
public class SmartCarClient{
    public static void main(String[] args){
        SmartCarFacade myCar = new SmartCarFacade(80);

        System.out.println("Starting normal drive to Downtown.");
        try{
            myCar.beginAutopilotJourney("Downtown");
            System.out.println("Pausing the trip.");
            myCar.pauseJourney();
            System.out.println("Resuming the trip to Uptown.");
            myCar.resumeJourney();
            System.out.println("Parking the car.");
            myCar.autoPark();
        }
        catch(CarSystemException error){
            System.out.println("Drive failed: " + error.getMessage());
        }

        System.out.println("Testing a broken sensor.");
        try{
            myCar.simulateSensorFailure();
            System.out.println("Trying to drive to the Airport.");
            myCar.beginAutopilotJourney("Airport");
        }
        catch(CarSystemException error){
            System.out.println("Drive stopped: " + error.getMessage());
        }
    }
}