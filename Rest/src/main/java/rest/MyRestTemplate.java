package rest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class MyRestTemplate {

    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.printAllCommand();
        String cmd = "";
        while (!cmd.equals("exit")) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.println("Enter command");
                cmd = in.nextLine();
                userInterface.getCommand(cmd);
            } catch (ResponseStatusException | HttpClientErrorException | HttpServerErrorException ex) {
                String result = ex.getMessage();
                result = Objects.requireNonNull(result).substring(result.indexOf("\"message\":\""), result.indexOf(",\"path\""));
                System.out.println(result);
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("Wrong input format");
            } catch (BadCredentialsException ex){
                System.out.println("Authentication fail");
            }
        }

    }

}
