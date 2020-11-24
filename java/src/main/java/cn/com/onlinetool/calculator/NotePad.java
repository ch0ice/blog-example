package cn.com.onlinetool.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class NotePad implements ActionListener, MouseListener {
    JFrame frame = new JFrame("Simple NotePad");
    JFileChooser fc = new JFileChooser();
    JTextArea tf = new JTextArea();
    JMenuBar mb;
    JMenu m,m1,m2,m3;
    JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9,mi10,mi11;
    JScrollPane jsp = new JScrollPane(tf,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JButton ob,sb,db,nb,fb,jb,qb;
    static boolean b = true;

    public void run(){
        mb = new JMenuBar();
        m = new JMenu("文件");
        m1 = new JMenu("编辑");
        m2 = new JMenu("帮助");
        m3 = new JMenu("信息");
        mi1 = new JMenuItem("打开");
        mi1.addActionListener(this);
        mi2 = new JMenuItem("保存");
        mi2.addActionListener(this);
        mi3 = new JMenuItem("删除");
        mi3.addActionListener(this);
        mi4 = new JMenuItem("复制");
        mi4.addActionListener(this);
        mi5 = new JMenuItem("粘贴");
        mi5.addActionListener(this);
        mi6 = new JMenuItem("剪切");
        mi6.addActionListener(this);
        mi7 = new JMenuItem("帮助信息");
        mi7.addActionListener(this);
        mi8 = new JMenuItem("版本信息");
        mi8.addActionListener(this);
        mi9 = new JMenuItem("清空");
        mi9.addActionListener(this);
        mi10 = new JMenuItem("新建");
        mi10.addActionListener(this);
        mi11 = new JMenuItem("关闭");
        mi11.addActionListener(this);

//        ob = new JButton(new ImageIcon("图标路径"));
        ob = new JButton("打开");
        ob.addMouseListener(this);
        sb = new JButton("保存");
        sb.addMouseListener(this);
        db = new JButton("删除");
        db.addMouseListener(this);
        fb = new JButton("复制");
        fb.addMouseListener(this);
        nb = new JButton("粘贴");
        nb.addMouseListener(this);
        jb = new JButton("剪切");
        jb.addMouseListener(this);
        qb = new JButton("清空");
        qb.addMouseListener(this);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout(FlowLayout.LEFT));
        jp.add(ob);
        jp.add(sb);
        jp.add(db);
        jp.add(fb);
        jp.add(jb);
        jp.add(nb);
        jp.add(qb);

        m.add(mi1);
        m.add(mi2);
        m.add(mi3);
        m.add(mi10);
        m.add(mi11);

        m1.add(mi4);
        m1.add(mi5);
        m1.add(mi6);
        m1.add(mi7);

        m2.add(mi7);

        m3.add(mi8);

        mb.add(m);
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);

        Container cp = frame.getContentPane();
        frame.setJMenuBar(mb);
        cp.add(jp,BorderLayout.NORTH);
        cp.add(jsp,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        tf.setLineWrap(true);
        tf.setEnabled(b);

    }

    public static void main(String[] args) {
        NotePad notePad = new NotePad();
        notePad.run();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        try{
            if(item == mi1){
                b = true;
                tf.setEnabled(b);
                int select = fc.showOpenDialog(frame);
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    FileInputStream fil = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fil);
                    BufferedReader bfr = new BufferedReader(isr);
                    int t = JOptionPane.showConfirmDialog(frame,"是否打开文件：" + file.getName(),"打开",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        String s;
                        tf.setText("");
                        while((s = bfr.readLine()) != null){
                            tf.append(s);
                        }
                        bfr.close();
                    }
                }
            }else if(item == mi2){
                int select = fc.showSaveDialog(frame);
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    FileOutputStream fio = new FileOutputStream(file);
                    OutputStreamWriter isw = new OutputStreamWriter(fio);
                    BufferedWriter bfw = new BufferedWriter(isw);
                    int t = JOptionPane.showConfirmDialog(frame, "是否保存到：" + file.getName(),"保存",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        String s = tf.getText();
                        bfw.write(s);
                        JOptionPane.showConfirmDialog(frame,"Saving：" + file.getName(),"保存",JOptionPane.INFORMATION_MESSAGE);
                        bfw.close();
                    }

                }
            }else if(item == mi3){
                int select = fc.showDialog(frame,"删除");
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    int t = JOptionPane.showConfirmDialog(frame, "是否删除此文件：" + file.getName(), "删除", JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        file.delete();
                        JOptionPane.showMessageDialog(frame, file.getName() + "已删除", "删除",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else if(item == mi4){
                tf.copy();
            }else if(item == mi5){
                tf.paste();
            }else if(item == mi6){
                tf.cut();
            }else if(item == mi7){
                JOptionPane.showMessageDialog(frame,"该记事本可以进行简单的纯文本的操作编辑","帮助信息",JOptionPane.INFORMATION_MESSAGE);
            }else if(item == mi8){
                JOptionPane.showMessageDialog(frame, "简单笔记本 V1.0 版：2020.10.07", "版本信息", JOptionPane.INFORMATION_MESSAGE);
            }else if(item == mi9){
                int t = JOptionPane.showConfirmDialog(frame, "是否清空","删除",JOptionPane.YES_NO_OPTION);
                if(t == JOptionPane.YES_OPTION){
                    tf.setText("");
                }
            }else if(item == mi10){
                b = true;
                tf.setEnabled(b);
            }else if(item == mi11){
                tf.setText("");
                b = false;
                tf.setEnabled(b);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "文件未找到","出错",JOptionPane.ERROR_MESSAGE);
        } catch (EOFException ex) {
            JOptionPane.showMessageDialog(frame, "输入遇到错误","出错",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "无法从文件中读取数据","出错",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try{
            JButton button = (JButton) e.getSource();
            if(button == ob){
                int select = fc.showOpenDialog(frame);
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    FileInputStream fil = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fil);
                    BufferedReader bfr = new BufferedReader(isr);
                    int t = JOptionPane.showConfirmDialog(frame, "是否打开此文件：" + file.getName(),"打开",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        String s;
                        tf.setText("");
                        while((s = bfr.readLine()) != null){
                            tf.append(s);
                        }
                        bfr.close();
                    }
                }
            }else if(button == sb){
                int select = fc.showSaveDialog(frame);
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    FileOutputStream fio = new FileOutputStream(file);
                    OutputStreamWriter isw = new OutputStreamWriter(fio);
                    BufferedWriter bfw = new BufferedWriter(isw);
                    int t = JOptionPane.showConfirmDialog(frame, "是否保存到：" + file.getName(), "保存", JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        String s = tf.getText();
                        bfw.write(s);
                        JOptionPane.showMessageDialog(frame,"Saving：" + file.getName(),"保存",JOptionPane.INFORMATION_MESSAGE);
                        bfw.close();
                    }
                }
            }else if(button == db){
                int select = fc.showDialog(frame,"删除");
                if(select == JFileChooser.APPROVE_OPTION){
                    File file = fc.getSelectedFile();
                    int t = JOptionPane.showConfirmDialog(frame, "是否删除文件：" + file.getName(),"删除",JOptionPane.YES_NO_OPTION);
                    if(t == JOptionPane.YES_OPTION){
                        file.delete();
                        JOptionPane.showMessageDialog(frame,file.getName() + "已删除","删除",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else if(button == fb){
                tf.copy();
            }else if(button == nb){
                tf.paste();
            }else if(button == jb){
                tf.cut();
            }else if(button == qb){
                int t = JOptionPane.showConfirmDialog(frame,"是否清空","删除",JOptionPane.YES_NO_OPTION);
                if(t == JOptionPane.YES_OPTION){
                    tf.setText("");
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,"文件未找到","出错了",JOptionPane.ERROR_MESSAGE);
        } catch (EOFException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,"输入遇到错误","出错了",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,"无法从文件中读取数据","出错了",JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
