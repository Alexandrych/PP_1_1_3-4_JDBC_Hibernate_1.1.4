package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.JdbcPropertySet;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_DRIVER_KEY = "db.driver";
    private static final String DB_URL_KEY = "db.url";
    private static final String DB_USER_KEY = "db.username";
    private static final String DB_PASSWORD_KEY = "db.password";


    private static final SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    public static SessionFactory getConnectionHibernate() {
        return sessionFactory;
    }

    public static Connection getConnection () {
        Connection connection = null;
        try {
            Class.forName(JdbcPropertiesUtil.get(DB_DRIVER_KEY));
            connection = DriverManager.getConnection(
                    JdbcPropertiesUtil.get(DB_URL_KEY),
                    JdbcPropertiesUtil.get(DB_USER_KEY),
                    JdbcPropertiesUtil.get(DB_PASSWORD_KEY));
            System.out.println("Соединение с базой данных выполнено");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка соединения с базой данных");
        }
        return connection;
    }

    private static final class JdbcPropertiesUtil {
        private static final Properties PROPERTIES = new Properties();

        static {
            try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
                PROPERTIES.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static String get(String key) {
            return PROPERTIES.getProperty(key);
        }
    }
}
