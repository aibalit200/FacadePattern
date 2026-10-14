// I use custom exceptions (Chapter 7: Error Handling) to separate error logic from the happy path.
public class CarSystemException extends Exception{
    public CarSystemException(String message){
        super(message);
    }
}
