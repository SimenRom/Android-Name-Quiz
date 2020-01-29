package com.example.thenamequiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;
//https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
public class MinAdapter extends BaseAdapter {
    //Variables
    Context context;
    int resource;
    List<Profil> profiler;


    public MinAdapter(Context context, int resource, List<Profil> profiler){
    this.context = context;
    this.profiler = profiler;
    this.resource = resource;
    }

    @Override
    public int getCount() {
        return profiler.size();
    }

    @Override
    public Object getItem(int position) {
        return profiler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view = LayoutInflater.from(context).inflate(R.layout.listeentity, null);

        //getItem(position).getNavn()...... //her er avslutta. Video under "se senere"
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listeentity, null); ; //View.inflate(context, resource, null);
        }
        Profil profil = (Profil) getItem(position);
        TextView navnTekst = convertView.findViewById(R.id.navnEntity);
        ImageView profilBilde = convertView.findViewById(R.id.bildeEntity);


        navnTekst.setText(profil.getNavn());
        profilBilde.setImageBitmap(profil.getBilde());
        navnTekst.setTag(R.id.tagHjelp, navnTekst);
        return convertView;
    }
}
