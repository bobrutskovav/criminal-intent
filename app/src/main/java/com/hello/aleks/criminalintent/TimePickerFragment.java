package com.hello.aleks.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Aleks on 12.06.2017.
 */

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;

    private static final String ARG_TIME = "time";
    public static final String EXTRA_TIME = "com.hello.aleks.criminalintent.timepickerfragment.time";

    public static TimePickerFragment newInstance(Date time) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TIME, time);
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(arguments);
        return timePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date time = (Date) getArguments().get(ARG_TIME);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_timepicker);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        mTimePicker.setIs24HourView(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.dialog_time_crime).setView(v).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour = 0;
                int minute = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = mTimePicker.getHour();
                    minute = mTimePicker.getMinute();
                } else {
                    hour = mTimePicker.getCurrentHour();
                    minute = mTimePicker.getCurrentMinute();
                }

                Calendar calendar1 = new GregorianCalendar();
                calendar1.set(Calendar.HOUR_OF_DAY, hour);
                calendar1.set(Calendar.MINUTE, minute);
                Date time = calendar1.getTime();
                sendResult(Activity.RESULT_OK, time);
            }
        }).create();
    }

    private void sendResult(int requestCode, Date time) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);
        getTargetFragment().onActivityResult(getTargetRequestCode(), requestCode, intent);
    }
}
