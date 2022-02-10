// Raggav Subramani - 20BCT0127

/*
Algorithm:
1) Take input from user regarding the 4 data segments that need to be transmitted.
2) Calculate the checksum of the data segments on the sender side.
    i) Calculate the sum of all the data segments and store it in a variable 'sum'. 
    ii) Calculate the 1's complement of the sum and store it in a variable 'checksum'.
    iii) Convert all data segments into binary and append the checksum in binary to the data segments. The string containing the data segments and the checksum is the message to be transmitted.
4) Send the data message to the recevier.
5) Check the if the data message has been corrupted or not.
    i) Calculate the total sum of all the data segments as receieved by the recevier.
    ii) Calculate the 1's complement of the sum.
    iii) If the 1's complement of the sum is equivalent to integer 0, print the data is not corrupted. If it is any other value, print the data is corrupted.
6) End.
*/

import java.util.*;

class Checksum {
    static int complement(int n) { // Function to calculate ones complement
        int noOfBits = (int) (Math.floor(Math.log(n) / Math.log(2))) + 1;
        return ((1 << noOfBits) - 1) ^ n;
    }

    static void sender(int d[]) {
        int sum = d[0] + d[1] + d[2] + d[3]; // Calculating the sum
        int checksum = complement(sum); // Calculating the checksum
        // Converting checksum into binary and printing

        System.out.println("Checksum: " + Integer.toBinaryString(checksum));
        // Calculating the transmitted data
        String dataToBeTrasmited[] = { Integer.toBinaryString(d[0]), Integer.toBinaryString(d[1]),
                Integer.toBinaryString(d[2]), Integer.toBinaryString(d[3]), Integer.toBinaryString(checksum) };
        // displaying the transmitted data
        System.out.print("Data to be transmitted: ");
        for (String data : dataToBeTrasmited)
            System.out.print(data);
    }

    static void receiver(String[] data) { // Receiver function
        int sum = 0;
        for (int i = 0; i < 5; i++)
            sum += Integer.parseInt(data[i], 2); // Calculating the sum of the data

        System.out.println("Sum: " + Integer.toBinaryString(sum));
        int complement = complement(sum); // Calculating the ones complement of the sum
        System.out.println("Complement: " + Integer.toBinaryString(complement));
        if (complement == 0) // Checking if recieved data is valid or not
            System.out.println("Data received with no error.");
        else
            System.out.println("Data received has error.");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // Sender end
        System.out.println("Input 4 segments:");
        // Reading all the data segments to be transmitted
        int d[] = new int[4];
        for (int i = 0; i < 4; i++)
            d[i] = sc.nextInt();
        sender(d);
        System.out.println();

        // Receiver end
        System.out.println("Input 5 segments received as binary strings:");
        String received[] = new String[5];
        for (int i = 0; i < 5; i++)
            received[i] = sc.next();
        receiver(received);

        sc.close();
    }
}