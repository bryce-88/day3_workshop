package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        String dirPath = "\\data2";
        String fileName = "";

        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to My Shopping Cart");

        List<String> cartItems = new ArrayList<String>();
        
        Console cons = System.console();

        String input = "";

        while (!input.equals("quit")) {
            input = cons.readLine("What do you want to perform? (Type 'quit' to exit program)");

            switch(input) {
                case "help":
                    System.out.println("Enter 'list' to show a list of items in shopping cart");
                    System.out.println("'login <name>'' to access your shopping cart");
                    System.out.println("add <item> to add items into your shopping cart");
                    System.out.println("delete <item #>");
                    System.out.println("'quit' to exit this program");                    
                    break;
                case "list":
                    if (cartItems.size() > 0) {
                        for (String item : cartItems) {
                            System.out.println(item);
                        }
                    } else {
                        System.out.println("Your cart is empty");
                    }
                    break;
                case "user":
                    break;
                default:
            }

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');
                
                String strValue = "";
                Scanner scanner = new Scanner(input.substring(4));
                
                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);
                }
            }


        }


    }

    // public void displayMessage(String message) {
    //     System.out.println(message);
    // }

}
