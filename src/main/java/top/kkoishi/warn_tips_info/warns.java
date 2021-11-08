package top.kkoishi.warn_tips_info;

import javax.swing.*;

public class warns extends Build {
    public void frameWithOneButton(String title,String text) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setSize(300,200);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextArea area = new JTextArea("");
        area.setEditable(true);
        area.setBackground(frame.getBackground());
        area.setText(text);
        area.setBounds(10,10,270,110);
        JButton button = new JButton("关闭");
        button.addActionListener(e->frame.dispose());
        button.setBounds(100,140,100,30);

        panel.add(area);
        panel.add(button);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void frameWithTwoButton(String title,String text) {

    }
}
