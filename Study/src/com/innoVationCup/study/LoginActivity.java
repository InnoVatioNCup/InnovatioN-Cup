package com.innoVationCup.study;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class LoginActivity extends Activity {

	private Button LOGIN_button;
	private EditText ID,Password;
	private CheckBox Auto_Log,IDPW_Save;
	private String ID_string,PW_string;
	private SharedPreferences IDPW_Save_Xml;
	private ImageButton quitbutton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /*this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        this.setContentView(Window.FEATURE_NO_TITLE);
        this.setContentView(Window.FEATURE_LEFT_ICON);*/
        setContentView(R.layout.login);
        
        LOGIN_button = (Button) findViewById(R.id.LOGINbutton);
        ID = (EditText) findViewById(R.id.IDedittext);
        Password = (EditText) findViewById(R.id.PWedittext);
        Auto_Log = (CheckBox) findViewById(R.id.AUTOPWcheckbox);
        IDPW_Save = (CheckBox) findViewById(R.id.PWsavebox);
        quitbutton = (ImageButton) findViewById(R.id.quitbutton);
        IDPW_Save_Xml = this.getSharedPreferences("Info",Context.MODE_WORLD_READABLE);
        
        jump();
      
        loginlistener();
        
        IDPWsavelistener();
        
        auto_loglistener();
        
        quitlistener();
    }
    
    private void jump(){
    	  if(IDPW_Save_Xml.getBoolean("isCheck", false)){
          	IDPW_Save.setChecked(true);
          	ID.setText(IDPW_Save_Xml.getString("ID",""));
          	Password.setText(IDPW_Save_Xml.getString("PassWord", ""));
          	if(IDPW_Save_Xml.getBoolean("AUTOLOG", false)){
          		Auto_Log.setChecked(true);
          		Intent jump = new Intent(LoginActivity.this,WaitActivity.class);
          		LoginActivity.this.startActivity(jump);
          	}	
          }
    }
    
    private void loginlistener(){
    	LOGIN_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ID_string = ID.getText().toString();
				PW_string = Password.getText().toString();
				if(ID_string.equals("admin")&&PW_string.equals("admin")){
					Toast toast;
					toast = Toast.makeText(LoginActivity.this, "Log Success", Toast.LENGTH_SHORT);
					toast.show();
					if(IDPW_Save.isChecked()){
						Editor edit =IDPW_Save_Xml.edit();
						edit.putString("ID", ID_string);
						edit.putString("PW", PW_string);
						edit.commit();
					}
					Intent jump = new Intent(LoginActivity.this,WaitActivity.class);
					LoginActivity.this.startActivity(jump);
				}else{
					Toast toast_unsuccess;
					toast_unsuccess = Toast.makeText(LoginActivity.this, "Log fail",Toast.LENGTH_SHORT);
					toast_unsuccess.show();
				}
				
			}
		});
    }

    private void IDPWsavelistener(){
        IDPW_Save.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(IDPW_Save.isChecked()){
					//"Auto login chosen"
					IDPW_Save_Xml.edit().putBoolean("REMPW", true).commit();
				}else{
					//"Not chosen"
					IDPW_Save_Xml.edit().putBoolean("REMPW", false).commit();
				}
			}
		});
    }
    
    private void auto_loglistener(){
    	Auto_Log.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(Auto_Log.isChecked()){
					//"Auto login chosen"
					IDPW_Save_Xml.edit().putBoolean("AUTOLOG", true).commit();
				}else{
					//"Not chosen"
					IDPW_Save_Xml.edit().putBoolean("AUTOLOG", false).commit();
				}
			}
		});
    }

    private void quitlistener(){
    	quitbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }
    
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
