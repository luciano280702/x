import javax.swing.*;
import java.sql.*;

public class DataBase {
    private Connection con;
    private ResultSet rs;
    private int RestultSetSize;
    private ResultSetMetaData rsm;
    public DataBase(String url, String user, String password) throws SQLException {
        super();
        this.con = DriverManager.getConnection(url, user, password);
    }
    public void AddProgramareToDataBase(Programare givenProgramare) throws SQLException {
        PreparedStatement pst = con.prepareStatement("INSERT INTO programari values (?,?,?,?,?)");
        if (givenProgramare.getOra()>=8 && givenProgramare.getOra()<=16){
        pst.setString(1, givenProgramare.getNume());
        pst.setString(2, givenProgramare.getTelefon());
        pst.setInt(3, givenProgramare.getOra());
        pst.setString(4, givenProgramare.getPachet());
        pst.setString(5, givenProgramare.getCod());
        if (pst.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "Programare adaugata cu succes!");
        }}
        else
            JOptionPane.showMessageDialog(null, "Ora invalida!");
        pst.close();
    }
}
