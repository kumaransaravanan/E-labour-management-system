package com.example.labour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Clientprof extends Activity {

	ImageView img1,img2;
	String name;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clientprof);
		
		img1=(ImageView)findViewById(R.id.imageView1);
		img2=(ImageView)findViewById(R.id.imageView2);
		
		 final Bundle getdata = getIntent().getExtras();
			if (getdata != null) {
				
				name = getdata.getString("cname");
			
				}
		
		
		
		
	img1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		  Intent i1=new Intent(getApplication(),Dealerlabours.class);
		  i1.putExtra("cname", name);
		  
		  
		  startActivity(i1);
			
			
			
		}
	});	
		
   img2.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		Intent i2=new Intent(getApplicationContext(),Employeeprof.class);
		  i2.putExtra("cname", name);
		startActivity(i2);
	}
});		
		
		
		
		
		
	}


}
