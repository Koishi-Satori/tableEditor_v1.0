package top.kkoishi.warn_tips_info;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;

public class Build {
    public static final int ONE_BUTTON_FRAME = 1;
    public static final int TWO_BUTTON_FRAME = 2;
    public static final int INFO_FRAME = 3;

    public void buildFrame(int buildMode,String title,String buttonText,String text) {
        switch (buildMode) {
            case ONE_BUTTON_FRAME -> buildOneButton(title, buttonText, text);
            case TWO_BUTTON_FRAME -> new TypeDoesNotMatchException().printStackTrace();
            case INFO_FRAME -> buildInfo(title, text);
            default -> new UnDefinedFrameTypeException().printStackTrace();
        }
    }

    public void buildFrame(int buildMode,String title,String buttonText1,String buttonText2,String text) {
        switch (buildMode) {
            case ONE_BUTTON_FRAME -> {
                buildOneButton(title, buttonText1, text);
            }
            case TWO_BUTTON_FRAME -> {
                buildTwoButton(title, buttonText1, buttonText2, text);
            }
            case INFO_FRAME -> {
                buildInfo(title, text);
            }
            default -> new UnDefinedFrameTypeException().printStackTrace();
        }
    }

    protected void buildOneButton(String title,String buttonText,String text) {

    }

    protected void buildTwoButton(String title,String buttonText1,String buttoneText2,String text) {

    }

    private void buildInfo(String title,String infoText) {
        JFrame frame = new JFrame("关于...");
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setSize(500,430);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(frame.getBackground());
        textArea.setText(infoText);
        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.pink,Color.cyan));
        textArea.setBounds(45,30,400,270);

        JButton info1 = new JButton("GitHub");
        JButton info2 = new JButton("个人主页");
        JButton back = new JButton("关闭");

        info1.setBounds(50,330,100,30);
        info2.setBounds(200,330,100,30);
        back.setBounds(350,330,100,30);

        info1.addActionListener(e -> {
            try {
                Runtime.getRuntime().exec("cmd /c start " + "https://githubd.com//Koishi-Satori");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        info2.addActionListener(e -> {
            try {
                Runtime.getRuntime().exec("cmd /c start " + "http://kkoishi-514.top");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> frame.dispose());

        panel.add(info1);
        panel.add(info2);
        panel.add(back);
        panel.add(textArea);

        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }
}

class UnDefinedFrameTypeException extends Exception {
    public UnDefinedFrameTypeException () {
        super("The frame type you want to build does not exist!");
    }
}

class TypeDoesNotMatchException extends Exception {
    public TypeDoesNotMatchException () {
        super("Expect two text,but provide one:");
    }
}