import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Form1 extends JFrame{
    private JPanel mainTabbedPane;
    private JPanel mainPanel;
    private JSpinner spinner1;
    private JTextField textField1;
    private JCheckBox tunsCheckBox;
    private JCheckBox barbaCheckBox;
    private JCheckBox tunsSiBarbaCheckBox;
    private JButton adaugaButton;
    private JTextField textField2;
    private JButton stergeButton;
    private JButton actualizeazaButton;
    private JButton anuleazaButton;
    private JScrollPane jScrollPane;
    public DefaultTableModel table= new DefaultTableModel(new Object[][] {}, new String[] { "Nume", "Telefon", "Ora", "Pachet" });;
    String[][]data=new String[3][4];
    public JTable table1= new JTable(table);
    private JTextField textField3;
    private Programare newProgramare1 = null;

    public Form1(String title, DataBase db) throws HeadlessException, SQLException {
        super(title);
        Connection con;
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/frizerie", "root", "parola");
        Statement st = con.createStatement();
        jScrollPane.setViewportView(table1);
        ResultSet rs = st.executeQuery("select * from programari");
        while (rs.next()) {
            table.addRow(new String[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
        }


        frameSetup();
        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(" ");
                textField2.setText(" ");
                spinner1.setValue(8);
                tunsCheckBox.setSelected(false);
                barbaCheckBox.setSelected(false);
                tunsSiBarbaCheckBox.setSelected(false);
            }
        });

        adaugaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    newProgramare1 = createProgramre();
                    db.AddProgramareToDataBase(newProgramare1);
                    addProgramareToTable(newProgramare1, db);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ora respectiva este ocupata");
                }
            }

        });

        anuleazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = 0, n = 0, ok = 0;
                    PreparedStatement ps = con.prepareStatement("delete from programari where Anulare=? and Ora=?");
                    String cod = textField3.getText();
                    String ora = spinner1.getValue().toString();
                    ps.setString(1, cod);
                    ps.setInt(2, Integer.parseInt(ora));
                    for (i = 0; i < table.getRowCount(); i++)
                        if (cod.equalsIgnoreCase(table.getValueAt(i, 1).toString()) && ora.equalsIgnoreCase(table.getValueAt(i, 1).toString())) {
                            ok = 1;//gasit
                            n = i;
                        }
                    if (ok == 1) {
                        table.removeRow(n);
                        ok = 0;
                    }
                    int m = ps.executeUpdate();
                    if (m != 0) {
                        JOptionPane.showMessageDialog(null, "Programare anulata!");
                    } else JOptionPane.showMessageDialog(null, "Programare inexistenta!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        actualizeazaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement ps = con.prepareStatement("update programari set Nume=?, Ora=?, Pachet=? where Telefon=?");
                    ps.setString(1, textField1.getText());
                    ps.setString(2, spinner1.getValue().toString());

                    String tipPachet = null;
                    if (tunsCheckBox.isSelected()) tipPachet = tunsCheckBox.getText();
                    if (barbaCheckBox.isSelected()) tipPachet = barbaCheckBox.getText();
                    if (tunsSiBarbaCheckBox.isSelected()) tipPachet = tunsSiBarbaCheckBox.getText();


                    ps.setString(3, tipPachet);

                    ps.setString(4, textField2.getText());
                    int n = ps.executeUpdate();
                    if (n != 0)
                        JOptionPane.showMessageDialog(null, "Actualizat cu succes!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    private void frameSetup() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setEnabled(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setContentPane(mainTabbedPane);
        mainPanel.setBorder(BorderFactory.createCompoundBorder());
        this.spinner1.setValue(8);
        this.pack();
    }

    private Programare createProgramre() {
        String nume = this.textField1.getText();
        String telefon = this.textField2.getText();
        int ora = (int) this.spinner1.getValue();
        String tipJob = null;
        if (tunsCheckBox.isSelected()) tipJob = tunsCheckBox.getText();
        if (barbaCheckBox.isSelected()) tipJob = barbaCheckBox.getText();
        if (tunsSiBarbaCheckBox.isSelected()) tipJob = tunsSiBarbaCheckBox.getText();
        String cod = this.textField3.getText();
        Programare newProgramare1 = new Programare(nume, telefon, ora, tipJob, cod);
        return newProgramare1;
    }

    private void addProgramareToTable(Programare newProgramare1, DataBase db) throws SQLException {


    }
}
