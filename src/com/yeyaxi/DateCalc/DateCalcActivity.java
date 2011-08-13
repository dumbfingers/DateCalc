package com.yeyaxi.DateCalc;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
/**
 * DateCalc - A simple Date Calculator
 * @author Yaxi Ye
 * icon Design thanks to Deziner Folio (http://www.dezinerfolio.com)
 * icon link: http://findicons.com/icon/73173/calendar?id=73227#
 *
 */
public class DateCalcActivity extends Activity {
    /** Called when the activity is first created. */
	DatePicker startDate;
	DatePicker endDate;
	TextView result;
	Button calculate;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startDate = (DatePicker) findViewById(R.id.datePicker1);
        endDate = (DatePicker) findViewById(R.id.datePicker2);
        result = (TextView) findViewById(R.id.textView4);
        calculate = (Button) findViewById(R.id.button1);
    }
    public void onStart() {
    	super.onStart();
    	calculate.setOnClickListener(clickListener);
    }
    
    private void calcDate () {
    	Calendar firstDate = Calendar.getInstance();
    	Calendar secondDate = Calendar.getInstance();
    	firstDate.clear();
    	firstDate.set(Calendar.YEAR, startDate.getYear());
    	firstDate.set(Calendar.MONTH, startDate.getMonth());
    	firstDate.set(Calendar.DAY_OF_MONTH, startDate.getDayOfMonth());
    	secondDate.clear();
    	secondDate.set(Calendar.YEAR, endDate.getYear());
    	secondDate.set(Calendar.MONTH, endDate.getMonth());
    	secondDate.set(Calendar.DAY_OF_MONTH, endDate.getDayOfMonth());
    	//Judge which date is the earlier
    	if (firstDate.after(secondDate)) {
    		Calendar swap = firstDate;
    		firstDate = secondDate;
    		secondDate = swap;
    	}
    	int days = secondDate.get(Calendar.DAY_OF_YEAR) - firstDate.get(Calendar.DAY_OF_YEAR);
    	//Judge if the two dates are under the same year
    	int year = secondDate.get(Calendar.YEAR);
    	if (firstDate.get(Calendar.YEAR) != year) {
    		do {
    			days += firstDate.getActualMaximum(Calendar.DAY_OF_YEAR);
    			firstDate.add(Calendar.YEAR, 1);
    		}
    		while (firstDate.get(Calendar.YEAR) != year);
    	}
    	result.setText(Integer.toString(days));
    	}

    
    OnClickListener clickListener = new OnClickListener () {
		@Override
		public void onClick(View v) {
			if (v == calculate) {
				calcDate();
			}
		}
    };
}