package lambda.file;

import java.io.*;

/**
 * 文件服务类
 */
public class FileService {

    /**
     * 通过URL获取本地文件内容，调用函数式接口处理
     * @param url
     * @param fileConsumer
     */
    public void fileHandler(String url,FileConsumer fileConsumer) throws IOException {
        //创建文件读取流
        BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(new FileInputStream(url)));

        //循环读取文件内容
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }

        //调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
        fileConsumer.fileHandler(stringBuilder.toString());
    }
}
