import java.util.*;
public class StopAndWait {
    public static void sender(int frames, Scanner sc) {
        // Scanner sc = new Scanner(System.in);
        int canSend = 1;
        System.out.println("Input data frames: ");
        for (int i = 0; (i < frames); i++) {
            if(canSend == 0) { // Since acknowledgement is not received, it is stuck for an infinite amount of time
                System.out.println("Waiting for acknowledgement...");
                return;
            }
            String frame = "";
            if (sc.hasNextLine() && canSend == 1)
            frame = sc.nextLine(); // creating frame
            System.out.print("Sending Frame: " + frame);
            canSend = receiver(frame); // sending frame
        }
    }

    public static int receiver(String data) {
        if ((int) ((Math.random() * 3) + 1) % 3 != 0) { // checking if frame received successfully
            System.out.println("\t\tReceived data: " + data + "\n");
            return 1; // sending acknowledgement
        } else {
            System.out.println("\t\tData not received");
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of data items to be sent: ");
        int frames = sc.nextInt();
        sc.nextLine();
        sender(frames, sc);
        sc.close();
    }
}
