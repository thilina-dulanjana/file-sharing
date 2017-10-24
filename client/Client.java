import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client{

    public static void main(String[] args) {
        String s, username, ip;
        InetAddress inetAddress;
        Scanner scanner;
        int port;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Select your username :");
            username = scanner.nextLine().replace(" ", "");
            System.out.println("Your username is : "+username);
            System.out.println("Choose your port :");
            port = Integer.parseInt(scanner.next());
            ip = getLocalIp();

            /**Registering with BS server initially*/
            inetAddress = InetAddress.getByName(ip);
            String msg = "REG "+ip+" "+port+" "+username;
            msg = String.format("%04d", msg.length() + 5) + " " + msg;
            System.out.println(msg);
            byte[] buf = new byte[65507];
            buf = msg.getBytes();
            DatagramPacket pkt = new DatagramPacket(buf, buf.length, inetAddress, 55555);
            pkt.setData(buf);
            DatagramSocket sock = new DatagramSocket(port);
            sock.send(pkt);

            while(true){
                /**Resolve responses*/
                byte[] buffer = new byte[65536];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                System.out.println("in created");
                sock.receive(incoming);
                System.out.println("incoming");

                byte[] data = incoming.getData();
                s = new String(data, 0, incoming.getLength());

                //echo the details of incoming data - client ip : client port - client message
                echo(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - " + s);

                StringTokenizer st = new StringTokenizer(s, " ");
                String length = st.nextToken();
                String command = st.nextToken();

                if(command.equals("REGOK")){

                }else if(command.equals("UNROK")){

                }else if(command.equals("JOINOK")){

                }else if(command.equals("LEAVEOK")){

                }else if(command.equals("SEROK")){

                }else if(command.equals("ERROR")){

                }else{
                    //invalid command
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static String getLocalIp(){
        String ip="";
        try {
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                java.net.NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (i.isSiteLocalAddress()) {
                        ip = i.getHostAddress();
                    }

                }
            }
        }catch(SocketException ex){
            ex.printStackTrace();
        }
        return ip;
    }

    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}