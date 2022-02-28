// Use random function to generate error every 1 in 4 times

import java.util.*;

class SimplestProtocol {
    public static String sender() {
        Scanner sc = new Scanner(System.in);
        String frame = "";
        if (sc.hasNextLine())
            frame = sc.nextLine(); // get data from user and creating frame
        return frame; // sending frame
    }

    public static void receiver(String frame) {
        if (frame.length() > 0) // checking if frame receieved successfully
            System.out.println("Received data: " + frame);
        else
            System.out.println("Data not received");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfFrames = sc.nextInt();
        for (int i = 0; i < numberOfFrames; i++) {
            String frame = sender();
            System.out.println(frame);
            receiver(frame);
        }
        sc.close();
    }
}