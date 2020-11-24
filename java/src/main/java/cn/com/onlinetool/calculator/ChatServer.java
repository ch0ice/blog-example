package cn.com.onlinetool.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends JFrame {
    private JTextField inputBox;
    private JTextArea outFrame;

    private JPanel jp;
    private JLabel lb;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private ServerSocket toServer;
    private Socket connection;
    private int counter = 1;

    public ChatServer(){
        super("服务器端");
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

        outFrame = new JTextArea("聊天记录如下：");
        container.add(new JScrollPane(outFrame),BorderLayout.CENTER);

        setSize(280,160);
        setVisible(true);
    }

    public void connectServer(){
        try{
            toServer = new ServerSocket(4000,100);
            while(true){
                wait4Connection();
                getStreams();
                processConnection();
                closeConnection();
                ++counter;

            }
        } catch (IOException e) {
            System.out.println("发生异常");
            e.printStackTrace();
        }
    }

    private void wait4Connection() throws IOException {
        outFrame.setText("等待客户端链接......\n");
        connection = toServer.accept();
        outFrame.append("Connection " + counter + "from：" + toServer.getInetAddress().toString());

    }

    private void getStreams() throws IOException {
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        outputStream.flush();
        inputStream = new ObjectInputStream(connection.getInputStream());
        outFrame.append("\n连接成功，可以聊天了！\n");
    }

    private void processConnection() throws IOException {
        String msg = "服务器端>> 连接成功";
        outputStream.writeObject(msg);
        outputStream.flush();
        inputBox.setEnabled(true);
        do{
            try {
                msg = (String) inputStream.readObject();
                outFrame.append("\n" + msg);
                outFrame.setCaretPosition(outFrame.getText().length());
            } catch (ClassNotFoundException e) {
                System.out.println("未知的消息类型");
                e.printStackTrace();
            }
        }while (!msg.equals("客户端>> 终止"));
    }

    private void closeConnection() throws IOException {
        outFrame.append("\n服务器端已下线");
        inputBox.setEnabled(false);
        outputStream.close();
        inputStream.close();
        connection.close();
    }

    private void sendMsg(String msg){
        try {
            msg = "服务器端>> " + msg;
            outputStream.writeObject(msg);
            outputStream.flush();
            outFrame.append("\n" + msg);
        } catch (IOException e) {
            System.out.println("发送消息过程中发生异常");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer process = new ChatServer();
        process.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        process.connectServer();
    }

}
