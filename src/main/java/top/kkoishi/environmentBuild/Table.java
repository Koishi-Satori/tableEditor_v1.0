package top.kkoishi.environmentBuild;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import top.kkoishi.files.FileFiliter;
import top.kkoishi.files.FileOperation;
import top.kkoishi.files.FormatStream;
import top.kkoishi.warn_tips_info.Build;
import top.kkoishi.warn_tips_info.warns;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main (String[] args) {
        new Table().selectDataBase();
    }
}

public class Table {
    private static boolean ifAutoSave = false;
    public volatile String filePath;
    public volatile String saveAsPath;
    private final String propertie = "./prof_v1.properties";
    private Properties properties = new Properties();
    private String lastFilePath;

    public void selectDataBase () {
        comFrame();
    }

    private void comFrame () {
        try {
            properties.load(new FileInputStream(propertie));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame(properties.getProperty("frameTitle"));
        frame.setLocationByPlatform(true);
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();

        class BackMouseEvent implements MouseListener {
            @Override
            public void mouseClicked (MouseEvent e) {

            }

            @Override
            public void mousePressed (MouseEvent e) {
                System.exit(514);
            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }
        }

        DefaultTableModel tableModel = new DefaultTableModel();
        JMenu file = new JMenu("??????");
        JMenu operation = new JMenu("??????");
        JMenu back = new JMenu("??????(?????????)");
        JMenu editMenu = new JMenu("??????");
        JMenu help = new JMenu("??????");
        JMenu show = new JMenu("????????????:???");

        help.addMouseListener(new HelpActionEvent());
        back.addMouseListener(new BackMouseEvent());
        show.setEnabled(false);


        JMenuItem itemOpen = new JMenuItem("??????");
        JMenuItem itemClose = new JMenuItem("????????????");
        JMenuItem itemSave = new JMenuItem("??????");
        JMenuItem itemNew = new JMenuItem("??????");
        JMenuItem itemChange = new JMenuItem("???????????????????????????");

        file.add(itemOpen);
        file.addSeparator();
        file.add(itemClose);
        file.addSeparator();
        file.add(itemSave);
        file.addSeparator();
        file.add(itemNew);
        file.addSeparator();
        file.add(itemChange);

        JMenuItem itemSaveAs = new JMenuItem("?????????");
        JMenuItem itemCheck = new JMenuItem("??????");
        JMenuItem itemCopy = new JMenuItem("???????????????");
        JMenuItem itemReplace = new JMenuItem("??????");
        JMenuItem itemResetColor = new JMenuItem("??????????????????");

        operation.add(itemSaveAs);
        operation.addSeparator();
        operation.add(itemCheck);
        operation.addSeparator();
        operation.add(itemCopy);
        operation.addSeparator();
        operation.add(itemReplace);
        operation.addSeparator();
        operation.add(itemResetColor);

        JMenuItem itemSwitch = new JMenuItem("???????????????????????????");
        JMenuItem itemDelRow = new JMenuItem("?????????");
        JMenuItem itemDelColumn = new JMenuItem("?????????");
        JMenuItem itemEditColumn = new JMenuItem("?????????");
        JMenuItem itemEditLine = new JMenuItem("?????????");
        JMenuItem itemAdd = new JMenuItem("?????????/???");

        editMenu.add(itemSwitch);
        editMenu.addSeparator();
        editMenu.add(itemAdd);
        editMenu.addSeparator();
        editMenu.add(itemEditLine);
        editMenu.addSeparator();
        editMenu.add(itemEditColumn);
        editMenu.addSeparator();
        editMenu.add(itemDelRow);
        editMenu.addSeparator();
        editMenu.add(itemDelColumn);

        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        menuBar.add(file);
        menuBar.add(operation);
        menuBar.add(back);
        menuBar.add(editMenu);
        menuBar.add(help);
        menuBar.add(show);

        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        table.setModel(new EmptyTableModel());
        //table.setColumnModel(new TableColumnModel());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(30);
        frame.setVisible(true);

        itemCopy.addActionListener(e -> {
            String operateSystem = System.getProperty("os.name");
            if (operateSystem.matches("windows\s\\d")) {
                try {
                    Runtime.getRuntime().exec("cmd");
                    System.out.println(1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        itemResetColor.addActionListener(e -> {
            if (lastFilePath == null || lastFilePath.equals("")) {
                return;
            }
            try {
                properties.load(new FileInputStream(propertie));
                JFrame frame1 = new JFrame("??????????????????");
                frame1.setResizable(false);
                frame1.setLocationByPlatform(true);
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setSize(300, 220);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JLabel label = new JLabel("?????????(?????????????????????)");
                JTextArea area = new JTextArea();
                JButton confirm = new JButton("??????");
                JButton backButton = new JButton("??????");

                label.setBounds(10, 10, 280, 30);
                area.setBounds(10, 40, 280, 100);
                confirm.setBounds(40, 160, 90, 30);
                backButton.setBounds(170, 160, 90, 30);

                panel0.add(label);
                panel0.add(area);
                panel0.add(confirm);
                panel0.add(backButton);
                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);

                confirm.addActionListener(e1 -> {
                    properties.setProperty("frameTitle", area.getText());
                    try {
                        properties.store(new FileOutputStream(propertie), "# table.properties");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String frameName = properties.getProperty("frameTitle");
                    frame.setTitle(frameName);
                    frame1.dispose();
                });

                backButton.addActionListener(e1 -> frame1.dispose());

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked (MouseEvent e) {
                        try {
                            properties.load(new FileInputStream(propertie));
                            String defaultName = properties.getProperty("defaultTitle");
                            properties.setProperty("frameTitle", defaultName);
                            properties.store(new FileOutputStream(propertie), "# table.properties");
                            frame.setTitle(properties.getProperty("defaultTitle"));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        itemChange.addActionListener(e -> {
            try {
                filePath = lastFilePath;
                if (filePath != null) {
                    DataTableModel dataTableModel = new DataTableModel();
                    dataTableModel.run();
                    table.setModel(dataTableModel);
                }

                String[][] data = new FormatStream().formatInput(new FileOperation().readFile(filePath));
                int len = data[0].length;
                String[] head = new String[len];
                Vector<String> vectorHead = new Vector<>();
                for (int i = 0; i < len; i++) {
                    head[i] = String.valueOf(i);
                    vectorHead.add(head[i]);
                }
                Vector<Vector<String>> dataVector = new Vector<>();
                for (String[] datum : data) {
                    Vector<String> temp = new Vector<>(Arrays.asList(datum).subList(0, len));
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, vectorHead);
                table.setModel(tableModel);
                String name = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
                show.setText("????????????:" + name);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        class history implements Runnable {
            @Override
            public void run () {
                try {
                    properties.load(new FileInputStream(propertie));
                    String temp = properties.getProperty("hsitoryFile_Last");
                    if (!temp.equals(null) || !temp.equals("null")) {
                        lastFilePath = temp;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(new history(), 50, 50, TimeUnit.MILLISECONDS);

        class Check_Replace {
            final point EMPTY_RESULT_POINT = new point(-1, -1);

            class point {
                int x;
                int y;

                public point (int x, int y) {
                    this.x = x;
                    this.y = y;
                }

                public point () {
                }

                public int getX () {
                    return x;
                }

                public int getY () {
                    return y;
                }

                @Override
                public String toString () {
                    return ("[" + x + "," + y + "]");
                }
            }

            private point check (String value) {
                int rowLimit = table.getRowCount();
                int columnLimit = table.getColumnCount();

                for (int i = 0; i < rowLimit; i++) {
                    for (int j = 0; j < columnLimit; j++) {
                        if (table.getValueAt(i, j).equals(value)) {
                            return new point(i, j);
                        }
                    }
                }

                return EMPTY_RESULT_POINT;
            }

            private point[] checkAll (String value) {
                List<point> pointList = new ArrayList<>();
                int rowLimit = table.getRowCount();
                int columnLimit = table.getColumnCount();
                point[] points;

                for (int i = 0; i < rowLimit; i++) {
                    for (int j = 0; j < columnLimit; j++) {
                        if (table.getValueAt(i, j).equals(value)) {
                            pointList.add(new point(i, j));
                        }
                    }
                }

                points = new point[pointList.size()];
                points = pointList.toArray(points);
                return points;
            }

            public void checkBuild () {
                JFrame checkFrame = new JFrame();
                checkFrame.setResizable(false);
                checkFrame.setLocationByPlatform(true);
                checkFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                checkFrame.setSize(350, 200);
                JPanel checkPanel = new JPanel();
                checkPanel.setLayout(null);

                JLabel label0 = new JLabel("???????????????");
                JTextField fieldValue = new JTextField();
                JButton checkButton = new JButton("???????????????");
                JButton checkAll = new JButton("????????????");
                JButton backButton = new JButton("??????");

                label0.setBounds(10, 20, 100, 30);
                fieldValue.setBounds(130, 20, 200, 30);
                checkButton.setBounds(20, 80, 90, 30);
                checkAll.setBounds(130, 80, 90, 30);
                backButton.setBounds(240, 80, 90, 30);

                checkPanel.add(label0);
                checkPanel.add(fieldValue);
                checkPanel.add(checkAll);
                checkPanel.add(checkButton);
                checkPanel.add(backButton);

                checkPanel.setVisible(true);
                checkFrame.add(checkPanel);
                checkFrame.setVisible(true);

                checkButton.addActionListener(e -> {
                    if (fieldValue.getText() == null || "".equals(fieldValue.getText())) {
                        new warns().frameWithOneButton("??????", "????????????????????????");
                    } else {
                        point show = check(fieldValue.getText());
                        result(show);
                    }
                });

                checkAll.addActionListener(e -> {
                    if (fieldValue.getText() == null || "".equals(fieldValue.getText())) {
                        new warns().frameWithOneButton("??????", "????????????????????????");
                    } else {
                        result(checkAll(fieldValue.getText()));
                    }
                });

                backButton.addActionListener(e -> checkFrame.dispose());
            }

            private void result (point show) {
                if (show != EMPTY_RESULT_POINT) {
                    JFrame frameCheck = new JFrame("????????????");
                    frameCheck.setResizable(false);
                    frameCheck.setLocationByPlatform(true);
                    frameCheck.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frameCheck.setSize(230, 160);
                    JPanel panelCheck = new JPanel();
                    panelCheck.setLayout(null);

                    JTextArea areaPoints = new JTextArea();
                    areaPoints.setLineWrap(true);
                    areaPoints.setEditable(false);
                    areaPoints.setText("???????????????????????????(??????????????????0):\n" + show.toString());
                    JButton buttonChangeColor = new JButton("??????");
                    JButton buttonBack = new JButton("??????");

                    areaPoints.setBounds(10, 10, 180, 60);
                    buttonChangeColor.setBounds(30, 80, 70, 30);
                    buttonBack.setBounds(130, 80, 70, 30);
                    panelCheck.add(areaPoints);
                    panelCheck.add(buttonChangeColor);
                    panelCheck.add(buttonBack);

                    panelCheck.setVisible(true);
                    frameCheck.add(panelCheck);
                    frameCheck.setVisible(true);

                    buttonChangeColor.addActionListener(e -> {
                        ColoredTableCellRenderer renderer = new ColoredTableCellRenderer();
                        renderer.setBackground(Color.orange);
                        table.prepareRenderer(renderer, show.getY(), show.getX());
                    });
                    buttonBack.addActionListener(e -> frameCheck.dispose());
                } else {
                    new warns().frameWithOneButton("??????", "?????????" + show + "?????????");
                }
            }

            private void result (point[] points) {
                boolean flag = false;
                for (point p : points) {
                    if (p != EMPTY_RESULT_POINT) {
                        flag = true;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    StringBuilder builder = new StringBuilder();
                    for (point p : points) {
                        builder.append(p.toString()).append("  ");
                    }
                    JFrame frameCheck = new JFrame("????????????");
                    frameCheck.setResizable(false);
                    frameCheck.setLocationByPlatform(true);
                    frameCheck.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frameCheck.setSize(230, 400);
                    JPanel panelCheck = new JPanel();
                    panelCheck.setLayout(null);

                    JTextArea areaPoints = new JTextArea();
                    areaPoints.setLineWrap(true);
                    areaPoints.setEditable(false);
                    areaPoints.setText("???????????????????????????(??????????????????0):\n" + builder);
                    JButton buttonChangeColor = new JButton("??????");
                    JButton buttonBack = new JButton("??????");

                    areaPoints.setBounds(10, 10, 180, 260);
                    buttonChangeColor.setBounds(30, 280, 70, 30);
                    buttonBack.setBounds(130, 280, 70, 30);

                    panelCheck.add(areaPoints);
                    panelCheck.add(buttonChangeColor);
                    panelCheck.add(buttonBack);
                    panelCheck.setVisible(true);
                    frameCheck.add(panelCheck);
                    frameCheck.setVisible(true);

                    buttonChangeColor.addActionListener(e -> {
                        ColoredTableCellRenderer renderer = new ColoredTableCellRenderer();
                        renderer.setBackground(Color.orange);
                        for (point point : points) {
                            table.prepareRenderer(renderer, point.getY(), point.getX());
                        }
                    });

                    buttonBack.addActionListener(e -> frameCheck.dispose());
                } else {
                    new warns().frameWithOneButton("??????", "?????????" + points + "?????????");
                }
            }

            public void replaceFrameBuild () {
                JFrame frameReplace = new JFrame("??????");
                frameReplace.setResizable(false);
                frameReplace.setLocationByPlatform(true);
                frameReplace.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frameReplace.setSize(400, 210);
                JPanel panelReplace = new JPanel();
                panelReplace.setLayout(null);

                JLabel labelFind = new JLabel("????????????");
                JTextField findValue = new JTextField();
                JLabel labelReplace = new JLabel("?????????");
                JTextField replaceValue = new JTextField();
                JButton replaceNext = new JButton("???????????????");
                JButton replaceAll = new JButton("????????????");
                JButton backButton = new JButton("??????");

                labelFind.setBounds(10, 20, 80, 30);
                findValue.setBounds(90, 20, 300, 30);
                labelReplace.setBounds(10, 70, 80, 30);
                replaceValue.setBounds(90, 70, 300, 30);
                replaceNext.setBounds(25, 130, 100, 30);
                replaceAll.setBounds(150, 130, 100, 30);
                backButton.setBounds(275, 130, 100, 30);

                panelReplace.add(labelFind);
                panelReplace.add(findValue);
                panelReplace.add(labelReplace);
                panelReplace.add(replaceValue);
                panelReplace.add(replaceNext);
                panelReplace.add(replaceAll);
                panelReplace.add(backButton);

                panelReplace.setVisible(true);
                frameReplace.add(panelReplace);
                frameReplace.setVisible(true);

                replaceAll.addActionListener(e -> {
                    if (findValue.getText() != null && !findValue.getText().equals("")) {
                        replaceAll(findValue.getText(), replaceValue.getText());
                    }
                });

                replaceNext.addActionListener(e -> {
                    if (findValue.getText() != null && !findValue.getText().equals("")) {
                        replace(findValue.getText(), replaceValue.getText());
                    }
                });

                backButton.addActionListener(e -> frameReplace.dispose());
            }

            private void replace (String content, String newContent) {
                int rowLimit = table.getRowCount();
                int columnLimit = table.getColumnCount();

                for (int i = 0; i < rowLimit; i++) {
                    for (int j = 0; j < columnLimit; j++) {
                        if (table.getValueAt(i, j).equals(content)) {
                            table.setValueAt(newContent, i, j);
                            return;
                        }
                    }
                }

                new warns().frameWithOneButton("????????????", "???????????????");
            }

            private void replaceAll (String content, String newContent) {
                int rowLimit = table.getRowCount();
                int columnLimit = table.getColumnCount();

                for (int i = 0; i < rowLimit; i++) {
                    for (int j = 0; j < columnLimit; j++) {
                        if (table.getValueAt(i, j).equals(content)) {
                            table.setValueAt(newContent, i, j);
                        }
                    }
                }
            }
        }

        itemCheck.addActionListener(e -> {
            new Check_Replace().checkBuild();
        });

        itemReplace.addActionListener(e -> {
            new Check_Replace().replaceFrameBuild();
        });

        itemSwitch.addActionListener(e -> {
            try {
                String[][] data = new FormatStream().formatInput(new FileOperation().readFile(filePath));
                int len = data[0].length;
                String[] head = new String[len];
                Vector<String> vectorHead = new Vector<>();
                for (int i = 0; i < len; i++) {
                    head[i] = String.valueOf(i);
                    vectorHead.add(head[i]);
                }
                Vector<Vector<String>> dataVector = new Vector<>();
                for (String[] datum : data) {
                    Vector<String> temp = new Vector<>(Arrays.asList(datum).subList(0, len));
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, vectorHead);
                table.setModel(tableModel);
                String name = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
                show.setText("????????????:" + name);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        class DelEvent {
            public void buildFrameDelRow () {
                JFrame frame1 = new JFrame("?????????");
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setLocationByPlatform(true);
                frame1.setResizable(false);
                frame1.setSize(300, 300);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JTextArea areaInfo = new JTextArea("");
                areaInfo.setEditable(false);
                areaInfo.setLineWrap(true);
                areaInfo.setText("???????????? ?????????????????? ????????????????????????????????????????????????????????????????????? ?????????0??????");
                JTextField indexField = new JTextField();
                JLabel label0 = new JLabel("??????");
                JButton delButton = new JButton("??????");
                JButton backButton = new JButton("??????");

                areaInfo.setBounds(10, 10, 280, 60);
                label0.setBounds(10, 70, 80, 30);
                indexField.setBounds(100, 70, 170, 30);
                delButton.setBounds(40, 130, 90, 30);
                backButton.setBounds(170, 130, 90, 30);

                panel0.add(areaInfo);
                panel0.add(label0);
                panel0.add(indexField);
                panel0.add(delButton);
                panel0.add(backButton);

                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);
                backButton.addActionListener(e1 -> frame1.dispose());
                delButton.addActionListener(e1 -> {
                    if (indexField.getText() == null || "".equals(indexField.getText())) {
                        delRow();
                    } else {
                        delRow(Integer.parseInt(indexField.getText()));
                    }
                    frame1.dispose();
                });
            }

            private void delRow () {
                int newRowLimit = table.getRowCount() - 1;
                int columnLimit = table.getColumnCount();
                Vector<String> headVector = new Vector<>();
                Vector<Vector<String>> dataVector = new Vector<>();

                for (int i = 0; i < columnLimit; i++) {
                    headVector.add(String.valueOf(i));
                }

                for (int i = 0; i < newRowLimit; i++) {
                    Vector<String> temp = new Vector<>();
                    for (int j = 0; j < columnLimit; j++) {
                        temp.add(getTableData(i, j));
                    }
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, headVector);
                table.setModel(tableModel);
            }

            private void delRow (int rowIndex) {
                int newRowLimit = table.getRowCount() - 1;
                int columnLimit = table.getColumnCount();
                Vector<String> headVector = new Vector<>();
                Vector<Vector<String>> dataVector = new Vector<>();

                for (int i = 0; i < columnLimit; i++) {
                    headVector.add(String.valueOf(i));
                }

                for (int i = 0; i < newRowLimit; i++) {
                    Vector<String> temp = new Vector<>();
                    if (i < rowIndex) {
                        for (int j = 0; j < columnLimit; j++) {
                            temp.add(getTableData(i, j));
                        }
                    } else {
                        for (int j = 0; j < columnLimit; j++) {
                            temp.add(getTableData(i + 1, j));
                        }
                    }
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, headVector);
                table.setModel(tableModel);
            }

            public void buildFrameDelColumn () {
                JFrame frame1 = new JFrame("?????????");
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setLocationByPlatform(true);
                frame1.setResizable(false);
                frame1.setSize(300, 300);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JTextArea areaInfo = new JTextArea("");
                areaInfo.setEditable(false);
                areaInfo.setLineWrap(true);
                areaInfo.setText("???????????? ?????????????????? ????????????????????????????????????????????????????????????????????? ?????????0??????");
                JTextField indexField = new JTextField();
                JLabel label0 = new JLabel("??????");
                JButton delButton = new JButton("??????");
                JButton backButton = new JButton("??????");

                areaInfo.setBounds(10, 10, 280, 60);
                label0.setBounds(10, 70, 80, 30);
                indexField.setBounds(100, 70, 170, 30);
                delButton.setBounds(40, 130, 90, 30);
                backButton.setBounds(170, 130, 90, 30);

                panel0.add(areaInfo);
                panel0.add(label0);
                panel0.add(indexField);
                panel0.add(delButton);
                panel0.add(backButton);

                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);
                backButton.addActionListener(e1 -> frame1.dispose());
                delButton.addActionListener(e1 -> {
                    if (indexField.getText() == null || "".equals(indexField.getText())) {
                        delColumn();
                    } else {
                        delColumn(Integer.parseInt(indexField.getText()));
                    }
                    frame1.dispose();
                });
            }

            private void delColumn () {
                int newColumnLimit = table.getColumnCount() - 1;
                int rowLimit = table.getRowCount();

                Vector<String> headVector = new Vector<>();
                Vector<Vector<String>> dataVector = new Vector<>();
                for (int i = 0; i < newColumnLimit; i++) {
                    headVector.add(String.valueOf(i));
                }

                for (int i = 0; i < rowLimit; i++) {
                    Vector<String> temp = new Vector<>();
                    for (int j = 0; j < newColumnLimit; j++) {
                        temp.add(getTableData(i, j));
                    }
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, headVector);
                table.setModel(tableModel);
            }

            private void delColumn (int columnIndex) {
                int newColumnLimit = table.getColumnCount() - 1;
                int rowLimit = table.getRowCount();
                Vector<String> headVector = new Vector<>();
                Vector<Vector<String>> dataVector = new Vector<>();

                for (int i = 0; i < newColumnLimit; i++) {
                    headVector.add(String.valueOf(i));
                }

                for (int i = 0; i < rowLimit; i++) {
                    Vector<String> temp = new Vector<>();
                    for (int j = 0; j < newColumnLimit; j++) {
                        if (j < columnIndex) {
                            temp.add(getTableData(i, j));
                        } else {
                            temp.add(getTableData(i, j + 1));
                        }
                    }
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, headVector);
                table.setModel(tableModel);
            }

            private String getTableData (int row, int column) {
                return String.valueOf(table.getValueAt(row, column));
            }
        }

        itemDelRow.addActionListener(e -> {
            new DelEvent().buildFrameDelRow();
        });

        itemDelColumn.addActionListener(e -> {
            new DelEvent().buildFrameDelColumn();
        });

        itemClose.addActionListener(e -> {
            filePath = null;
            show.setText("????????????:???");
            table.setModel(new EmptyTableModel());
        });

        class CreateFrame {
            public void build () {
                JFrame frame1 = new JFrame("???????????????");
                frame1.setResizable(false);
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setLocationByPlatform(true);
                frame1.setSize(380, 330);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JTextArea areaInfo = new JTextArea();
                areaInfo.setEditable(false);
                areaInfo.setLineWrap(true);
                areaInfo.setText("????????????????????????????????? ????????????????????? ?????????????????? ??????????????????????????????????????????(??????????????????)");
                JLabel labelPath = new JLabel("?????????");
                JTextField pathField = new JTextField();
                JLabel labelName = new JLabel("?????????");
                JTextField nameField = new JTextField();
                JLabel labelRow = new JLabel("??????");
                JTextField rowField = new JTextField();
                JLabel labelColumn = new JLabel("??????");
                JTextField columnField = new JTextField();
                JButton selectButton = new JButton("...");
                JButton buttonCreate = new JButton("??????");
                JButton backButton = new JButton("??????");

                areaInfo.setBounds(10, 10, 380, 60);
                labelPath.setBounds(10, 80, 70, 30);
                pathField.setBounds(100, 80, 200, 30);
                selectButton.setBounds(310, 80, 50, 30);
                labelName.setBounds(10, 130, 70, 30);
                nameField.setBounds(100, 130, 250, 30);
                labelRow.setBounds(10, 180, 60, 30);
                rowField.setBounds(80, 180, 100, 30);
                labelColumn.setBounds(190, 180, 60, 30);
                columnField.setBounds(250, 180, 100, 30);
                buttonCreate.setBounds(40, 240, 120, 30);
                backButton.setBounds(200, 240, 120, 30);

                panel0.add(areaInfo);
                panel0.add(labelName);
                panel0.add(labelPath);
                panel0.add(pathField);
                panel0.add(nameField);
                panel0.add(labelRow);
                panel0.add(labelRow);
                panel0.add(rowField);
                panel0.add(columnField);
                panel0.add(selectButton);
                panel0.add(buttonCreate);
                panel0.add(backButton);
                panel0.add(labelColumn);

                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);

                String[] path = new String[1];
                String[] name = new String[1];
                selectButton.addActionListener(e -> {
                    try {
                        path[0] = new FileFiliter().directary();
                        pathField.setText(path[0]);
                    } catch (ClassNotFoundException | InstantiationException |
                            IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                });

                buttonCreate.addActionListener(e -> {
                    name[0] = nameField.getText();
                    if (path[0] != null && name[0] != null) {
                        filePath = path[0] + "\\" + name[0] + ".ktm";
                        System.out.println(filePath);
                        Vector<Vector<String>> dataVector = new Vector<>();
                        int rowLimit = Integer.parseInt(rowField.getText());
                        int columnLimit = Integer.parseInt(columnField.getText());
                        for (int i = 0; i < rowLimit; i++) {
                            Vector<String> temp = new Vector<>();
                            for (int j = 0; j < columnLimit; j++) {
                                temp.add("null");
                            }
                            dataVector.add(temp);
                        }
                        //System.out.println(dataVector.size());
                        StringBuilder builder = new StringBuilder(columnLimit);
                        builder.append("#\n");
                        for (int i = 0; i < rowLimit; i++) {
                            builder.append("|");
                            for (int j = 0; j < columnLimit - 1; j++) {
                                builder.append("null").append(";");
                            }
                            builder.append("null").append("|\n");
                        }
                        String content = builder.toString();
                        try {
                            new FileOperation().createFile(path[0], name[0] + ".ktm");
                            new FileOperation().writeFile(filePath, content);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Vector<String> headVector = new Vector<>();
                        for (int i = 0; i < columnLimit; i++) {
                            headVector.add(String.valueOf(i));
                        }
                        tableModel.setDataVector(dataVector, headVector);
                        table.setModel(tableModel);
                        frame1.dispose();
                        show.setText("????????????:" + name[0] + ".ktm");
                    } else {
                        new warns().frameWithOneButton("??????", "?????????????????????????????????");
                    }
                });

                backButton.addActionListener(e -> frame1.dispose());
            }
        }

        itemNew.addActionListener(e -> {
            new CreateFrame().build();
        });

        itemOpen.addActionListener(e -> {
            try {
                filePath = new FileFiliter().file();
                if (filePath != null) {
                    DataTableModel dataTableModel = new DataTableModel();
                    dataTableModel.run();
                    table.setModel(dataTableModel);
                }

                String[][] data = new FormatStream().formatInput(new FileOperation().readFile(filePath));
                int len = data[0].length;
                String[] head = new String[len];
                Vector<String> vectorHead = new Vector<>();
                for (int i = 0; i < len; i++) {
                    head[i] = String.valueOf(i);
                    vectorHead.add(head[i]);
                }
                Vector<Vector<String>> dataVector = new Vector<>();
                for (String[] datum : data) {
                    Vector<String> temp = new Vector<>(Arrays.asList(datum).subList(0, len));
                    dataVector.add(temp);
                }

                tableModel.setDataVector(dataVector, vectorHead);
                table.setModel(tableModel);
                String name = filePath.split("\\\\")[filePath.split("\\\\").length - 1];
                show.setText("????????????:" + name);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException | IOException ex) {
                ex.printStackTrace();
            }
        });

        class SaveAs {
            private String path;

            public void pathConfirm (String text) {
                JFrame frame0 = new JFrame("?????????");
                frame0.setLocationByPlatform(true);
                frame0.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame0.setResizable(false);
                frame0.setSize(300, 240);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JLabel label = new JLabel("????????????????????????????????????");
                JTextField pathField = new JTextField("?????????????????????");
                JTextField nameField = new JTextField();
                JButton select = new JButton("??????");
                JButton confirm = new JButton("??????");
                JButton back = new JButton("??????");

                label.setBounds(10, 10, 280, 30);
                pathField.setBounds(10, 40, 280, 30);
                nameField.setBounds(10, 90, 190, 30);
                select.setBounds(225, 90, 50, 30);
                confirm.setBounds(40, 150, 90, 30);
                back.setBounds(170, 150, 90, 30);

                panel0.add(label);
                panel0.add(pathField);
                panel0.add(nameField);
                panel0.add(select);
                panel0.add(confirm);
                panel0.add(back);
                panel0.setVisible(true);
                frame0.add(panel0);
                frame0.setVisible(true);

                select.addActionListener(e -> {
                    try {
                        path = new FileFiliter().directary();
                        pathField.setText(path);
                    } catch (ClassNotFoundException | InstantiationException |
                            IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        ex.printStackTrace();
                    }
                });

                confirm.addActionListener(e -> {
                    try {
                        if ((nameField.getText() != null || !nameField.getText().equals("")) &&
                                (path != null || !path.equals(""))) {
                            path = path + "\\" + nameField.getText() + ".ktm";
                            new FileOperation().createFile(path);
                            new FileOperation().writeFile(path, text);
                            frame0.dispose();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        new warns().frameWithOneButton("??????", "??????????????????");
                    }
                });

                back.addActionListener(e -> frame0.dispose());
            }
        }

        itemSaveAs.addActionListener(e -> {
            int len = tableModel.getColumnCount();
            String[] columnNames = new String[len];
            for (int i = 0; i < len; i++) {
                columnNames[i] = tableModel.getColumnName(i);
            }
            String text = new FormatStream().formatOutput(tableModel.getDataVector(), columnNames);
            new SaveAs().pathConfirm(text);
        });

        itemSave.addActionListener(e -> {
            int len = tableModel.getColumnCount();
            String[] columnNames = new String[len];
            for (int i = 0; i < len; i++) {
                columnNames[i] = tableModel.getColumnName(i);
            }
            String text = new FormatStream().formatOutput(tableModel.getDataVector(), columnNames);
            new FileOperation().writeFile(filePath, text);
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                //inner class which provides operation to edit the table
                class TableOperation {
                    public void buildFrame (int row, int column) {
                        JFrame jFrame = new JFrame("?????????:" + row + "???" + column + "???");
                        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        jFrame.setLocationByPlatform(true);
                        jFrame.setResizable(false);
                        jFrame.setSize(300, 320);
                        JPanel jPanel = new JPanel();
                        jPanel.setLayout(null);

                        JTextArea area = new JTextArea("?????????:" + getTableValue(row, column));
                        area.setLineWrap(true);
                        area.setEditable(false);
                        JTextArea newValue = new JTextArea();
                        newValue.setLineWrap(true);
                        JLabel label = new JLabel("?????????(????????????????????????)");
                        JButton confirm0 = new JButton("????????????");
                        JButton back0 = new JButton("??????");
                        area.setBounds(10, 10, 280, 70);
                        label.setBounds(10, 100, 280, 20);
                        newValue.setBounds(10, 140, 280, 70);
                        confirm0.setBounds(40, 240, 90, 30);
                        back0.setBounds(170, 240, 90, 30);

                        jPanel.add(area);
                        jPanel.add(newValue);
                        jPanel.add(label);
                        jPanel.add(confirm0);
                        jPanel.add(back0);
                        jPanel.setVisible(true);
                        jFrame.add(jPanel);
                        jFrame.setVisible(true);

                        confirm0.addActionListener(e1 -> {
                            edit(row, column, newValue.getText());
                            jFrame.dispose();
                        });
                        back0.addActionListener(e -> jFrame.dispose());
                    }

                    public String getTableValue (int row, int column) {
                        return String.valueOf(table.getValueAt(row, column));
                    }

                    public void edit (int row, int column, String value) {
                        table.setValueAt(value, row, column);
                    }
                }
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();

                new TableOperation().buildFrame(row, column);
            }
        });

        itemAdd.addActionListener(e -> {
            class TableAddEcho {

                public void buildSelect () {
                    JFrame frame2 = new JFrame("??????????????????");
                    frame2.setResizable(false);
                    frame2.setLocationByPlatform(true);
                    frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame2.setSize(300, 120);
                    JPanel panel0 = new JPanel();
                    panel0.setLayout(null);

                    JButton rowButton = new JButton("?????????");
                    JButton columnButton = new JButton("?????????");
                    rowButton.setBounds(40, 30, 90, 30);
                    columnButton.setBounds(170, 30, 90, 30);

                    panel0.add(rowButton);
                    panel0.add(columnButton);
                    panel0.setVisible(true);
                    frame2.add(panel0);
                    frame2.setVisible(true);

                    rowButton.addActionListener(e1 -> {
                        frameBuildAddRow();
                        frame2.dispose();
                    });
                    columnButton.addActionListener(e1 -> {
                        frameBuildAddColumn();
                        frame2.dispose();
                    });
                }

                private void frameBuildAddColumn () {
                    JFrame frame1 = new JFrame("?????????");
                    frame1.setLocationByPlatform(true);
                    frame1.setResizable(false);
                    frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame1.setSize(300, 400);
                    JPanel panel1 = new JPanel();
                    panel1.setLayout(null);

                    JTextArea area0 = new JTextArea();
                    JLabel labelHead = new JLabel("????????????");
                    JLabel label0 = new JLabel("?????????");
                    JButton addButton = new JButton("??????");
                    JButton backButton = new JButton("??????");
                    JTextField fieldHeadName = new JTextField();
                    JTextField fieldDefaultValue = new JTextField();

                    area0.setBounds(10, 10, 280, 60);
                    area0.setText("???????????????????????????????????? ???????????????head???null ??????????????????????????? ???????????????????????????");
                    area0.setEditable(false);
                    area0.setLineWrap(true);
                    labelHead.setBounds(10, 80, 80, 30);
                    fieldHeadName.setBounds(90, 80, 180, 30);
                    label0.setBounds(10, 130, 80, 30);
                    fieldDefaultValue.setBounds(90, 130, 180, 30);
                    addButton.setBounds(40, 200, 90, 30);
                    backButton.setBounds(170, 200, 90, 30);

                    panel1.add(area0);
                    panel1.add(label0);
                    panel1.add(labelHead);
                    panel1.add(fieldHeadName);
                    panel1.add(fieldDefaultValue);
                    panel1.add(addButton);
                    panel1.add(backButton);
                    panel1.setVisible(true);
                    frame1.add(panel1);
                    frame1.setVisible(true);

                    addButton.addActionListener(e1 -> {
                        String headName, defaultValue;
                        if (fieldHeadName.getText() == null || "".equals(fieldHeadName.getText())) {
                            headName = "head";
                        } else {
                            headName = fieldHeadName.getText();
                        }
                        if (fieldDefaultValue.getText() == null || "".equals(fieldDefaultValue.getText())) {
                            defaultValue = "null";
                        } else {
                            defaultValue = fieldDefaultValue.getText();
                        }
                        addColumn(headName, defaultValue);
                        frame1.dispose();
                    });
                    backButton.addActionListener(e -> frame1.dispose());
                }

                private void addColumn (String headName, String value) {
                    int len_new = table.getColumnCount() + 1;
                    Vector<String> headVector = new Vector<>();
                    for (int i = 0; i < len_new; i++) {
                        headVector.add(String.valueOf(i));
                    }
                    Vector<Vector<String>> dataVector = new Vector<>();
                    int rowLimit = table.getRowCount();
                    for (int i = 0; i < rowLimit; i++) {
                        Vector<String> temp = new Vector<>();
                        for (int j = 0; j < len_new - 1; j++) {
                            temp.add(getTableVale(i, j));
                        }
                        if (i == 0) {
                            temp.add(headName);
                        }
                        temp.add(value);
                        dataVector.add(temp);
                    }
                    tableModel.setDataVector(dataVector, headVector);
                    table.setModel(tableModel);
                }

                private void addRow (String value) {
                    int height_new = table.getRowCount() + 1;
                    Vector<Vector<String>> dataVector = new Vector<>();
                    int columnLimit = table.getColumnCount();
                    for (int i = 0; i < height_new; i++) {
                        Vector<String> temp = new Vector<>();
                        if (i < height_new - 1) {
                            for (int j = 0; j < columnLimit; j++) {
                                temp.add(getTableVale(i, j));
                            }
                        } else {
                            for (int j = 0; j < columnLimit; j++) {
                                temp.add(value);
                            }
                        }
                        dataVector.add(temp);
                    }
                    Vector<String> headVector = new Vector<>();
                    for (int i = 0; i < columnLimit; i++) {

                        headVector.add(String.valueOf(i));
                    }
                    tableModel.setDataVector(dataVector, headVector);
                    table.setModel(tableModel);
                }

                private void frameBuildAddRow () {
                    JFrame frame1 = new JFrame("?????????");
                    frame1.setLocationByPlatform(true);
                    frame1.setResizable(false);
                    frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frame1.setSize(300, 300);
                    JPanel panel1 = new JPanel();
                    panel1.setLayout(null);

                    JTextArea area0 = new JTextArea();
                    JLabel label0 = new JLabel("?????????");
                    JButton addButton = new JButton("??????");
                    JButton backButton = new JButton("??????");
                    JTextField fieldDefaultValue = new JTextField();

                    area0.setBounds(10, 20, 280, 60);
                    area0.setText("????????????????????? ?????????null ??????????????????????????? ???????????????????????????????????????????????????");
                    area0.setEditable(false);
                    area0.setLineWrap(true);
                    label0.setBounds(10, 100, 80, 30);
                    fieldDefaultValue.setBounds(90, 100, 180, 30);
                    addButton.setBounds(40, 180, 90, 30);
                    backButton.setBounds(170, 180, 90, 30);

                    panel1.add(area0);
                    panel1.add(label0);
                    panel1.add(fieldDefaultValue);
                    panel1.add(addButton);
                    panel1.add(backButton);
                    panel1.setVisible(true);
                    frame1.add(panel1);
                    frame1.setVisible(true);

                    addButton.addActionListener(e1 -> {
                        String defaultValue;
                        if (fieldDefaultValue.getText() == null || "".equals(fieldDefaultValue.getText())) {
                            defaultValue = "null";
                        } else {
                            defaultValue = fieldDefaultValue.getText();
                        }
                        addRow(defaultValue);
                        frame1.dispose();
                    });
                    backButton.addActionListener(e -> frame1.dispose());
                }

                private String getTableVale (int row, int column) {
                    return String.valueOf(table.getValueAt(row, column));
                }
            }

            new TableAddEcho().buildSelect();
        });

        class TableEditOperation {
            @Deprecated
            public void frameSelect () {
                JFrame frame2 = new JFrame("??????????????????");
                frame2.setResizable(false);
                frame2.setLocationByPlatform(true);
                frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame2.setSize(300, 120);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JButton rowButton = new JButton("?????????");
                JButton columnButton = new JButton("?????????");
                rowButton.setBounds(40, 30, 90, 30);
                columnButton.setBounds(170, 30, 90, 30);

                panel0.add(rowButton);
                panel0.add(columnButton);
                panel0.setVisible(true);
                frame2.add(panel0);
                frame2.setVisible(true);

                rowButton.addActionListener(e1 -> {
                    frameBuildEditRow();
                    frame2.dispose();
                });
                columnButton.addActionListener(e1 -> {
                    frameBuildEditColumn();
                    frame2.dispose();
                });
            }

            private void frameBuildEditColumn () {
                JFrame frame1 = new JFrame("?????????");
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setResizable(false);
                frame1.setLocationByPlatform(true);
                frame1.setSize(400, 400);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JTextArea areaInfo = new JTextArea();
                JLabel labelValue = new JLabel("???");
                JTextField fieldValue = new JTextField();
                JLabel labelIndex = new JLabel("??????");
                JTextField index = new JTextField();
                JLabel labelStart = new JLabel("????????????");
                JTextField start = new JTextField();
                JLabel labelEnd = new JLabel("????????????");
                JTextField end = new JTextField();
                JButton editButton = new JButton("??????");
                JButton backButton = new JButton("??????");

                areaInfo.setEditable(false);
                areaInfo.setLineWrap(true);
                areaInfo.setText("??????????????????????????????????????????????????????????????? ??????????????????0");
                areaInfo.setBounds(10, 10, 380, 60);
                labelValue.setBounds(10, 80, 100, 30);
                fieldValue.setBounds(110, 80, 270, 30);
                labelIndex.setBounds(10, 130, 100, 30);
                index.setBounds(110, 130, 270, 30);
                labelStart.setBounds(10, 180, 100, 30);
                start.setBounds(110, 180, 270, 30);
                labelEnd.setBounds(10, 230, 100, 30);
                end.setBounds(110, 230, 270, 30);
                editButton.setBounds(60, 280, 110, 30);
                backButton.setBounds(230, 280, 110, 30);

                panel0.add(areaInfo);
                panel0.add(labelValue);
                panel0.add(fieldValue);
                panel0.add(labelIndex);
                panel0.add(index);
                panel0.add(labelStart);
                panel0.add(start);
                panel0.add(labelEnd);
                panel0.add(end);
                panel0.add(editButton);
                panel0.add(backButton);

                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);

                editButton.addActionListener(e1 -> {
                    if ((fieldValue.getText() == null || "".equals(fieldValue.getText())) ||
                            (index.getText() == null || "".equals(index.getText())) ||
                            (start.getText() == null || "".equals(start.getText())) ||
                            (end.getText() == null || "".equals(end.getText()))) {
                        new warns().frameWithOneButton("??????", "?????????????????????");
                    } else {
                        editColumn(Integer.parseInt(start.getText()), Integer.parseInt(end.getText())
                                , Integer.parseInt(index.getText()), fieldValue.getText());
                        frame1.dispose();
                    }
                });
                backButton.addActionListener(e1 -> frame1.dispose());
            }

            private void editColumn (int start, int end, int columnIndex, String value) {
                for (int i = start; i <= end; i++) {
                    table.setValueAt(value, i, columnIndex);
                }
            }

            private void frameBuildEditRow () {
                JFrame frame1 = new JFrame("?????????");
                frame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame1.setResizable(false);
                frame1.setLocationByPlatform(true);
                frame1.setSize(400, 400);
                JPanel panel0 = new JPanel();
                panel0.setLayout(null);

                JTextArea areaInfo = new JTextArea();
                JLabel labelValue = new JLabel("???");
                JTextField fieldValue = new JTextField();
                JLabel labelIndex = new JLabel("??????");
                JTextField index = new JTextField();
                JLabel labelStart = new JLabel("????????????");
                JTextField start = new JTextField();
                JLabel labelEnd = new JLabel("????????????");
                JTextField end = new JTextField();
                JButton editButton = new JButton("??????");
                JButton backButton = new JButton("??????");

                areaInfo.setEditable(false);
                areaInfo.setLineWrap(true);
                areaInfo.setText("??????????????????????????????????????????????????????????????? ??????????????????0");
                areaInfo.setBounds(10, 10, 380, 60);
                labelValue.setBounds(10, 80, 100, 30);
                fieldValue.setBounds(110, 80, 270, 30);
                labelIndex.setBounds(10, 130, 100, 30);
                index.setBounds(110, 130, 270, 30);
                labelStart.setBounds(10, 180, 100, 30);
                start.setBounds(110, 180, 270, 30);
                labelEnd.setBounds(10, 230, 100, 30);
                end.setBounds(110, 230, 270, 30);
                editButton.setBounds(60, 280, 110, 30);
                backButton.setBounds(230, 280, 110, 30);

                panel0.add(areaInfo);
                panel0.add(labelValue);
                panel0.add(fieldValue);
                panel0.add(labelIndex);
                panel0.add(index);
                panel0.add(labelStart);
                panel0.add(start);
                panel0.add(labelEnd);
                panel0.add(end);
                panel0.add(editButton);
                panel0.add(backButton);

                panel0.setVisible(true);
                frame1.add(panel0);
                frame1.setVisible(true);

                editButton.addActionListener(e1 -> {
                    if ((fieldValue.getText() == null || "".equals(fieldValue.getText())) ||
                            (index.getText() == null || "".equals(index.getText())) ||
                            (start.getText() == null || "".equals(start.getText())) ||
                            (end.getText() == null || "".equals(end.getText()))) {
                        new warns().frameWithOneButton("??????", "?????????????????????");
                    } else {
                        editRow(Integer.parseInt(start.getText()), Integer.parseInt(end.getText())
                                , Integer.parseInt(index.getText()), fieldValue.getText());
                        frame1.dispose();
                    }
                });
                backButton.addActionListener(e1 -> frame1.dispose());
            }

            private void editRow (int start, int end, int rowIndex, String value) {
                for (int i = start; i <= end; i++) {
                    table.setValueAt(value, rowIndex, i);
                }
            }
        }

        itemEditColumn.addActionListener(e -> {
            new TableEditOperation().frameBuildEditColumn();
        });

        itemEditLine.addActionListener(e -> {
            new TableEditOperation().frameBuildEditRow();
        });

        //TODO:finish echo events.

    }

    public String getFilePath () {
        return this.filePath;
    }
}

class DataTableModel extends AbstractTableModel {

    static class Data {
        String[][] data;
        String filePath;

        public Data (String filePath) {
            if (filePath == null) {
                return;
            }
            this.filePath = filePath;
            try {
                this.data = new FormatStream().formatInput(new FileOperation().readFile(this.filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Data data;
    int rowCount;
    int columnCount;

    public void run () {
        if (new Table().getFilePath() == null) {
            return;
        }
        this.data = new Data(new Table().getFilePath());
        this.rowCount = data.data.length;
        this.columnCount = data.data[0].length;
    }

    @Override
    public int getRowCount () {
        return this.rowCount;
    }

    @Override
    public int getColumnCount () {
        return this.columnCount;
    }

    @Override
    public Object getValueAt (int rowIndex, int columnIndex) {
        return data.data[rowIndex][columnIndex];
    }
}

class data {
    public int headAmount;
    public String[] column;
    //public
}

class EmptyTableModel extends AbstractTableModel {
    @Override
    public int getRowCount () {
        return 20;
    }

    @Override
    public int getColumnCount () {
        return 10;
    }

    @Override
    public Object getValueAt (int rowIndex, int columnIndex) {
        return null;
    }
}


class TableColumnModel implements javax.swing.table.TableColumnModel {

    @Override
    public void addColumn (TableColumn aColumn) {

    }

    @Override
    public void removeColumn (TableColumn column) {

    }

    @Override
    public void moveColumn (int columnIndex, int newIndex) {

    }

    @Override
    public void setColumnMargin (int newMargin) {

    }

    @Override
    public int getColumnCount () {
        return 0;
    }

    @Override
    public Enumeration<TableColumn> getColumns () {
        return null;
    }

    @Override
    public int getColumnIndex (Object columnIdentifier) {
        return 0;
    }

    @Override
    public TableColumn getColumn (int columnIndex) {
        return null;
    }

    @Override
    public int getColumnMargin () {
        return 0;
    }

    @Override
    public int getColumnIndexAtX (int xPosition) {
        return 0;
    }

    @Override
    public int getTotalColumnWidth () {
        return 0;
    }

    @Override
    public void setColumnSelectionAllowed (boolean flag) {

    }

    @Override
    public boolean getColumnSelectionAllowed () {
        return false;
    }

    @Override
    public int[] getSelectedColumns () {
        return new int[0];
    }

    @Override
    public int getSelectedColumnCount () {
        return 0;
    }

    @Override
    public void setSelectionModel (ListSelectionModel newModel) {

    }

    @Override
    public ListSelectionModel getSelectionModel () {
        return null;
    }

    @Override
    public void addColumnModelListener (TableColumnModelListener x) {

    }

    @Override
    public void removeColumnModelListener (TableColumnModelListener x) {

    }
}

class HelpActionEvent implements MouseListener {
    @Override
    public void mouseClicked (MouseEvent e) {

    }

    @Override
    public void mousePressed (MouseEvent e) {
        new Build().buildFrame(Build.INFO_FRAME, "????????????", null, """
                ??????:KKoishi_
                ??????:http://kkoishi-514.top
                GitHub:https://github.com/Koishi-Satori
                ?????????:???????????? ???????????? ???????????????
                """);
    }

    @Override
    public void mouseReleased (MouseEvent e) {

    }

    @Override
    public void mouseEntered (MouseEvent e) {

    }

    @Override
    public void mouseExited (MouseEvent e) {

    }
}

class ColoredTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public void setBackground (Color c) {
        super.setBackground(Color.ORANGE);
    }
}