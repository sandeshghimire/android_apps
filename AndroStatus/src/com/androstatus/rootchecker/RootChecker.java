package com.androstatus.rootchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ninadjayandrostatus.R;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class RootChecker extends Activity {
	
	TextView rootResultsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_root_checker);
		rootResultsView = (TextView)findViewById(R.id.rootResultsView);
		rootResultsView.setText(rootCheck());
		rootResultsView.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.root_checker, menu);
		return true;
	}
	
	//Executes a given command on the Android shell and returns output
	public String runCommand(String cmd){
		String output = "";
		Runtime run = Runtime.getRuntime();
		Process pr;
		try {
			pr = run.exec(cmd);
			if(pr.waitFor()==0){

				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
				String line = "";
		
				while ((line=buf.readLine())!=null) {
					output=output+"\n"+line;
				}
			}
			else{

				BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
				String line = "";
		
				while ((line=buf.readLine())!=null) {
					output=output+"\n"+line;
				}
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		return output;
		
	}

	//Checks for root and returns a description of the result	
	public String rootCheck(){
		String result,roSecureResult="",packages,roSecure,binSu,xbinSu,sbinSu,xbinSudo;
		int roSecureInt;
		boolean isSuperSUInstalled, isSuperUserInstalled;
		
	/*	//Get a list of packages to check if superuser or superSU apps. are installed
		packages = runCommand("pm list packages -f");
		
		if(packages.contains("superuser"))
			isSuperUserInstalled = true;
		else
			isSuperUserInstalled = false;
		
		if(packages.contains("superSU"))
			isSuperSUInstalled = true;
		else
			isSuperSUInstalled = false;
		*/
		roSecure=runCommand("getprop ro.secure");
		roSecure=roSecure.replaceAll("\n", "");
		roSecureInt=Integer.parseInt(roSecure);
		if(roSecureInt==1)
			roSecureResult="1) The value of ro.secure is set to: " + roSecure + "\nThis means that the adb shell won't run commands as a root user on your device. The ro.secure property is set at boot time from the default.prop file in the root directory.";
		else if(roSecureInt==0)
			roSecureResult="1) The value of ro.secure is set to: " + roSecure + "\nThis means that the adb shell will run commands as a root user on your device. The ro.secure property is set at boot time from the default.prop file in the root directory.";	
		
		
		binSu=runCommand("ls -l /system/bin/su");
		xbinSu=runCommand("ls -l /system/xbin/su");
		sbinSu=runCommand("ls -l /sbin/su");
		xbinSudo=runCommand("ls -l /system/xbin/sudo");
		
		result=roSecureResult + "\n\n" + binSu + "\n" + xbinSu + "\n" + xbinSudo + "\n" + sbinSu;
		Log.d("TEST_MESSAGES",result);
			
		return result;
	}

}
