package com.example.chebaane.elim;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment that displays "Monday".
 */
public class ListFragment extends Fragment {

    private List<Wifi> listwifi = new ArrayList<Wifi>();
    private WifiManager wifiManager;
    private List<ScanResult> wifiList;
    ArrayAdapter<String> adapter;
    private ListView list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        detectWifi();
        ArrayAdapter<Wifi> adapter = new MyListAdapter(getActivity());
        list = (ListView) rootView.findViewById(R.id.list_Wifi);
        list.setAdapter(adapter);

        return rootView;
    }

    public void detectWifi() {

        this.wifiManager = (WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
        this.wifiManager.startScan();
        this.wifiList = this.wifiManager.getScanResults();


        Log.d("TAG", wifiList.toString());

        for(ScanResult scanResult : wifiList){
            boolean exist = false;
            Wifi Oldwifi = null;
            int i=0;
            boolean superieur =false;
            while (!exist){
                for(Wifi wifi: listwifi){
                    if((wifi.getSecurity().equals(scanResult.capabilities))&&(wifi.getSSID().equals(scanResult.SSID))){
                        exist=true;
                        if(Integer.parseInt(wifi.getLevel()) < scanResult.level){
                            Oldwifi=wifi;
                            superieur=true;
                            break;
                        }}
                }
                if(superieur){
                    this.listwifi.remove(Oldwifi);
                    this.listwifi.add(new Wifi(scanResult.SSID, scanResult.capabilities, String.valueOf(scanResult.level)) );
                    Log.d("SSID " + listwifi.size(), scanResult.SSID);
                    Log.d("Capabilities " + listwifi.size(), scanResult.capabilities);
                    Log.d("Level " + listwifi.size(), String.valueOf(scanResult.level));
                }
                else if (!exist){
                    this.listwifi.add(new Wifi(scanResult.SSID, scanResult.capabilities, String.valueOf(scanResult.level)) );
                    Log.d("SSID " + listwifi.size(), scanResult.SSID);
                    //   Log.d("Capabilities " + listwifi.size(), scanResult.capabilities);
                    //     Log.d("Level " + listwifi.size(), String.valueOf(scanResult.level));

                }
            }

        }
    }

    public class MyListAdapter extends ArrayAdapter<Wifi> {

        public MyListAdapter(Context context) {

            super(context, R.layout.item_view, listwifi);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView=convertView;
            if(itemView== null){
//                itemView=getActivity().getLayoutInflater().inflate(R.layout.item_view,parent,false);
                itemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_view, parent, false);
            }
            // Find the wifi to work with.

            Wifi wifi =listwifi.get(position);

            // Fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_imageView);
            if (wifi.getSecurity().contains("WPA") || wifi.getSecurity().contains("WPA2")){
                if (Integer.parseInt(wifi.getLevel())<-90){
                    imageView.setImageResource(R.drawable.secured_low_signal);}
                else if(Integer.parseInt(wifi.getLevel())>-50){
                    imageView.setImageResource(R.drawable.secured_high_signal);}

                else{
                    imageView.setImageResource(R.drawable.secured_medium_signal);
                }
            }else{
                Log.d("Level " + listwifi.size(), String.valueOf(Integer.parseInt(wifi.getLevel())));
                if (Integer.parseInt(wifi.getLevel())<-90){
                    imageView.setImageResource(R.drawable.free_low_signal);}
                else if(Integer.parseInt(wifi.getLevel())>-50){
                    imageView.setImageResource(R.drawable.free_high_signal);}

                else{
                    imageView.setImageResource(R.drawable.free_medium_signal);
                }
            }

            TextView SSID = (TextView) itemView.findViewById(R.id.item_SSID);
            SSID.setText(wifi.getSSID());
            final RatingBar rate =(RatingBar)itemView.findViewById(R.id.ratingBar);
            rate.setRating(2f);

            rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    rate.setRating(rating);
                    rate.invalidate();
                    Log.d("Rating", String.valueOf(rating));
                }
            });
            return itemView;
//            return super.getView(position,convertView,parent);
        }

    }

}
