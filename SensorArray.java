public class SensorArray{
    private SensorStatus lidarStatus;
    private SensorStatus radarStatus;
    private boolean obstacleDetected;

    public SensorArray(){
        this.lidarStatus = SensorStatus.ACTIVE;
        this.radarStatus = SensorStatus.ACTIVE;
        this.obstacleDetected = false;
    }

    public void calibrateLidar() throws CarSystemException{
        if(lidarStatus == SensorStatus.BLOCKED){
            throw new CarSystemException("lidar is blocked by dirt or snow");
        }
        else{
            lidarStatus = SensorStatus.ACTIVE;
        }
    }

    public void scanEnvironment(){
        if(lidarStatus == SensorStatus.ACTIVE && radarStatus == SensorStatus.ACTIVE){
            obstacleDetected = false;
        }
        else{
            obstacleDetected = true;
        }
    }

    public void simulateHardwareFailure(){
        lidarStatus = SensorStatus.BLOCKED;
    }

    public boolean isPathClear(){
        return !obstacleDetected;
    }
}
