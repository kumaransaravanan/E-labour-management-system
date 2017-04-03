package com.example.labour;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Addlabours extends Activity implements OnItemSelectedListener{
	
	EditText ed1,ed2,ed3;
	Spinner expr,fieldss;
	Button addlab;
	ArrayAdapter<String> adapter;
	ArrayList<String> arr;

		
		String name,phno,exprs,city,fields;


		

		public static String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
		private static final String NAMESPACE = "http://tempuri.org/";

		 private static final String SOAP_ACTION = "http://tempuri.org/addlabour";           
		 private static final String METHOD_NAME ="addlabour";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addlabours);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		ed1=(EditText)findViewById(R.id.name);
		ed2=(EditText)findViewById(R.id.phno);
		expr=(Spinner)findViewById(R.id.labourexp);
		
		ed3=(EditText)findViewById(R.id.city);
		fieldss=(Spinner)findViewById(R.id.labourfield);
		
		addlab=(Button)findViewById(R.id.button1);
	  
		
		
		

		expr.setOnItemSelectedListener(this);
		fieldss.setOnItemSelectedListener(this);
		
		  arr=new ArrayList<String>();
			arr.add("Choose Exp..");
			arr.add("0-1 years");
			arr.add("1-2 years");
			arr.add("2-3 years");
			arr.add("3-4 years");
			arr.add("4-5 years");
			arr.add("5-6 years");
			
		    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,arr);
			expr.setAdapter(adapter);
			

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
		fieldss.setAdapter(adapter);
		
		
		addlab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				name=ed1.getText().toString();
				phno=ed2.getText().toString();
				exprs=expr.getSelectedItem().toString();
			     city=ed3.getText().toString(); 
				fields=fieldss.getSelectedItem().toString();
				
				if(!(name.equals("")&&phno.equals("")&&exprs.equals("") && city.equals("")&&fields.equals("")))
					
				  {
						
					
						if(checkInternetConnection()) 
						{
							try
							{
								
								String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
								final String NAMESPACE = "http://tempuri.org/";

								 final String SOAP_ACTION = "http://tempuri.org/addlabour";
								 final String METHOD_NAME ="addlabour";

								
									Log.v("name", name);
									Log.v("phno", phno);
									Log.v("exprs", exprs);
									Log.v("city", city);
									Log.v("fields", fields);
									SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
									
									request.addProperty("name", name);
									request.addProperty("phno", phno);
									request.addProperty("exprs", exprs);
                                   	request.addProperty("city", city);
									request.addProperty("fields", fields);
									request.addProperty("flg",1);
									SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
									envelope.dotNet = true;
									
									
									envelope.setOutputSoapObject(request);
									HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
									androidHttpTransport.call(SOAP_ACTION, envelope);
									
									final Object result = (Object) envelope.getResponse();
									//String rs=result.toString();
									
									//Log.v("field", rs);
									if (result.toString().equalsIgnoreCase("labouradded")) {

										
										
										Toast.makeText(getApplicationContext(), "Added Successfully..",
												Toast.LENGTH_LONG).show();
										
									
										Intent intent=new Intent(getApplicationContext(),Addlabours.class);
									
										startActivity(intent);
										
									
									} else if (result.toString().equals("failed")) {
										Toast.makeText(getApplicationContext(), "Please Enter the Fiedls Correctly..",
												Toast.LENGTH_LONG).show();
									} 

								} catch (Exception e) {
									e.printStackTrace();

								}
					
						}
						
						else
						{
							
							display("NO INTERNET");
							
							
						}
							
				 }
				
				else
				{
					
					Toast.makeText(getApplicationContext(), "Fill all the Fields",
							Toast.LENGTH_LONG).show();
					
					
				}
				
				
			}
				
			});			
	     }	
	

	

	
	
	
	public void display(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	
	
			private boolean checkInternetConnection() {
				// TODO Auto-generated method stub
				
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				// test for connection
				if (cm.getActiveNetworkInfo() != null
						&& cm.getActiveNetworkInfo().isAvailable()
						&& cm.getActiveNetworkInfo().isConnected()) {
					
					return true;
				} 
				else {
				Toast.makeText(getApplicationContext(), "No Network Connection ", Toast.LENGTH_LONG).show();
				return false;
			}
				
			
		
		
		
		
		
	}







			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}







			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}


}
