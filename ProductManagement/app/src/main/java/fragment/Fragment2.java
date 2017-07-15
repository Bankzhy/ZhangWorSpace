package fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

import com.productmanagement.bankj.productmanagement.R;

public class Fragment2 extends Fragment  implements OnTabChangeListener {
    // TabHost
    public static TabHost mTabHost;
    // Last selected tabId
    private String mLastTabId;    
 
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment2, null);
	}
	public void onActivityCreated(Bundle savedInstanceState){
		 super.onActivityCreated(savedInstanceState);
		
		 mTabHost = (TabHost)getActivity().findViewById(android.R.id.tabhost);
	        mTabHost.setup();        
	 
	        /* Tab1 �O�� */
	        TabSpec tab1 = mTabHost.newTabSpec("BuyHistory");
	        tab1.setIndicator("Buy");                  
	        tab1.setContent(new DummyTabFactory(this.getActivity())); 
	        mTabHost.addTab(tab1); 
	 
	        // Tab2 �O��
	        TabSpec tab2 = mTabHost.newTabSpec("SellHistory");
	        tab2.setIndicator("Sell");                  
	        tab2.setContent(new DummyTabFactory(this.getActivity())); 
	        mTabHost.addTab(tab2);
	        // Tab3 �O��
	        TabSpec tab3 = mTabHost.newTabSpec("AbandonHistory");
	        tab3.setIndicator("Abandon");                  
	        tab3.setContent(new DummyTabFactory(this.getActivity())); 
	        mTabHost.addTab(tab3);
	 

	 
	        // ���։���r���٥�ȥϥ�ɥ�
	        mTabHost.setOnTabChangedListener(this);
	 
	        // ���ڥ����x�k
	        onTabChanged("tab1"); 
	}

	@Override
	public void onTabChanged(String tabId) {
        Log.d("TAB_FRAGMENT_LOG","tabId:" + tabId);
        if(mLastTabId != tabId){
            FragmentTransaction fragmentTransaction 
                 = getChildFragmentManager().beginTransaction();
            if("BuyHistory" == tabId){
                fragmentTransaction
                    .replace(R.id.realtabcontent, new BuyFragment());
            }else if("SellHistory" == tabId){
                fragmentTransaction
                    .replace(R.id.realtabcontent, new SellFragment());
            }else if("AbandonHistory" == tabId){
                fragmentTransaction
                    .replace(R.id.realtabcontent, new AbandonFragment());
            }
            mLastTabId = tabId;
            fragmentTransaction.commit();
        }
		// TODO Auto-generated method stub
		
	}
    private static class DummyTabFactory implements TabContentFactory {
    	 
        /* Context */
        private final Context mContext;
 
        DummyTabFactory(Context context) {
            mContext = context;
        }
 
        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            return v;
        }
    }  

}
