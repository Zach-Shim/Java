/* Zach Shim
   Program 2
   CSS 430
*/
import java.net.*;
import java.io.*;
import java.util.*;
public class Worker implements Runnable {
    private Socket client;      /* Worker services a client */
    private int workerID;       /* used for testing purposes */

    Worker(Socket c, int ID) {
       client = c;
       workerID = ID;
    }
   
   // Print date to client's console
   public void run() {
       try {
           /*      Use to test mulithreading ability
           for(int i = 0; i < 10; i++) {
               Thread.sleep(1000);
               System.out.println("Worker... " + workerID);
           }
           */
           PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
           pout.println(new java.util.Date().toString());
           client.close();
       } catch(IOException ioe) {}
       // catch(InterruptedException ine) {}
   }
}
