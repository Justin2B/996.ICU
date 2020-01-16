package lambda.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandler() throws IOException {
        FileService fileService = new FileService();
        String url="/home/cbpro/IdeaProjects/cbpro996.ICU/src/main/java/lambda/file/FileService.java";
        fileService.fileHandler(url,fileContent -> {
            //打印文本内容
            System.out.println(fileContent);
        });
    }
}
