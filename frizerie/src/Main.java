import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBase db = new DataBase("jdbc:mysql://127.0.0.1:3306/frizerie", "root", "parola");
        JFrame frame = new Form1("Best Barber", db);
        frame.setVisible(true);
    }
}   