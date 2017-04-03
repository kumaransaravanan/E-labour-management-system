package com.example.labour;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Employeeprof extends Activity {
	
	
	ImageView m,p,c,h,e,pl,pes,ag;
	
	ImageButton back,close;
	
	Button wallet;
	String name;
	 
	 Dialog listDialog;
		ArrayAdapter<String> adpt_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employeeprof);
		
		m=(ImageView)findViewById(R.id.builds);
		p=(ImageView)findViewById(R.id.pains);
		c=(ImageView)findViewById(R.id.carps);
		h=(ImageView)findViewById(R.id.cleans);
		e=(ImageView)findViewById(R.id.elecs);
		pl=(ImageView)findViewById(R.id.plumbers);
		pes=(ImageView)findViewById(R.id.pess);
		ag=(ImageView)findViewById(R.id.agrics);
		wallet=(Button)findViewById(R.id.wallets);
		back=(ImageButton)findViewById(R.id.back);
		close=(ImageButton)findViewById(R.id.close);
		
		
		 final Bundle getdata = getIntent().getExtras();
			if (getdata != null) {
				
				name = getdata.getString("cname");
			
				}
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent b=new Intent(getApplicationContext(),Clientprof.class);
				startActivity(b);
				
				
			}
		});
		
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();
				System.exit(0);
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		m.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
				i1.putExtra("field","Mason");
				i1.putExtra("name",name);
				i1.putExtra("flg","0");
				startActivity(i1);
				finish();
			
				
				
				
			}
		});
		
    p.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
				i1.putExtra("field","Painter");
				i1.putExtra("name",name);
				i1.putExtra("flg","0");
				startActivity(i1);
				
				
				
			}
		});
    c.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
		i1.putExtra("field","Carpenter");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
	}
});

   h.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
		i1.putExtra("field","Housekeeping");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
	}
   });
    e.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
		i1.putExtra("field","Electrician");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
	 }
   });
		
//   pl.setOnClickListener(new OnClickListener() {
//	
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//	
//		
//		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
//		i1.putExtra("field","Plumber");
//		i1.putExtra("name",name);
//		i1.putExtra("flg","0");
//		startActivity(i1);
//		
//		
//		
//	 }
//  });	
   
   pes.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
		i1.putExtra("field","PestCleaner");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
		
		
		
		
	}
});
   
 ag.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		Intent i1=new Intent(getApplicationContext(),Employeefieldlabours.class);
		i1.putExtra("field","Agriculture");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");;
		startActivity(i1);
		
		
		
		
		
	}
});  
   
   
		
		
		
		
	}

	
}
