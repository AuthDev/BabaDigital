package com.evar.babadigital.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import bbgetset.Criança;
import bbgetset.Notificacao;
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
    public boolean addVacinas(ArrayList<Vacina> vacina)
    {
        if (vacina == null)
        {
            return false;
        }

        openDB();
        ContentValues values;
        for (int i = 0; i < vacina.size(); i++) {
            values = new ContentValues();
            values.put("cod",vacina.get(i).getCod());
            values.put("idade",vacina.get(i).getDias());
            values.put("vacina",vacina.get(i).getNome());
            values.put("dose",vacina.get(i).getDoses());
            values.put("descricao",vacina.get(i).getDescricao());
            values.put("publica",String.valueOf(vacina.get(i).isPublica()));
            values.put("particular",String.valueOf(vacina.get(i).isParticular()));
            values.put("intervalo",vacina.get(i).getIntervalo());

            try {
                db.replace(TABLE_VACINAS,null,values);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        db.close();
        return true;

    }
    public boolean addVacinaTomada(VacinaTomada vacinaTomada)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("data",vacinaTomada.getData().getTime());
        values.put("localTomado",vacinaTomada.getLocalTomado());
        values.put("validade", String.valueOf(vacinaTomada.getValidade().getTime()));
        values.put("vacina_cod", vacinaTomada.getCod());
        values.put("dose", vacinaTomada.getDose());
        values.put("isTomada", String.valueOf(vacinaTomada.isTomada()));
        values.put("personalizadas", String.valueOf(vacinaTomada.isPersonalizada()));
        values.put("criança_cod", vacinaTomada.getCriancaCod());
        values.put("idade",vacinaTomada.getDias());

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


    public boolean addNotificacoes(Notificacao notificacao)
    {
        openDB();
        ContentValues values = new ContentValues();
        values.put("titulo", notificacao.getTitulo());
        values.put("dose", notificacao.getDose());
        values.put("descricao", notificacao.getDescricao());
        values.put("item_cod", notificacao.getCodItem());
        values.put("crianca_cod", notificacao.getCriança().getCod());

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

    public boolean deleteNotificacoes(Notificacao notificacao)
    {
        openDB();


        try {
            db.delete(TABLE_NOTICACOES,"item_cod="+notificacao.getCodItem()+" and crianca_cod="+notificacao.getCriança().getCod()+" and dose="+notificacao.getDose(),null);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean deleteAllNotifByCrianca(int CodCrianca)
    {
        openDB();


        try {
            db.delete(TABLE_NOTICACOES,"crianca_cod="+CodCrianca,null);
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
        values.put("data",vacinaTomada.getData().getTime());
        values.put("localTomado",vacinaTomada.getLocalTomado());
        values.put("validade", vacinaTomada.getValidade().getTime());
        values.put("dose",vacinaTomada.getDose());
        values.put("idade",vacinaTomada.getDias());


        try {
            db.update(TABLE_VACINAS_TOMADAS,values,"cod="+vacinaTomada.getVacinaTomadaCod(),null);
            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
    public boolean deleteVacinaTomada(VacinaTomada vacinaTomada)
    {
        openDB();

        try {
            db.delete(TABLE_VACINAS_TOMADAS,"cod ="+vacinaTomada.getVacinaTomadaCod(),null);
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
            db.delete(TABLE_CRIANÇAS,"cod="+codigo,null);
            deleteAllNotifByCrianca(codigo);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }

    }

    public boolean deleteVacinaTomada(int codigo) {

        openDB();
        try {
            db.delete(TABLE_VACINAS_TOMADAS,"cod="+codigo,null);
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
