// Raggav Subramani - 20BCT0127

/*
Algorithm:
1) Enter the details of the data bit to transfer and the polynomial.
2) Send the data to the sender for encryption.
    i) Perform modulo-2 division operation, where the dividend is the data bit and the divisor is the coefficients of the polynomial.
    ii) Find the remainder after the division and append it to the data bits. Ensure that the number of bits appended to the data bits is equal to the degree of the polynomial. This is the CRC code generated for the given data bit.
3) Input the data that needs to be sent to the receiver end.
    i) Perform modulo-2 division operation, where the dividend is the data bit and the divisor is the coefficients of the polynomial.
    ii) If the remainder is 0, the data is not corrupted.
    iii) Else the received data is corrupted.
4) End.
*/

import java.util.*;

class CRC {

    static int[] division(int oldData[], int divisor[]) {
        int remainder[] = new int[divisor.length];
        int data[] = new int[oldData.length + divisor.length];
        System.arraycopy(oldData, 0, data, 0, oldData.length);
        System.arraycopy(data, 0, remainder, 0, divisor.length);

        for (int i = 0; i < oldData.length; i++) {
            if (remainder[0] == 1) {
                for (int j = 1; j < divisor.length; j++)
                    remainder[j - 1] = (remainder[j] == divisor[j]) ? 0 : 1;
            } else {
                for (int j = 1; j < divisor.length; j++)
                    remainder[j - 1] = (remainder[j] == 0) ? 0 : 1;
            }
            remainder[divisor.length - 1] = data[i + divisor.length];
        }
        return remainder;
    }

    static int[] sender(int data[], int divisor[]) {
        // calculating the remainder
        int remainder[] = division(data, divisor);
        System.out.println("Remainder is: ");
        for (int i = 0; i < remainder.length - 1; i++)
            System.out.print(remainder[i]);
        System.out.println();

        System.out.println("Generated CRC code: ");
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);
        for (int i = 0; i < remainder.length - 1; i++)
            System.out.print(remainder[i]);
        System.out.println();
        return remainder;
    }

    static void receiver(int data[], int divisor[]) {
        int remainder[] = division(data, divisor);
        for (int i = 0; i < remainder.length; i++) {
            if (remainder[i] != 0) {
                System.out.println("Corrupted data received");
                return;
            }
        }
        System.out.println("Data received without error.");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // Taking input of data to be sent
        int n;
        System.out.println("Enter number of data bits: ");
        n = sc.nextInt();
        int dataBits[] = new int[n];
        System.out.println("Enter data bits: ");
        for (int i = 0; i < n; i++)
            dataBits[i] = sc.nextInt();
        // Taking input of the divisor polynomial
        System.out.println("Enter highest power of divisor polynomial:");
        n = sc.nextInt();
        int divisor[] = new int[n];
        System.out.println("Enter co-efficients of divisor polynomial: ");
        for (int i = 0; i < n; i++)
            divisor[i] = sc.nextInt();

        int remainder[] = sender(dataBits, divisor);
        // Sending the data to the receiver
        int sentData[] = new int[dataBits.length + remainder.length - 1];
        System.out.println("Enter bits in the array which you want to send: ");
        for (int i = 0; i < sentData.length; i++)
            sentData[i] = sc.nextInt();

        receiver(sentData, divisor);
        sc.close();
    }
}