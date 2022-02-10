// Raggav Subramani = 20BCT0127
// Code to generate the 7 bit hammming code using even parity

/*
Algorithm:
1) Enter details of the data bits that needs to be transmitted.
2) The code is sent to the sender to encrypt.
    i) All parity bits(bits at positions 1, 2, 4, 8 etc.) in the code is initialized to 9.
    ii) The data bit is checked for even parity and parity bit is set to 1 if the total number of ones in the positions it checks is odd.The required parity bits are then calculated as follows:
        a. Parity bit 1 is calculated by performing XOR operation on the bit positions whose binary representation includes a 1 in the least significant position (1, 3, 5, 7, 9, 11, etc).
        b. Parity bit 2 is calculated by performing XOR operation on the bit positions whose binary representation includes a 1 in the second position from the least significant bit (2, 3, 6, 7, 10, 11, etc).
        c. Parity bit 4 is calculated by performing XOR operation on the bit positions whose binary representation includes a 1 in the third position from the least significant bit (4–7, 12–15, 20–23, etc).
    iii) The hamming code is generated.
3) The code is then asked if it needs to be dirtied or not. If code is dirtied, the corresponding data bit position is changed and sent to the receiver.
4) The data sent to the receiver end is processed as follows:
    i) The parity bits are checked for even parity.
    ii) If any of the parity bits is not coming out to be as calculated, the location of the parity bit is appended into another variable called errorLocation. 
    iii) After checking all the parity bits, we analyse the errorLocation variable to find the location of the error. To find the error location in the data bit, we see the set of bits that are set in the errorLocation variable and check which bit do those parities correspond to.
    iv) After discovering the location of the error, the code is corrected.
    v) If there was no error, the message "no error received" is displayed.
5) End.
*/

import java.util.*;

class HammingCode {

    static int getParity(int code[], int power) {
        int parity = 0;
        // Performing XOR operation for the required bits corresponding to the parity
        for (int i = 0; i < code.length; i++) {
            if (code[i] != 9) {
                int k = i + 1;
                // Finding the bit to check for the required parity
                int x = ((Integer.parseInt(Integer.toBinaryString(k))) / ((int) Math.pow(10, power))) % 10;
                if (x == 1) // XOR operation
                    if (code[i] == 1)
                        parity = (parity + 1) % 2;
            }
        }
        return parity;
    }

    static int[] sender(int dataBits[]) {
        int code[], i = 0, j = 0, k = 0, parityCount = 0;
        // Finding the number of parity bits required according to the number of data
        // bits
        while (i < dataBits.length) {
            if (Math.pow(2, parityCount) == i + parityCount + 1)
                parityCount++;
            else
                i++;
        }
        code = new int[dataBits.length + parityCount];
        // Setting parity bits in array
        for (i = 1; i <= code.length; i++) {
            if (Math.pow(2, j) == i) {
                code[i - 1] = 9;
                j++;
            } else
                code[k + j] = dataBits[k++];
        }
        // Setting the parity
        for (i = 0; i < parityCount; i++)
            code[((int) Math.pow(2, i)) - 1] = getParity(code, i);
        return code;
    }

    static void receiver(int dataBits[], int parityCount) {
        int power;
        int parity[] = new int[parityCount]; // indicates number of parity bits
        String errorLocation = new String(); // string to detect error location

        for (power = 0; power < parityCount; power++) {
            for (int i = 0; i < dataBits.length; i++) {
                int k = i + 1;
                // Finding the bit to check for the required parity
                int bit = ((Integer.parseInt(Integer.toBinaryString(k))) / ((int) Math.pow(10, power))) % 10;
                if (bit == 1) // XOR operation
                    if (dataBits[i] == 1)
                        parity[power] = (parity[power] + 1) % 2;
            }
            errorLocation = parity[power] + errorLocation;
        }
        // correcting the bit at the location of the error bit
        int errorLoc = Integer.parseInt(errorLocation, 2); // to store location of error to the base 10
        if (errorLoc != 0) {
            System.out.println("Error location: " + errorLoc);
            dataBits[errorLoc - 1] = (dataBits[errorLoc - 1] + 1) % 2;
            System.out.println("Corrected code:");
            for (int i = 0; i < dataBits.length; i++)
                System.out.print(dataBits[dataBits.length - i - 1]);
        }
        // if there is no error
        else
            System.out.println("No error in received data.");
        System.out.println("\nOriginal data sent:");
        power = parityCount - 1;
        for (int i = dataBits.length; i > 0; i--) {
            if (Math.pow(2, power) != i)
                System.out.print(dataBits[i - 1]);
            else
                power--;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of data bits");
        int n = sc.nextInt();
        int dataBits[] = new int[n];
        System.out.println("Enter data bits to transmit: ");
        for (int i = 0; i < n; i++)
            dataBits[n - i - 1] = sc.nextInt();
        System.out.println("Data bits entered:");
        for (int i = 0; i < n; i++)
            System.out.print(dataBits[n - i - 1]);
        System.out.println();
        int code[] = sender(dataBits);
        System.out.println("Generated code:");
        for (int i = 0; i < code.length; i++)
            System.out.print(code[code.length - i - 1]);
        System.out.println();
        // Dirtying the code
        System.out.println("Enter position of a bit to dirty (0 for no error):");
        int dirty = sc.nextInt();
        if (dirty != 0)
            code[dirty - 1] = (code[dirty - 1] == 1) ? 0 : 1;
        System.out.println("Code sent to receiver:");
        for (int i = 0; i < code.length; i++)
            System.out.print(code[code.length - i - 1]);
        System.out.println();
        receiver(code, code.length - dataBits.length);
        sc.close();
    }
}