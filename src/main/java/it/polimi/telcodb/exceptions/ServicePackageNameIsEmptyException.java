package it.polimi.telcodb.exceptions;

public class ServicePackageNameIsEmptyException extends Exception {

    @Override
    public String getMessage() {
        return "Service Package name is empty!";
    }

}
