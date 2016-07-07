package app.com.appprestamo.model;

public class Usuario {
    private int usu_id;
    private int usu_tip_id;
    private String usu_nom;
    private String usu_ape_pat;
    private String usu_ape_mat;
    private String usu_log;
    private String usu_pas;
    private String usu_tel;
    private String usu_cor;

    public Usuario(){}

    public Usuario(int usu_id, int usu_tip_id, String usu_nom, String usu_ape_pat, String usu_ape_mat, String usu_log, String usu_pas, String usu_tel, String usu_cor) {
        this.usu_id = usu_id;
        this.usu_tip_id = usu_tip_id;
        this.usu_nom = usu_nom;
        this.usu_ape_pat = usu_ape_pat;
        this.usu_ape_mat = usu_ape_mat;
        this.usu_log = usu_log;
        this.usu_pas = usu_pas;
        this.usu_tel = usu_tel;
        this.usu_cor = usu_cor;
    }

    public Usuario(int usu_id, String usu_nom, String usu_tel, int usu_tip_id , String usu_cor) {
        this.usu_id = usu_id;
        this.usu_nom = usu_nom;
        this.usu_tel = usu_tel;
        this.usu_tip_id = usu_tip_id;
        this.usu_cor = usu_cor;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public int getUsu_tip_id() {
        return usu_tip_id;
    }

    public void setUsu_tip_id(int usu_tip_id) {
        this.usu_tip_id = usu_tip_id;
    }

    public String getUsu_nom() {
        return usu_nom;
    }

    public void setUsu_nom(String usu_nom) {
        this.usu_nom = usu_nom;
    }

    public String getUsu_ape_pat() {
        return usu_ape_pat;
    }

    public void setUsu_ape_pat(String usu_ape_pat) {
        this.usu_ape_pat = usu_ape_pat;
    }

    public String getUsu_ape_mat() {
        return usu_ape_mat;
    }

    public void setUsu_ape_mat(String usu_ape_mat) {
        this.usu_ape_mat = usu_ape_mat;
    }

    public String getUsu_log() {
        return usu_log;
    }

    public void setUsu_log(String usu_log) {
        this.usu_log = usu_log;
    }

    public String getUsu_pas() {
        return usu_pas;
    }

    public void setUsu_pas(String usu_pas) {
        this.usu_pas = usu_pas;
    }

    public String getUsu_tel() {
        return usu_tel;
    }

    public void setUsu_tel(String usu_tel) {
        this.usu_tel = usu_tel;
    }

    public String getUsu_cor() {
        return usu_cor;
    }

    public void setUsu_cor(String usu_cor) {
        this.usu_cor = usu_cor;
    }
}
