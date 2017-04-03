package com.example.labour;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Addealerview extends Activity {
	Spinner spin1;
	Button v;
	ArrayList<String> arr;
	Handler handle=new Handler();
	
	ArrayAdapter<String> adapter,adapter2;
	boolean internetconnection=false;
	int rcount=0,cnt=0,rflg,ncat=0;
	ProgressDialog pbar;
	
	ListView lv;
	
	String field;
	
String[][] labours=new String[50][7];
	
	String fields,fflg,cname;
	
	public static String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
	private static final String NAMESPACE = "http://tempuri.org/";
	
	 private static final String SOAP_ACTION = "http://tempuri.org/delabview";
	 private static final String METHOD_NAME = "delabview";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addealerview);
		
		spin1=(Spinner)findViewById(R.id.categ);
		v=(Button)findViewById(R.id.view);
		lv=(ListView)findViewById(R.id.labviews);
		
		
		

		  arr=new ArrayList<String>();
			arr.add("Choose Area..");
			arr.add("Mason");
			arr.add("Housekeeping");
			arr.add("Plumber");
			arr.add("Electrician");
			arr.add("Painter");
			arr.add("Carpenter");
			arr.add("PestCleaner");
			arr.add("Agriculture");
			
		    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,arr);
			spin1.setAdapter(adapter);
			
			
			
			adapter2 = new ArrayAdapter<String>(this,R.layout.activity_addealerview);
			lv.setAdapter(adapter2);
		
		
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				field=spin1.getSelectedItem().toString();
				
				
				if(checkInternetConnection()) {
					pbar = ProgressDialog.show(Addealerview.this,"","Fetching Information");
					pbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
					
					
					
					new Thread() {
						public void run() {
							
							SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
							SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
							envelope.dotNet = true;
							Log.v("field",field);
							Log.v("flg",fflg);
							request.addProperty("field", field);
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
									labours[ncat][2]=res.getProperty(i).toString();
									i++;
									labours[ncat][3]=res.getProperty(i).toString();
									i++;
									labours[ncat][4]=res.getProperty(i).toString();
									
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
										
										adapter2.add("\nNAME:\t"+labours[i][1]+"\n\nPHNO:\t"+labours[i][2]+"\n\nEXPR:\t"+labours[i][3]+"\n\nCITY:\t"+labours[i][4]+"\n\nFIELD:\t"+labours[i][5]+"\n");
									}	
											
									
											
								}
							});
							}
							
							
						}.start();
					} else {
						display("No Internet Connection");
					}
				
				
			}
		});
		
		
		
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
