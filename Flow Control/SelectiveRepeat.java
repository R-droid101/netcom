import java.util.*;

public class SelectiveRepeat {

    public static void sender(int window, int frames) {
        int sent[] = new int[window];
        int frame = 1;

        int c = 0, i;
        boolean check = false, check1 = false;
        System.out.println("-----------------------------------------------");
        while (frame <= frames || (check1)) {
            // transmitting the frames
            for (i = c; i < window; i++) { // setting the window
                if (check)
                    break;
                sent[i] = frame++;
                if (frame > frames)
                    check = true;

                c++;
                System.out.println("Transmitted Frame: " + sent[i]);
            }
            System.out.println("-----------------------------------------------");
            int ack[] = receiver(sent, frames, c);
            // checking if all acknowledgements are received
            c = 0;
            for (i = 0; i < ack.length; i++) {
                if (ack[i] != 1) {
                    System.out.println("Retransmitting frame: " + sent[i]);
                    sent[c++] = sent[i];
                    check1 = true;
                }
            }
            if(c == 0)
            check1 = false;
        }
    }

    public static int[] receiver(int sent[], int frames, int j) {
        int ack[] = new int[sent.length];
        for (int i = 0; i < j; i++) {
            if ((int) ((Math.random() * 3) + 1) % 3 != 0) { // if data received successfully
                System.out.println("Received data: " + sent[i]);
                ack[i] = 1;
            } else { // if data not received successfully returning that particular frame number for
                     // retransmission
                System.out.println("Acknowledgement for " + sent[i] + " not received");
                ack[i] = 0;
            }
            if (frames == sent[i])
                break;
        }
        for (int i = j; i < ack.length; i++)
            ack[i] = 1;
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