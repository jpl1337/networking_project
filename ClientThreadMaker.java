import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


public class ClientThreadMaker {
	

    public static void main(String[] args) throws UnknownHostException, IOException{
    	
    	int y;
    	Scanner input = new Scanner(System.in);
        System.out.println("Please input the number of Threads you want to create: "); //select how many times for the loop that creates threads to run
        int n = input.nextInt();
       
    	
        System.out.println("Select what you want reported");
        System.out.println("1. Host current Date and Time");
        System.out.println("2. Host uptime");
        System.out.println("3. Host memory use");
        System.out.println("4. Host Netstat");
        System.out.println("5. Host current users");
        System.out.println("6. Host running processes");
        System.out.println("7. Quit");
        
         y = input.nextInt();
        while(y< 0 || y>8) { //checks for valid input and reasks for valid input
        	System.out.println("Invalid input please select a choice.");
        	System.out.println("Select what you want reported");
            System.out.println("1. Host current Date and Time");
            System.out.println("2. Host uptime");
            System.out.println("3. Host memory use");
            System.out.println("4. Host Netstat");
            System.out.println("5. Host current users");
            System.out.println("6. Host running processes");
            System.out.println("7. Quit");
            y = input.nextInt();
        }
        System.out.println(y);
        long startTime = System.nanoTime();
        CountDownLatch latch = new CountDownLatch(1);

        for (int x=0; x<n; x++){ //for loop that creates previously decided on threads

            Thread temp= new Client(Integer.toString(y), args[0]);//passes integer to client program responsible for actually sending y to the server program
            //temp.start();//starts thread after created
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println((duration/1000000)+ " ms");
        
    }



}
