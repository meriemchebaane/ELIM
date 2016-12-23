import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import persistance.Favoris;
import persistance.Profil;
import persistance.Wifi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by chebaane on 23/12/2016.
 */
public class Serveur {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Profil.class);
            configuration.addAnnotatedClass(Wifi.class);
            configuration.addAnnotatedClass(Favoris.class);
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }


    public static class Connecter implements Runnable {

        private ServerSocket socketserver;
        private Socket socket;
        private int nbrProfil = 1;

        public Connecter(ServerSocket s) {
            socketserver = s;
        }

        public void run() {

            try {
                while (true) {
                    socket = socketserver.accept();
                    System.out.println(nbrProfil + " est connecté !");
                    nbrProfil++;
                    socket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        ServerSocket socket;
        try {
            socket = new ServerSocket(3000);
            Thread t = new Thread(new Connecter(socket));
            t.start();
            //System.out.println("!");

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
//            Profil profil = new Profil();
//            profil.setUsername("Meriem");
//            profil.setPassword("1234");
            Wifi wifi =new Wifi();
            wifi.setBssid("Test");
            wifi.setSsid("");
            wifi.setSecurity("secured");

            Transaction transaction = session.beginTransaction();
            session.save(wifi);
            transaction.commit();
        }
        finally {
            session.close();
        }
    }
}