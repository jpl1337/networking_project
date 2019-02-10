import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class MultiThreadServer implements Runnable {
    Socket csocket;
    MultiThreadServer(Socket csocket) {
        this.csocket = csocket;

    }

    public static void main(String args[]) throws Exception {
        ServerSocket ssock = new ServerSocket(1234);
        System.out.println("Listening");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadServer(sock)).start();
        }
    }

    public void run() {

        try {
            InputStream input = csocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();    // reads a line of text
            System.out.println("message recieved from client" + line);

            Process p;
            String s;

            if(line.equals("1")) {
                java.util.Date date = new java.util.Date();

                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                writer.println(date);
                writer.flush();
            }
            if(line.equals("2")) {
                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
                long uptime = rb.getUptime();
                writer.println(uptime + " ms");
                writer.flush();
            }
            if(line.equals("3")) {
                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                p = Runtime.getRuntime().exec("free -mh");
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null){
                //change this
                    writer.println("line: " + s);
                    writer.flush();
                }
                //don't know if you need the two lines below
                //p.waitFor();
                writer.println ("exit: " + p.exitValue());
                //the line below closes
                writer.flush();
                p.destroy();
            }
            if(line.equals("4")) {
                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                p = Runtime.getRuntime().exec("netstat");
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null) {
                    //change this
                    writer.println("line: " + s);
                }
                //don't know if you need the two lines below
                //p.waitFor();
                writer.println ("exit: " + p.exitValue());
                //the line below closes
                writer.flush();
                p.destroy();
            }
            if(line.equals("5")) {
                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                p = Runtime.getRuntime().exec("users");
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null) {
                    //change this
                    writer.println("line: " + s);
                }
                //don't know if you need the two lines below
                //p.waitFor();
                writer.println ("exit: " + p.exitValue());
                //the line below closes
                writer.flush();
                p.destroy();
            }
            if(line.equals("6")) {
                PrintWriter writer = new PrintWriter(csocket.getOutputStream());
                p = Runtime.getRuntime().exec("ps -A");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(p.getInputStream()));
                while ((s = br.readLine()) != null)
                    //change this
                    writer.println("line: " + s);
                //don't know if you need the two lines below
                //p.waitFor();
                writer.println ("exit: " + p.exitValue());
                //the line below closes
                writer.flush();
                p.destroy();
            }

            csocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}