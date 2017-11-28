package TCPProgramming;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

public class Server extends JFrame{
    JTextArea txt_message = new JTextArea();
    BufferedReader fromClient;
    Server(){   
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(txt_message),BorderLayout.CENTER);
        setSize(400, 400);
        txt_message.setFont(new Font("Tahoma", Font.PLAIN, 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("เครื่อง Server");
        setResizable(false);
        //middle monitor
        setLocationRelativeTo(null);
        setVisible(true);
        runner();
    }
    public static void main(String[] args){
           new Server();
       }
    private void runner(){
        txt_message.append("สถานะพร้อมทำงาน...\n");
        txt_message.append("รอเครื่อง client มาเชื่อมต่อ....\n");
        //connect between server and client
        try {
            //create socket for creat connect pass port 8189
            ServerSocket myServer = new ServerSocket(8189);
            Socket connected=myServer.accept();//wait client connect
            //server do not everything
            txt_message.append("Client ทำการเชื่อมต่อเรียบร้อย\n");
            fromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
            while (true){
                String messageClient = fromClient.readLine();
                txt_message.append("ข้อความที่ส่งมา:"+messageClient+"\n");
            }
        } catch (IOException ex) {
      
        }

    }
}
