package com.example.poupixterminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.com.paxbr.easypaymentpos.POSConfig;

public class CustomizedApplicationAdapter extends ArrayAdapter<String> {
    public List <String> products;
    public POSConfig config;
    Context context;
    int resource;
    ScreenStatus screenStatus;

    public CustomizedApplicationAdapter(@NonNull Context context, int resource, List<String> products, POSConfig config) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
        this.config = config;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //getting the view
        final View view = layoutInflater.inflate(resource, null, false);
        Button applicationButton = view.findViewById(R.id.methodNameButton);
        applicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                config.select(position+1);
            }
        });
        //getting the product and setting button's name as the name of the method
        String application = products.get(position);
        applicationButton.setText(application);
        return view;
    }
}
