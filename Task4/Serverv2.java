import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Serverv2 {

    public static void main(String[] args) throws Exception {

        // loon server socketi
        // сервер слушает порт 1337 и ждёт подключения клиентов
        try (ServerSocket ss = new ServerSocket(1337)) {
            System.out.println("now listening on :1337");

            // lõpmatu tsükkel
            // сервер должен работать постоянно, поэтому он принимает клиентов бесконечно
            while (true) {

                // ootab klienti
                // как только клиент подключился, создаём отдельный поток именно для него
                Socket socket = ss.accept();

                new Thread(() -> {

                    // see thread tegeleb ühe kliendiga
                    // сокет закрывает именно тот поток, который с ним работает
                    try (socket;
                         DataInputStream in = new DataInputStream(socket.getInputStream());
                         DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                        System.out.println("client connected");

                        // loeb requestide arvu
                        // клиент сначала сообщает, сколько всего запросов он пришлёт
                        int kogus = in.readInt();

                        // tsükkel requestide jaoks
                        // сервер читает запросы по одному и на каждый сразу отвечает
                        for (int i = 0; i < kogus; i++) {

                            // loeb requesti tüübi
                            // 1 = echo, 2 = file
                            int type = in.readInt();

                            if (type == 1) {
                                // loeb sõnumi
                                // для echo просто получаем текст
                                String sonum = in.readUTF();

                                // saadab staatuse ok
                                // сначала отправляем статус, чтобы клиент понял что всё нормально
                                out.writeInt(1);

                                // saadab sama teksti tagasi
                                // echo tähendab, et сервер возвращает тот же текст
                                out.writeUTF(sonum);
                                out.flush();

                            } else if (type == 2) {
                                // loeb failinime
                                // клиент просит прислать содержимое файла
                                String failinimi = in.readUTF();

                                Path path = Path.of(failinimi);

                                // kontrollib absolute pathi
                                // по условию absolute path запрещён
                                if (path.isAbsolute()) {
                                    out.writeInt(0);
                                    out.writeUTF("error");
                                    out.flush();
                                    continue;
                                }

                                // kontrollib kas fail eksisteerib
                                // если файла нет или это не обычный файл, отправляем ошибку
                                if (!Files.isRegularFile(path)) {
                                    out.writeInt(0);
                                    out.writeUTF("error");
                                    out.flush();
                                    continue;
                                }

                                // loeb faili sisu
                                // читаем файл как массив байтов, потому что файл может быть не только текстовый
                                byte[] sisu = Files.readAllBytes(path);

                                // saadab staatuse ok
                                // клиент сначала получает статус, потом длину, потом сами данные
                                out.writeInt(1);

                                // saadab faili pikkuse
                                // length prefix нужен, чтобы клиент знал сколько байтов читать
                                out.writeInt(sisu.length);

                                // saadab faili andmed
                                out.write(sisu);
                                out.flush();
                            } else {
                                // tundmatu request
                                // если тип запроса неправильный, отправляем ошибку
                                out.writeInt(0);
                                out.writeUTF("error");
                                out.flush();
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("client error");
                    }

                }).start();
            }
        }
    }
}