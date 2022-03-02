// Raggav Subramani - 20BCT0127

/*
Algorithm:
Sender:
Sn = 0;  // Frame 0 should be sent first
canSend = true;   // It will allow the first frame to go.
while(true)                //Repeat forever
{
   WaitForEvent();                  //sleep until the occurrence of an event
   if(Event(RequestToSend) AND canSend)
    {
       GetData();
       MakeFrame(Sn);    // The seqNo is Sn
       StoreFrame(Sn);   // Keep copy
       SendFrame(Sn);   //Send the data frame
       StartTimer();
       Sn = Sn + 1;
       canSend=false;   //cannot send until the acknowledgement arrives.
    }
    WaitForEvent(); // Sleep
    if(Event(AckReceived))   // An ACK has arrived
    {
        ReceiveFrame(ackNo);   // Receive the ACK frame
        if(not corrupted AND ackNo == Sn)  // Valid ACK
        {
            StopTimer();
            PurgeFrame(Sn-1);   // Copy is not needed
            canSend = true;
        }
    }
    if(Event(Timeout))   // The timer expired
    {
        StartTimer();
        ResendFrame(Sn-1);    // Resend a copy check
    }
}

Receiver:
Rn = 0;    // Frame 0 should be received first
while(true) 
{
    WaitForEvent();    // Sleep until an event occurs
    if(Event(ReceiveFrame))   // Data frame arrives
    {
        ReceiveFrame(Rn);
        if(corrupted(frame))
            sleep()
        if(Rn == Sn)    // Valid data frame
        {
            ExtractData();
            DeliverData();    // Deliver data
            Rn = Rn + 1;
        }
        SendFrame(Rn);    // Send an ACK
    }
}
*/


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
