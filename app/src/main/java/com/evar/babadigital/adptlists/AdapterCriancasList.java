package com.evar.babadigital.adptlists;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evar.babadigital.CriancasEditarActivity;
import com.evar.babadigital.R;

import java.util.ArrayList;
import java.util.Date;

import bbgetset.Criança;
import bbgetset.Sexo;

/**
 * Created by Maxson Jordan on 23/01/2017.
 */

public class AdapterCriancasList extends RecyclerView.Adapter<AdapterCriancasList.ViewHolder> {

    private ArrayList<Criança> crianças;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView nome;
        public TextView idade;
        public ImageView image;
        public ImageView editImage;
        public int codCrianca;
        public String dataNascimentoString;
        public String sexo;
        public String nomeString;
        public Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.nomeItem);
            idade = (TextView) itemView.findViewById(R.id.idadeItem);
            image = (ImageView) itemView.findViewById(R.id.imagemOP);
            editImage = (ImageView) itemView.findViewById(R.id.editCrianca);

            editImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Log.e("onClick EDITAR: ","Você clicou em EDitar"+ this.codCrianca);
            Intent editar = new Intent(v.getContext(), CriancasEditarActivity.class);
            editar.putExtra("nome",this.nomeString);
            editar.putExtra("dataNascimento",this.dataNascimentoString);
            editar.putExtra("sexo",this.sexo);
            editar.putExtra("cod",codCrianca);
           context.startActivity(editar);


        }
    }

    public AdapterCriancasList(ArrayList<Criança> crianças)
    {
        this.crianças = crianças;
    }


    @Override
    public AdapterCriancasList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemopcriancas,parent,false);
        this.context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterCriancasList.ViewHolder holder, int position) {

        holder.nome.setText(crianças.get(position).getNome());
        holder.idade.setText(crianças.get(position).getIdadeString());

        if(crianças.get(position).getSexo().contains(Sexo.MASCULINO))
        {
            holder.image.setImageResource(R.drawable.boycolor);
        }else
            {
                holder.image.setImageResource(R.drawable.girlcolor);
            }
        holder.codCrianca = crianças.get(position).getCod();
        holder.nomeString =  crianças.get(position).getNome();
        holder.dataNascimentoString =  crianças.get(position).getDataNascimentoString();
        holder.sexo =  crianças.get(position).getSexo();
        holder.context = context;

    }

    @Override
    public int getItemCount() {
       return crianças.size();
    }
}
