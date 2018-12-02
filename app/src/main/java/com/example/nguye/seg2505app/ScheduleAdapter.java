package com.example.nguye.seg2505app;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.util.ArrayList;
import java.util.List;

// From https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//  and https://developer.android.com/guide/topics/ui/layout/recyclerview#java
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<TimeNode> mSchedule;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ScheduleAdapter(Context context, ArrayList<TimeNode> schedule) {
        this.mInflater = LayoutInflater.from(context);
        this.mSchedule = schedule;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_timeslot, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String timeSlotStart = FormatValue.minToTimeString(mSchedule.get(position).getTime());
        String timeSlotEnd = FormatValue.minToTimeString(mSchedule.get(position).getNext().getTime());
        ScheduleState avail = mSchedule.get(position).getState();
//        TextView tvTimeSlotStart = (TextView) holder.viewGroup.getChildAt(0);
//        TextView tvTimeSlotEnd = (TextView) holder.viewGroup.getChildAt(1);
//        TextView tvAvail = (TextView) holder.viewGroup.getChildAt(2);
        TextView tvTimeSlotStart = holder.viewGroup.findViewById(R.id.timeSlotStart);
        TextView tvTimeSlotEnd = holder.viewGroup.findViewById(R.id.timeSlotEnd);
        TextView tvAvail = holder.viewGroup.findViewById(R.id.availability);
        switch (avail) {
            case AVAILABLE:
                holder.viewGroup.setBackgroundColor(ContextCompat.getColor(holder.viewGroup.getContext(), R.color.colorAvailableFill));
                break;
            case UNAVAILABLE:
                holder.viewGroup.setBackgroundColor(ContextCompat.getColor(holder.viewGroup.getContext(), R.color.colorUnavailableFill));
                break;
            case BOOKED:
                holder.viewGroup.setBackgroundColor(ContextCompat.getColor(holder.viewGroup.getContext(), R.color.colorBookedFill));
                break;
            default:
                break;
        }
        tvTimeSlotStart.setText(timeSlotStart);
        tvTimeSlotEnd.setText(timeSlotEnd);
        tvAvail.setText(mSchedule.get(position).getState().name());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mSchedule.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewGroup viewGroup;

        ViewHolder(View itemView) {
            super(itemView);
            viewGroup = itemView.findViewById(R.id.row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}