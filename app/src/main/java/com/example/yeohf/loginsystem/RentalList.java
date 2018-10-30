package com.example.yeohf.loginsystem;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RentalList extends ArrayAdapter<Rental> {

    private Activity context;
    private List<Rental> rentalList;

    public RentalList(Activity context, List<Rental> rentalList){
        super(context,R.layout.rental_layout, rentalList);
        this.context=context;
        this.rentalList= rentalList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.rental_layout,null,true);

        TextView rentaltitle= (TextView)listViewItem.findViewById(R.id.tvTitle);
        TextView rentaldesc= (TextView)listViewItem.findViewById(R.id.tvDesc);
        TextView rentalprice= (TextView)listViewItem.findViewById(R.id.tvPrice);
        TextView rentaladd= (TextView)listViewItem.findViewById(R.id.tvAdd);

        Rental rental= rentalList.get(position);

        rentaltitle.setText(rental.getTitle());
        rentaldesc.setText(rental.getDesc());
        rentalprice.setText(rental.getPrice());
        rentaladd.setText(rental.getAddress());


        return listViewItem;
    }
}
