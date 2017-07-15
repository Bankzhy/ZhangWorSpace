package com.productmanagement.bankj.productmanagement;
import fragment.Fragment0;
import fragment.Fragment1;
import fragment.Fragment2;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {

	public MyFragmentStatePagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch(i){
		case 0:
			return new Fragment0();
		case 1:
			return new Fragment1();
		default:
			return new Fragment2();
		}
		// TODO Auto-generated method stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	public CharSequence getPageTitle(int position) {
		switch(position){
		case 0:
			return "Buy";
		case 1:
			return "Sell";
		default:
			return "History";
		}
		
	}


}
