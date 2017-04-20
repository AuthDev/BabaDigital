package com.evar.babadigital.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import bbgetset.Criança;
import bbgetset.Notificacoes;
import bbgetset.Usuário;
import bbgetset.Vacina;
import bbgetset.VacinaTomada;

/**
 * Created by maxson on 16/04/17.
 */

public class DBUpdate extends SQLiteOpenHelper{

    private static final String DB_PATH = "/data/user/0/com.evar.babadigital/databases/BB_DB";
    private static final String DB_NAME = "BB_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABLE_CRIANÇAS = "crianças";
    private static final String TABLE_VACINAS = "vacinas";
    private static final String TABLE_VACINAS_TOMADAS = "vacinas_tomadas";
    private static final String TABLE_NOTICACOES = "notificacoes";

    private Context nContext;
    private SQLiteDatabase db;



    public DBUpdate(Context context) {
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

    public boolean addCrianca(Criança criança, Usuário usuário)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("nome",criança.getNome());


        values.put("dataNascimento",criança.getDataNascimentoString());


        values.put("sexo",criança.getSexo().toString());
        values.put("usuarioEmail",usuário.getEmail());

        try {
            db.insert(TABLE_CRIANÇAS,null,values);
            return true;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
    public boolean addVacina(Vacina vacina)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("nome",vacina.getNome());
        values.put("informacoes",vacina.getInformaçao());
        values.put("vacinaIdade", vacina.getVacinaIdade());

        try {
            db.insert(TABLE_VACINAS,null,values);
            return true;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
    public boolean addVacinaTomada(VacinaTomada vacinaTomada)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("data",vacinaTomada.getData().toString());
        values.put("local",vacinaTomada.getLocalTomado());
        values.put("validade", vacinaTomada.getValidade().toString());
        values.put("vacina_cod", vacinaTomada.getVacinaCod());
        values.put("crianca_cod", vacinaTomada.getCriancaCod());

        try {
            db.insert(TABLE_VACINAS_TOMADAS,null,values);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean addNotificacoes(Notificacoes notificacoes)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("descricao",notificacoes.getDescricao());
        values.put("crianca_cod",notificacoes.getCriança().getCod());

        try {
            db.insert(TABLE_NOTICACOES,null,values);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }



    public boolean updateCrianca(Criança criança, Usuário usuário)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("nome",criança.getNome());
        values.put("dataNascimento",criança.getDataNascimentoString());
        values.put("sexo",criança.getSexo().toString());
        values.put("usuarioEmail",usuário.getEmail());

        try {
            db.update(TABLE_CRIANÇAS,values,"cod="+criança.getCod(),null);
            return true;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateVacina(Vacina vacina)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("nome",vacina.getNome());
        values.put("informacoes",vacina.getInformaçao());
        values.put("vacinaIdade", vacina.getVacinaIdade());

        try {
            db.insert(TABLE_VACINAS,null,values);
            return true;


        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateVacinaTomada(VacinaTomada vacinaTomada)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("data",vacinaTomada.getData().toString());
        values.put("local",vacinaTomada.getLocalTomado());
        values.put("validade", vacinaTomada.getValidade().toString());
        values.put("vacina_cod", vacinaTomada.getVacinaCod());
        values.put("crianca_cod", vacinaTomada.getCriancaCod());

        try {
            db.insert(TABLE_VACINAS_TOMADAS,null,values);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean deleteCrianca(int codigo) {

        openDB();
        try {
            Log.e("deleteCrianca: ", " "+codigo);
            db.delete(TABLE_CRIANÇAS,"cod="+codigo,null);
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
