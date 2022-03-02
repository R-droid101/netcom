// Raggav Subramani - 20BCT0127

/*
Algorithm:
Sender:
begin
   frame s; //s denotes frame to be sent
   frame t; //t is temporary frame
   S_window = power(2,m-1); //Assign maximum window size
   SeqFirst = 0; // Sequence number of first frame in window
   SeqN = 0; // Sequence number of Nth frame window
   while (true) //check repeatedly
      do
         Wait_For_Event(); //wait for availability of packet
         if ( Event(Request_For_Transfer)) then
            //check if window is full
            if (SeqN–SeqFirst >= S_window) then
               doNothing();
            end if;
            Get_Data_From_Network_Layer();
            s = Make_Frame();
            s.seq = SeqN;
            Store_Copy_Frame(s);
            Send_Frame(s);
            Start_Timer(s);
            SeqN = SeqN + 1;
         end if;
         if ( Event(Frame_Arrival) then
            r = Receive_Acknowledgement();
            //Resend frame whose sequence number is with ACK
            if ( r.type = NAK) then
               if ( NAK_No > SeqFirst && NAK_No < SeqN ) then
                  Retransmit( s.seq(NAK_No));
                  Start_Timer(s);
               end if
                  //Remove frames from sending window with positive ACK
               else if ( r.type = ACK ) then
                  Remove_Frame(s.seq(SeqFirst));
                  Stop_Timer(s);
                  SeqFirst = SeqFirst + 1;
               end if
         end if
         // Resend frame if acknowledgement haven’t been received
         if ( Event(Time_Out)) then
            Start_Timer(s);
            Retransmit_Frame(s);
         end if
end

Receiver:
Begin
   frame f;
   RSeqNo = 0; // Initialise sequence number of expected frame
   NAKsent = false;
   ACK = false;
   For each slot in receive_window
   Mark(slot)=false;
   while (true) //check repeatedly
      do
         Wait_For_Event(); //wait for arrival of frame
         if ( Event(Frame_Arrival) then
            Receive_Frame_From_Physical_Layer();
            if ( Corrupted ( f.SeqNo ) AND NAKsent = false) then
               SendNAK(f.SeqNo);
               NAKsent = true;
            end if
            if ( f.SeqNo != RSeqNo AND NAKsent = false ) then
               SendNAK(f.SeqNo);
               NAKsent = true;
               if ( f.SeqNo is in receive_window ) then
                  if ( Mark(RSeqNo) = false ) then
                     Store_frame(f.SeqNo);
                     Mark(RSeqNo) = true;
                  end if
               end if
               else
               while ( Mark(RSeqNo))
                  Extract_Data(RSeqNo);
                  Deliver_Data_To_Network_Layer();
                  RSeqNo = RSeqNo + 1;
                  Send_ACK(RSeqNo);
               end while
            end if
         end if
   end while
end
*/

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