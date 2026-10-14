public class MotorController{
    private boolean isRunning;
    private int currentSpeed;
    private DriveMode currentMode;
    private static final int MAX_SPEED = 120;
    private static final int CRUISE_SPEED = 80;
    private static final int PARKING_SPEED = 5;

    public MotorController(){
        this.isRunning = false;
        this.currentSpeed = 0;
        this.currentMode = DriveMode.PARK;
    }

    public void engageMotor() throws CarSystemException{
        if(isRunning){
            throw new CarSystemException("motor is already running");
        }
        else{
            isRunning = true;
        }
    }

    public void stopMotor(){
        isRunning = false;
        currentSpeed = 0;
        currentMode = DriveMode.PARK;
    }

    public void setSpeed(int speed) throws CarSystemException{
        if(!isRunning){
            throw new CarSystemException("cannot set speed while motor is off");
        }
        else{
            if(speed <= MAX_SPEED){
                currentSpeed = speed;
            }
            else{
                currentSpeed = MAX_SPEED;
            }
        }
    }

    public void applyBrakes(){
        currentSpeed = 0;
    }

    public void engageCruiseControl() throws CarSystemException{
        setSpeed(CRUISE_SPEED);
    }

    public void engageParkingSpeed() throws CarSystemException{
        setSpeed(PARKING_SPEED);
    }
    
    public void shiftMode(DriveMode mode){
        currentMode = mode;
    }
    
    public boolean isRunning(){
        return isRunning;
    }
    
    public int getCurrentSpeed(){
        return currentSpeed;
    }
    
    public DriveMode getCurrentMode(){
        return currentMode;
    }
}
