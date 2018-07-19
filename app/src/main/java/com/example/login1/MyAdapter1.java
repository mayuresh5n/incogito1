package com.example.login1;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class MyAdapter1 extends ArrayAdapter<NoteTask>
{
    Context context;
    List<NoteTask> taskList=new ArrayList<NoteTask>();
    int layoutResourceId;
    public MyAdapter1(Context context, int layoutResourceId,
            List<NoteTask> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.taskList=objects;
        this.context=context;
    }
   
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.note_inner_view, parent, false);
        TextView t=(TextView)rowView.findViewById(R.id.textView1);
        NoteTask current=taskList.get(position);
        t.setText(current.getTaskName());
        t.setTextColor(Color.WHITE);
       // t.setChecked(current.getStatus()==1?true:false);
 
        return rowView;
    }
}