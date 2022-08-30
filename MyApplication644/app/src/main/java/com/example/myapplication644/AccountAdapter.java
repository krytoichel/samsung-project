package com.example.myapplication644;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends ArrayAdapter<Account> {
    private LayoutInflater inflater;
    private List<Account> accounts = new ArrayList<>();



    public AccountAdapter(@NonNull Context context, int resource, List<Account> accounts, LayoutInflater inflater) {
        super(context, resource, accounts);
        this.inflater = inflater;
        this.accounts = accounts;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        Account account1 = accounts.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item, null, false);
            viewHolder = new ViewHolder();
            viewHolder.dir = convertView.findViewById(R.id.dir);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.price = convertView.findViewById(R.id.price);
            convertView.setTag(viewHolder);



        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dir.setText(account1.getDir());
        viewHolder.name.setText(account1.getName());
        viewHolder.price.setText(account1.getPrice());

        return convertView;
    }
    private class ViewHolder{
        TextView dir;
        TextView name;
        TextView price;
    }
}
