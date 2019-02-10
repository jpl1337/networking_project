import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
    public String selection;
    public Socket socket;
    public Client(String meh) throws UnknownHostException, IOException {
        selection = meh;
        socket = new Socket("localhost", 1234);
        //port has to be higher than 2000 and less than 6000
        //192.168.101.133
        //cisvm-wkstn2-133
        //192.168.101.134
        //cisvm-wkstn2-134
    }
    public void run() {




        PrintWriter writer;
        try {
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(selection);
            writer.flush();


            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine()) != null){    // reads a line of text
                System.out.println( line); //"message recieved from server " +
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}