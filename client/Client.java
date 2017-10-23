import java.lang.Exception;
import java.lang.String;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;

public class Client{

    public static void main(String[] args) {
        String s;
        InetAddress inetAddress;
        try {
            /**Registering with BS server initially*/
            inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 44444;
            String msg = "REG 192.168.16.203 44444 thilina";
            msg = String.format("%04d", msg.length() + 5) + " " + msg;
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

    public static void echo(String msg)
    {
        System.out.println(msg);
    }
}