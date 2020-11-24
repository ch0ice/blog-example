package cn.com.onlinetool.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatClient extends JFrame {
    private JTextField inputBox;
    private JTextArea outFrame;
    private JPanel jp;
    private JLabel lb;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String msg = "";
    private String chatServer;
    private Socket toClient;

    public ChatClient(String host){
        super("客户端");

        chatServer = host;
        Container container = getContentPane();
        inputBox = new JTextField();
        inputBox.setEnabled(false);
        inputBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg(e.getActionCommand());
                inputBox.setText("");
            }
        });
        lb = new JLabel("请在下面输入聊天内容：");
        jp = new JPanel();
        jp.setLayout(new BorderLayout());

        jp.add(lb,BorderLayout.NORTH);
        jp.add(inputBox);
        container.add(jp,BorderLayout.SOUTH);

        outFrame = new JTextArea();
        container.add(new JScrollPane(outFrame),BorderLayout.CENTER);
        setSize(280,160);
        setVisible(true);
    }

    public void connectClient(){
        try{
            connect2Server();
            getStreams();
            processConnection();
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connect2Server() throws IOException {
        outFrame.setText("正在和服务器连接中......\n");
        toClient = new Socket(chatServer,4000);
        outFrame.append("成功连接至：" + toClient.getInetAddress().getHostAddress());

    }

    private void getStreams() throws IOException {
        outputStream = new ObjectOutputStream(toClient.getOutputStream());
        outputStream.flush();

        inputStream = new ObjectInputStream(toClient.getInputStream());
        outFrame.append("\n连接成功，可以开始聊天了！\n");

    }

    private void processConnection(){
        inputBox.setEnabled(true);
        do {
            try{
                msg = (String)inputStream.readObject();
                outFrame.append("\n" + msg);
                outFrame.setCaretPosition(outFrame.getText().length());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                outFrame.append("\n未知的消息类型");
            }
        }while (!msg.equals("服务器端>> 终止"));
    }

    private void closeConnection() throws IOException {
        outFrame.append("\n服务器端已下线");
        outputStream.close();
        inputStream.close();
        toClient.close();
    }

    private void sendMsg(String msg){
        try{
            msg = "客户端>> " + msg;
            outputStream.writeObject(msg);
            outputStream.flush();
            outFrame.append("\n" + msg);
        } catch (IOException e) {
            outFrame.append("\nError writting object");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ChatClient begining;
        if(args.length == 0){
            begining = new ChatClient("127.0.0.1");
        }else {
            begining = new ChatClient(args[0]);

        }
        begining.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        begining.connectClient();
    }
}
