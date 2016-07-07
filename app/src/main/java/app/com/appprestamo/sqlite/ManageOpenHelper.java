package app.com.appprestamo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.com.appprestamo.util.Querys;

/**
 * Manejador de BD Sqlite
 * constructor CONTEXTO , NOMBRE DE BD DE SQLITE , NULL , NÚMERO DE VERSIÓN DE BD SQLITE
 * onCreate cuando se instala la app
 * onUpgrade por cada actualización que tenga la app
 *
 */
public class ManageOpenHelper extends SQLiteOpenHelper {

    public ManageOpenHelper(Context context) {
        super(context, Querys.DB_PRESTAMOS, null, Querys.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Querys.CREATE_TB_TIPOUSUARIO);
        db.execSQL(Querys.CREATE_TB_USUARIO);
        db.execSQL(Querys.CREATE_TB_PRESTAMO);
        db.execSQL("insert into Tb_TipoUsuario(tip_usu_des)values('usuario')"); //1
        db.execSQL("insert into Tb_TipoUsuario(tip_usu_des)values('admin')");   //2

        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_cor,usu_pas,usu_tel)values(1,'user','123','123','924948292')");
        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_cor,usu_pas,usu_tel)values(1,'fasf','111','111','924948292')");
        //Usuarios
        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_ape_pat,usu_ape_mat,usu_pas,usu_tel,usu_cor) values" +
                "(1,'joven','pat','mat','qwe','99900111','joven@mail.com')");

        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_ape_pat,usu_ape_mat,usu_pas,usu_tel,usu_cor) values" +
                "(1,'fabian','pat','mat','qwe','954794144','fabian@mail.com')");
        //Administradores
        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_ape_pat,usu_ape_mat,usu_pas,usu_tel,usu_cor) values" +
                "(2,'christian','pat','mat','qwe','961879126','cristan@mail.com')");

        db.execSQL("insert into TB_Usuario(usu_tip_id,usu_nom,usu_ape_pat,usu_ape_mat,usu_pas,usu_tel,usu_cor) values" +
                "(2,'luis','pat','mat','qwe','999333999','luis@mail.com')");

        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(1,'I need money',2000.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(2,'Go to party',300.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(3,'Dont have money',200.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(4,'Only this case i need $500',500.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(1,'I have a meeting',450.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(2,'Dont have dress',300.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(3,'This day need some money',210.00)");
        db.execSQL("insert into TB_Prestamo(pre_usu_id,pre_des,pre_pla)values(4,'Need money guys',200.00)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Querys.DROP_TB_TIPOUSUARIO);
        db.execSQL(Querys.DROP_TB_USUARIO);
        db.execSQL(Querys.DROP_TB_PRESTAMO);
        onCreate(db);
    }
}
