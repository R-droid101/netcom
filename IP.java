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
