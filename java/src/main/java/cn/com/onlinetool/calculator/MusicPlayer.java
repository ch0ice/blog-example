package cn.com.onlinetool.calculator;


import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MusicPlayer extends Frame implements ActionListener {
    JFrame frame = new JFrame("MusicPlayer");
    JLabel playL,stopL,nextL,lastL,chooseL,loopL,lrcL;
    JTextArea ta = new JTextArea();
    JComboBox choose;
    JFileChooser mc = new JFileChooser();
    JButton play,stop,next,last,loop,open;
    String[] musicList = {"test.wav","Burning.wav"};

    AudioClip sound1 = Applet.newAudioClip(new URL("file:///Users/choice/Downloads/test.wav"));
    AudioClip sound2 = Applet.newAudioClip(new URL("file:///Users/choice/Downloads/Burning.wav"));
    AudioClip chooseClip = sound1;
    boolean looping = false;

    public MusicPlayer() throws MalformedURLException {
    }


    public static void main(String[] args) throws MalformedURLException {
        MusicPlayer that = new MusicPlayer();
        that.run();
    }

    public void run(){
        ta.setEditable(false);
        ta.setBackground(Color.cyan);

        Font font = new Font("TimesNew Roman",Font.PLAIN,14);
        ta.setFont(font);

        choose = new JComboBox(musicList);
        choose.setEditable(false);
        choose.setSelectedIndex(0);
        choose.addActionListener(this);

        JScrollPane jsp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playL = new JLabel("播放");
        playL.setForeground(Color.blue);
        stopL = new JLabel("停止");
        stopL.setForeground(Color.blue);
        lastL = new JLabel("上一曲");
        lastL.setForeground(Color.blue);
        nextL = new JLabel("下一曲");
        nextL.setForeground(Color.blue);
        loopL = new JLabel("循环播放");
        loopL.setForeground(Color.blue);
        chooseL = new JLabel("选择播放文件");
        chooseL.setForeground(Color.blue);
        lrcL = new JLabel("歌词：");
        lrcL.setForeground(Color.blue);

//        last = new JButton(new ImageIcon("图标路径"));
        last = new JButton("上一曲");
        last.addActionListener(this);
        play = new JButton("播放");
        play.addActionListener(this);
        stop = new JButton("停止");
        stop.addActionListener(this);
        next = new JButton("下一曲");
        next.addActionListener(this);
        loop = new JButton("循环播放");
        loop.addActionListener(this);
        open = new JButton("添加歌曲");
        open.addActionListener(this);

        Container cp = frame.getContentPane();
        cp.setLayout(null);

        cp.add(play);
        cp.add(stop);
        cp.add(loopL);
        cp.add(next);
        cp.add(last);
        cp.add(choose);
        cp.add(playL);
        cp.add(stopL);
        cp.add(nextL);
        cp.add(lastL);
        cp.add(loop);
        cp.add(jsp);
        cp.add(chooseL);
        cp.add(lrcL);
        cp.add(open);

        last.setBounds(10,400,95,95);
        lastL.setBounds(40,490,120,30);
        play.setBounds(105,400,95,95);
        playL.setBounds(135,495,120,20);
        stop.setBounds(200,400,95,95);
        stopL.setBounds(235,495,120,20);
        next.setBounds(295,400,95,95);
        nextL.setBounds(320,495,129,29);
        choose.setBounds(45,40,250,30);
        chooseL.setBounds(115,20,150,20);
        loop.setBounds(310,15,70,40);
        loop.setBounds(320,55,60,20);
        jsp.setBounds(10,90,380,300);
        lrcL.setBounds(10,70,70,20);
        open.setBounds(0,0,60,20);

        frame.setSize(405,550);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(choose.getSelectedItem() == "test.wav"){
            ta.setText("天外来物 - 薛之谦\n" +
                    "词：薛之谦\n" +
                    "曲：罗小黑\n" +
                    "你降落的 太突然了\n" +
                    "我刚好呢 又路过了\n" +
                    "机会难得 又主观觉得\n" +
                    "想明抢 又碰不得\n" +
                    "你带来了 我的快乐\n" +
                    "让这世界 有点颜色\n" +
                    "我好想指责 你太随意了\n" +
                    "宝物该有人捧着 你是不是我的\n" +
                    "你像 天外来物一样 求之不得\n" +
                    "你在世俗里的名字 不重要了\n" +
                    "正好 我隐藏的人格是契而不舍\n" +
                    "直到蜂拥而至的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "你占领了 我的快乐\n" +
                    "和这世界 已没有瓜葛\n" +
                    "任事物干渴 都褪去颜色\n" +
                    "只有你是天蓝色 我开始找你了\n" +
                    "会像 天外来物一样 失而复得\n" +
                    "你在世俗里的名字 被人用了\n" +
                    "反正 我隐藏的人格是契而不舍\n" +
                    "直到蜂拥而至的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "你就像 天外来物一样 求之不得\n" +
                    "我在世俗里的描写被取笑了\n" +
                    "反正我隐藏的人格是非你不可\n" +
                    "直到别有用心的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "制作人：周以力/郑伟\n" +
                    "编曲：周以力\n" +
                    "吉他：张淞\n" +
                    "大提琴：郎莹\n" +
                    "鼓：褚伟明\n" +
                    "bass：努而德柯\n" +
                    "人声录制：郑伟/夏之炜/吴身宝\n" +
                    "人声编辑：郑伟\n" +
                    "人声录音棚：上海广播大厦200studio\n" +
                    "乐器录音棚：soundhub studio\n" +
                    "混音：全相彦 @OK master studio\n" +
                    "母带：全相彦 @OK master studio\n" +
                    "编辑于2020/07/17更新");
            chooseClip = sound1;
            chooseClip.stop();
            loop.setEnabled(true);
        }
        if(choose.getSelectedItem() == "Burning.wav"){
            ta.setText("天外来物 - 薛之谦\n" +
                    "词：薛之谦\n" +
                    "曲：罗小黑\n" +
                    "你降落的 太突然了\n" +
                    "我刚好呢 又路过了\n" +
                    "机会难得 又主观觉得\n" +
                    "想明抢 又碰不得\n" +
                    "你带来了 我的快乐\n" +
                    "让这世界 有点颜色\n" +
                    "我好想指责 你太随意了\n" +
                    "宝物该有人捧着 你是不是我的\n" +
                    "你像 天外来物一样 求之不得\n" +
                    "你在世俗里的名字 不重要了\n" +
                    "正好 我隐藏的人格是契而不舍\n" +
                    "直到蜂拥而至的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "你占领了 我的快乐\n" +
                    "和这世界 已没有瓜葛\n" +
                    "任事物干渴 都褪去颜色\n" +
                    "只有你是天蓝色 我开始找你了\n" +
                    "会像 天外来物一样 失而复得\n" +
                    "你在世俗里的名字 被人用了\n" +
                    "反正 我隐藏的人格是契而不舍\n" +
                    "直到蜂拥而至的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "你就像 天外来物一样 求之不得\n" +
                    "我在世俗里的描写被取笑了\n" +
                    "反正我隐藏的人格是非你不可\n" +
                    "直到别有用心的人都透明了\n" +
                    "我在 不近又不远处\n" +
                    "用明天换你 靠近我\n" +
                    "制作人：周以力/郑伟\n" +
                    "编曲：周以力\n" +
                    "吉他：张淞\n" +
                    "大提琴：郎莹\n" +
                    "鼓：褚伟明\n" +
                    "bass：努而德柯\n" +
                    "人声录制：郑伟/夏之炜/吴身宝\n" +
                    "人声编辑：郑伟\n" +
                    "人声录音棚：上海广播大厦200studio\n" +
                    "乐器录音棚：soundhub studio\n" +
                    "混音：全相彦 @OK master studio\n" +
                    "母带：全相彦 @OK master studio\n" +
                    "编辑于2020/07/17更新");
            chooseClip = sound2;
            chooseClip.stop();
            loop.setEnabled(true);
        }
        Object source = event.getSource();
        if(source == play){
            stop.setEnabled(true);
            chooseClip.play();
            chooseL.setText("正在播放");
        }
        if(source == open){
            int select = mc.showOpenDialog(frame);
            if(select == JFileChooser.APPROVE_OPTION){
                File file = mc.getSelectedFile();
                chooseL.setText(file.getName());
                choose.setSelectedItem(file.getName());

            }
        }
        if(source == loop){
            chooseClip.loop();
            looping = true;
            loop.setEnabled(false);
            stop.setEnabled(true);
            chooseL.setText("正在循环播放");
        }
        if(source == next){
            if(choose.getSelectedItem() == "No More.wav"){
                choose.setSelectedItem("Burning.wav");
            }
            chooseL.setText("已选择下一曲");
            chooseClip.play();
            stop.setEnabled(true);
        }
        if(source == last){
            if(choose.getSelectedItem() == "Burning.wav"){
                choose.setSelectedItem("No More.wav");
            }
            chooseClip.play();;
            stop.setEnabled(true);
            chooseL.setText("已选择上一曲");
        }
        if(source == stop){
            if(looping){
                looping = false;
                chooseClip.stop();
                loop.setEnabled(true);
            }else {
                chooseClip.stop();
            }
            stop.setEnabled(false);
            chooseL.setToolTipText("已停止播放");
        }
    }
}
