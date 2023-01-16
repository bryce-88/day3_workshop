package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    public static void main(String[] args) throws IOException {
        String dirPath = "data2";
        String fileName = "";

        // instantiate a file/directory object
        File newDirectory = new File(dirPath);
        //if directory exists, print to console
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

            //use switch for static function to perform
            switch(input) {
                case "help":
                    System.out.println("Enter 'list' to show a list of items in shopping cart");
                    System.out.println("'login <name>'' to access your shopping cart");
                    System.out.println("add <item> to add items into your shopping cart");
                    System.out.println("delete <item #>");
                    System.out.println("'quit' to exit this program");                    
                    break;
                case "list":
                    listCart(cartItems);
                    break;
                case "user":
                    listUsers(dirPath);
                    break;
                default:
            }

            if (input.startsWith("login")) {
                fileName = createLoginFile(input, dirPath, fileName);
            } 

            if (input.startsWith("add")) {

                String strValue = "";

                input = input.replace(',', ' ');
                
                Scanner scanner = new Scanner(input.substring(4));

                FileWriter fw = new FileWriter(dirPath + File.separator + fileName);
                PrintWriter pw = new PrintWriter(fw);
                
                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);

                    pw.printf("%s\n", strValue);
                }

                pw.flush();
                fw.flush();
                pw.close();
                fw.close();
            }

            if (input.startsWith("delete")) {
                cartItems = deleteCartItem(cartItems, input);
            }

        }

    }

    public static void listCart(List<String> cartItems) {
        if (cartItems.size() > 0) {
            //     for (String item : cartItems) {
            //         System.out.println(item);
            //     }

            for (int i = 0; i < cartItems.size(); i++) {
            System.out.printf("%d: %s\n", i, cartItems.get(i));
            }
        } else {
            System.out.println("Your cart is empty");
        }
    }
    

    public static List<String> deleteCartItem(List<String> cartItems, String input) {

        String strValue = "";

        Scanner scanner = new Scanner(input.substring(6));

        while (scanner.hasNext()) {
            strValue = scanner.next();
            int listItemIndex = Integer.parseInt(strValue);

            if (listItemIndex < cartItems.size()) {
                cartItems.remove(listItemIndex); 
            } else {
                System.out.println("Incorrect item index");
            }
        }

        return cartItems;
    }

    public static void updateCartItemToFile(List<String> cartItems, String dirPath, String fileName) throws IOException {
        FileWriter fw = new FileWriter(dirPath + File.separator + fileName, false);
        BufferedWriter bw = new BufferedWriter(fw);

        int listCount = 0;
        while (listCount < cartItems.size()) {
            bw.write(cartItems.get(listCount));
            bw.newLine();
            listCount++;
        }

        bw.flush();
        fw.flush();
        bw.close();
        fw.close();

    }

    public static void listUsers(String dirPath) {
        File directoryPath = new File(dirPath);

        String contents[] = directoryPath.list();
        for (String file : contents) {
            System.out.println(file);
        }
    }

    public static String createLoginFile(String input, String dirPath, String fileName) throws IOException {
        input = input.replace(',', ' ');

        Scanner scanner = new Scanner((input.substring(6)));

        while (scanner.hasNext()) {
            fileName = scanner.next();
        }

        //define the filepath + filename
        File loginFile = new File(dirPath + File.separator +fileName);

        // try to create a file
        // isCreated set to true if its a new file to create
        // isCreated is set to false if named file already exists
        boolean isCreated = loginFile.createNewFile();

        if (isCreated) {
            System.out.println("New file created successfully" + loginFile.getCanonicalFile());
        } else {
            System.out.println("File already created");
        }

        return fileName;
    }

    public static List<String> readCartItemFromFile(String dirPath, String fileName) throws IOException {

        List<String> items = new ArrayList<>();

        File file = new File(dirPath + File.separator + fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));
        String sr;

        while ((sr = br.readLine()) != null) {
            items.add(sr);
        }
        br.close();

        return null;
    }

}
