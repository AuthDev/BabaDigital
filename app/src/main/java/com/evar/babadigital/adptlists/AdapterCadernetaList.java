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

/**
 * Created by Clinica on 24/01/2017.
 */

public class AdapterCadernetaList extends BaseAdapter {

    private LayoutInflater myInflater;
    private ArrayList<CadernetaList> itens;

    public AdapterCadernetaList(Context context, ArrayList<CadernetaList> itens)
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
    public CadernetaList getItem(int position)
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
        CadernetaList item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = myInflater.inflate(R.layout.itemcaderneta, null);
       // view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.nomeCrianCaderneta)).setText("Nome: "+item.getNome());
        ((TextView) view.findViewById(R.id.IdadeCriancaCaderneta)).setText("Idade: "+item.getIdade());
        ((ImageView) view.findViewById(R.id.imagemOP)).setImageResource(item.getImagem());

        return view;
    }
}
