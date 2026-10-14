// I restrict this class to one job (Chapter 10: Classes) to maintain high cohesion.
public class BatteryManagementSystem{
    private int chargeLevel;
    private int temperature;
    private PowerState powerState;
    private static final int MAX_CHARGE = 100;
    private static final int MIN_CHARGE_TO_START = 20;
    private static final int DRAIN_RATE = 5;
    private static final int NORMAL_TEMP = 30;

    public BatteryManagementSystem(int initialCharge){
        this.chargeLevel = initialCharge;
        this.temperature = NORMAL_TEMP;
        this.powerState = PowerState.OFF;
    }

    public boolean activate() throws CarSystemException{
        if(chargeLevel >= MIN_CHARGE_TO_START){
            powerState = PowerState.ON;
            return true;
        }
        else{
            throw new CarSystemException("battery too low to start");
        }
    }

    public void drainPower(){
        int newLevel = chargeLevel - DRAIN_RATE;
        if(newLevel >= 0){
            chargeLevel = newLevel;
        }
        else{
            chargeLevel = 0;
        }
    }

    public void recharge(int amount){
        int newLevel = chargeLevel + amount;
        if(newLevel <= MAX_CHARGE){
            chargeLevel = newLevel;
            powerState = PowerState.CHARGING;
        }
        else{
            chargeLevel = MAX_CHARGE;
        }
    }

    public void regulateTemperature(){
        if(temperature > NORMAL_TEMP){
            temperature = NORMAL_TEMP;
        }
    }
    
    public int getChargeLevel(){
        return chargeLevel;
    }
    
    public PowerState getPowerState(){
        return powerState;
    }
}
