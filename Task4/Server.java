import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        // loon server socketi
        // делаем сервер который будет слушать порт, без этого клиенты не смогут подключаться
        try (ServerSocket ss = new ServerSocket(1337)) {
            System.out.println("now listening on :1337");

            // lõpmatu tsükkel
            // сервер должен работать постоянно, поэтому используем while(true)
            while (true) {

                // ootab kuni klient ühendub
                // accept() блокируется и ждёт клиента, поэтому сервер не крутит цикл зря
                try (Socket socket = ss.accept();

                     // loome sisendvoo
                     // используем DataInputStream потому что он умеет читать int и UTF строки
                     DataInputStream in = new DataInputStream(socket.getInputStream());

                     // loome väljundvoo
                     // через DataOutputStream удобно отправлять строки обратно клиенту
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("client connected");

                    // loeb int väärtuse
                    // клиент сначала отправляет число,
                    // которое показывает сколько сообщений он будет отправлять
                    int kogus = in.readInt();

                    // tsükkel sõnumite lugemiseks
                    // читаем ровно столько сообщений, сколько сказал клиент
                    for (int i = 0; i < kogus; i++) {

                        // loeb UTF stringi сообщение клиента
                        String sonum = in.readUTF();

                        // saadab stringi tagasi
                        // сервер здесь работает как echo — возвращает то же сообщение
                        out.writeUTF(sonum);

                        // sunnib andmed kohe välja saatma
                        // flush нужен чтобы сообщение сразу отправилось клиенту
                        out.flush();
                    }
                }

                // ühendus suletakse automaatselt
                // после завершения работы с клиентом сервер просто ждёт следующего
            }
        }
    }
}