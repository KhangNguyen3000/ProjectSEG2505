package com.example.nguye.seg2505app;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.FormatValue;
import com.example.nguye.seg2505app.Utilities.Validation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Booking extends AppCompatActivity {
// From https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//  and https://developer.android.com/guide/topics/ui/layout/recyclerview#java

    private int providerID;
    private String selectedDate;
    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private ScheduleAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    // TODO Make sure appointments are registered and displayed properly
    // TODO when generating, it only seems to show the availabilities of the same day for every day in the current effective defaultSchedule
    // TODO it probably has something to do with a function of DailySchedule (generate or toArrayList)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        Bundle extras = getIntent().getExtras();
        providerID = extras.getInt("providerID");

        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.daily_schedule);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        // Set the minimum date to today
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dfMinDate = new SimpleDateFormat("MM/dd/yyyy");
        selectedDate = dfMinDate.format(calendar.getTime());

        // Store today's date in selectedDate
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = df.format(calendar.getTime());

        // When a day is selected, store that value in selectedDate
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Make sure the format is "yyyy-MM-dd"
                selectedDate = FormatValue.dateYMD(year + "-" + month + "-" + dayOfMonth);
                // Generate the corresponding dailySchedule
                showDailySchedule();
            }
        });

        // TODO show modal window when a timeSlot is selected.
    }

    public void showDailySchedule() {
        DailySchedule dailySchedule = new DailySchedule().generate(this, providerID, selectedDate);
        ArrayList<TimeNode> arrayDailySchedule = dailySchedule.toArrayList();
        rvAdapter = new ScheduleAdapter(this, arrayDailySchedule);

        setOnClickListeners(rvAdapter);

//        rvAdapter.setClickListener(new ScheduleAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                TextView tvTimeSlotStart = view.findViewById(R.id.timeSlotStart);
//                TextView tvTimeSlotEnd = view.findViewById(R.id.timeSlotEnd);
//                TextView tvAvail = view.findViewById(R.id.availability);
//                // Set the default times in the dialog using the times above
//
//                // TODO prompt a modal window for appointement
//                // only allow to click on AVAILABLE slots
//                final Dialog dialog = new Dialog(view.getContext());
//                dialog.setContentView(R.layout.appointment);
//                final EditText etTimeSlotStart = dialog.findViewById(R.id.timeSlotStart);
//                final EditText etTimeSlotEnd = dialog.findViewById(R.id.timeSlotEnd);
//                Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
//                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
//
//                // Bind onClick event to the timeSlotStart field
//                etTimeSlotStart.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        DateTimePicker.showTimePicker(v);
//                    }
//                });
//
//                // Bind onClick event to the timeSlotEnd field
//                etTimeSlotEnd.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        DateTimePicker.showTimePicker(v);
//                    }
//                });
//
//                // Bind onClick event to the confirm button
//                btnConfirm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO add the appointment
//                        CustomSchedule appointment = new CustomSchedule(providerID,
//                                selectedDate,
//                                FormatValue.timeStringToMin(etTimeSlotStart.getText().toString()),
//                                FormatValue.timeStringToMin(etTimeSlotEnd.getText().toString()),
//                                ScheduleState.BOOKED);
//
//                        // TODO finish all activities after the WelcomePage
//                        dialog.dismiss();
//                        finish();
//                    }
//                });
//
//                // Bind onClick event to the cancel field
//                btnCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // TODO just close the dialog
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();
//            }
//        });
        recyclerView.setAdapter(rvAdapter);
    }

    // Bind an onClick event to each item in the RecyclerView everytime a new one is generated
    //  (i.e. a new date is selected)
    public void setOnClickListeners(ScheduleAdapter adapter) {
        adapter.setClickListener(new ScheduleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView tvAvail = view.findViewById(R.id.availability);
                // Only prompt a booking window if the clicked time slot is AVAILABLE
                if (tvAvail.getText().toString().equals(ScheduleState.AVAILABLE.name())) {
                    TextView tvTimeSlotStart = view.findViewById(R.id.timeSlotStart);
                    TextView tvTimeSlotEnd = view.findViewById(R.id.timeSlotEnd);

                    final Dialog dialog = new Dialog(view.getContext());
                    dialog.setContentView(R.layout.appointment);
                    TextView tvDate = dialog.findViewById(R.id.txt_date);
                    final EditText etTimeSlotStart = dialog.findViewById(R.id.input_from);
                    final EditText etTimeSlotEnd = dialog.findViewById(R.id.input_to);
                    Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
                    Button btnCancel = dialog.findViewById(R.id.btn_cancel);

                    // Set the initial values of the prompt window
                    tvDate.setText(selectedDate);
                    etTimeSlotStart.setText(tvTimeSlotStart.getText().toString());
                    etTimeSlotEnd.setText(tvTimeSlotEnd.getText().toString());

                    // Bind onClick event to the timeSlotStart field
                    etTimeSlotStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DateTimePicker.showTimePicker(v);
                        }
                    });

                    // Bind onClick event to the timeSlotEnd field
                    etTimeSlotEnd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DateTimePicker.showTimePicker(v);
                        }
                    });

                    // Bind onClick event to the confirm button
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ViewGroup layout = dialog.findViewById(R.id.llh_times);
                            // Validate the fields
                            if (Validation.validateAll(layout)) {
                                CustomSchedule appointment = new CustomSchedule(providerID,
                                        selectedDate,
                                        FormatValue.timeStringToMin(etTimeSlotStart.getText().toString()),
                                        FormatValue.timeStringToMin(etTimeSlotEnd.getText().toString()),
                                        ScheduleState.BOOKED);
                                // TODO make sure this is fine (i.e. the provider does not have to confirm)
                                // Add the appointment as a CustomSchedule
                                appointment.add(v.getContext());
                                // TODO finish all activities after the WelcomePage
                                dialog.dismiss();
                                finish();
                            }
                        }
                    });

                    // Bind onClick event to the cancel field
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Simply close the dialog window
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    // If the clicked time slot is not AVAILABLE
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Unavailable");
                    builder.setMessage("The selected time slot is unavailable.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        TextView tvTimeSlotStart = view.findViewById(R.id.timeSlotStart);
//        TextView tvTimeSlotEnd = view.findViewById(R.id.timeSlotEnd);
//        TextView tvAvail = view.findViewById(R.id.availability);
//        // Set the default times in the dialog using the times above
//
//        // TODO prompt a modal window for appointement
//        // only allow to click on AVAILABLE slots
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.appointment);
//        final EditText etTimeSlotStart = dialog.findViewById(R.id.timeSlotStart);
//        final EditText etTimeSlotEnd = dialog.findViewById(R.id.timeSlotEnd);
//        Button btnConfirm = dialog.findViewById(R.id.btn_confirm);
//        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
//
//        // Bind onClick event to the timeSlotStart field
//        etTimeSlotStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DateTimePicker.showTimePicker(v);
//            }
//        });
//
//        // Bind onClick event to the timeSlotEnd field
//        etTimeSlotEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DateTimePicker.showTimePicker(v);
//            }
//        });
//
//        // Bind onClick event to the confirm button
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO add the appointment
//                CustomSchedule appointment = new CustomSchedule(providerID,
//                        selectedDate,
//                        FormatValue.timeStringToMin(etTimeSlotStart.getText().toString()),
//                        FormatValue.timeStringToMin(etTimeSlotEnd.getText().toString()),
//                        ScheduleState.BOOKED);
//
//                // TODO finish all activities after the WelcomePage
//                dialog.dismiss();
//                finish();
//            }
//        });
//
//        // Bind onClick event to the cancel field
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO just close the dialog
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();







//        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(R.layout.appointment);
//        builder.setTitle("TITLETITLE");
//        builder.setMessage("REQUESTING APPOINTMENT");
//
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // TODO add the appointment
//                CustomSchedule appointment = new CustomSchedule(providerID,
//                        selectedDate,
//                        R);
//
//                // TODO finish all activities after the WelcomePage
//                finish();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
}
