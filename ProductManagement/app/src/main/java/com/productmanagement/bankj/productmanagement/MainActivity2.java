package com.productmanagement.bankj.productmanagement;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import fragment.Fragment0;
import fragment.Fragment1;
import fragment.Fragment2;

public class MainActivity2 extends AppCompatActivity implements
	NavigationView.OnNavigationItemSelectedListener{
	ViewPager viewPager;
	DrawerLayout drawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2_drawer);
		// Pager
		viewPager=(ViewPager)findViewById(R.id.pager);
		viewPager.setAdapter(
				new MyFragmentStatePagerAdapter(
				getSupportFragmentManager()));


		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
		android.support.v7.app.ActionBarDrawerToggle toggle=new android.support.v7.app.ActionBarDrawerToggle(this,
				drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
		navView.setNavigationItemSelectedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManfifest.xml.
		int id = item.getItemId();
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		android.app.Fragment fragment = null;
		Class fragmentClass;


		int id = item.getItemId();
		if (id==R.id.nav_Buy){
			Log.d("Buy","s1");
			viewPager.setCurrentItem(0);
		}else if (id==R.id.nav_Sell){
			viewPager.setCurrentItem(1);
			Log.d("Buy","s2");
		}else if (id==R.id.nav_buyhistory){
			viewPager.setCurrentItem(2);
			Fragment2.mTabHost.setCurrentTab(0);
		}else  if (id==R.id.nav_sellhistory){
			viewPager.setCurrentItem(2);
			Fragment2.mTabHost.setCurrentTab(1);
		}else  if(id==R.id.nav_abandonhistory){
			viewPager.setCurrentItem(3);
			Fragment2.mTabHost.setCurrentTab(2);
		}

//		switch (id) {
//			case R.id.nav_Buy:
//				viewPager.setCurrentItem(0);
//				Log.d("sds", "s1");
//				//fragmentClass = Fragment0.class;
//			case R.id.nav_Sell:
//				Log.d("sds", "s2");
//				//Log.d("currentPage", String.valueOf(viewPager.getCurrentItem()));
//				viewPager.setCurrentItem(1);
//				Log.d("currentPage", String.valueOf(viewPager.getCurrentItem()));
				//fragmentClass = Fragment1.class;
//			case  R.id.nav_buyhistory:
//				viewPager.setCurrentItem(2);
//				Log.d("currentPage", String.valueOf(viewPager.getCurrentItem()));
//			//	Fragment2.mTabHost.setCurrentTab(0);
//			case  R.id.nav_sellhistory:
//				viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
//			//	Fragment2.mTabHost.setCurrentTab(1);
//			case  R.id.nav_abandonhistory:
//				viewPager.setCurrentItem(viewPager.getCurrentItem()+2);
//			//	Fragment2.mTabHost.setCurrentTab(2);
//			default:
//				viewPager.setCurrentItem(viewPager.getCurrentItem());
				//fragmentClass = Fragment0.class;
				//MyFragmentStatePagerAdapter mf=new MyFragmentStatePagerAdapter(getSupportFragmentManager());
				//mf.getItem(0);
				//fragmentManager.beginTransaction().replace(R.id.pager,mf.getItem(0)).commit();
//		}
//		try{
//			fragment=(android.app.Fragment) fragmentClass.newInstance();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		FragmentManager fragmentManager=getSupportFragmentManager();
//		getFragmentManager().beginTransaction().replace(R.id.contentFrame,fragment).commit();
//		item.setChecked(true);
		drawer.closeDrawers();
//
		return true;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		Log.d("1","1");
		IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
		if (result!=null){
			if (result.getContents()==null){
				Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show();

			}else {
				EditText name=(EditText)findViewById(R.id.editname);
				name.setText(result.getContents());
				name.setSelection(name.getText().length());
		//		Log.d("YES","YES");
		//		Log.d("kekka",result.getContents());
			}
		}else {
			super.onActivityResult(requestCode,resultCode,data);
		}
	}
}
