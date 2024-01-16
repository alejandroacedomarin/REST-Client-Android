package com.example.tracksapp.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tracksapp.Models.Track;
import com.example.tracksapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterTracks extends RecyclerView.Adapter<AdapterTracks.ViewHolderDatos> implements View.OnClickListener {
    private ArrayList<Track> listaTracks;
    private Context context;
    private View.OnClickListener listener;

    public AdapterTracks(ArrayList<Track> listaTracks, Context context){
        this.listaTracks=listaTracks;
        this.context = context;
        //listaProductos=new ArrayList<>();
    }
    /*public void setListaProductos(ArrayList<ProductoVo> listaProducts)
    {
        listaProductos = listaProducts;
        notifyDataSetChanged();
    }*/
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_track, parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTracks.ViewHolderDatos holder, int position) {

        holder.etiI.setText(listaTracks.get(position).getId().toString());
        holder.etiT.setText(listaTracks.get(position).getTitle().toString());
        holder.etiS.setText(listaTracks.get(position).getSinger().toString());
    }



    @Override
    public int getItemCount() {
        return listaTracks.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView etiI, etiT, etiS;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            etiI=(TextView) itemView.findViewById(R.id.id_track);

            etiT=(TextView)itemView.findViewById(R.id.title);
            etiS=(TextView)itemView.findViewById(R.id.sing);



        }


    }
}