package top.kkoishi.files;

import top.kkoishi.environmentBuild.Table;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

/**
 * <p><a href="http://kkoishi-514.top">Author's personal website</a></p>
 * <p><a href="https://github.com/Koishi-Satori">GitHub Homepage</a></p>
 *
 * @author KKoishi
 * @version 1.0es1
 * @since java8
 */
public class FileFiliter {
    public String file () throws ClassNotFoundException, InstantiationException
            , IllegalAccessException, UnsupportedLookAndFeelException {
        String path = null;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Table File", "ktm");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setDialogTitle("Please select file path");
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = jFileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }

    public String directary () throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        String path = null;
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Directary", "directories");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setDialogTitle("Please select a directary");
        if (JFileChooser.APPROVE_OPTION == jFileChooser.showOpenDialog(null)) {
            path = jFileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }

    public void pathConfirm(String text) {
        String[] name = new String[1];
        String[] path = new String[1];

        JFrame frame = new JFrame("另存为");
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300,210);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("选择文件夹并且输入文件名");
        JTextField pathField = new JTextField();
        JTextField nameField = new JTextField();
        JButton select = new JButton("选择");
        JButton confirm = new JButton("确认");
        JButton back = new JButton("返回");

        label.setBounds(10,10,280,30);
        pathField.setBounds(10,40,280,30);
        nameField.setBounds(10,90,190,30);
        select.setBounds(225,90,50,30);
        confirm.setBounds(40,150,90,30);
        back.setBounds(170,150,90,30);

        panel.add(label);
        panel.add(pathField);
        panel.add(nameField);
        panel.add(select);
        panel.add(confirm);
        panel.add(back);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

        select.addActionListener(e->{
            try {
                path[0] = directary();
                pathField.setText(path[0]);
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        });

        confirm.addActionListener(e->{
            if (nameField.getText() != null) {
                name[0] = nameField.getText();
                if (pathField.getText() != null) {
                    new Table().saveAsPath = path[0] + "\\" + name[0];
                    try {
                        new FileOperation().createFile(path[0],name[0]);
                        new FileOperation().writeFile(new Table().saveAsPath,text);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println(text);
                        frame.dispose();
                    }
                }
            }
        });

        back.addActionListener(e -> frame.dispose());
    }
}
