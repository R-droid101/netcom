// Raggav Subramani - 20BCT0127

/*
Algorithm:
Sender:
while(true)  //Repeat Forever
{
   WaitForEvent();   //Sleep until there is occurrence of an event
   if(Event(RequestToSend)) //means there is a packet to send
   {
     GetData();
     MakeFrame();    
     SendFrame();   //Send the frame 
   }
}

Receiver:
while(true)  //Repeat Forever
{
   WaitForEvent();   //Sleep until there is occurrence of an event
   if(Event(ArrivalNotification)) //means there is a packet to send
   {
     ReceiveFrame();
     ExtractData();    
     DeliverData();   //Send the frame 
   }
}
*/

import java.util.*;

class SimplestProtocol {
    public static String sender(Scanner sc) {
        // Scanner sc = new Scanner(System.in);
        String frame = "";
        System.out.println("Input data frames:");
        if (sc.hasNextLine())
            frame = sc.nextLine(); // get data from user and creating frame
        System.out.print("Sending Frame: " + frame);
        return frame; // sending frame
    }

    public static void receiver(String frame) {
        if ((int)((Math.random()*3)+1) % 3 == 0) // failure of frame
            System.out.println("\t\tFrame not received");
        else
            System.out.println("\t\tReceived data: " + frame);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of data items to be sent: ");
        int frames = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < frames; i++) {
            String frame = sender(sc);
            receiver(frame);
        }
        sc.close();
    }
}