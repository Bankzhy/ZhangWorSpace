package fragment;
import java.util.ArrayList;
import java.util.List;



import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.productmanagement.bankj.productmanagement.R;

public class SellFragment extends Fragment{
	static List<Merchandise> selllist =new ArrayList<Merchandise>();
	static DBAdapter dbadapter;
	static ListView listView;
	static BsListAdapter bslistadapter;
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	               Bundle savedInstanceState) {
	    // Inflate the layout for this fragment
	    return inflater.inflate(R.layout.sellhistory, container, false);
	  }
	  public void onActivityCreated(Bundle savedInstanceState){
		  super.onActivityCreated(savedInstanceState);
		  
		  dbadapter=new DBAdapter(this.getActivity());
		  listView=(ListView)getActivity().findViewById(R.id.sellhistory_listView);
		  bslistadapter=new BsListAdapter(this.getActivity(), 0, selllist);
		  listView.setAdapter(bslistadapter);
		  registerForContextMenu(listView);
		  sellload();
		  
	  }
	 public void sellload() {
		 selllist.clear();
		 dbadapter.open();
		 Cursor c=dbadapter.getAllList("SELLHISTORY_NAME");
		 
		 if(c.moveToFirst()){
			 do{
				  Merchandise mer=new Merchandise();
				  mer.setName(c.getString(c.getColumnIndex("SName")));
				  mer.setQty(c.getString(c.getColumnIndex("SQty")));
				  mer.setPrice(c.getString(c.getColumnIndex("SPrice")));
				  mer.setTime(c.getString(c.getColumnIndex("STime")));
				  selllist.add(mer);
			 }while(c.moveToNext());
		 }
		 dbadapter.close();
		 bslistadapter.notifyDataSetChanged();
		// TODO Auto-generated method stub
		
	 }
}
