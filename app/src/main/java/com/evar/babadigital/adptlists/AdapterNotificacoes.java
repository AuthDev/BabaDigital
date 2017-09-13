package com.evar.babadigital.adptlists;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.evar.babadigital.CRUD.DBRead;
import com.evar.babadigital.R;
import com.evar.babadigital.VacinaTomadaActivity;

import java.util.ArrayList;

import bbgetset.Criança;
import bbgetset.Notificacao;
import bbgetset.Vacina;

/**
 * Created by Maxson Jordan on 23/01/2017.
 */

public class AdapterNotificacoes extends RecyclerView.Adapter<AdapterNotificacoes.ViewHolder> {

    private ArrayList<Notificacao> notificacoes;
    private DBRead db;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView nomeVacina;
        public TextView nomeCriaca;
        public TextView doseVacina;
        public TextView redeVcina;
        public Button registrarVBTN;

        public Vacina vacina;
        public Criança criança;
        public Notificacao notificacao;
        public int cod;

        public ViewHolder(View itemView) {
            super(itemView);

            nomeVacina = (TextView)itemView.findViewById(R.id.nomeVacinaTV);
            nomeCriaca = (TextView)itemView.findViewById(R.id.nomeCriancaTV);
            doseVacina = (TextView)itemView.findViewById(R.id.doseTV);
            redeVcina = (TextView)itemView.findViewById(R.id.vacinaRedeTV);
            registrarVBTN = (Button) itemView.findViewById(R.id.registrarVBTN);


            registrarVBTN.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), VacinaTomadaActivity.class);
                intent.putExtra("nome",vacina.getNome());
                intent.putExtra("doses",vacina.getDoses());
                intent.putExtra("idade",vacina.getIdadeString());
                intent.putExtra("cod",vacina.getCod());
                intent.putExtra("dp",true);
                intent.putExtra("dose",notificacao.getDose());
                intent.putExtra("criançaCod",criança.getCod());
                v.getContext().startActivity(intent);

        }
    }

    public AdapterNotificacoes(ArrayList<Notificacao> notificacoes)
    {
        this.notificacoes = notificacoes;

    }


    @Override
    public AdapterNotificacoes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacoes_list,parent,false);
        this.context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterNotificacoes.ViewHolder holder, int position) {

        db = new DBRead(this.context);
        Vacina vacina = db.getVacina(notificacoes.get(position).getCodItem());
        holder.notificacao = notificacoes.get(position);
        holder.cod = notificacoes.get(position).getCod();
        holder.criança = notificacoes.get(position).getCriança();
        holder.vacina = vacina;

        holder.nomeCriaca.setText("Criança: "+ notificacoes.get(position).getCriança().getNome());
        holder.nomeVacina.setText(vacina.getNome());
        holder.doseVacina.setText(notificacoes.get(position).getDose()+"° dose");
        holder.redeVcina.setText("Rede: "+vacina.getRede());

    }

    @Override
    public int getItemCount() {
       return notificacoes.size();
    }
}
