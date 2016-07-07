package app.com.appprestamo.model;

public class Prestamo {
    /*PRE_ID  +   " INTEGER AUTO_INCREMENT," +
            PRE_ID_USU  +   " INTEGER," +
            PRE_DES     +   " TEXT," +
            PRE_FEC     +   " TEXT," +
            PRE_PLA     +   " TEXT," +
            PRE_EST     +   " INTEGER,"+*/
    private int pre_id;
    private int pre_id_usu;
    private String pre_nom;
    private String pre_fec;
    private double pre_pla;
    private int pre_est;

    public Prestamo(int pre_id, int pre_id_usu, String pre_nom, String pre_fec, double pre_pla, int pre_est) {
        this.pre_id = pre_id;
        this.pre_id_usu = pre_id_usu;
        this.pre_nom = pre_nom;
        this.pre_fec = pre_fec;
        this.pre_pla = pre_pla;
        this.pre_est = pre_est;
    }

    public int getPre_id() {
        return pre_id;
    }

    public void setPre_id(int pre_id) {
        this.pre_id = pre_id;
    }

    public int getPre_id_usu() {
        return pre_id_usu;
    }

    public void setPre_id_usu(int pre_id_usu) {
        this.pre_id_usu = pre_id_usu;
    }

    public String getPre_nom() {
        return pre_nom;
    }

    public void setPre_nom(String pre_nom) {
        this.pre_nom = pre_nom;
    }

    public String getPre_fec() {
        return pre_fec;
    }

    public void setPre_fec(String pre_fec) {
        this.pre_fec = pre_fec;
    }

    public double getPre_pla() {
        return pre_pla;
    }

    public void setPre_pla(double pre_pla) {
        this.pre_pla = pre_pla;
    }

    public int getPre_est() {
        return pre_est;
    }

    public void setPre_est(int pre_est) {
        this.pre_est = pre_est;
    }
}
