import java.util.*;

public class GoBackN {

    public static void sender(int window, int frames) {
        int sent[] = new int[window];
        int frame = 1;

        while (frame <= frames) {
            // transmitting the frames
            System.out.println("-----------------------------------------------");
            for (int i = 0; i < window; i++) { // setting the window
                if (frame > frames)
                    break;
                sent[i] = frame++;
                System.out.println("Transmitted Frame: " + sent[i]);
            }
            System.out.println("-----------------------------------------------");
            int ack = receiver(sent, frames);
            // checking if all acknowledgements are received
            if (ack != frame) {
                frame = ack;
            }

        }
    }

    public static int receiver(int sent[], int frames) {
        int ack = sent[0];
        for (int send : sent) {
            if ((int) ((Math.random() * 3) + 1) % 3 != 0) { // if data received successfully
                System.out.println("Received data: " + send);
                ack++;
            } else { // if data not received successfully returning that particular frame number for retransmission
                System.out.println("Acknowledgement for " + send + " not received");
                System.out.println("-----------------------------------------------");
                return ack;
            }
            if (frames == send) 
                break;
        }
        System.out.println("-----------------------------------------------");
        return ack;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames:");
        int frames = sc.nextInt();
        System.out.println("Please enter the Window Size: ");
        int window = sc.nextInt();
        sender(window, frames);
        sc.close();
    }

}