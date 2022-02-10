import java.util.*;

class SimplestProtocol {
    public static String sender(String data) {
        Scanner sc = new Scanner(System.in);
        String frame = "";
        if(sc.hasNextLine()) {
            data = sc.nextLine(); // get data from user
            frame = data; // creating frame
        }
        sc.close();
        return frame; // sending frame
    }

    public static void receiver(String frame) {
        if(frame.length() > 0) 
            System.out.println("Received data: " + frame);
        else
        System.out.println("Data not received");
    }
    public static void main(String[] args) {
        String data[] = new String[5];
        for(int i = 0; i < data.length; i++) {
            String frame = sender(data[i]);
            receiver(frame);
        }
    }
}