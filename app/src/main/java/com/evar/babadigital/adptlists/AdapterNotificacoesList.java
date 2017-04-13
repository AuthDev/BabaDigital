package com.evar.babadigital.adptlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.evar.babadigital.R;

import java.util.ArrayList;

/**
 * Created by Clinica on 23/01/2017.
 */

public class AdapterNotificacoesList extends BaseAdapter {
    private LayoutInflater myInflater;
    private ArrayList<NotificacoeList> itens;

    public  AdapterNotificacoesList(Context context, ArrayList<NotificacoeList> itens)
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
    public NotificacoeList getItem(int position)
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
        NotificacoeList item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = myInflater.inflate(R.layout.itemnotificacoeslist, null);
        //view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.nomeCriancaNotificacoesListTitle)).setText(item.getNome());
        ((TextView) view.findViewById(R.id.not1ItemNotficacoes)).setText(item.getNot1());
        ((TextView) view.findViewById(R.id.not2ItemNotficacoes)).setText(item.getNot2());
        ((TextView) view.findViewById(R.id.not3ItemNotficacoes)).setText(item.getNot3());
        ((TextView) view.findViewById(R.id.verMaisItemNotficacoes)).setText("Ver mais");


        return view;
    }
}
