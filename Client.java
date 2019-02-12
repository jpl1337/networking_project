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
	public Client(String meh, String ip) throws UnknownHostException, IOException {
        selection = meh;
        socket = new Socket(ip, 2009);

    }
	 public void run() {

		 PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.println(selection);
			 writer.flush();
			 
			 String line = null;
			 String totalReceive = null;
			 InputStream input = socket.getInputStream();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			  do{
			  	line = reader.readLine();
			  	totalReceive+=line;
			  	// reads a line of text
			  }while (line!=null);
			   System.out.println("message recieved from server " + line);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
