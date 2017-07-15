package fragment;

import android.app.AlertDialog;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;

import SQLite.MyOpenHelper;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;



import SQLite.MyOpenHelper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.productmanagement.bankj.productmanagement.R;

public class Fragment1 extends Fragment{
	static List<Merchandise> list=new ArrayList<Merchandise>();
	static DBAdapter dbadapter;
	static ListView listView ;
	static MerchandiseListAdapter rowAdapter;
	
	static final int CONTEXT_MENU1_ID = 0;
	static final int CONTEXT_MENU2_ID = 1;
	

	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment1, null);
		
	}
	public void onActivityCreated(Bundle savedInstanceState){
		 super.onActivityCreated(savedInstanceState);
		 

		 dbadapter=new DBAdapter(this.getActivity());
		 listView = (ListView)getActivity().findViewById(R.id.F1listView);
		 rowAdapter=new MerchandiseListAdapter(this.getActivity(), 0, list);
		 listView.setAdapter(rowAdapter);
		 registerForContextMenu(listView);
		 load();
	}
	public void load(){
		list.clear();
		dbadapter.open();
		Cursor c=dbadapter.getAllList("SFTABLE_NAME");
		
		
		if(c.moveToFirst()){
					 do{
						 Merchandise mer=new Merchandise();
						 mer.setName(c.getString(c.getColumnIndex("Name")));
						 mer.setQty(c.getString(c.getColumnIndex("Qty")));
						 list.add(mer);
					 }while(c.moveToNext());				
		}
		dbadapter.close();
		rowAdapter.notifyDataSetChanged();
		 

		
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		menu.setHeaderTitle("Choose your operation");
		menu.add(0, CONTEXT_MENU1_ID, 0, "Sell");
		menu.add(0, CONTEXT_MENU2_ID, 0, "Abandon");
	}
	public boolean onContextItemSelected(MenuItem item){
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		AdapterContextMenuInfo adapterInfo=(AdapterContextMenuInfo) item.getMenuInfo();
		Merchandise mer1=(Merchandise) listView.getItemAtPosition(adapterInfo.position);
		
		switch(item.getItemId()){
		case CONTEXT_MENU1_ID:
			setSellDialog(mer1.getName());
			
			return true;
		case CONTEXT_MENU2_ID:
			setAbandonDialog(mer1.getName());
			return true;
		default:
			return super.onContextItemSelected(item);
		}
		
	}
	public void setSellDialog(final String positionName){
		LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout =inflater.inflate(R.layout.sell_dialog, (ViewGroup)getActivity().findViewById(R.id.layout_sellroot));

		AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
		builder.setTitle("Sell");
		builder.setView(layout);
		builder.setPositiveButton("OK", new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText selldqtyedit=(EditText) layout.findViewById(R.id.selldialog_qty);
				EditText selldpriceedit=(EditText)layout.findViewById(R.id.selldialog_price);
				String selldqty=selldqtyedit.getText().toString();
				String selldprice=selldpriceedit.getText().toString();
				int dq=new Integer(selldqty).intValue();
				dbadapter.open();
				dbadapter.sellinsert(positionName, selldqty, selldprice);
				
				dbadapter.decreaseQty(positionName, dq);
				load();
				dbadapter.close();
				// TODO Auto-generated method stub
				
			}
			
		});




		builder.create().show();
	}
	public void setAbandonDialog(final String positionName){
		LayoutInflater inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout =inflater.inflate(R.layout.abandon_dialog, (ViewGroup)getActivity().findViewById(R.id.layout_abandonroot));
		AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
		builder.setTitle("Abandon");
		builder.setView(layout);
		builder.setPositiveButton("OK", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText abandondqtyedit=(EditText) layout.findViewById(R.id.abandondialog_qty);
				String qtyabandon=abandondqtyedit.getText().toString();
				int dq=new Integer(qtyabandon).intValue();
				dbadapter.open();
				dbadapter.abandoninsert(positionName, qtyabandon);
				dbadapter.decreaseQty(positionName, dq);
				load();
				dbadapter.close();
				
				// TODO Auto-generated method stub
				
			}
		});
		builder.create().show();
	}
}