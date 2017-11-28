
package TCPProgramming;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;
import java.io.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Client extends JFrame implements ActionListener{
    JTextArea txt_message = new JTextArea();
    JTextField txt_send = new JTextField();
    PrintWriter toServer;
    public Client(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("สนธนา:"),BorderLayout.WEST);
        panel.add(txt_send,BorderLayout.CENTER);
        add(panel,BorderLayout.SOUTH);
        add(new JScrollPane(txt_message),BorderLayout.CENTER);
 
        getContentPane().add(new JScrollPane(txt_message),BorderLayout.CENTER);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("เครื่อง Client");
        Font f = new Font("Tahoma",Font.PLAIN,20);
        txt_send.setFont(f);
        txt_message.setFont(f);
        setResizable(false);
        //middle monitor
        setLocationRelativeTo(null);
        setVisible(true);
        txt_send.requestFocus();
        txt_send.addActionListener(this);
        runner();
    }
    public static void main(String[] args){
        new Client();
    }
    public void runner(){
        //start server
        try  {
            //create socket
            Socket myClient = new Socket(InetAddress.getLocalHost(), 8189);
            txt_message.append("เชื่อมต่อกับ server เรียบร้อย \n");
            toServer = new PrintWriter(myClient.getOutputStream(),true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()instanceof JTextField){
            String message = txt_send.getText();
            txt_message.append(message+"\n");
            txt_send.setText("");
            toServer.println(message);
        }
    }
}