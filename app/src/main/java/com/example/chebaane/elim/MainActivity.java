package com.example.chebaane.elim;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    private List<Wifi> listwifi = new ArrayList<Wifi>();
    private WifiManager wifiManager;
    private List<ScanResult> wifiList;
    ArrayAdapter<String> adapter;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_wifi_activity);
        detectWifi();
        ArrayAdapter <Wifi>adapter = new MyListAdapter();
        ListView List = (ListView) findViewById(R.id.list_Wifi);
        List.setAdapter(adapter);
    }

    public void detectWifi() {

        this.wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
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

    }}

    private class MyListAdapter extends ArrayAdapter<Wifi> {

        public MyListAdapter() {

            super(context, R.layout.item_view, listwifi);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView=convertView;
            if(itemView== null){
                itemView=getLayoutInflater().inflate(R.layout.item_view,parent,false);
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
            RatingBar rate =(RatingBar)itemView.findViewById(R.id.ratingBar);
            rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                }
            });
            return itemView;
           // return super.getView(position,convertView,parent);
        }

        }

    }
