package app.com.appprestamo.util;

/**
 * CLASE SCRIPT DE LA BASE DE DATOS SQLITE
 */
public class Querys {
    public static final String DB_PRESTAMOS = "Prestamo.db";
    public static final int DB_VERSION = 1;

    //  TABLA TB_TIPO_USUARIO
    public static final String TB_TIPOUSUARIO = "Tb_TipoUsuario";
    public static final String TIP_USU_ID = "tip_usu_id";
    public static final String TIP_USU_DES = "tip_usu_des";
    public static final String CREATE_TB_TIPOUSUARIO = "CREATE TABLE IF NOT EXISTS "+TB_TIPOUSUARIO+"(" +
            TIP_USU_ID + " INTEGER AUTO_INCREMENT," +
            TIP_USU_DES + " TEXT," +
            "PRIMARY KEY("+TIP_USU_ID+"))";
    public static final String DROP_TB_TIPOUSUARIO = "DROP TABLE IF EXISTS "+TB_TIPOUSUARIO;

    //  TABLA USUARIO
    public static final String TB_USUARIO   = "TB_Usuario";
    public static final String USU_ID       =   "usu_id";
    public static final String USU_TIP_USU  =   "usu_tip_id";
    public static final String USU_NOM      =   "usu_nom";
    public static final String USU_APE_PAT  =   "usu_ape_pat";
    public static final String USU_APE_MAT  =   "usu_ape_mat";
    public static final String USU_PAS      =   "usu_pas";
    public static final String USU_TEL      =   "usu_tel";
    public static final String USU_COR      =   "usu_cor";
    public static final String CREATE_TB_USUARIO ="CREATE TABLE IF NOT EXISTS "+TB_USUARIO+"(" +
            USU_ID      +   " INTEGER AUTO_INCREMENT," +
            USU_TIP_USU +   " INTEGER," +
            USU_NOM     +   " TEXT," +
            USU_APE_PAT +   " TEXT," +
            USU_APE_MAT +   " TEXT," +
            USU_PAS     +   " TEXT,"+
            USU_TEL     +   " TEXT,"+
            USU_COR     +   " TEXT UNIQUE,"+
            "PRIMARY KEY("+USU_ID+")," +
            "FOREIGN KEY("+USU_TIP_USU+")REFERENCES "+TB_TIPOUSUARIO+"("+TIP_USU_ID+"))";
    public static final String DROP_TB_USUARIO = "DROP TABLE IF EXISTS "+TB_USUARIO;

    // TABLA PRÉSTAMO
    public static final String TB_PRESTAMO  =   "TB_Prestamo";
    public static final String PRE_ID       =   "pre_id";
    public static final String PRE_ID_USU   =   "pre_usu_id";
    public static final String PRE_DES      =   "pre_des";
    public static final String PRE_FEC      =   "pre_fec";
    public static final String PRE_PLA      =   "pre_pla";
    public static final String PRE_EST      =   "pre_est";
    public static final String CREATE_TB_PRESTAMO   = "CREATE TABLE IF NOT EXISTS "+TB_PRESTAMO+"(" +
            PRE_ID  +   " INTEGER AUTO_INCREMENT," +
            PRE_ID_USU  +   " INTEGER," +
            PRE_DES     +   " TEXT," +
            PRE_FEC     +   " DATE DEFAULT (datetime('now','localtime'))," +
            PRE_PLA     +   " DECIMAL," +
            PRE_EST     +   " INTEGER DEFAULT 0,"+
            "PRIMARY KEY("+PRE_ID+")," +
            "FOREIGN KEY("+PRE_ID_USU+")REFERENCES "+TB_USUARIO+"("+USU_ID+"))";
    public static final String DROP_TB_PRESTAMO = "DROP TABLE IF EXISTS "+TB_PRESTAMO;

}
