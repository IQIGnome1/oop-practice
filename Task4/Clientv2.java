import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Clientv2 {

    public static void main(String[] args) throws Exception {

        // kontrollib argumente
        // аргументы должны идти парами: echo tekst või file failinimi
        if (args.length % 2 != 0) {
            System.out.println("Arguments must be in pairs");
            return;
        }

        System.out.println("connecting to server");

        // loob ühenduse serveriga
        // подключаемся к localhost:1337
        try (Socket socket = new Socket("localhost", 1337);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("connected");

            // loob received kausta
            // сюда будем сохранять файлы, которые пришли от сервера
            Files.createDirectories(Path.of("received"));

            // arvutab requestide arvu
            // каждая пара аргументов = один запрос
            int kogus = args.length / 2;

            // saadab requestide arvu
            // сервер должен заранее знать, сколько запросов мы ему пришлём
            out.writeInt(kogus);
            out.flush();

            // tsükkel paaride kaupa
            // идём по аргументам по два: тип + значение
            for (int i = 0; i < args.length; i += 2) {
                String type = args[i];
                String value = args[i + 1];

                if (type.equals("echo")) {
                    // saadab requesti tüübi
                    // type 1 tähendab echo request
                    out.writeInt(1);

                    // saadab sõnumi
                    out.writeUTF(value);
                    out.flush();

                    // loeb staatuse
                    // сначала получаем статус ответа
                    int status = in.readInt();

                    if (status == 1) {
                        // loeb vastuse teksti
                        String vastus = in.readUTF();
                        System.out.println(vastus);
                    } else {
                        // loeb veateate
                        String error = in.readUTF();
                        System.out.println(error);
                    }

                } else if (type.equals("file")) {
                    // saadab requesti tüübi
                    // type 2 tähendab file request
                    out.writeInt(2);

                    // saadab failinime
                    out.writeUTF(value);
                    out.flush();

                    // loeb staatuse
                    int status = in.readInt();

                    if (status == 1) {
                        // loeb faili pikkuse
                        // клиент сначала узнаёт размер файла
                        int pikkus = in.readInt();

                        byte[] andmed = new byte[pikkus];

                        // loeb kõik baidid
                        // readFully важен, чтобы дочитать файл полностью
                        in.readFully(andmed);

                        // võtab ainult faili nime
                        // сохраняем файл только по имени, без путей
                        String nimi = Path.of(value).getFileName().toString();
                        Path tee = Path.of("received", nimi);

                        // salvestab faili
                        Files.write(tee, andmed);

                        System.out.println("saved " + tee);
                    } else {
                        // loeb veateate
                        String error = in.readUTF();
                        System.out.println(error);
                    }

                } else {
                    // tundmatu argument
                    // если в командной строке не echo и не file, просто сообщаем об ошибке
                    System.out.println("Unknown request type: " + type);
                }
            }
        }

        System.out.println("finished");
    }
}