package cn.com.onlinetool.calculator;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
public class tt1 extends JApplet{
    private AudioClip ac;
    private JButton jbtPlay,jbtLoop,jbtStop;
    @SuppressWarnings("deprecation")
    public tt1()
    {
        setLayout(new GridLayout(1,3));
        add(jbtPlay=new JButton("Play"));
        add(jbtLoop=new JButton("Loop"));
        add(jbtStop=new JButton("Stop"));

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@唯一改动处  ST
        File f=new File("/Users/choice/Downloads/test.wav");
        URL urlAudio;
        try {
            urlAudio = f.toURL();
            ac=Applet.newAudioClip(urlAudio);
        } catch (MalformedURLException e1) {
// TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@唯一改动处  ED
        jbtPlay.addActionListener(new ButtonListener());
        jbtLoop.addActionListener(new ButtonListener());
        jbtStop.addActionListener(new ButtonListener());
    }
    class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==jbtPlay)
                ac.play();
            else if(e.getSource()==jbtLoop)
                ac.loop();
            else if(e.getSource()==jbtStop)
                ac.stop();
        }
    }
    public static void main(String[] args) throws MalformedURLException
    {
        JFrame frame = new JFrame();
        tt1 applate=new tt1();
        frame.add(applate);
        frame.setSize(420,80);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}