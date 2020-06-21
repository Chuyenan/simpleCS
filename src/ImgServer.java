import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ImgServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8998);
        System.out.println("服务器已启动");
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        File file = new File("./outImg");
        if (!file.exists()){
            boolean mkdir = file.mkdir();
            if (mkdir){
                System.out.println("初始化创建完成");
            }
        }
        FileOutputStream fos = new FileOutputStream(file + "/1.png");
        byte[] bytes = new byte[1024];
        int len;
        while((len = is.read(bytes)) != -1){
            fos.write(bytes, 0, len);

        }
        OutputStream os = socket.getOutputStream();
        os.write("已上传".getBytes());
        socket.close();
        serverSocket.close();
        fos.close();

    }
}
