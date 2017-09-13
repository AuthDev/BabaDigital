package com.evar.babadigital.adptlists;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evar.babadigital.R;

import java.util.ArrayList;

import bbgetset.Noticia;

/**
 * Created by Maxson Jordan on 23/01/2017.
 */

public class AdapterNoticiasList extends RecyclerView.Adapter<AdapterNoticiasList.ViewHolder> {

    private ArrayList<Noticia> noticias;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView titulo;
        public TextView descricao;
        public TextView autor;
        public ImageView image;
        public String url;
        public Context context;

        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.tituloNoticia);
            descricao = (TextView) itemView.findViewById(R.id.conteudoNoticia);
            autor = (TextView) itemView.findViewById(R.id.fontNoticias);
            image = (ImageView) itemView.findViewById(R.id.imagemNoticia);

            titulo.setOnClickListener(this);
            descricao.setOnClickListener(this);
            autor.setOnClickListener(this);
            image.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Uri uri = Uri.parse(url);
            Intent verNoticias = new Intent(Intent.ACTION_VIEW,uri);
            context.startActivity(verNoticias);


        }
    }

    public AdapterNoticiasList(ArrayList<Noticia> noticias)
    {
        this.noticias = noticias;
    }


    @Override
    public AdapterNoticiasList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticias_list,parent,false);
        this.context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterNoticiasList.ViewHolder holder, int position) {

        try { holder.titulo.setText(noticias.get(position).getTitulo());}catch (Exception e) {holder.titulo.setText("Noticia");}
        try {
            if(noticias.get(position).getConteudo().length() < 2)
            {
                holder.descricao.setText(noticias.get(position).getTitulo());
            }else
                {
                    holder.descricao.setText(noticias.get(position).getConteudo());
                }



        }catch (Exception e) {holder.titulo.setText("Sem descrição");}
        try { holder.autor.setText(noticias.get(position).getAutor());}catch (Exception e) {holder.autor.setText("Autor desconhecido");}
        try { holder.image.setImageBitmap(noticias.get(position).getBitmap());}catch (Exception e) {holder.image.setImageResource(R.drawable.news);}
        try { holder.url = noticias.get(position).getFonte();}catch (Exception e) {}


        holder.context = context;

    }

    @Override
    public int getItemCount() {
        if(noticias != null) {
            return noticias.size();

        }else
            {
                return 0;
            }
    }
}
