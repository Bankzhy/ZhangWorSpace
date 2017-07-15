package fragment;

import java.util.List;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;

import com.productmanagement.bankj.productmanagement.R;

public class MerchandiseListAdapter extends ArrayAdapter<Merchandise> {
	private LayoutInflater layoutinflater;
	
	public MerchandiseListAdapter(Context context, int textViewResourceId,List<Merchandise> objects) {
		super(context, textViewResourceId, objects);
		layoutinflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		Merchandise detail=(Merchandise)getItem(position);
		if(null==convertView){
			convertView=layoutinflater.inflate(R.layout.row, null);
			
		}
		TextView nametext=(TextView)convertView.findViewById(R.id.TextViewname);
		nametext.setText(String.valueOf(detail.getName()));
		
		TextView qtytext=(TextView)convertView.findViewById(R.id.TextViewqty);
		qtytext.setText(String.valueOf(detail.getQty()));
		return convertView;
		
	}

}
