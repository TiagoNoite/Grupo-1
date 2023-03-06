import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

public class ServerThread extends Thread {
    private final int port;
    private DataInputStream in;
    private PrintWriter out;
    private ServerSocket server;
     private Socket socket;

    public ServerThread ( int port ) {
        this.port = port;
        try {
            server = new ServerSocket ( this.port );
        } catch ( IOException e ) {
            e.printStackTrace ( );
        }
    }

    public void run ( ) {
        read_file();

        while ( true ) {
            try {
                System.out.println ( "Accepting Data" );
                socket = server.accept ( );
                in = new DataInputStream ( socket.getInputStream ( ) );
                out = new PrintWriter ( socket.getOutputStream ( ) , true );
                String message = in.readUTF ( );
                System.out.println ( "***** " + message + " *****" );
                out.println ( message.toUpperCase ( ) );
            } catch ( IOException e ) {
                e.printStackTrace ( );
            }
        }
    }


    private void read_file(){
        try {
            File myObj = new File("test.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
