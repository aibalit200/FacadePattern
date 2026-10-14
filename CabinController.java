public class CabinController{
    private ClimateMode currentClimate;
    private boolean isSeatAdjusted;

    public CabinController(){
        this.currentClimate = ClimateMode.OFF;
        this.isSeatAdjusted = false;
    }

    public void setClimate(ClimateMode mode){
        currentClimate = mode;
    }

    public void adjustSeat(){
        isSeatAdjusted = true;
    }

    public void turnOffCabin(){
        currentClimate = ClimateMode.OFF;
        isSeatAdjusted = false;
    }
    
    public ClimateMode getCurrentClimate(){
        return currentClimate;
    }
}
