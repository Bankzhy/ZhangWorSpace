package fragment;
import java.util.ArrayList;
import java.util.List;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.productmanagement.bankj.productmanagement.R;

public class BuyFragment extends Fragment {
	Fragment1 f1=new Fragment1();
	static List<Merchandise> buylist =new ArrayList<Merchandise>();
	static DBAdapter dbadapter;
	static ListView buylistView;
	static BsListAdapter bslistadapter;
	static final int CONTEXT_MENU3_ID = 3;
	  @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	               Bundle savedInstanceState) {
	    // Inflate the layout for this fragment
	    return inflater.inflate(R.layout.buyhistory, container, false);
	  }
	  public void onActivityCreated(Bundle savedInstanceState){
		  
		  super.onActivityCreated(savedInstanceState);
		  
		  dbadapter=new DBAdapter(this.getActivity());
		  buylistView=(ListView)getActivity().findViewById(R.id.buyhistory_listView);
		  bslistadapter=new BsListAdapter(this.getActivity(), 0, buylist);
		  buylistView.setAdapter(bslistadapter);
		  registerForContextMenu(buylistView);
		  buyload();
		  
	  }
	  public void buyload(){
		  buylist.clear();
		  dbadapter.open();
		  Cursor c=dbadapter.getAllList("BUYHISTORY_NAME");
		  
		  if(c.moveToFirst()){
			  do{
				  Merchandise mer=new Merchandise();
				  mer.setName(c.getString(c.getColumnIndex("BName")));
				  mer.setQty(c.getString(c.getColumnIndex("BQty")));
				  mer.setPrice(c.getString(c.getColumnIndex("BPrice")));
				  mer.setTime(c.getString(c.getColumnIndex("BTime")));
				  buylist.add(mer);
			  }while(c.moveToNext());
			  
		  }
		  bslistadapter.notifyDataSetChanged();
	  }
	  @Override
	  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		  super.onCreateContextMenu(menu, v, menuInfo);
		  
		  menu.setHeaderTitle("Choose your operation");
		  menu.add(0,CONTEXT_MENU3_ID, 0, "Delete");
	  }
	  public boolean onContextItemSelected(MenuItem item){
		  
		  AdapterContextMenuInfo adapterInfo=(AdapterContextMenuInfo) item.getMenuInfo();
		  Merchandise mer2=(Merchandise) buylistView.getItemAtPosition(adapterInfo.position);
		  if(item.getItemId()==CONTEXT_MENU3_ID){
			  setConfirmDiaLog(mer2);
			  return true;

			  
		  }else{
			  return super.onContextItemSelected(item);
		  }

	  }
	public void setConfirmDiaLog(final Merchandise merchandise) {
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
		builder.setTitle("Confirm");

		builder.setPositiveButton("OK", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				  int dq=new Integer(merchandise.getQty()).intValue();
				  if(dbadapter.checkName(merchandise.getName())==true){
					  dbadapter.open();
					  dbadapter.decreaseQty(merchandise.getName(), dq);
					  dbadapter.deleterecord("BUYHISTORY_NAME", merchandise.getName(),merchandise.getTime());
					  dbadapter.close();
					  f1.load();
					  buyload();

				  }else{
					  dbadapter.open();
					  dbadapter.deleterecord("BUYHISTORY_NAME", merchandise.getName(),merchandise.getTime());
					  dbadapter.close();
					  buyload();
				  }
				// TODO Auto-generated method stub
				
			}
			
		});
		builder.create().show();


		// TODO Auto-generated method stub
		

	}
	  
}
