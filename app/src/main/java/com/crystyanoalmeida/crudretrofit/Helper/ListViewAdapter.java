package com.crystyanoalmeida.crudretrofit.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.api.view.BootstrapTextView;
import com.crystyanoalmeida.crudretrofit.R;

import java.util.ArrayList;

/**
 * Created by cryst on 19/01/2018.
 */

public class ListViewAdapter extends BaseAdapter{
    private final Context context;
    private final ArrayList<String> id;
    private final ArrayList<String> name;
    private final ArrayList<String> age;
    private final ArrayList<String> mobile;
    private final ArrayList<String> email;
    LayoutInflater layoutInflater;

    public ListViewAdapter(Context context, ArrayList<String> id, ArrayList<String> name, ArrayList<String> age, ArrayList<String> mobile, ArrayList<String> email) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Holder holder = new Holder();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.list_item, null);
        holder.txt_name = (TextView) view.findViewById(R.id.name);
        //holder.txt_age = (TextView) view.findViewById(R.id.age);
        //holder.txt_mobile = (TextView) view.findViewById(R.id.mobile);
        //holder.txt_mail =  (TextView) view.findViewById(R.id.mail);

        holder.txt_name.setText(name.get(position));
        //holder.txt_age.setText(age.get(position));
        //holder.txt_mobile.setText(mobile.get(position));
        //holder.txt_mail.setText(email.get(position));

        return view;
    }
    static class Holder {
        TextView txt_name,txt_age,txt_mobile,txt_mail;
        BootstrapTextView txtname;
    }
}

