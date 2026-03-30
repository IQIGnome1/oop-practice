
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {

        System.out.println("connecting to server");

        // loob ühenduse serveriga
        // подключаемся к localhost:1337
        try (Socket socket = new Socket("localhost", 1337);

             // loome sisendvoo
             // нужен чтобы читать ответы сервера
             DataInputStream in = new DataInputStream(socket.getInputStream());

             // loome väljundvoo
             // через этот поток будем отправлять данные серверу
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("connected");

            // saadab sõnumite arvu
            // сначала отправляем серверу сколько сообщений мы пришлём
            out.writeInt(args.length);
            out.flush();

            // tsükkel sõnumite saatmiseks
            // отправляем все строки из args и читаем ответ
            for (String sonum : args) {

                // saadab UTF string /отправляем сообщение серверу
                out.writeUTF(sonum);
                out.flush();
                String vastus = in.readUTF();


                System.out.println(vastus);
            }
        }

        System.out.println("finished");
    }
}