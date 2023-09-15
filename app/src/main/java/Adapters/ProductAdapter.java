package Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.supercooker.R;

import java.util.ArrayList;

import UISteak.Ingredients;

public class ProductAdapter extends ArrayAdapter<RecipeHeader> {
    private LayoutInflater inflater;
    private int layout;
    private ArrayList<RecipeHeader> recipeHeaders;
    private ArrayList<Intent> intents;

    public ProductAdapter(Context context, int resource, ArrayList<RecipeHeader> recipeHeaders, ArrayList<Intent> intents) {
        super(context, resource, recipeHeaders);
        this.recipeHeaders = recipeHeaders;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.intents = intents;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final RecipeHeader recipeHeader = recipeHeaders.get(position);

        viewHolder.nameView.setText(recipeHeader.getName());

        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(intents.get(position));
                ((Activity) v.getContext()).overridePendingTransition(R.anim.right_in,android.R.anim.slide_out_right);
            }
        });

        return convertView;
    }
    private class ViewHolder {
        final Button addButton;
        final TextView nameView;
        ViewHolder(View view){
            addButton = view.findViewById(R.id.Button);
            nameView =  view.findViewById(R.id.Header);
        }
    }
}
