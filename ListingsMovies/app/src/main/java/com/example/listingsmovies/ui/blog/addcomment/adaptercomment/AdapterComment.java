package com.example.listingsmovies.ui.blog.addcomment.adaptercomment;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class AdapterComment extends ArrayAdapter<String> {

    private Context context;
    private List<String> itemString;

    public AdapterComment(@NonNull Context context, int resource, Context context1, List<String> itemString) {
        super(context, resource);
        this.context = context1;
        this.itemString = itemString;
    }


}



