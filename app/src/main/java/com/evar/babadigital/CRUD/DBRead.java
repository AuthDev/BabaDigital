package com.evar.babadigital.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

import bbgetset.Criança;
import bbgetset.Sexo;
import bbgetset.Usuário;

/**
 * Created by maxson on 16/04/17.
 */

public class DBRead extends SQLiteOpenHelper{

    private static final String DB_PATH = "/data/user/0/com.evar.babadigital/databases/BB_DB";
    private static final String DB_NAME = "BB_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABLE_CRIANÇAS = "crianças";
    private static final String TABLE_VACINAS = "vacinas";
    private static final String TABLE_VACINAS_TOMADAS = "vacinas_tomadas";
    private static final String TABLE_NOTICACOES = "notificacoes";

    private Context nContext;
    private SQLiteDatabase db;



    public DBRead(Context context) {
        super(context, DB_NAME, null, VERSAO_DB);
        this.nContext =context;
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Criança> getCrianças(Usuário usuário)
    {
        openDB();
        ArrayList<Criança> cArray = new ArrayList<>();
        String getCriaças = "SELECT * FROM "+TABLE_CRIANÇAS+" WHERE usuarioEmail='"+usuário.getEmail()+"'";
        Criança crianca;
        try
        {
            Cursor c = db.rawQuery(getCriaças,null);
            if(c.moveToFirst())
            {
                do{
                    crianca = new Criança();
                   crianca.setCod(c.getInt(0));
                   crianca.setNome(c.getString(1));
                    Sexo sexo = new Sexo();
                    if(c.getString(2).contains("asculi"))
                    {
                        crianca.setSexo(Sexo.MASCULINO);
                    }else
                        {
                            crianca.setSexo(Sexo.FEMININO);
                        }
                    Log.d("data no banco: ",c.getString(3));

                    Date dataNascimento;
                    java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    dataNascimento = df.parse(c.getString(3));
                    crianca.setDataNascimento(dataNascimento);
                    cArray.add(crianca);

                }while (c.moveToNext());
                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return cArray;

    }



    public void openDB()
    {
        if(!db.isOpen())
        {
            db = nContext.openOrCreateDatabase(DB_PATH,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
