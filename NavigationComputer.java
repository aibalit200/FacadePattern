public class NavigationComputer{
    private boolean isRouteCalculated;
    private String currentDestination;
    private int distanceRemaining;
    private static final int BASE_DISTANCE = 10;
    private static final int TRAFFIC_FACTOR = 20;
    private static final int ROUTE_DISTANCE_FACTOR = 5;

    public NavigationComputer(){
        this.isRouteCalculated = false;
        this.currentDestination = "";
        this.distanceRemaining = 0;
    }

    public void calculateRoute(String destination){
        currentDestination = destination;
        distanceRemaining = (BASE_DISTANCE + TRAFFIC_FACTOR) * ROUTE_DISTANCE_FACTOR;
        isRouteCalculated = true;
    }

    public void updateGps(){
        if(isRouteCalculated && distanceRemaining > 0){
            distanceRemaining = distanceRemaining - 1;
        }
    }

    public void clearRoute(){
        isRouteCalculated = false;
        currentDestination = "";
        distanceRemaining = 0;
    }
    
    public boolean hasRoute(){
        return isRouteCalculated;
    }
    
    public String getCurrentDestination(){
        return currentDestination;
    }
}
