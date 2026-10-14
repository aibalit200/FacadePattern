// I built this facade (Chapter 11: Systems) to provide a simple, unified API that hides subsystem complexity.
public class SmartCarFacade{
    private BatteryManagementSystem battery;
    private MotorController motor;
    private NavigationComputer navigation;
    private SensorArray sensors;
    private CabinController cabin;

    public SmartCarFacade(int initialBattery){
        this.battery = new BatteryManagementSystem(initialBattery);
        this.motor = new MotorController();
        this.navigation = new NavigationComputer();
        this.sensors = new SensorArray();
        this.cabin = new CabinController();
    }

    public void beginAutopilotJourney(String destination) throws CarSystemException{
        battery.activate();
        battery.regulateTemperature();
        sensors.calibrateLidar();
        sensors.scanEnvironment();
        
        if(!sensors.isPathClear()){
            throw new CarSystemException("path is blocked by an obstacle");
        }
        
        navigation.calculateRoute(destination);
        motor.engageMotor();
        motor.shiftMode(DriveMode.AUTOPILOT);
        motor.engageCruiseControl();
        cabin.setClimate(ClimateMode.AUTO);
        cabin.adjustSeat();
    }

    public void pauseJourney() throws CarSystemException{
        if(!motor.isRunning()){
            throw new CarSystemException("car is not moving");
        }
        else{
            motor.applyBrakes();
            cabin.setClimate(ClimateMode.OFF);
        }
    }

    public void resumeJourney() throws CarSystemException{
        if(!navigation.hasRoute()){
            throw new CarSystemException("no route is set");
        }
        else{
            sensors.scanEnvironment();
            if(!sensors.isPathClear()){
                throw new CarSystemException("path is blocked by an obstacle");
            }
            else{
                motor.engageCruiseControl();
                cabin.setClimate(ClimateMode.AUTO);
            }
        }
    }

    public void emergencyStop(){
        motor.applyBrakes();
        motor.stopMotor();
        navigation.clearRoute();
        cabin.turnOffCabin();
    }

    public void autoPark() throws CarSystemException{
        if(!motor.isRunning()){
            motor.engageMotor();
        }
        motor.engageParkingSpeed();
        motor.shiftMode(DriveMode.REVERSE);
        sensors.scanEnvironment();
        
        if(!sensors.isPathClear()){
            throw new CarSystemException("obstacle detected while parking");
        }
        else{
            motor.applyBrakes();
            motor.stopMotor();
            navigation.clearRoute();
            cabin.turnOffCabin();
        }
    }

    public void simulateSensorFailure(){
        sensors.simulateHardwareFailure();
    }
}
