package com.example.labour;



import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;

import android.widget.ViewFlipper;



public class MainActivity extends Activity {
	
	
ImageView img;
	
Button start;
	
int mFlipping = 0 ;

	ViewFlipper flipper;

  
  @Override
    protected void onCreate(Bundle savedInstanceState)
 {
        super.onCreate(savedInstanceState);
   
     setContentView(R.layout.activity_main);
     
   start=(Button)findViewById(R.id.start);
     
   flipper=(ViewFlipper)findViewById(R.id.viewFlipper1);

 if(mFlipping==0){
  
          /** Start Flipping */
         
   flipper.startFlipping();
         
   mFlipping=1;

        }
     
   else{
      
      /** Stop Flipping */
 
           flipper.stopFlipping();
        
    mFlipping=0;
      
     
        }
     
   
      start.setOnClickListener(new OnClickListener() {
	
	@Override

public void onClick(View v) {

// TODO Auto-generated method stub
	
Intent i1=new Intent(getApplicationContext(),Login.class);

				startActivity(i1);
	
}
		});
     }


   
}
