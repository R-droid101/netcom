import java.util.*;

public class StopWaitARQ {
    public static void sender(int frames, Scanner sc) {
        // Scanner sc = new Scanner(System.in);
        int canSend = 1;
        System.out.println("Input data frames: ");
        // tried to implement a timer for timeout but it didn't work
            // Timer timer = new Timer();
            // TimerTask task = new TimerTask() {
            // public void run() {
            // int ack = receiver(data);
            // if(ack == 1) {
            // data = "";
            // canSend = 1;
            // timer.cancel();
            // return;
            // }
            // }
            // };
            // timer.schedule(task, 5000);
        for (int i = 0; i < frames; i++) {
            String frame = "";
            if (sc.hasNextLine() && canSend == 1)
                frame = sc.nextLine(); // creating frame
            System.out.print("Sending Frame: " + frame);
            canSend = receiver(frame); // sending frame
            while (canSend == 0) {
                System.out.println("Resending frame...\n");
                System.out.print("Sending Frame: " + frame);
                canSend = receiver(frame);
            }
        }
    }

    public static int receiver(String data) {
        if ((int) ((Math.random() * 3) + 1) % 3 != 0) { // checking if frame received successfully
            System.out.println("\t\tReceived data: " + data + "\n");
            return 1; // sending acknowledgement
        } else {
            System.out.println("\t\tTimeout - Data not received");
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
