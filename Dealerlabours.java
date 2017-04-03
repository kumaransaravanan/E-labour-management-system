package com.example.labour;




import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Dealerlabours extends Activity implements OnItemClickListener,OnClickListener {
	
	
	ImageView m,p,c,h,e,pl,pes,ag;
	Button wallet;
	ImageButton back,close;
	 
	 Dialog listDialog;
		ArrayAdapter<String> adpt_list;
		String name;
		int cnt=0;
		String[] pdt=new String[30];
		int pcnt=0;
		
		SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dealerlabours);
		
		m=(ImageView)findViewById(R.id.homebuild);
		p=(ImageView)findViewById(R.id.homepaint);
		c=(ImageView)findViewById(R.id.homecarpenter);
		h=(ImageView)findViewById(R.id.homeresiclean);
		e=(ImageView)findViewById(R.id.homeelectric);
		pl=(ImageView)findViewById(R.id.homeplumb);
		pes=(ImageView)findViewById(R.id.pestclean);
		ag=(ImageView)findViewById(R.id.agriculture);
		
		wallet=(Button)findViewById(R.id.wallet);
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
			
				
				Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
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
			
				
				Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
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
	
		
		Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
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
	
		
		Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
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
	
		
		Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
		i1.putExtra("field","Electrician");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
	 }
   });
		
   pl.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		
		Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
		i1.putExtra("field","Plumber");
		i1.putExtra("name",name);
		i1.putExtra("flg","0");
		startActivity(i1);
		
		
		
	 }
  });	
   pes.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			
			Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
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
		
			
			Intent i1=new Intent(Dealerlabours.this,Fieldlabours.class);
			i1.putExtra("field","Agriculture");
			i1.putExtra("name",name);
			i1.putExtra("flg","0");
			startActivity(i1);
			
			
			
		 }
	  });
		
	wallet.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			//adpt_list.clear();
		
			
			
	try {
			Cursor c=db.rawQuery("SELECT * FROM clientwallet",null);
			c.moveToFirst();
			cnt=c.getCount();
			int i=0;
		
			int quty=0;
			double cost=0;
			pcnt=0;
			adpt_list.add("Name\t\t\t\t\tPhno\t\t\t\tExpr\t\t\t\tCity\t\t\t\tField");
			while (i<cnt) {
				
				  Log.v("hello",c.getString(1));
				  Log.v("hello",c.getString(2));
				
				
				String ss=c.getString(1)+"\t\t\t\t\t"+c.getString(2)+"\t\t\t\t"+c.getString(3)+"\t\t\t\t"+c.getString(4)+"\t\t\t\t"+c.getString(5);
				
				adpt_list.add(ss);
				c.moveToNext();
				i++;
			}
			
		
		if(cnt==0) {
			adpt_list.clear();
			display("No Order Placed");
		}
		
		
		 if(!c.isClosed())
			 c.close();
		 } catch (Exception e) {
			
			e.printStackTrace();
		
		 }
		
	showdialog();
		}
	});	
	
	
	}
	
		private void showdialog() {
			// TODO Auto-generated method stub
			listDialog = new Dialog(this);
	        listDialog.setTitle("Selected Labours");
	         LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         View v = li.inflate(R.layout.activity_viewselectlabours, null, false);
	         listDialog.setContentView(v);
	         listDialog.setCancelable(true);
	         //there are a lot of settings, for dialog, check them all out!
	 
	         ListView list1 = (ListView) listDialog.findViewById(R.id.listView1);
	         Button bt_ok = (Button) listDialog.findViewById(R.id.confirm);
	         
	         
	         list1.setOnItemClickListener(this);
	         list1.setAdapter(adpt_list);
	         
	         bt_ok.setOnClickListener(Dealerlabours.this); 
	        
	         //now that the dialog is set up, it's time to show it
	         listDialog.show();
			
			
		}
 


	public void display(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	// TODO Auto-generated method stub
	
}

	
}
