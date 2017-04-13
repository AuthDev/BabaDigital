package com.evar.babadigital.adptlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evar.babadigital.R;

import java.util.ArrayList;

import bbgetset.Criança;
import bbgetset.Sexo;

/**
 * Created by Maxson Jordan on 23/01/2017.
 */

public class AdapterCriancasList extends BaseAdapter {
    private LayoutInflater myInflater;
    private ArrayList<Criança> itens;

    public AdapterCriancasList(Context context, ArrayList<Criança> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        myInflater = LayoutInflater.from(context);
    }
    public int getCount()
    {
        return itens.size();
    }
    public Criança getItem(int position)
    {
        return itens.get(position);
    }
    public long getItemId(int position)
    {
        return position;
    }
    public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        Criança item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = myInflater.inflate(R.layout.itemopcriancas, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.nomeItem)).setText("Nome: "+item.getNome());
        ((TextView) view.findViewById(R.id.idadeItem)).setText("Idade: "+item.getIdade());
        ((TextView) view.findViewById(R.id.notifcacoesItem)).setText(String.valueOf(item.getNotificações().length));
        if(item.getSexo().toString().contains(Sexo.MASCULINO))
        {
            ((ImageView) view.findViewById(R.id.imagemOP)).setImageResource(R.drawable.boycolor);
        }else
            {
                ((ImageView) view.findViewById(R.id.imagemOP)).setImageResource(R.drawable.girlcolor);
            }


        return view;
    }
}
