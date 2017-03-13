package com.example.vishnubk.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vishnubk on 1/3/17.
 */

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Contacts>contact;
    private LayoutInflater inflater;
    Context context;
    public ContactsAdapter(Context context, List<Contacts> contact) {
        this.contact=contact;
        this.context=context;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_contact_content, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder= (MyHolder) holder;
        myHolder.textName.setText(contact.get(position).name);
        myHolder.textAddress.setText(contact.get(position).address);
        myHolder.textGender.setText(contact.get(position).gender);
        myHolder.textEmail.setText(contact.get(position).email);
    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        final TextView textName;
        final TextView textAddress;
        final TextView textGender;
        final TextView textEmail;
        public MyHolder(View itemView) {
            super(itemView);

            textName=(TextView)itemView.findViewById(R.id.textName);
            textAddress=(TextView)itemView.findViewById(R.id.textAddress);
            textGender=(TextView)itemView.findViewById(R.id.textGender);
            textEmail=(TextView)itemView.findViewById(R.id.textEmail);
        }
    }

}
