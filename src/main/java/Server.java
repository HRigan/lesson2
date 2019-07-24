import model.UserManager;
import interfaces.UserInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Server extends Thread implements Runnable  {
    Socket s;
    int num;
    public Server(int num, Socket s)
    {
        this.num = num;
        this.s = s;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    @Override
    public void run() {
        try
        {
            // из сокета клиента берём поток входящих данных
            InputStream is = s.getInputStream();
            // и оттуда же - поток данных от сервера к клиенту
            OutputStream os = s.getOutputStream();

            // буффер данных в 64 килобайта
            byte buf[] = new byte[64*1024];
            // читаем 64кб от клиента, результат - кол-во реально принятых данных
            int r = is.read(buf);
            System.out.println(is.read(buf));
            // создаём строку, содержащую полученную от клиента информацию
            String data = new String(buf, 0, r);

            UserInfo userInfo= new UserManager();
            // выводим данные:
            os.write(userInfo.findById(num,data).getBytes());

            // завершаем соединение
            s.close();
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}
