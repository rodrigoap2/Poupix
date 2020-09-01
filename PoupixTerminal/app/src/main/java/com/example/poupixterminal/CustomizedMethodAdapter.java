package com.example.poupixterminal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.com.paxbr.easypaymentpos.POSConfig;
import br.com.paxbr.easypaymentpos.domain.Product;

public class CustomizedMethodAdapter extends ArrayAdapter<Product> {
    public List <Product> products;
    public POSConfig config;
    Context context;
    int resource;
    ScreenStatus screenStatus;

    public CustomizedMethodAdapter(@NonNull Context context, int resource, List<Product> products, POSConfig config, ScreenStatus screenStatus) {
        super(context, resource, products);
        this.products = products;
        this.context = context;
        this.resource = resource;
        this.config = config;
        this.screenStatus = screenStatus;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //getting the view
        final View view = layoutInflater.inflate(resource, null, false);
        Button methodButton = view.findViewById(R.id.methodNameButton);
        methodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenStatus.hideMethodsScreen();
                config.response(products.get(position));
            }
        });
        //getting the product and setting button's name as the name of the method
        Product product = products.get(position);
        methodButton.setText(product.getLabelName());
        return view;
    }
}
