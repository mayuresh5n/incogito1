package com.example.login1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityNote extends Activity {
	protected NoteDbHelper db;
    List<NoteTask> list;
    MyAdapter1 adapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_note);
		
		  db = new NoteDbHelper(this);
	        list = db.getAllTasks();
	        adapt = new MyAdapter1(this, R.layout.note_inner_view, list);
	        ListView listTask = (ListView) findViewById(R.id.listView1);
	        listTask.setAdapter(adapt);
	}
	
	public void addTaskNow(View v) {
        EditText t = (EditText) findViewById(R.id.editText1);
        EditText t1=(EditText) findViewById(R.id.editText2);
        String s = t.getText().toString();
        String s1 = t1.getText().toString();
        if (s.equalsIgnoreCase("") || s1.equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the given fields!",
                    Toast.LENGTH_LONG).show();
        } else {
        	NoteTask task = new NoteTask(s, s1);
            db.addTask(task);
          //  Log.d("tasker", "data added");
            t.setText("");
            t1.setText("");
            adapt.add(task);
            adapt.notifyDataSetChanged();
        }
 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class MyAdapter1 extends ArrayAdapter<NoteTask> {
		 
	    Context context;
	    List<NoteTask> taskList = new ArrayList<NoteTask>();
	    int layoutResourceId;

	    public MyAdapter1(Context context, int layoutResourceId,
	            List<NoteTask> objects) {
	        super(context, layoutResourceId, objects);
	        this.layoutResourceId = layoutResourceId;
	        this.taskList = objects;
	        this.context = context;
	    }
	    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.note_inner_view,
                        parent, false);
                t = (TextView) convertView.findViewById(R.id.textView1);
                convertView.setTag(t);
                t.setTextColor(Color.WHITE);
                t.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						TextView tv = (TextView) v;
						final NoteTask delTask = (NoteTask) tv.getTag();
						AlertDialog.Builder builder=new AlertDialog.Builder(MainActivityNote.this);
						builder.setMessage("Do you want to delete this entry?");
			        	builder.setCancelable(false);
			        	builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			        		@Override
							public void onClick(DialogInterface arg0, int arg1) {
			        			db.deleteTask(delTask);
			        			Bundle savedInstanceState = null;
								onCreate(savedInstanceState);
			        		}
			        		});
			        	builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.cancel();
							}
						});
			        	AlertDialog alert=builder.create();
			        	alert.show();
					//	db.deleteTask(delTask);
					//	Toast.makeText(getApplicationContext(), "Long click!", Toast.LENGTH_LONG).show();
			        	 
			        	return false;
					}
				});                
                t.setOnClickListener(new View.OnClickListener() {
 
                    @Override
                    public void onClick(View v) {
                    	TextView tv = (TextView) v;
                    	NoteTask changeTask = (NoteTask) tv.getTag();
                    	String res=db.onSelect(changeTask);
                    	Bundle savedInstanceState = null;
						onCreate(savedInstanceState);
                    	Intent intent=new Intent(MainActivityNote.this,FullNote.class);
                    	//Bundle extras=new Bundle();
                    	//extras.put
                    	//extras.putInt("Name", changeTask.getId());
                    	//Toast.makeText(getApplicationContext(), changeTask.getId(),Toast.LENGTH_LONG).show();
                    	intent.putExtra("ID", res);
                    	startActivity(intent);
                    	
						//NoteTask changeTask = (NoteTask) tv.getTag();
                     //   changeTask.t(tv.isChecked() == true ? 1 : 0);
                      //  db.updateTask(changeTask);
                       // Bundle savedInstanceState = null;
						//onCreate(savedInstanceState);
                      //  Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() + " is "+ cb.isChecked(), Toast.LENGTH_LONG).show();
                    }
 
                });
            } else {
                t = (TextView) convertView.getTag();
            }
            NoteTask current = taskList.get(position);
            t.setText(current.getTaskName());
        //    t.setChecked(current.getStatus() == 1 ? true : false);
            t.setTag(current);
      //      Log.d("listener", String.valueOf(current.getId()));
            return convertView;
        }
 
    }
}


	
