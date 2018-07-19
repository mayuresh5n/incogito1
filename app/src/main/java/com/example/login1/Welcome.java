package com.example.login1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		ImageButton b1=(ImageButton) findViewById(R.id.imageButton1);
		ImageButton b2=(ImageButton) findViewById(R.id.imageButton2);
		//ImageButton b3=(ImageButton) findViewById(R.id.imageButton3);
		//ImageButton b4=(ImageButton) findViewById(R.id.imageButton4);
		//ImageButton b5=(ImageButton) findViewById(R.id.imageButton5);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Welcome.this, MainActivityNote.class);
				startActivity(intent);
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Welcome.this, MainActivityTodo.class);
				startActivity(intent);
			}
		});


		
	}
}
