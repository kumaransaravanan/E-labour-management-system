package com.example.labour;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.string;
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

public class Register extends Activity implements OnItemSelectedListener{

	EditText ed1, ed2, ed3, ed4;
	Spinner role, exp, field;
	Button record;
    String roles, name, pass, expr, phno, city, fields;
    
    ArrayList<String> al;
    ArrayAdapter<String> adapter;
    
    
    

	public static String url = "http://cyberstudents.in/android/1617Staff/rakesh/Restaurant/Service.asmx";
	private static final String NAMESPACE = "http://tempuri.org/";

	private static final String SOAP_ACTION0 = "http://tempuri.org/employeeregis";
	private static final String SOAP_ACTION1 = "http://tempuri.org/dealerregis";
	private static final String SOAP_ACTION2 = "http://tempuri.org/clientregis";
	private static final String EMPLOYEE_METHOD_NAME = "employeeregis";
	private static final String DEAL_METHOD_NAME = "dealerregis";
	private static final String CLIENT_METHOD_NAME = "clientregis";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		
		ed1=(EditText)findViewById(R.id.name);
		exp=(Spinner)findViewById(R.id.exp);
		ed2=(EditText)findViewById(R.id.password);
		ed3=(EditText)findViewById(R.id.phno);
		ed4=(EditText)findViewById(R.id.city);
		field=(Spinner)findViewById(R.id.empfield);
	    record=(Button)findViewById(R.id.record);
	    role=(Spinner)findViewById(R.id.role);
		role.setOnItemSelectedListener(this);
		
