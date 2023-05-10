package com.iharkusha.util.menu_mini_framework;

import java.io.Console;


public class ConsoleUtils {
    private static Console console = System.console();

    private ConsoleUtils() {
    }

    public static void pauseExecution() {
        System.out.print("Press Enter to Continue... ");
        console.readLine();
    }

    public static boolean requestConfirmation() {
        while (true) {
            System.out.print("Confirm Operation (y/n)... ");
            String in = console.readLine().toLowerCase();
            if (in.equals("y") || in.equals("yes"))
                return true;
            else if (in.equals("n") || in.equals("no"))
                return false;
        }
    }
}