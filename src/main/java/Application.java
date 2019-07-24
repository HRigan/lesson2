
import model.LangList;
import model.UserDeveloper;
import org.hibernate.Session;
import service.HibernateService;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Application extends Thread {

    public static void main(String[] args) {

        LangList langList=new LangList("java");
        ArrayList<LangList> langLists1=new ArrayList<>();
        langLists1.add(langList);
        UserDeveloper userDeveloper=new UserDeveloper("a","900",langLists1);
        ArrayList<UserDeveloper> userDevelopers=new ArrayList<>();
        userDevelopers.add(userDeveloper);
        langList.setLangName(userDevelopers);

        Session session =HibernateService.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(langList);
        session.save(userDeveloper);
        session.getTransaction().commit();
        session.close();
        Session session1 =HibernateService.getSessionFactory().openSession();
        session1.beginTransaction();
        List<UserDeveloper> users = session1.createQuery("from UserDeveloper").list();
        users.forEach(u -> System.out.println(String.format("%s, %s, %s!",
                u.getFio(),
                u.getPhone(),
                u.getLang().stream().findFirst().get().getName())));
        session1.getTransaction().commit();
        session1.close();

//        try
//        {
//            int i = 0; // счётчик подключений
//
//            // привинтить сокет на локалхост, порт 3128
//            ServerSocket server = new ServerSocket(3128, 0,
//                    InetAddress.getByName("localhost"));
//
//            System.out.println("server is started");
//
//            // слушаем порт
//            while(true)
//            {
//                // ждём нового подключения, после чего запускаем обработку клиента
//                // в новый вычислительный поток и увеличиваем счётчик на единичку
//                new Server(i, server.accept());
//                i++;
//            }
//        }
//        catch(Exception e)
//        {System.out.println("init error: "+e);} // вывод исключений
    }
}
