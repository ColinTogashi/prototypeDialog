package edu.ucsd.fccr.prototypedialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class changeVariableDialogFragment extends DialogFragment{
		
	TextView variableNameTextView;
	TextView variableMaxTextView;
	TextView variableMinTextView;
	EditText variableValueEditText;
	SeekBar variableValueSeekBar;
	//public String[] nameArray = ((MainActivity)getActivity()).nameArray;
	//public int index = ((MainActivity)getActivity()).index;
	
	public String[] nameArray = {"x1","x2","x3","x4"};
	public double[] valueArray = {1,2,3,4};
	public double[] maxArray = {1,1,1,1};
	public double[] minArray = {0,0,0,0};
	public int index = 1;
	
	private double value;
	private double newValue;
	private double min;
	private double max;
	private int progress = (int) Math.round((value-min)*100/(max-min));
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        
        builder.setView(dialogView)
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               })
               .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               });
        
		value = valueArray[index];
		newValue = value;
		min = minArray[index];
		max = maxArray[index];
		
        variableNameTextView = (TextView) dialogView.findViewById(R.id.variableNameTextView);
        variableMaxTextView = (TextView) dialogView.findViewById(R.id.variableMaxTextView);
        variableMinTextView = (TextView) dialogView.findViewById(R.id.variableMinTextView);
        variableValueEditText = (EditText) dialogView.findViewById(R.id.variableValueEditText);
        variableValueSeekBar = (SeekBar) dialogView.findViewById(R.id.variableValueSeekBar);
        
        variableNameTextView.setText(nameArray[index]);
        variableMaxTextView.setText("Max Value : " + Double.toString(maxArray[index]));
        variableMinTextView.setText("Min Value : " + Double.toString(minArray[index]));
		variableValueEditText.setText(Double.toString(newValue));
		variableValueSeekBar.setMax(100);
		variableValueSeekBar.setProgress(progress);
		variableValueSeekBar.setEnabled(true);
		
		variableValueSeekBar.setOnSeekBarChangeListener(variableValueSeekBarListener);
		variableValueEditText.addTextChangedListener(variableValueEditTextListener);
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
    
	private OnSeekBarChangeListener variableValueSeekBarListener = new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			
			newValue = (variableValueSeekBar.getProgress())*0.01*(max-min) + min;
			variableValueEditText.setText(Double.toString(newValue));
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private TextWatcher variableValueEditTextListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			
			try{
		
				newValue = Double.parseDouble(arg0.toString());		
				
			}
			catch(NumberFormatException e) {
				
				newValue = value;
				
			}
		}
	};
    

}
