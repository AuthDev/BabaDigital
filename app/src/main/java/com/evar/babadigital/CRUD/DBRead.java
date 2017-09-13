package com.evar.babadigital.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

import bbgetset.Criança;
import bbgetset.Notificacao;
import bbgetset.Sexo;
import bbgetset.Usuário;
import bbgetset.Vacina;
import bbgetset.VacinaTomada;

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
    private static final String TABLE_VACINAS_PERSONALIZADAS = "vacinas_personalizadas";
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

    public boolean hasNotificacoes(Criança criança)
    {
        openDB();
        ArrayList<Object> cArray = new ArrayList<>();
        String getNotificacoes = "SELECT * FROM "+TABLE_NOTICACOES+" WHERE crianca_cod="+criança.getCod();
        Object object;
        try
        {
            Cursor c = db.rawQuery(getNotificacoes,null);
            if(c.moveToFirst())
            {
                do{
                    object = new Object();
                    cArray.add(object);
                }while (c.moveToNext());
                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
       return !cArray.isEmpty();

    }
    public int getNotificacoesQt(Usuário usuário)
    {
        int qt = 0;
        ArrayList<Criança> crianças = getCrianças(usuário);
        for (int i = 0; i < crianças.size(); i++) {
            qt+= getNotificacoes(crianças.get(i)).size();
        }
        return qt;
    }
    public ArrayList<Notificacao> getNotificacoes(Criança criança)
    {
        openDB();
        ArrayList<Notificacao> cArray = new ArrayList<>();
        String getNotificacoes = "SELECT * FROM "+TABLE_NOTICACOES+" WHERE crianca_cod="+criança.getCod();
        Notificacao notificacao;
        try
        {
            Cursor c = db.rawQuery(getNotificacoes,null);
            if(c.moveToFirst())
            {
                do{
                    notificacao = new Notificacao(criança);
                    notificacao.setTitulo(c.getString(1));
                    notificacao.setDescricao(c.getString(2));
                    notificacao.setCodItem(c.getInt(3));
                    notificacao.setCod(c.getInt(0));
                    notificacao.setDose(c.getInt(4));
                    cArray.add(notificacao);
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
    public Criança getCriança(int cod)
    {
        openDB();
        ArrayList<Criança> cArray = new ArrayList<>();
        String getCriaças = "SELECT * FROM "+TABLE_CRIANÇAS+" WHERE cod="+cod;
        Criança crianca = new Criança();
        try
        {
            Cursor c = db.rawQuery(getCriaças,null);
            if(c.moveToFirst())
            {
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

                    Date dataNascimento;
                    java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
                    dataNascimento = df.parse(c.getString(3));
                    crianca.setDataNascimento(dataNascimento);
                    cArray.add(crianca);

                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return crianca;

    }

    public ArrayList<Vacina> getVacinas()
    {
        openDB();
        ArrayList<Vacina> cArray = new ArrayList<>();
        String getVacinas = "SELECT * FROM "+TABLE_VACINAS +" WHERE 1";
        Vacina vacina;
        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                do{
                    vacina = new Vacina();
                    vacina.setCod(c.getInt(0));
                    vacina.setIdade(c.getInt(1));
                    vacina.setNome(c.getString(2));
                    vacina.setDoses(c.getInt(3));
                    vacina.setDescricao(c.getString(4));
                    vacina.setPublica(Boolean.parseBoolean(c.getString(5)));
                    vacina.setParticular(Boolean.parseBoolean(c.getString(6)));
                    vacina.setIntervalo(c.getInt(7));

                    cArray.add(vacina);

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

    public Vacina getVacina(int cod)
    {
        openDB();
        String getVacinas = "SELECT * FROM "+TABLE_VACINAS +" WHERE cod="+cod;
        Vacina vacina = new Vacina();
        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                    vacina = new Vacina();
                    vacina.setCod(c.getInt(0));
                    vacina.setIdade(c.getInt(1));
                    vacina.setNome(c.getString(2));
                    vacina.setDoses(c.getInt(3));
                    vacina.setDescricao(c.getString(4));
                    vacina.setPublica(Boolean.parseBoolean(c.getString(5)));
                    vacina.setParticular(Boolean.parseBoolean(c.getString(6)));
                    vacina.setIntervalo(c.getInt(7));
                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return vacina;

    }


    public ArrayList<VacinaTomada> getVacinasCaderneta(int codCrianca)
    {
        openDB();
        ArrayList<VacinaTomada> cArray = new ArrayList<>();
        String getVacinas = "SELECT * FROM "+TABLE_VACINAS_TOMADAS +" WHERE criança_cod="+codCrianca;
        VacinaTomada vacina;
        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                do{
                    String valor1= "";
                    String valor2="";
                    for (int i = 0; i <c.getColumnCount() ; i++) {
                        valor1+="  "+c.getColumnName(i);
                        valor2+=" "+c.getString(i);
                    }
                }while (c.moveToNext());
            }
            if(c.moveToFirst())
            {
                do{
                    vacina = new VacinaTomada();

                    vacina.setVacinaTomadaCod(c.getInt(0));

                    long dateLong = Long.parseLong(c.getString(1));
                    Date date = new Date(dateLong);
                    vacina.setData(date);

                    vacina.setLocalTomado(c.getString(2));
                    vacina.setDose(c.getInt(3));
                    vacina.setCriancaCod(c.getInt(4));
                    vacina.setCod(c.getInt(5));
                    vacina.setTomada(true);
                    vacina.setPersonalizada(Boolean.parseBoolean(c.getString(7)));
                    if(!c.getString(8).isEmpty()) {
                        date = new Date(Long.valueOf(c.getString(8)));
                        vacina.setValidade(date);
                    }

                    if(vacina.isPersonalizada())
                    {
                        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS_PERSONALIZADAS+" WHERE cod="+vacina.getCod(),null);
                        vacina.setIdade(d.getInt(1));
                        vacina.setNome(d.getString(2));
                        vacina.setDoses(d.getInt(3));
                        vacina.setDescricao(d.getString(4));
                        vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                        vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                        vacina.setIntervalo(d.getInt(7));
                        d.close();


                    }else
                        {
                            Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS+" WHERE cod="+vacina.getCod(),null);
                            if(d.moveToFirst()){
                                vacina.setCod(d.getInt(0));
                                vacina.setIdade(d.getInt(1));
                                vacina.setNome(d.getString(2));
                                vacina.setDoses(d.getInt(3));
                                vacina.setDescricao(d.getString(4));
                                vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                                vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                                vacina.setIntervalo(d.getInt(7));
                                d.close();
                            }
                        }
                        cArray.add(vacina);

                }while (c.moveToNext());
                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        getVacinas = "SELECT * FROM "+TABLE_VACINAS +" WHERE 1";

        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                do{
                    vacina = new VacinaTomada();
                    vacina.setCod(c.getInt(0));
                    vacina.setIdade(c.getInt(1));
                    vacina.setNome(c.getString(2));
                    vacina.setDoses(c.getInt(3));
                    vacina.setDescricao(c.getString(4));
                    vacina.setPublica(Boolean.parseBoolean(c.getString(5)));
                    vacina.setParticular(Boolean.parseBoolean(c.getString(6)));
                    vacina.setIntervalo(c.getInt(7));
                    vacina.setPersonalizada(false);
                    vacina.setTomada(false);

                    cArray.add(vacina);

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

    public ArrayList<VacinaTomada> getVacinasADD()
    {
        openDB();
        ArrayList<VacinaTomada> cArray = new ArrayList<>();
        VacinaTomada vacina;

        String getVacinas = "SELECT * FROM "+TABLE_VACINAS +" WHERE 1";

        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                do{
                    vacina = new VacinaTomada();
                    vacina.setCod(c.getInt(0));
                    vacina.setIdade(c.getInt(1));
                    vacina.setNome(c.getString(2));
                    vacina.setDoses(c.getInt(3));
                    vacina.setDescricao(c.getString(4));
                    vacina.setPublica(Boolean.parseBoolean(c.getString(5)));
                    vacina.setParticular(Boolean.parseBoolean(c.getString(6)));
                    vacina.setIntervalo(c.getInt(7));
                    vacina.setPersonalizada(false);
                    vacina.setTomada(false);

                    cArray.add(vacina);

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

    public ArrayList<VacinaTomada> getVacinasTomadas(int codCrianca)
    {
        openDB();
        ArrayList<VacinaTomada> cArray = new ArrayList<>();
        String getVacinas = "SELECT * FROM "+TABLE_VACINAS_TOMADAS +" WHERE criança_cod="+codCrianca+" ORDER BY idade";
        VacinaTomada vacina;
        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                do{
                    String valor1= "";
                    String valor2="";
                    for (int i = 0; i <c.getColumnCount() ; i++) {
                        valor1+="  "+c.getColumnName(i);
                        valor2+=" "+c.getString(i);
                    }
                }while (c.moveToNext());
            }
            if(c.moveToFirst())
            {
                do{
                    vacina = new VacinaTomada();

                    vacina.setVacinaTomadaCod(c.getInt(0));

                    long dateLong = Long.parseLong(c.getString(1));
                    Date date = new Date(dateLong);
                    vacina.setData(date);

                    vacina.setLocalTomado(c.getString(2));
                    vacina.setDose(c.getInt(3));
                    vacina.setCriancaCod(c.getInt(4));
                    vacina.setCod(c.getInt(5));
                    vacina.setTomada(true);
                    vacina.setPersonalizada(Boolean.parseBoolean(c.getString(7)));
                    if(!c.getString(8).isEmpty()) {
                        date = new Date(Long.valueOf(c.getString(8)));
                        vacina.setValidade(date);
                    }

                    if(vacina.isPersonalizada())
                    {
                        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS_PERSONALIZADAS+" WHERE cod="+vacina.getCod(),null);
                        vacina.setIdade(d.getInt(1));
                        vacina.setNome(d.getString(2));
                        vacina.setDoses(d.getInt(3));
                        vacina.setDescricao(d.getString(4));
                        vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                        vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                        vacina.setIntervalo(d.getInt(7));
                        d.close();


                    }else
                    {
                        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS+" WHERE cod="+vacina.getCod(),null);
                        if(d.moveToFirst()){
                            vacina.setCod(d.getInt(0));
                            vacina.setIdade(d.getInt(1));
                            vacina.setNome(d.getString(2));
                            vacina.setDoses(d.getInt(3));
                            vacina.setDescricao(d.getString(4));
                            vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                            vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                            vacina.setIntervalo(d.getInt(7));
                            d.close();
                        }
                    }
                    cArray.add(vacina);

                }while (c.moveToNext());
                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return cArray;

    }



    public VacinaTomada getVacinaTomada(int codVacinatomada)
    {
        openDB();
        String getVacinas = "SELECT * FROM "+TABLE_VACINAS_TOMADAS +" WHERE cod="+codVacinatomada;
        VacinaTomada vacina = new VacinaTomada();
        try
        {
            Cursor c = db.rawQuery(getVacinas,null);
            if(c.moveToFirst())
            {
                    vacina = new VacinaTomada();
                    vacina.setVacinaTomadaCod(c.getInt(0));
                    long dateLong = Long.parseLong(c.getString(1));
                    Date date = new Date(dateLong);
                    vacina.setData(date);
                    vacina.setLocalTomado(c.getString(2));
                    vacina.setDose(c.getInt(c.getInt(3)));
                    vacina.setCriancaCod(c.getInt(c.getInt(4)));
                    vacina.setCod(c.getInt(c.getInt(5)));
                    vacina.setTomada(true);
                    if(c.getInt(7)==0)
                    {
                        vacina.setPersonalizada(true);
                    }else
                    {
                        vacina.setPersonalizada(false);
                    }
                    if(!c.getString(8).isEmpty()) {
                        dateLong = Long.parseLong(c.getString(8));
                        date = new Date(dateLong);
                        vacina.setValidade(date);
                    }

                    if(vacina.isPersonalizada())
                    {
                        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS_PERSONALIZADAS+" WHERE cod="+vacina.getCod(),null);
                        vacina.setIdade(d.getInt(1));
                        vacina.setNome(d.getString(2));
                        vacina.setDoses(d.getInt(3));
                        vacina.setDescricao(d.getString(4));
                        vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                        vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                        vacina.setIntervalo(d.getInt(7));
                        d.close();


                    }else
                    {
                        Cursor d = db.rawQuery("SELECT * FROM "+TABLE_VACINAS+" WHERE cod="+vacina.getCod(),null);
                        vacina.setIdade(d.getInt(1));
                        vacina.setNome(d.getString(2));
                        vacina.setDoses(d.getInt(3));
                        vacina.setDescricao(d.getString(4));
                        vacina.setPublica(Boolean.parseBoolean(d.getString(5)));
                        vacina.setParticular(Boolean.parseBoolean(d.getString(6)));
                        vacina.setIntervalo(d.getInt(7));
                        d.close();
                    }


                c.close();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return vacina;

    }




    public void openDB()
    {
        if(!db.isOpen())
        {
            db = nContext.openOrCreateDatabase(DB_PATH,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}