		  al=new ArrayList<String>();
			al.add("Client");
			al.add("Dealer");
			al.add("Employee");
		

	    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,al);
	    role.setAdapter(adapter);
	    setupspinner();
	    
	    al=new ArrayList<String>();
		al.add("0-1 years");
		al.add("1-2 years");
		al.add("2-3 years");
		al.add("3-4 years");
		al.add("4-5 years");
		al.add("5-6 years");
	

    adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,al);
    exp.setAdapter(adapter);
	    
	    
    al=new ArrayList<String>();
	al.add("Mason");
	al.add("Painter");
	al.add("Carpenter");
	al.add("Housekeeping");
	al.add("Electrician");
	al.add("Plumber");
	al.add("PestCleaner");
	al.add("Agriculture");


  adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,al);
  field.setAdapter(adapter);
    
    
	    
	    
	    
	    
	    
	    record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				roles=role.getSelectedItem().toString();
				name=ed1.getText().toString();
		pass=ed2.getText().toString();
		expr=exp.getSelectedItem().toString();
		phno=ed3.getText().toString();
	    city=ed4.getText().toString();
	    fields=field.getSelectedItem().toString();
				
 if(checkInternetConnection()) 
  {
			    	
	 if(roles.equalsIgnoreCase("EMPLOYEE"))
	  {		
		if(!(name.equals("") && pass.equals("")&&expr.equals("") && phno.equals("")&&city.equals("")&&fields.equals("")))
								
		 {                                                                                                                                               
			try
			{
								
				String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
				final String NAMESPACE = "http://tempuri.org/";

				 final String SOAP_ACTION0 = "http://tempuri.org/employeeregis";
				 final String EMPLOYEE_METHOD_NAME ="employeeregis";

				    Log.v("name", name);
					Log.v("pass", pass);
					Log.v("expr", expr);
					Log.v("phno", phno);
					Log.v("city", city);
					Log.v("field", fields);
					SoapObject request = new SoapObject(NAMESPACE, EMPLOYEE_METHOD_NAME);
					request.addProperty("role",roles);
					request.addProperty("name", name);
					request.addProperty("pass", pass);
					request.addProperty("expr", expr);
					request.addProperty("phno", phno);
					request.addProperty("city", city);
					request.addProperty("fields", fields);
					request.addProperty("flg", 1);
					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
					envelope.dotNet = true;
					
					
					envelope.setOutputSoapObject(request);
					HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
					androidHttpTransport.call(SOAP_ACTION0, envelope);
					final Object result = (Object) envelope.getResponse();						

					if (result.toString().equalsIgnoreCase("employeeregistered")) {

						
						
						Toast.makeText(getApplicationContext(), "Registered Successfully..",
								Toast.LENGTH_LONG).show();
						
					
						Intent intent=new Intent(getApplicationContext(),Login.class);
					
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
								
								Toast.makeText(getApplicationContext(), "Fill All The Fields", Toast.LENGTH_LONG).show();
								
								
							}
							
						}
			    	
	  if(roles.equalsIgnoreCase("DEALER"))
 	{
 		
 		
 		if(!(name.equals("") && pass.equals("") && phno.equals("")&&city.equals("")))
				
			  {
					try
					{
						
						String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
						final String NAMESPACE = "http://tempuri.org/";

						 final String SOAP_ACTION1 = "http://tempuri.org/dealerregis";
						 final String DEAL_METHOD_NAME="dealerregis";

						    Log.v("name", name);
							Log.v("pass", pass);
						    Log.v("phno", phno);
							Log.v("city", city);
							
							SoapObject request = new SoapObject(NAMESPACE, DEAL_METHOD_NAME);
							request.addProperty("role",roles);
                            request.addProperty("name", name);
							request.addProperty("pass", pass);
							request.addProperty("phno", phno);
							request.addProperty("city", city);
							request.addProperty("flg", 1);
							
							SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
							envelope.dotNet = true;
							
							
							envelope.setOutputSoapObject(request);
							HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
							androidHttpTransport.call(SOAP_ACTION1, envelope);
							final Object result = (Object) envelope.getResponse();

							if (result.toString().equals("dealerregistered")) {

								
								
								Toast.makeText(getApplicationContext(), "Registered Successfully..",
										Toast.LENGTH_LONG).show();
								
							
								Intent intent=new Intent(getApplicationContext(),Login.class);
							
								startActivity(intent);
								
							
		   } else if (result.toString().equals("failed")) {
			Toast.makeText(getApplicationContext(), "Please Enter the Fiedls Correctly..",
			Toast.LENGTH_LONG).show();
	      } 

	  } catch (Exception e) {
	 e.printStackTrace();

	}
				  
 }	
}		    	
	

	if(roles.equalsIgnoreCase("CLIENT"))			    	
	{
		if(!(name.equals("") && pass.equals("") && phno.equals("")&&city.equals("")))
			
		  {
				try
				{
					
					String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
					final String NAMESPACE = "http://tempuri.org/";

					 final String SOAP_ACTION2 = "http://tempuri.org/clientregis";
					 final String CLIENT_METHOD_NAME ="clientregis";

					    Log.v("name", name);
						Log.v("pass", pass);
					    Log.v("phno", phno);
						Log.v("city", city);
						
						SoapObject request = new SoapObject(NAMESPACE, CLIENT_METHOD_NAME);
						request.addProperty("role",roles);
						request.addProperty("name", name);
						request.addProperty("pass", pass);
						request.addProperty("phno", phno);
						request.addProperty("city", city);
						request.addProperty("flg", 1);
						
						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
						envelope.dotNet = true;
						
						
						envelope.setOutputSoapObject(request);
						HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
						androidHttpTransport.call(SOAP_ACTION2, envelope);
						final Object result = (Object) envelope.getResponse();

						if (result.toString().equals("clientregistered")) {

							
							
							Toast.makeText(getApplicationContext(), "Registered Successfully..",
									Toast.LENGTH_LONG).show();
							
						
							Intent intent=new Intent(getApplicationContext(),Login.class);
						
							startActivity(intent);
							
						
	   } else if (result.toString().equals("failed")) {
		Toast.makeText(getApplicationContext(), "Please Enter the Fiedls Correctly..",
		Toast.LENGTH_LONG).show();
    } 

   } catch (Exception e) {
          e.printStackTrace();

          }
			  
      }
		
		
	} 
	}
				
			    else
				{
									
					display("NO INTERNET");
									
					}
				
			
			}
	
	   
			

			private void display(String msg) {
				// TODO Auto-generated method stub
				
				
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
				
				
				
			}




			private boolean checkInternetConnection() {
				// TODO Auto-generated method stub
				ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				// test for connection
				if (cm.getActiveNetworkInfo() != null
						&& cm.getActiveNetworkInfo().isAvailable()
						&& cm.getActiveNetworkInfo().isConnected()) {
					
					return true;
				} else {
					
					return false;
				}
				
			}
	    
	    });	
	
	}


	private void setupspinner() {
		// TODO Auto-generated method stub

		role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 2) {
					exp.setVisibility(View.VISIBLE);
					field.setVisibility(View.VISIBLE);
					
					

				} else {
					
					exp.setVisibility(View.GONE);
					field.setVisibility(View.GONE);
					
					
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		
	});


	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
}