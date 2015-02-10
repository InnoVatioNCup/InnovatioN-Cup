package com.innoVationCup.study;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class WaitActivity extends Activity{

	private Button cancelbutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.waiting);
		
		cancelbutton = (Button) findViewById(R.id.cancel);
		
		Intent jump = new Intent(getApplicationContext(),mainActivity.class);
		this.startActivity(jump);
		
		cancelbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
}
