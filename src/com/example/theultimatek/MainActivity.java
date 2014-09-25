package com.example.theultimatek;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderer onRender=new renderer(this);
        
        setContentView(onRender);
    }
    

    
    
}
