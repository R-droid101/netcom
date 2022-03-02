// Raggav Subramani - 20BCT0127

/*
Algorithm:
1) Read the number to be transmitted from the user.
2) Calculate the parity of the number on the sender side.
    i) Convert the number to binary.
    ii) Calculate the parity of the binary number.
    iii) If the parity is odd, append 1 to the binary number.
    iv) If the parity is even, append 0 to the binary number.
    v) Return the number after fixing the parity.
3) Send the number to the receiver.
    i) Check the parity of the number received.
    ii) If the parity is odd, print the data is corrupted.
    iii) If the parity is even, print the data is not corrupted.
4) End.
*/

import java.util.*;

public class Parity {

    static int bits[];

    static int sender(int n) {
        int t, c = 0, i = 0;
        int temp = n;
        while (temp > 0) {
            t = temp % 2;
            if (t == 1)
                c++;
            temp = temp / 2;
            bits[i++] = t;
        }
        System.out.println("Number of 1's: " + c);
        if (c % 2 == 1) {
            System.out.println("Odd parity, adding 1 to the number.");
            bits[i++] = 1; // adding the parity bit
            return c+1;
        }
        else 
            System.out.println("Even parity");
        return c;
    }

    static void receiver(int count) {
        int c = 0;
        for (int i : bits) { // Calculating parity
            if (i == 1)
                c++;
        }
        if (count == c) // Checking if data is valid or not
            System.out.println("Data recieved with no error.");
        else
            System.out.println("Data received has error.");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n;
        bits = new int[25];
        System.out.println("Enter the number:");
        n = sc.nextInt();
        int c = sender(n);

        // Corrupting the data
        System.out.println("\nDo you want to corrupt the data? If yes enter 1.");
        int check = sc.nextInt();
        if (check == 1) {
            System.out.println("What bit would you like to change?");
            int pos = sc.nextInt();
            bits[pos - 1] = (bits[pos - 1] == 1) ? 0 : 1;
        }

        receiver(c);
        sc.close();
    }
}