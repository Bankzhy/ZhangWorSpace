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

public class AbandonFragment extends Fragment {
	static List<Merchandise> abandonlist =new ArrayList<Merchandise>();
	static DBAdapter dbadapter;
	static ListView listView;
	static BsListAdapter bslistadapter;
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	               Bundle savedInstanceState) {
	    // Inflate the layout for this fragment
	    return inflater.inflate(R.layout.abandonhistory, container, false);
	  }
	  public void onActivityCreated(Bundle savedInstanceState){
		  super.onActivityCreated(savedInstanceState);
		  
		  dbadapter=new DBAdapter(this.getActivity());
		  listView=(ListView)getActivity().findViewById(R.id.abandonhistory_listView);
		  bslistadapter=new BsListAdapter(this.getActivity(), 0, abandonlist);
		  listView.setAdapter(bslistadapter);
		  registerForContextMenu(listView);
		  abandonload();
	  }
	public void abandonload() {
		abandonlist.clear();
		dbadapter.open();
		Cursor c=dbadapter.getAllList("ABANDONHISTORY_NAME");
		  if(c.moveToFirst()){
			  do{
				  Merchandise mer=new Merchandise();
				  mer.setName(c.getString(c.getColumnIndex("AName")));
				  mer.setQty(c.getString(c.getColumnIndex("AQty")));
				  mer.setPrice("NULL");
				  mer.setTime(c.getString(c.getColumnIndex("ATime")));
				  abandonlist.add(mer);
			  }while(c.moveToNext());
			  
		  }
		 dbadapter.close();
		 bslistadapter.notifyDataSetChanged();
		// TODO Auto-generated method stub
		
	}

}
