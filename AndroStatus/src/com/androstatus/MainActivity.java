package com.androstatus;


import com.androstatus.about.About;
import com.androstatus.rootchecker.RootChecker;
import com.ninadjayandrostatus.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

	public final static String MAIN_TO_ABOUT_MESSAGE="com.androstatus.MESSAGE_TO_ABOUT";
	public final static String MAIN_TO_ROOT_CHECKER_MESSAGE="com.androstatus.MESSAGE_TO_ROOT_CHECKER";
			
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void onAboutClick(View view)  
    {  
    	Intent intent = new Intent(this, About.class);
    	intent.putExtra(MAIN_TO_ABOUT_MESSAGE, "Message from Main Activity to About Activity");
    	startActivity(intent);
    	
    } 
    
    public void onRootCheckerClick(View view)  
    {  
    	Intent intent = new Intent(this, RootChecker.class);
    	intent.putExtra(MAIN_TO_ROOT_CHECKER_MESSAGE, "Message from Main Activity to Root Checker Activity");
    	startActivity(intent);
    	
    } 
}
