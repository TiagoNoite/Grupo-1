import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;



public class ClientThread extends Thread {
    private final int port;
    private final int id;
    private final int freq;
    private DataOutputStream out;
    private BufferedReader in;
    private Socket socket;
    private Scanner myObj = new Scanner(System.in);

    public ClientThread ( int port , int id , int freq ) {
        this.port = port;
        this.id = id;
        this.freq = freq;
    }

    public void run ( ) {
        //try {
        int i = 0;
        while ( true ) {
            System.out.println ( "Sending Data" );
            try {
                // if(sem.tryAcquire(1, TimeUnit.SECONDS)) {
                socket = new Socket ( "localhost" , port );
                out = new DataOutputStream ( socket.getOutputStream ( ) );
                in = new BufferedReader ( new InputStreamReader ( socket.getInputStream ( ) ) );
                out.writeUTF ( "My message number " + i + " to the server " + "I'm " + id );
                String userName = myObj.nextLine();  // Read user input
                System.out.println("Username is: " + userName);
                String response;
                response = in.readLine ( );
                System.out.println ( "From Server " + response );
                out.flush ( );
                socket.close ( );
                sleep ( freq );
                i++;
            } catch ( IOException | InterruptedException e ) {
                e.printStackTrace ( );
            }
        }
    }
}
