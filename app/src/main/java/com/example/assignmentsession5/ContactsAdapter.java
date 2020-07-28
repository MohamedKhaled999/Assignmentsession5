package com.example.assignmentsession5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {
private Context context;
private List<Contact> contacts;
private ContactClickListener contactClickListener;

    public ContactsAdapter(Context context, List<Contact> contacts, ContactClickListener contactClickListener) {
        this.context = context;
        this.contacts = contacts;
        this.contactClickListener = contactClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact contact=contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone;
        CircleImageView circleImageView;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            circleImageView = itemView.findViewById(R.id.profile_image);
            relativeLayout = itemView.findViewById(R.id.relative);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition()!=-1){
                        Contact contact=contacts.get(getAdapterPosition());
                        contactClickListener.contactOnClickListener(contact);

                    }
                }
            });
        }
    }
}
