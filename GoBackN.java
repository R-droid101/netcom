import java.util.*;

public class GoBackN {
    static void sender(int n, int window) {
        Scanner sc = new Scanner(System.in);
        String data[] = new String[n];
        System.out.println("Enter data:");
            for(int i = 0; i < n; i++) { // initializing window of data
                if(sc.hasNextLine()) 
                    data[i] = sc.nextLine();
            }
        int c = 0;
        while(c < n) {
            String frames[] = new String[window];
            for(int i = 0; i < window; i++) {
                frames[i] = data[c + i];
            }


        }
    }

    static int receiver(String data[]) {
        int c = 0;
        for(String frame: data) {
            c++;
            System.out.println("Received frame: " + frame);
        }
        
        return c;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames: ");
        int n = sc.nextInt();
        System.out.println("Enter window size: ");
        int window = sc.nextInt();
        sender(n, window);
        sc.close();

    }

}