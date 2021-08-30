/* Zach Shim
   Program 2
   CSS 430
 */
import java.net.*;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
public class DateServerMTP {
    public final static int NUM_WORKERS = 10;
    public int port;

    public static void main(String args[]) {
        DateServerMTP man = new DateServerMTP(args);
        man.listen();
    }
   
    // initialize port number; error check if port is valid
    DateServerMTP(String args[]) {
        try
        {
            port = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Please enter a valid number");
            System.exit(0);
        }
        catch (ArrayIndexOutOfBoundsException aio)
        {
            System.out.println("Please enter a port number");
            System.exit(0);
        }
    }

    // listen for a client; on connection, create thread to service client
    public void listen() {
        int id = 0;
        try
        {
            ServerSocket sock = new ServerSocket(port);
            ExecutorService threadExecutor = Executors.newCachedThreadPool();
            while (true)
            {
                Socket client = sock.accept();             // blocks
                Runnable worker = new Worker(client, id);
                threadExecutor.execute(worker);            // run Worker class
                id++;
            }
        }
        catch (IOException ie) {}
    }
}

