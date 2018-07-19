package com.example.login1;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FullNote extends Activity {
	//SQLiteDatabase db=openOrCreateDatabase("NoteManager", MODE_PRIVATE, null);
	String default1;
	String sql;
	protected NoteDbHelper db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullnote);
		TextView t=(TextView) findViewById(R.id.textView1);
		default1=getIntent().getExtras().getString("ID");
		
		//Toast.makeText(getApplicationContext(), default1, Toast.LENGTH_LONG).show();
		t.setText(default1);
		//Cursor c=db.rawQuery("SELECT * FROM Notes", null);
		//c.moveToFirst();
		//t.setText(c.getString(c.getColumnIndex("note")).toString());
			//while(c.getString(c.getColumnIndex("noteName"))!=default1)
			//{	
				//c.moveToNext();
			//}
			//t.setText(c.getString(c.getColumnIndex("note")));
		}
}
