public class Programare {
    private String nume;
    private String telefon;
    private int ora;
    private String pachet;
    private String cod;

    public Programare(String nume, String telefon, int ora, String pachet, String cod) {
        this.nume = nume;
        this.telefon = telefon;
        this.ora = ora;
        this.pachet = pachet;
        this.cod = cod;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public String getPachet() {
        return pachet;
    }

    public void setPachet(String pachet) {
        this.pachet = pachet;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "nume='" + nume + '\'' +
                ", telefon='" + telefon + '\'' +
                ", ora=" + ora +
                ", pachet='" + pachet + '\'' +
                ", cod='" + cod + '\'' +
                '}';
    }
}
