// Raggav Subramani - 20BCT0127

/* 
Algorithm:
1) Read the IP address, number of groups, number of customers per group and number of addresses required per customer from the user.
2) Separate each octet of the IP address and store it in separate integer variables. Extract the mask value 'n' also from the IP address.
3) Repeat
4) Calculate the prefix length for the group requirements.
5) Repeat
6) If any of the octets go over 255, we increase the value of the next octet by one and resetting the current octet value.
7) Print the details of the IP address the customer receieves after adding it to the last octet.
8) Print the number of allocated resources, number of used resources and number of available resources.
*/

import java.util.*;

public class IP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input IP address");
        String IP = sc.nextLine();
        System.out.println("Input number of groups:");
        int groups = sc.nextInt();
        System.out.println("Input number of customers per group and number of addresses required per customer");
        int customers[] = new int[groups];
        int addresses[] = new int[groups];
        for (int i = 0; i < groups; i++) {
            System.out.println("Enter details for group " + (i + 1));
            customers[i] = sc.nextInt();
            addresses[i] = sc.nextInt();
        }
        int p1 = Integer.parseInt(IP.substring(0, IP.indexOf('.')));
        String temp = IP.substring(IP.indexOf('.') + 1, IP.length());
        int p2 = Integer.parseInt(temp.substring(0, temp.indexOf('.')));
        int p3 = Integer.parseInt(temp.substring(temp.indexOf('.') + 1, temp.lastIndexOf('.')));
        int p4 = Integer.parseInt(IP.substring(IP.lastIndexOf('.') + 1, IP.indexOf('/')));
        int sum = 0;
        for (int i = 0; i < groups; i++) {
            int prefLen = 32 - (int)(Math.log(addresses[i]) / Math.log(2));
            System.out.println("\nGroup " + (i+1));
            for (int j = 0; j < customers[i]; j++) {
                if (p4 >= 256) {
                    p4 = 0;
                    p3++;
                }
                if(p3 >= 256) {
                    p3 = 0;
                    p2++;
                }
                if(p2 >= 256) {
                    p2 = 0;
                    p3++;
                }
                System.out.print("Customer " + (j+1) + ": " + p1 + "." + p2 + "." + p3 + "." + p4 + "/" +prefLen + " - ");
                p4 += addresses[i] - 1;
                System.out.println(p1 + "." + p2 + "." + p3 + "." + p4 + "/" +prefLen);
                p4++;
            }
            sum += addresses[i]*customers[i];
        }
        System.out.println("\nNumber of allocated resources = 65536");
        System.out.println("Number of used resources = " + sum);
        System.out.println("Number of available resources = " + (65536-sum));
        sc.close();
    }
}
