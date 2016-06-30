package ro.teamnet.zerotohero.oop;

/**
 * Created by Eli Ionescu on 6/30/2016.
 */
public class myException extends Exception {
    String message;

    public myException(String message){
        this.message = message;
    }



    @Override
    public String toString() {
        return message;
    }
}
