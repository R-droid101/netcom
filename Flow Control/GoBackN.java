// Raggav Subramani - 20BCT0127

/*
Algorithm:
Sender:
Sw = 2^m - 1;
Sf = 0;
Sn = 0;

while(true)   // Repeat forever
{
    WaitForEvent();
    if(Event(RequestToSend))   // A packet to send
    {
        if(Sn-Sf >= Sw)    // If window is full
            sleep();
        GetData();
        MakeFrame(Sn);
        StoreFrame(Sn);
        SendFrame(Sn);
        Sn = Sn + 1;
        if(timer not running)
            StartTimer();
    }
    if(Event(ArrivalNotification)) // ACK arrives
    {
        Receiver(ACK);
        if(corrupted(ACK))
            sleep();
        if((ackNo>Sf) && (ackNo<=Sn))   // If a valid ACK
        While(Sf <= ackNo)
        {
            PurgeFrame(Sf);
            Sf = Sf + 1;
        }
        If(Sf == Sn) // the window is empty
            StopTimer();
        else
            StartTimer();
    }
    if(Event(TimeOut))   // The timer expires
    {
        StartTimer();
        Temp = Sf;
        while(Temp < Sn)
        {
            ResendFrame(Temp);
            Temp = Temp + 1;
        }
    }
}

Receiver:
Rn = 0; 

while(true)     // Repeat forever
{
    WaitForEvent();
    if(Event(ArrivalNotification))   // A packet arrives
    {
        ReceiveFrame(Rn);
        if(corrupted(frame))
            sleep();
        if(Rn == Sn)    // Valid data frame
        {
            DeliverData();    // Deliver data
            Rn = Rn + 1;      // Slide Window
            SendACK(Rn);
        }
    }
}
*/

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