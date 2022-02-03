package it.polimi.telcodb.exceptions;

public class AllServicesAreNullException extends Exception {

    @Override
    public String getMessage() {
        return "No Service has been selected!";
    }

}
