import java.util.*;

class StopWaitARQ {
    static void sender(int n) {
        Scanner sc = new Scanner(System.in);
        String data = "";
        int c = 0;
        int canSend = 1;
        while (c++ < n) {
            System.out.println("Enter data: ");
            if (canSend == 1 && sc.hasNextLine()) {
                data = sc.nextLine();
                canSend = 0;
            }
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
            int ack = receiver(data, c);
            if (ack == 1) {
                data = "";
                canSend = 1;
            }
            while (canSend != 1) {
                System.out.println("Frame not sent successfully, resending frame.");
                // timer.schedule(task, 5000);
                ack = receiver(data, c);
                if (ack == 1) {
                    data = "";
                    canSend = 1;
                }
            }
        }
    }

    static int receiver(String data, int c) {
        if (data.length() > 0) {
            System.out.println("Received data: " + data);
            System.out.println("Acknowledgement for: " + c);
            return 1;
        } else {
            System.out.println("Data not received");
            return 0;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames: ");
        int n = sc.nextInt();
        sender(n);
        sc.close();
    }
}