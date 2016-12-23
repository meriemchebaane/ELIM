package com.example.chebaane.elim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
    public void connecter(View v){
        Intent intent = new Intent(this, MainActivity.class);
        Server server = new Server();
        Thread t = new Thread(server);
        t.start();
        server.setMessage("S'identifier.");
        server.setReady(true);
        startActivity(intent);
    }
}
