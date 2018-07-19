package com.example.login1;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
 
public class MainActivityTodo extends Activity {
    protected TaskerDbHelper db;
    List<Task> list;
    MyAdapter adapt;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_todo);
        
        db = new TaskerDbHelper(this);
        list = db.getAllTasks();
        adapt = new MyAdapter(this, R.layout.list_inner_view, list);
        ListView listTask = (ListView) findViewById(R.id.listView1);
        listTask.setAdapter(adapt);
    }
 
    public void addTaskNow(View v) {
        EditText t = (EditText) findViewById(R.id.editText1);
        String s = t.getText().toString();
        if (s.equalsIgnoreCase("")) {
            Toast.makeText(this, "Enter the task description!",
                    Toast.LENGTH_LONG).show();
        } else {
            Task task = new Task(s, 0);
            db.addTask(task);
          //  Log.d("tasker", "data added");
            t.setText("");
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
 
    private class MyAdapter extends ArrayAdapter<Task> {
 
        Context context;
        List<Task> taskList = new ArrayList<Task>();
        int layoutResourceId;
 
        public MyAdapter(Context context, int layoutResourceId,
                List<Task> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.taskList = objects;
            this.context = context;
        }
 
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox chk = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_inner_view,
                        parent, false);
                chk = (CheckBox) convertView.findViewById(R.id.checkBox1);
                convertView.setTag(chk);
                chk.setOnLongClickListener(new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						CheckBox cb = (CheckBox) v;
						final Task delTask = (Task) cb.getTag();
						AlertDialog.Builder builder=new AlertDialog.Builder(MainActivityTodo.this);
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
                chk.setOnClickListener(new View.OnClickListener() {
 
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Task changeTask = (Task) cb.getTag();
                        changeTask.setStatus(cb.isChecked() == true ? 1 : 0);
                        db.updateTask(changeTask);
                        Bundle savedInstanceState = null;
						onCreate(savedInstanceState);
                      //  Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() + " is "+ cb.isChecked(), Toast.LENGTH_LONG).show();
                    }
 
                });
            } else {
                chk = (CheckBox) convertView.getTag();
            }
            Task current = taskList.get(position);
            chk.setText(current.getTaskName());
            chk.setChecked(current.getStatus() == 1 ? true : false);
            chk.setTag(current);
      //      Log.d("listener", String.valueOf(current.getId()));
            return convertView;
        }
 
    }
 
}