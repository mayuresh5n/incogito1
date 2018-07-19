package com.example.login1;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

class MyAdapter extends ArrayAdapter<Task>
{
    Context context;
    List<Task> taskList=new ArrayList<Task>();
    int layoutResourceId;
    public MyAdapter(Context context, int layoutResourceId,
            List<Task> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.taskList=objects;
        this.context=context;
    }
   
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_inner_view, parent, false);
        CheckBox chk=(CheckBox)rowView.findViewById(R.id.checkBox1);
        Task current=taskList.get(position);
        chk.setText(current.getTaskName());
        chk.setChecked(current.getStatus()==1?true:false);
 
        return rowView;
    }
}