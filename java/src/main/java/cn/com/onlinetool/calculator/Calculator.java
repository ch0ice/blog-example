package cn.com.onlinetool.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends WindowAdapter implements ActionListener {
    JFrame frame;
    JTextField field;
    //清除、加、减、乘、除、等号、小数点、倒数、正负号、n次幂、正弦、余弦、阶乘
    JButton ce, jia, jian, cheng, chu, equ, point, sqrt, dsh, zf, xy, sin, cos, jch;
    //数字键
    JButton[] jButtons = new JButton[10];
    double sum = 0, getValue;
    int i = 0, j = 0, p = 0;
    double t;
    int l, action;
    JDialog about;
    //显示结果有效长度
    final int slength = 30;

    public void run() {
        //设置面板属性
        frame = new JFrame("Calculator");
        frame.setSize(500, 220);
        frame.setLocation(400, 300);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.setResizable(false);

        //设置结果文本框属性
        field = new JTextField(34);
        field.setText("0");
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setEditable(false);

        Panel leftPanel = new Panel();
        //设置 网格布局（rows：行，cols：列，hgap：网格水平间距，vgap：网格垂直间距）
        leftPanel.setLayout(new GridLayout(4, 3, 3, 3));

        Panel rightPanel = new Panel();
        rightPanel.setLayout(new GridLayout(4, 3, 3, 3));

        //添加十个数字按钮
        for (l = 9; l >= 0; l--) {
            jButtons[l] = new JButton(String.valueOf(l));
            jButtons[l].setForeground(Color.BLUE);
            leftPanel.add(jButtons[l]);
            jButtons[l].addActionListener(this);
        }

        jia = new JButton("+");
        jia.addActionListener(this);
        jian = new JButton("-");
        jian.addActionListener(this);
        cheng = new JButton("*");
        cheng.addActionListener(this);
        chu = new JButton("/");
        chu.addActionListener(this);
        //x的y次方
        xy = new JButton("x^y");
        xy.addActionListener(this);
        //余弦
        cos = new JButton("cos");
        cos.addActionListener(this);
        //正弦
        sin = new JButton("sin");
        sin.addActionListener(this);
        ce = new JButton("清除");
        ce.addActionListener(this);
        equ = new JButton("=");
        equ.addActionListener(this);
        //倒数
        dsh = new JButton("1\\x");
        dsh.addActionListener(this);
        //小数点
        point = new JButton(".");
        point.addActionListener(this);
        //正负号
        zf = new JButton("+/-");
        zf.addActionListener(this);
        //开方
        sqrt = new JButton("sqrt");
        sqrt.addActionListener(this);
        //阶乘
        jch = new JButton("!");
        jch.addActionListener(this);

        //将各个按钮添加到左画板
        leftPanel.add(zf);
        leftPanel.add(point);

        //将各个按钮添加到右画板
        rightPanel.add(chu);
        rightPanel.add(sqrt);
        rightPanel.add(jch);
        rightPanel.add(cheng);
        rightPanel.add(sin);
        rightPanel.add(dsh);
        rightPanel.add(jian);
        rightPanel.add(cos);
        rightPanel.add(ce);
        rightPanel.add(jia);
        rightPanel.add(xy);
        rightPanel.add(equ);

        Panel northPanel = new Panel();
        northPanel.setLayout(new GridLayout(1, 2, 10, 10));
        northPanel.add(leftPanel);
        northPanel.add(rightPanel);

        frame.add(field);
        frame.add(northPanel);
        frame.addWindowListener(this);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //当前文本框显示的数字
        getValue = Double.valueOf(field.getText());

        //0 ~ 9数字键触发
        for (l = 0; l < 10; l++) {
            if (e.getSource() == jButtons[l]) {
                if (i == 0) {
                    field.setText(" ");
                }
                String s = field.getText();
                if (s.length() < slength) {
                    field.setText(field.getText() + e.getActionCommand());
                }
                if (e.getSource() == jButtons[0] && getValue == 0 && p == 0) {
                    field.setText("0");
                }
                if (e.getSource() != jButtons[0] && getValue == 0 && p == 0) {
                    field.setText(e.getActionCommand());
                    i++;
                }
            }
        }

        //加运算
        if (e.getSource() == jia) {
            if (j == 0) {
                sum = getValue;
            } else if (action == 1) {
                sum += getValue;
            }
            setSum();
            j++;
            p = 0;
            i = 0;
            action = 1;
        }

        //减运算
        else if (e.getSource() == jian) {
            if (j == 0) {
                sum = getValue;
            } else if (action == 2) {
                sum -= getValue;
            }
            setSum();
            j++;
            p = 0;
            i = 0;
            action = 2;
        }

        //乘运算
        else if (e.getSource() == cheng) {
            if (j == 0) {
                sum = getValue;
            } else if (action == 3) {
                sum *= getValue;
            }
            setSum();
            j++;
            p = 0;
            i = 0;
            action = 3;
        }

        //除运算
        else if (e.getSource() == chu) {
            if (j == 0) {
                sum = getValue;
            } else if (action == 4) {
                sum /= getValue;
            }
            setSum();
            j++;
            p = 0;
            i = 0;
            action = 4;
        }

        //x^y运算
        else if (e.getSource() == xy) {
            if (j == 0) {
                sum = getValue;
            } else if (action == 5) {
                sum = Math.pow(sum, getValue);
            }
            setSum();
            j++;
            i = 0;
            action = 5;
        }

        //等号
        else if (e.getSource() == equ) {
            switch (action) {
                case 1:
                    field.setText(String.valueOf(sum += getValue));
                    break;
                case 2:
                    field.setText(String.valueOf(sum -= getValue));
                    break;
                case 3:
                    field.setText(String.valueOf(sum *= getValue));
                    break;
                case 4:
                    field.setText(String.valueOf(sum /= getValue));
                    break;
                case 5:
                    field.setText(String.valueOf(sum = Math.pow(sum, getValue)));
                    break;
            }
            setSum();
            i = 0;
            j = 0;
            action = 0;
        }

        //余弦运算
        else if (e.getSource() == cos) {
            sum = Math.cos(getValue);
            setSum();
            i = 0;
        }

        //正弦运算
        else if (e.getSource() == sin) {
            sum = Math.sin(getValue);
            setSum();
            i = 0;
        }

        //开根运算
        else if (e.getSource() == sqrt) {
            sum = Math.sqrt(getValue);
            setSum();
            i = 0;
        }

        //清除
        else if (e.getSource() == ce) {
            i = 0;
            j = 0;
            p = 0;
            sum = 0;
            action = 0;
            field.setText("0");
        }

        //倒数
        else if (e.getSource() == dsh) {
            sum = 1 / getValue;
            setSum();
            i = 0;
        }

        //阶乘
        else if (e.getSource() == jch) {
            if (getValue == 0) {
                sum = 1;
            } else if (getValue > 0) {
                sum = 1;
                for (t = getValue; t >= 1; t--) {
                    sum = sum * t;
                }
            } else if (getValue < 0) {
                sum = 0;
            }
            setSum();
            i = 0;
        }

        //小数点
        else if (e.getSource() == point) {
            if (p == 0) {
                field.setText(field.getText() + e.getActionCommand());
                p = 1;
            }
        }

        //正负号切换，正号不显示
        else if (e.getSource() == zf) {
            String s = field.getText();
            char a = s.charAt(0);
            if (a == '-') {
                field.setText("");
                for (l = 1; l < s.length(); l++) {
                    field.setText(field.getText() + s.charAt(1));
                }
            } else if (getValue != 0) {
                field.setText("-" + s);
            }
        }

    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getSource() == about) {
            about.setVisible(false);
        } else if (e.getSource() == frame) {
            System.exit(0);
        }
    }

    public void setSum() {
        field.setText(String.valueOf(sum));
        String s = field.getText();
        char a = s.charAt(s.length() - 1);
        char b = s.charAt(s.length() - 2);
        if (a == '0' && b == '.') {
            field.setText(String.valueOf(Math.round(sum)));
        }
    }

    public static void main(String[] args) {
        new Calculator().run();
    }

}
