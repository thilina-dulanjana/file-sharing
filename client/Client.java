import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.String;
import java.lang.System;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client{

    private static DatagramSocket socket;
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
            setSocket(sock);
            sock.send(pkt);

            ClientInterface clientInterface = new ClientInterface();
            clientInterface.start();

            SocketListner socketListner = new SocketListner();
            socketListner.start();

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

    public void sendToClient(String message, String destIp, String destPort){
        try {
            DatagramSocket sock = getSocket();
            byte[] buf = new byte[65507];
            buf = message.getBytes();
            InetAddress inetAddress = InetAddress.getByName(destIp);
            DatagramPacket pkt = new DatagramPacket(buf, buf.length, inetAddress, Integer.parseInt(destPort));
            pkt.setData(buf);
            sock.send(pkt);
        }catch(UnknownHostException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static void echo(String msg)
    {
        System.out.println(msg);
    }

    public static DatagramSocket getSocket() {
        return socket;
    }

    public static void setSocket(DatagramSocket socket) {
        Client.socket = socket;
    }
}