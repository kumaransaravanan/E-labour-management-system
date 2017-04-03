package com.example.labour;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	TextView reg;
	EditText ed1,ed2;
	String name,pass;
	
	Button log;

	
	public static String url = "http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String SOAP_ACTION= "http://tempuri.org/login";
	private static final String METHOD_NAME = "login";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		reg=(TextView)findViewById(R.id.textView1);
		ed1=(EditText)findViewById(R.id.editText1);
		ed2=(EditText)findViewById(R.id.editText2);
		reg=(TextView)findViewById(R.id.register);
	
		log=(Button)findViewById(R.id.login);
		
		reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent i2=new Intent(getApplicationContext(),Register.class);
				startActivity(i2);
				
			}
		});

		
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 name=ed1.getText().toString();
				 pass=ed2.getText().toString();
				 
				 
				if(checkInternetConnection()) 
				{
//					final ProgressDialog progressDialog = new ProgressDialog(Login.this,
//			                R.style.AppBaseTheme);
//			        progressDialog.setIndeterminate(true);
//			        progressDialog.setMessage("Authenticating...");
//			        progressDialog.show();
					
				 if(!(name.equals("")&&pass.equals("")))
				 {
					 
					 if(name.equalsIgnoreCase("admin")&&pass .equalsIgnoreCase("admin"))
					 {
						 
						 Intent intent=new Intent(getApplicationContext(),Adminprof.class);
						 startActivity(intent);
						 
						 
					 }
					 else
					 {
						 try
							{
								
								String url="http://cyberstudents.in/android/1617Staff/rakesh/E-Labour/Service.asmx";
								final String NAMESPACE = "http://tempuri.org/";

								 final String SOAP_ACTION = "http://tempuri.org/login";
								 final String METHOD_NAME ="login";

								    Log.v("name", name);
									Log.v("pass", pass);
								
									SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);
									request.addProperty("name", name);
									request.addProperty("pass", pass);
									request.addProperty("flg","1");
									request.addProperty("flg","2");
									
									SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
									envelope.dotNet = true;
									envelope.setOutputSoapObject(request);
									HttpTransportSE androidHttpTransport = new HttpTransportSE(url);
									androidHttpTransport.call(SOAP_ACTION, envelope);
									final Object result = (Object) envelope.getResponse();

									if (result.toString().equals("clientsuccess")) {
        
										
										
										Toast.makeText(getApplicationContext(), "Authentication Success..",
												Toast.LENGTH_LONG).show();
										
									
										Intent i1=new Intent(getApplicationContext(),Clientprof.class);
										i1.putExtra("cname", name);
										
									
										startActivity(i1);
									}
									
									
									 if (result.toString().equals("dealersuccess")) {
        
										
										
										Toast.makeText(getApplicationContext(), "Authentication Success..",
												Toast.LENGTH_LONG).show();
										
									
										Intent i2=new Intent(getApplicationContext(),Dealerprof.class);
									
										startActivity(i2);
									 }
									
									
								 	 if (result.toString().equals("failed")) {
										  
										Toast.makeText(getApplicationContext(), "Please Enter the Fiedls Correctly..",
												Toast.LENGTH_LONG).show();
									}
								 	 
								 	 
									 	 

							} catch (Exception e) {
								e.printStackTrace();

						}
						 
					 }
				 }
				
				 else
				 {
					 display("Fill All The Fields");
					 
				 }
				
				}
				
				else
				{
					display("NO INTERNET");
					
				}
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

			private void display(String msg) {
				// TODO Auto-generated method stub
				
		
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
				
				
			}
		});
	
	}

	
}
