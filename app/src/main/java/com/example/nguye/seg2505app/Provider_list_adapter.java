package com.example.nguye.seg2505app;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Provider_list_adapter extends RecyclerView.Adapter<Provider_list_adapter.ViewHolder> {
    private ArrayList<Account> mProvider;
    private LayoutInflater mInflater;
    private ScheduleAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    Provider_list_adapter(Context context, ArrayList<Account> provider) {
        this.mInflater = LayoutInflater.from(context);
        this.mProvider = provider;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.visuel_search_result, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Account accP = mProvider.get(position);

        String name_p = accP.getFirstName() + " " + accP.getLastName();
        String adress_p = accP.getStreetNumber() + " " + accP.getStreetName() + ", " + accP.getCity() + ", " + accP.getProvince() + ", " + accP.getPostalCode();
        String phoneNumber_p = accP.getPhoneNumber();
        String email_p = accP.getEmail();
        String companyName_p = accP.getCompanyName();
        String license_p = Integer.toString(accP.getLicensed());

        TextView tvName = holder.viewGroup.findViewById(R.id.sr_txt_name);
        TextView tvAddress = holder.viewGroup.findViewById(R.id.sr_txt_adress);
        TextView tvTelephone = holder.viewGroup.findViewById(R.id.sr_txt_num);
        TextView tvEmail = holder.viewGroup.findViewById(R.id.sr_txt_email);
        TextView tvCompany = holder.viewGroup.findViewById(R.id.sr_txt_companyname);
        TextView tvLicense = holder.viewGroup.findViewById(R.id.sr_txt_licensed);

        tvName.setText(name_p);
        tvAddress.setText(adress_p);
        tvTelephone.setText(phoneNumber_p);
        tvEmail.setText(email_p);
        tvCompany.setText(companyName_p);
        if (accP.getLicensed() == 1) {
            tvLicense.setText(license_p);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProvider.size();
    }


    // stores and recycles views as they are scrolled off screen
    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewGroup viewGroup;

        ViewHolder(View itemView) {
            super(itemView);
            viewGroup = itemView.findViewById(R.id.csrtLayout);
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
    void setClickListener(ScheduleAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}