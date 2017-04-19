package com.evar.babadigital.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import bbgetset.Criança;
import bbgetset.Vacina;
import bbgetset.VacinaIdade;
import bbgetset.VacinaTomada;

/**
 * Created by maxson on 16/04/17.
 */

public class DBCreate extends SQLiteOpenHelper{

    private static final String DB_PATH = "/data/user/0/com.evar.babadigital/databases/BB_DB";
    private static final String DB_NAME = "BB_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABLE_CRIANÇAS = "crianças";
    private static final String TABLE_VACINAS = "vacinas";
    private static final String TABLE_VACINAS_TOMADAS = "vacinas_tomadas";
    private static final String TABLE_NOTICACOES = "notificacoes";

    private Context nContext;
    private SQLiteDatabase db;



    public DBCreate(Context context) {
        super(context, DB_NAME, null, VERSAO_DB);
        this.nContext =context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean createTable()
    {
        openDB();
        String createTable1 =  "CREATE TABLE IF NOT EXISTS "+TABLE_CRIANÇAS+" ("
                + "cod INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT,"
                + "sexo TEXT,"
                + "dataNascimento TEXT,"
                + "usuarioEmail TEXT)";
        String createTable2 =  "CREATE TABLE IF NOT EXISTS "+TABLE_VACINAS+" ("
                + "cod INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT,"
                + "informacoes TEXT,"
                + "vacinaIdade TEXT,"
                + "rede TEXT)";

        String createTable3 =  "CREATE TABLE IF NOT EXISTS "+TABLE_VACINAS_TOMADAS+" ("
                + "cod INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "data TEXT,"
                + "localTomado TEXT,"
                + "validade TEXT,"
                + "vacina_cod INT,"
                + "criança_cod INT)";

        String createTable4 = "CREATE TABLE IF NOT EXISTIS "+TABLE_NOTICACOES+"("
                + "cod INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"descricao TEXT,"
                +"crianca_cod INT)";



        try {
            db.execSQL(createTable1);
            db.execSQL(createTable2);
            db.execSQL(createTable3);
            db.execSQL(createTable4);
            return true;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public void openDB()
    {
        if(!db.isOpen())
        {
            db = nContext.openOrCreateDatabase(DB_PATH,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
