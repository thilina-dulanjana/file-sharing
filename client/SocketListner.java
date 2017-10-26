import java.lang.Override;
import java.lang.Thread;
import java.net.DatagramPacket;
import java.util.StringTokenizer;

class SocketListner extends Thread{
    @Override
    public void run() {
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
                System.out.println("Reg with server");
            }else if(command.equals("UNROK")){

            }else if(command.equals("JOINOK")){

            }else if(command.equals("LEAVEOK")){

            }else if(command.equals("SEROK")){

            }else if(command.equals("ERROR")){

            }else{
                System.out.println("Invalid command");
            }

            System.out.println("Enter a command: ");
        }
    }
}