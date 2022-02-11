package it.polimi.telcodb.services;

import org.springframework.stereotype.Service;

@Service
public class ExceptionFormatterService {

    public String formatSQLUserAlreadyExists(String exceptionText) {
        StringBuilder output = new StringBuilder("DataBase error");

        if (exceptionText.contains("Duplicate") && exceptionText.contains("user")) {
            output = new StringBuilder();
            String[] exceptionTokens = exceptionText.split("'");
            output.append("An User with username '").append(exceptionTokens[1]).append("' ").append("already exists in the Database!");
        }

        return output.toString();
    }


    public String formatSQLServicePackageAlreadyExists(String exceptionText) {
        String output = "DataBase error";

        if (exceptionText.contains("Duplicate")) {
            String[] exceptionTokens = exceptionText.split("'");
            output = "A Service Package with name '" + exceptionTokens[1] + "' already exists in the Database!";
        }

        return output;
    }


    public String formatSQLGenericElementAlreadyExists(String exceptionText) {
        StringBuilder output = new StringBuilder("DataBase error");

        if (exceptionText.contains("Duplicate")) {
            output = new StringBuilder();
            String[] exceptionTokens = exceptionText.split("'");
            String[] parametersTokens = exceptionTokens[1].split("-");
            output.append("An object with value").append(parametersTokens.length > 1 ? "s: " : ": ");
            for (String parametersToken : parametersTokens) {
                output.append("'").append(parametersToken).append("'").append(" ");
            }
            output.append("already exists in the Database!");
        }

        return output.toString();
    }

}
