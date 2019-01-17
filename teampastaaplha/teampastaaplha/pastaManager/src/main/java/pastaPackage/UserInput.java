package pastaPackage;

import java.util.Scanner;

public class UserInput {

    private static Scanner readIn = new Scanner(System.in);

    public static int readInt(String question){
        System.out.println(question);
        int result = readIn.nextInt();
        readIn.nextLine();
        return result;
    }

    public static String readString(String question){
        System.out.println(question);
        String result = readIn.nextLine();
        return result;
    }

    public static double readDouble(String question){
        System.out.println(question);
        double result = readIn.nextDouble();
        readIn.nextLine();
        return result;
    }
}