package app.com.appprestamo.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.com.appprestamo.model.Prestamo;
import app.com.appprestamo.model.Usuario;
import app.com.appprestamo.util.Querys;

/**
 * Clase de consultas al sqlite
 */
public class QuerysSQL {
    private static final String TAG = QuerysSQL.class.getSimpleName();
    private ManageOpenHelper dbConexion;

    public QuerysSQL(Context context){
        dbConexion = new ManageOpenHelper(context);
    }

    public boolean validarUsuario(String correo, String password){
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "Select * from "+ Querys.TB_USUARIO +" where " + Querys.USU_COR + "='"+ correo + "'" +
                                                        " and " + Querys.USU_PAS + "='" +password +"'",null);
        return (cursor != null ? (cursor.getCount() > 0 ? true : false) : false);
    }

    public ArrayList<Prestamo> listarPrestamos( int idUsuario){
        ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Querys.TB_PRESTAMO +
                //" where " + Querys.PRE_ID_USU + "=" + idUsuario +
                " order by "+ Querys.PRE_FEC +" desc",null);
        if (cursor!=null){
            if (cursor.getCount()>0){
                if (cursor.moveToFirst()){
                    do {
                        Prestamo prestamo = new Prestamo(cursor.getInt(cursor.getColumnIndex(Querys.PRE_ID)),
                                                        cursor.getInt(cursor.getColumnIndex(Querys.PRE_ID_USU)),
                                                        cursor.getString(cursor.getColumnIndex(Querys.PRE_DES)),
                                                        cursor.getString(cursor.getColumnIndex(Querys.PRE_FEC)),
                                                        cursor.getDouble(cursor.getColumnIndex(Querys.PRE_PLA)),
                                                        cursor.getInt(cursor.getColumnIndex(Querys.PRE_EST)));
                        lista.add(prestamo);
                    }while (cursor.moveToNext());
                }
            }
        }
        return lista;
    }

    public void registrarPrestamo(int pre_id_usu,double pre_pla,String pre_des){
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        db.execSQL("Insert into "+Querys.TB_PRESTAMO +"(" +
                Querys.PRE_ID_USU+","+Querys.PRE_PLA+","+Querys.PRE_DES+")" +
                "values (" + pre_id_usu + "," + pre_pla + ",'" + pre_des + "');");
    }


    public int crearSesion(String username, String password){
        int idPersona = 0;
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO
                +" where "+Querys.USU_NOM + " = '" + username +"' and " +Querys.USU_PAS + "='" + password + "'",null);
        if (cursor!=null){
            if (cursor.getCount()>0){
                if (cursor.moveToFirst()){
                    do {
                        idPersona = cursor.getInt(cursor.getColumnIndex(Querys.USU_ID));
                    }while (cursor.moveToNext());
                }
            }
        }
        return idPersona;
    }

    public int traerTipoUsuario(String correo){
        int tipoId = 0;
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Querys.TB_USUARIO
                + " where " + Querys.USU_COR + " = '" + correo+"'",null);
        if (cursor.moveToFirst()){
            do {
                tipoId = cursor.getInt(cursor.getColumnIndex(Querys.USU_TIP_USU));
            }while (cursor.moveToNext());
        }
        return tipoId;
    }

    public int traerIdUsuario(String correo){
        int idUsuario = 0;
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+Querys.TB_USUARIO
                + " where " + Querys.USU_COR + " = '" + correo+"'",null);
        if (cursor.moveToFirst()){
            do {
                idUsuario = cursor.getInt(cursor.getColumnIndex(Querys.USU_ID));
            }while (cursor.moveToNext());
        }
        return idUsuario;
    }

    public boolean registrarUsuario(String usu_nom,String usu_ape_pat,String usu_ape_mat,String usu_pas,String usu_tel,String usu_cor){
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO + " where "+Querys.USU_COR + " ='" + usu_cor + "'",null);
        if (cursor.moveToFirst()){
            Log.d(TAG,cursor.toString()+"FALSE");
            return false;
        }else{
            db.execSQL("Insert into "+Querys.TB_USUARIO+"(" +
                    Querys.USU_TIP_USU+","+Querys.USU_NOM+","+Querys.USU_APE_PAT+","+Querys.USU_APE_MAT+","+
                    Querys.USU_PAS+","+Querys.USU_TEL+","+Querys.USU_COR+")" +
                    "VALUES (" +
                    "1,'"+usu_nom+"','"+usu_ape_pat+"','"+usu_ape_mat+"','"+usu_pas+"','"+usu_tel+"','"+usu_cor+"');");
            Log.d(TAG,"TRUE");
            db.close();
            return true;
        }
    }

    public boolean actualizarPassword(String password,String newPassword){
        int codUsuario = -1;
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO + " where " +
                Querys.USU_PAS + " = "+password ,null);
        if (cursor!=null){
            if (cursor.getCount()>0){
                if (cursor.moveToFirst()){
                    do {
                        codUsuario = cursor.getInt(cursor.getColumnIndex(Querys.USU_ID));
                        db.execSQL("update "+Querys.TB_USUARIO + " set " +Querys.USU_COR +
                                "='" + newPassword + "' where "+Querys.USU_ID + " = "+codUsuario);
                        cursor.close();
                        db.close();
                    }while (cursor.moveToNext());
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean existeUsuario(String username, String correo){
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO +
                " where " + Querys.USU_NOM + " ='" + username + "'" +
                " and " + Querys.USU_COR + " ='"+correo + "'",null);
        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public void cambiarContrasenia(String correo, String contrasenia){
        SQLiteDatabase db = dbConexion.getWritableDatabase();
        db.execSQL("update " + Querys.TB_USUARIO + " set " +
            Querys.USU_PAS + " = '" + contrasenia +"'"+
            " where "+ Querys.USU_COR + " ='" + correo+"'");
        Log.d(TAG,contrasenia);
        db.close();
    }

    public ArrayList<Usuario> listarCobradoresOUsuarios(int id){
        ArrayList<Usuario> lista = new ArrayList<>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO +
                                    " where " + Querys.USU_TIP_USU + " = " + id ,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                do {
                    Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex(Querys.USU_ID)),
                                                cursor.getString(cursor.getColumnIndex(Querys.USU_NOM)),
                                                cursor.getString(cursor.getColumnIndex(Querys.USU_TEL)),
                                                cursor.getInt(cursor.getColumnIndex(Querys.USU_TIP_USU)),
                                                cursor.getString(cursor.getColumnIndex(Querys.USU_COR)));
                    lista.add(usuario);
                }while (cursor.moveToNext());
            }
        }
        return lista;
    }

    public ArrayList<Usuario> listarCobradoresOUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<>();
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                do {
                    Usuario usuario = new Usuario(cursor.getInt(cursor.getColumnIndex(Querys.USU_ID)),
                            cursor.getString(cursor.getColumnIndex(Querys.USU_NOM)),
                            cursor.getString(cursor.getColumnIndex(Querys.USU_TEL)),
                            cursor.getInt(cursor.getColumnIndex(Querys.USU_TIP_USU)),
                            cursor.getString(cursor.getColumnIndex(Querys.USU_COR)));
                    lista.add(usuario);
                }while (cursor.moveToNext());
            }
        }
        return lista;
    }

    public Usuario traerCuenta(String correo){
        SQLiteDatabase db = dbConexion.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Querys.TB_USUARIO
                + " where " + Querys.USU_COR + " = '" + correo + "'",null);
        if (cursor != null){
            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()){
                    do {
                        Usuario usuario = new Usuario();
                        usuario.setUsu_id(cursor.getInt(cursor.getColumnIndex(Querys.USU_ID)));
                        usuario.setUsu_nom(cursor.getString(cursor.getColumnIndex(Querys.USU_NOM)));
                        usuario.setUsu_ape_pat(cursor.getString(cursor.getColumnIndex(Querys.USU_APE_PAT)));
                        usuario.setUsu_ape_mat(cursor.getString(cursor.getColumnIndex(Querys.USU_APE_MAT)));
                        usuario.setUsu_pas(cursor.getString(cursor.getColumnIndex(Querys.USU_PAS)));
                        usuario.setUsu_tel(String.valueOf(cursor.getInt(cursor.getColumnIndex(Querys.USU_TEL))));
                        usuario.setUsu_cor(cursor.getString(cursor.getColumnIndex(Querys.USU_COR)));
                        Log.d(TAG,String.valueOf(usuario.getUsu_id()));
                        return usuario;
                    }while (cursor.moveToNext());
                }
            }
        }
        db.close();
        Log.d(TAG,"No se encontró registro joven con "+cursor.toString());
        return null;
    }

    /*db.execSQL("UPDATE " + Querys.TB_PRODUCTO + " set " + Querys.C_DES_PRO +
            "='" + productos.getDespro() + "'," + Querys.C_CANT_PRO +
            "='" + productos.getCantpro() + "'," + Querys.C_PRE_PRO +
            "='" + productos.getPrepro() + "'," + Querys.C_TIP_PRO +
            "='" + productos.getTippro() + "' WHERE " + Querys.C_COD_PRO +
            "=" + productos.getCodpro());*/

}
