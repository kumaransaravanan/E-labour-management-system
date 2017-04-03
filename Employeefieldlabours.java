package com.example.labour;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Employeefieldlabours extends Activity {
	
	ImageButton ib_close,ib_back;
	TextView tv_title;
	ListView lv_items;
	
	
	ArrayAdapter<String> adapter;

	Intent in;
	
	Handler handle=new Handler();
	ProgressDialog pbar;
	boolean internetconnection=false;
	int rcount=0,cnt=0,rflg,ncat=0;
	
	
	String[][] labours=new String[50][7];
	
	String fields,fflg,cname;
	
	
	String name,phno,exp,city,field;
	
	Typeface font;
	Dialog listDialog;
	
	SQLiteDatabase db;
	//String dbn="wallet",labname,labphno,labexp,labcity,labfield;
	
	
	
	
	public static String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
	private static final String NAMESPACE = "http://tempuri.org/";
	
	 private static final String SOAP_ACTION = "http://tempuri.org/viewemplabours";
	 private static final String METHOD_NAME = "viewemplabours";
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employeefieldlabours);
		
		ib_close=(ImageButton) findViewById(R.id.close);
		ib_back=(ImageButton) findViewById(R.id.back);
		tv_title=(TextView) findViewById(R.id.title);
		
		lv_items=(ListView) findViewById(R.id.list);
	 	

		ib_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				System.exit(0);
				
				
				
			}
		});
		
		ib_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			

				Intent i=new Intent(getApplicationContext(),Employeeprof.class);
				startActivity(i);
				
				
				
				
				
			}
		});
		
		
		
		
		
		adapter = new ArrayAdapter<String>(this,R.layout.textview);
		lv_items.setAdapter(adapter);
		
		final Bundle getdata = getIntent().getExtras();
		if (getdata != null) {
			fields = getdata.getString("field");
			cname=getdata.getString("name");
			fflg = getdata.getString("flg");
		
			
			webmethod();
			tv_title.setText(fields);
		}
		
		
try {
	
	db=openOrCreateDatabase("Wallet", Context.MODE_PRIVATE, null);
	db.execSQL("CREATE TABLE IF NOT EXISTS clientwallet(Name VARCHAR,Phno VARCHAR,Experience VARCHAR,City VARCHAR,Field VARCHAR);");
	
	
//	db=openOrCreateDatabase(dbn, SQLiteDatabase.CREATE_IF_NECESSARY, null);
//    db.setVersion(1);
//    db.setLocale(Locale.getDefault());
//    db.setLockingEnabled(false);
    
//    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//	s_tno= preferences.getString("tno","0");
			
	
	
	
	
    lv_items.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int arg2,
				long id) {
			// TODO Auto-generated method stub
			
			LayoutInflater li = LayoutInflater.from(Employeefieldlabours.this);
			View promptsView = li.inflate(R.layout.addtowallet, null);

			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Employeefieldlabours.this);

			// set prompts.xml to alertdialog builder
			alertDialogBuilder.setView(promptsView);

			final TextView tit = (TextView) promptsView.findViewById(R.id.textView1);
		
			
			tit.setTypeface(font);
			
			
			tit.setText("Labour"+"   -   "+labours[arg2][1]);
		

			// set dialog message
			alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Add to Wallet", new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    	String labname=labours[arg2][1];
				    	if(!labname.equals("")) {
				    		
				    		String name=labours[arg2][1];
				    		String phno=labours[arg2][2];
				    		String exp=labours[arg2][3];
				    		String city=labours[arg2][4];
				    		String field=labours[arg2][5];
				    		
				    		
				    	     Log.v("stringname",labours[arg2][1]);
				    	     Log.v("stringname",labours[arg2][2]);
				    	     Log.v("stringname",labours[arg2][3]);

							
				    		db.execSQL("INSERT INTO clientwallet VALUES('"+name+"','"+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				    				phno+"','"+exp+"','"+city+"','"+field+"');");
							
							display("labour added");
				    		
				    		
				    	} else {
				    		display("Invalid Order");
				    	}
				    	
				    	
				    	
				    }
				  })
				.setNegativeButton("Leave It",new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog,int id) {
				    	
				    	
					dialog.cancel();
				    }
				  });

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
			
		}
	});
    
   		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

public void webmethod() {
    	
    	if(checkInternetConnection()) {
			pbar = ProgressDialog.show(Employeefieldlabours.this,"","Fetching Information");
			pbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			
			
			
			new Thread() {
				public void run() {
					
					SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true;
					Log.v("field",fields);
					Log.v("flg",fflg);
					request.addProperty("field", fields);
					request.addProperty("flg", fflg);
					envelope.setOutputSoapObject(request);
					HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
					try {
					try {
						androidHttpTransport.call(SOAP_ACTION, envelope);
					} catch (XmlPullParserException e) {
						display("Error While calling Web Method");
						e.printStackTrace();
					}
					SoapObject result = (SoapObject) envelope.getResponse();
					pbar.dismiss();
				
					    
					Log.v("flg","hello");
					
					SoapObject res = (SoapObject) result.getProperty(0);
					rcount = (res.getPropertyCount());
					Log.v("field",res.getProperty(1).toString());
					Log.v("field",res.getProperty(1).toString());
					Log.v("field",res.getProperty(1).toString());
					Log.v("field",res.getProperty(1).toString());
					
					
					
					ncat=0;
					if (rcount>=1) {
						
							for (int i = 0; i < rcount; i++) {
							labours[ncat][1]=res.getProperty(i).toString();
							i++;
							labours[ncat][3]=res.getProperty(i).toString();
							i++;
							labours[ncat][4]=res.getProperty(i).toString();
							i++;
							labours[ncat][5]=res.getProperty(i).toString();
							i++;
							labours[ncat][6]=res.getProperty(i).toString();
							ncat++;
							
						}
						} else {
						runOnUiThread(new Runnable() {
					        @Override
						        public void run() {
					        	
						        	display("No Employees Available");
						        }
						    });
						}
						
					
					} catch (Exception e) {
						runOnUiThread(new Runnable() {
					        @Override
						        public void run() {
					        	display("Error in Retriving Information");
						        }
						    });
					e.printStackTrace();	                     
					}
					handle.post(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							for (int i = 0; i < ncat; i++) {
								
								adapter.add("\nNAME:\t"+labours[i][1]+"\n\nEXPR:\t"+labours[i][3]+"\n\nPHNO:\t"+labours[i][4]+"\n\nCITY:\t"+labours[i][5]+"\n\nFIELD:\t"+labours[i][6]+"\n");
							}	
									
							
									
						}
					});
					}
					
					
				}.start();
			} else {
				display("No Internet Connection");
			}
	    }
    private boolean checkInternetConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		// test for connection
		if (cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) {
			
			return true;
		} else {
			Toast.makeText(getApplicationContext(), "No Network Connection ", Toast.LENGTH_LONG).show();
			return false;
		}
	}
    
    
    
	
	public void display(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
		
		
	
		
	

	
}
