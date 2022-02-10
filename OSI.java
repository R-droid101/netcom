// Raggav - 20BCT0127

/*
Algorithm:
1) Input the data that is to be transmitted.
2) Add all the headers to the data corresponding to each layer of the OSI protocol.
3) Convert the data to binary.
4) Send the data to the receiever to decrypt.
5) Remove the headers corresponding to each layer of the OSI protocol one by one.
6) End.
*/

import java.util.*;

public class OSI {

    static String reduce(String data) { // utility function to reduce the string
        return data.substring(2);
    }

    static String stringToBin(String input) { // function to convert string to binary
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar)).replaceAll(" ", " "));
        }
        return result.toString();
    }

    // public static String bintoString(String input) {
    // int i = 0;
    // String output = "";
    // for(i = 0; i < input.length(); i++) {
    // i = input.indexOf(" ");
    // String letter = input.substring(0, i);
    // int let = Integer.parseInt(letter, 2);
    // output += (char)let;
    // input = input.substring(i+1);
    // }
    // output += (char)(Integer.parseInt(input.trim(), 2));
    // return output;
    // }

    static String transmitter(String data) { // function for transmitter
        System.out.println("\n--Transmitter:--");
        data = "AI" + data;
        System.out.println("Application Layer: " + data);
        data = "PI" + data;
        System.out.println("Presentation Layer: " + data);
        data = "SI" + data;
        System.out.println("Session Layer: " + data);
        data = "TI" + data;
        System.out.println("Transport Layer: " + data);
        data = "NI" + data;
        System.out.println("Network Layer: " + data);
        data = "DI" + data + "DI";
        System.out.println("Data link Layer: " + data);
        System.out.println("Binary data: " + stringToBin(data));
        return data;
    }

    static String receiver(String data) { // function for receiver
        System.out.println("\n--Receiver:--");
        System.out.println("Data link Layer: " + data);
        data = reduce(data);
        data = data.substring(0, data.length() - 2);
        System.out.println("Network Layer: " + data);
        data = reduce(data);
        System.out.println("Transport Layer: " + data);
        data = reduce(data);
        System.out.println("Session Layer: " + data);
        data = reduce(data);
        System.out.println("Presentation Layer: " + data);
        data = reduce(data);
        System.out.println("Application Layer: " + data);
        data = reduce(data);
        return data;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter data to transmit");
        String data = sc.next();
        data = transmitter(data);
        System.out.println("\nFinal data at Receiver end: " + receiver(data));
        sc.close();
    }
}