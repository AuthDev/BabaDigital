package com.evar.babadigital.adptlists;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evar.babadigital.R;

import java.util.ArrayList;

import bbgetset.Noticia;

/**
 * Created by Clinica on 23/01/2017.
 */

public class AdapterNoticiasList extends BaseAdapter {
    private LayoutInflater myInflater;
    private ArrayList<Noticia> itens;

    public AdapterNoticiasList(Context context, ArrayList<Noticia> itens)
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
    public Noticia getItem(int position)
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
        Noticia item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = myInflater.inflate(R.layout.itemnoticiaslist, null);
        //view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.tituloNoticia)).setText(item.getTitulo());
        ((TextView) view.findViewById(R.id.conteudoNoticia)).setText(item.getConteudo());
        ((ImageView) view.findViewById(R.id.imagemNoticia)).setImageURI(Uri.parse(item.getImagem()));


        return view;
    }
}
