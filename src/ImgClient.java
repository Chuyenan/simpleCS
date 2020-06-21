import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ImgClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8998);
        System.out.println("客户端已启动");
        FileInputStream fis = new FileInputStream("./img/center.png");
        OutputStream os = socket.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1){
            os.write(bytes, 0, len);
        }
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        int callBack = is.read(bytes);
        System.out.println(new String(bytes, 0, callBack));
        socket.close();
        fis.close();

    }
}
