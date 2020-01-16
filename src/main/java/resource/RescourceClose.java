package resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 对比JDK7前后资源关闭的方法
 * @author cbpro
 */
public class RescourceClose {

    /**
     * JDK7之前资源关闭的方法(显式关闭)
     */
    @Test
    public void oldCopyFile() {

        //定义输入输出流路径
        String originalUrl = "/home/cbpro/IdeaProjects/cbpro996.ICU/src/main/java/resource/RescourceClose.java";
        String targetUrl = "/home/cbpro/Desktop/oldCopyFile.txt";

        //声明输入输出流
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {

            //实例化输入输出流
            fileInputStream = new FileInputStream(originalUrl);
            fileOutputStream = new FileOutputStream(targetUrl);

            //读取输入流并写入输出流
            int content;
            while ((content = fileInputStream.read()) != -1) {
                fileOutputStream.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关流
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * JDK7以后资源关闭的方法(隐式关闭)
     */
    @Test
    public void newCopyFile() {

        //定义输入输出流路径
        String originalUrl = "/home/cbpro/IdeaProjects/cbpro996.ICU/src/main/java/resource/RescourceClose.java";
        String targetUrl = "/home/cbpro/Desktop/newCopyFile.txt";
        try (
                //实例化输入输出流
                FileInputStream fileInputStream = new FileInputStream(originalUrl);
                FileOutputStream fileOutputStream = new FileOutputStream(targetUrl)
        ) {
            //读取输入流并写入输出流
            int content;
            while ((content = fileInputStream.read()) != -1) {
                fileOutputStream.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
