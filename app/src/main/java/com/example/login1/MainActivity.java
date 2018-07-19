package com.example.login1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent intent=new Intent(MainActivity.this, Welcome.class);
		startActivity(intent);
		
		
	/*	final TextView t=(TextView) findViewById(R.id.editText1);
		Button b=(Button) findViewById(R.id.buttonlog1);

		final SQLiteDatabase db=openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
		//db.execSQL("DROP TABLE Pass");
		db.execSQL("CREATE TABLE IF NOT EXISTS Pass(Password VARCHAR);");
		db.execSQL("INSERT INTO Pass VALUES('Password');");
		final Cursor c=db.rawQuery("SELECT * FROM Pass", null);
		c.moveToFirst();
		//Log.d("Ankita",c.getString(c.getColumnIndex("Password")));

		b.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {

				if((t.getText().toString()).equals(c.getString(c.getColumnIndex("Password")).toString()))
				{
					//db.close();

				}
				else
				{
					//db.close();
					Toast.makeText(getApplicationContext(), "Invalid Password!", Toast.LENGTH_LONG).show();
				}
				//Toast.makeText(getApplicationContext(), t.getText().toString(), Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(), c.getString(c.getColumnIndex("Password")), Toast.LENGTH_LONG).show();

			}
		});
		db.close();*/
	}
}
