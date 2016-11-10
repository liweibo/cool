package com.example.weather;

import yjj.weather.application.UserInterface;
import yjj.weather.tool.NoStatus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DefalutcityActivity extends Activity implements OnClickListener{
	
	private TextView city1;
	private TextView city2;
	private TextView city3;
	private TextView city4;
	private Button ok;
	private Button word;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defaultcity);

		View llDefault = findViewById(R.id.ll_default);
		NoStatus.initAfterSetContentView(DefalutcityActivity.this,llDefault);

		initWidgets();
		showText();

	}
	/**
	 * 初始化组件
	 */
	private void initWidgets(){
		city1=(TextView)findViewById(R.id.city1);
		city2=(TextView)findViewById(R.id.city2);
		city3=(TextView)findViewById(R.id.city3);
		city4=(TextView)findViewById(R.id.city4);
		ok=(Button)findViewById(R.id.defaultcity_back);
		word=(Button)findViewById(R.id.defaultcity_word);
		city1.setOnClickListener(this);
		city2.setOnClickListener(this);
		city3.setOnClickListener(this);
		city4.setOnClickListener(this);
		ok.setOnClickListener(this);
		word.setOnClickListener(this);
	}
	
	private void showText(){
		String tmp=null;
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME1))!=null){
			city1.setText(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME2))!=null){
			city2.setText(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME3))!=null){
			city3.setText(tmp);
		}
		if((tmp=UserInterface.getDefaultCity(UserInterface.DEFAULT_CITY_NAME4))!=null){
			city4.setText(tmp);
		}
	}
	@Override
	public void onClick(View v) {
	    switch(v.getId()){
	    case R.id.city1:
	    	showDialog(UserInterface.DEFAULT_CITY_NAME1,city1);
	    	break;
	    case R.id.city2:
	    	showDialog(UserInterface.DEFAULT_CITY_NAME2,city2);
	    	break;
	    case R.id.city3:
	    	showDialog(UserInterface.DEFAULT_CITY_NAME3,city3);
	    	break;
	    case R.id.city4:
	    	showDialog(UserInterface.DEFAULT_CITY_NAME4,city4);
	    	break;
	    case R.id.defaultcity_back:
	    	finish();
	    	break;
			case R.id.defaultcity_word:
				startActivity(new Intent(DefalutcityActivity.this,TextActivity.class));
				finish();
				break;
	    }
		
	}
	
	private void showDialog(final String whichCity,final TextView whichTextView){
		final EditText name=new EditText(this);
		new AlertDialog.Builder(this).setTitle("请输入城市名称")
	    .setIcon(android.R.drawable.ic_dialog_info)
	    .setView(name)
	    .setPositiveButton("确定",new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface  dialog, int which) {
				if(!name.getText().toString().equals("")){
			    	UserInterface.setDefaultCity(whichCity, name.getText().toString());
			    	whichTextView.setText(name.getText().toString());
				}
			}
		})   
	    .setNegativeButton("取消", null).show();
	}
}
