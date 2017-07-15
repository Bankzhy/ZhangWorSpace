package fragment;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.productmanagement.bankj.productmanagement.R;

public class BsListAdapter extends ArrayAdapter<Merchandise>{
	private LayoutInflater layoutinflater;
	public BsListAdapter(Context context, int textViewResourceId,List<Merchandise> objects) {
		super(context, textViewResourceId, objects);
		layoutinflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Merchandise detail=(Merchandise)getItem(position);
		if(null==convertView){
			convertView=layoutinflater.inflate(R.layout.bslistview, null);
			
		}
		TextView nametext=(TextView)convertView.findViewById(R.id.bs_name);
		nametext.setText(String.valueOf(detail.getName()));
		
		TextView qtytext=(TextView)convertView.findViewById(R.id.bs_qty);
		qtytext.setText(String.valueOf(detail.getQty()));
		
		TextView pricetext=(TextView)convertView.findViewById(R.id.bs_price);
		pricetext.setText(String.valueOf(detail.getPrice()));
		
		TextView timetext=(TextView)convertView.findViewById(R.id.bs_time);
		timetext.setText(String.valueOf(detail.getTime()));
		return convertView;
		
	}
}
