package com.example.labour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Adminprof extends Activity {
	
	Button dlab,elab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adminprof);
		
		dlab=(Button)findViewById(R.id.deallab);
		elab=(Button)findViewById(R.id.Emplab);
		
		
		dlab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				Intent i1=new Intent(getApplicationContext(),Addealerview.class);
				startActivity(i1);
				
				
				
				
				
				
			}
		});
		
		elab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i1=new Intent(getApplicationContext(),Ademployeview.class);
				startActivity(i1);
				
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


}
