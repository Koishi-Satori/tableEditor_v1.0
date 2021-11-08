package top.kkoishi.files;

import top.kkoishi.warn_tips_info.Build;
import top.kkoishi.warn_tips_info.warns;

import java.io.*;

public class FileOperation {
    public String readFile (String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder builder = new StringBuilder();
        int len;
        char[] chars = new char[1024];
        while ((len = reader.read(chars)) != - 1) {
            builder.append(new String(chars,0,len));
        }
        reader.close();
        return builder.toString();
    }

    public void createFile (String path,String name) throws IOException {
        File file = new File(path + "\\" + name);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            new warns().buildFrame(Build.ONE_BUTTON_FRAME,"错误","关闭","文件创建失败!");
        }
    }

    public void createFile (String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            new warns().buildFrame(Build.ONE_BUTTON_FRAME,"错误","关闭","文件创建失败!");
        }
    }

    public void writeFile (String path,String text) {
        if (!new File(path).exists()) {
            new warns().buildFrame(Build.ONE_BUTTON_FRAME,"错误","关闭","文件已存在!");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(text);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyFile (String srcPath,String targetPath) throws IOException {
        if (new File(srcPath).exists() && new File(targetPath).exists()) {
            writeFile(targetPath,readFile(srcPath));
        } else {
            new warns().buildFrame(Build.ONE_BUTTON_FRAME,"错误","关闭","文件复制失败!");
        }
    }
}