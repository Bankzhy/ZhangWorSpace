package fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.productmanagement.bankj.productmanagement.R;

public class Fragment0 extends Fragment {
	Fragment1 f1=new Fragment1();
//	 EditText editname=(EditText)getActivity().findViewById(R.id.editname);
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
		return inflater.inflate(R.layout.fragment0, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		 super.onActivityCreated(savedInstanceState);
		 final DBAdapter dbadapter=new DBAdapter(this.getActivity());
		 
		 final EditText editname=(EditText)getActivity().findViewById(R.id.editname);
		 final EditText editqty=(EditText)getActivity().findViewById(R.id.editqty);
		 final EditText editprice=(EditText)getActivity().findViewById(R.id.editprice);
		 Button buttoninsert =(Button)getActivity().findViewById(R.id.buttoninsert);
		 buttoninsert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String nameinsert=editname.getText().toString();
				String qtyinsert=editqty.getText().toString();
				String priceinsert=editprice.getText().toString();
				if(editname.getText().toString().length()!=0&&editprice.getText().toString().length()!=0&&editqty.getText().toString().length()!=0){
					
				
					int insertqty=new Integer(qtyinsert).intValue();
				
					boolean ck=dbadapter.checkName(nameinsert);
				
					dbadapter.buyinsert(nameinsert, qtyinsert,priceinsert);
					if(ck==false){
					dbadapter.sfinsert(nameinsert, qtyinsert);
					dbadapter.close();
					f1.load();
					editname.setText("");
					editprice.setText("");
					editqty.setText("");
					}else{
					
					dbadapter.addQty(nameinsert, insertqty);
				
					f1.load();
					editname.setText("");
					editprice.setText("");
					editqty.setText("");
				    }
				
			     }else {
			    	// editname.setErrorEnabled(false);
			    	 editname.setError(null);
			    	 editprice.setError(null);
			    	 editqty.setError(null);

			    	 
//			    	 AlertDialog.Builder alertDlg = new AlertDialog.Builder(getActivity());
//			    	 alertDlg.setTitle("ERRO");
//			    	 alertDlg.setMessage("There is error in your input");
//			    	 alertDlg.setPositiveButton("OK", null);
//			    	 alertDlg.show();
			     }
				// TODO Auto-generated method stub
				
			}
		});
		 
		 Button qrbutton=(Button)getActivity().findViewById(R.id.buttonqr);
		 qrbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				IntentIntegrator integrator=new IntentIntegrator(getActivity());
				integrator.setPrompt("Scan a barcode");
				integrator.setBeepEnabled(false);
				integrator.setOrientationLocked(true);
				integrator.initiateScan();

				// TODO Auto-generated method stub
				
			}
		});
		
	}


	
}