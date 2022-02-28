// Use random function to generate error every 1 in 4 times

import java.util.*;

public class StopAndWait {
    public static void sender(int frames) {
        Scanner sc = new Scanner(System.in);
        int canSend = 1;
        for (int i = 0; i < frames; i++) {
            String frame = "";
            if (sc.hasNextLine() && canSend == 1)
                frame = sc.nextLine(); // creating frame
            canSend = receiver(frame); // sending frame
            if(canSend == 0) {
                frame = sc.nextLine();
                canSend = receiver(frame);
            }
        }
    }

    public static int receiver(String data) {
        if (data.length() > 0) { // checking if frame received successfully
            System.out.println("Received data: " + data);
            return 1; // sending acknowledgement
        } else {
            System.out.println("Data not received");
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int frames = sc.nextInt();
        sender(frames);
        sc.close();
    }
}
