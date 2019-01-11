package com.example.michel_desktop.mobiledev.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.michel_desktop.mobiledev.R;
import com.example.michel_desktop.mobiledev.models.KeyModel;
import com.example.michel_desktop.mobiledev.vieuwModel.KeyViewModel;

import java.util.ArrayList;
import java.util.List;

public class KeysSettingActivity extends AppCompatActivity {
    private Button saveButton;
    private TextInputEditText apiKey;
    private TextInputEditText apiSecretKey;
    private boolean keyModelExcits;
    private List<KeyModel> listKey = new ArrayList<>();
    private KeyViewModel keyViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        apiKey = findViewById(R.id.api_key_input_text);
        apiSecretKey = findViewById(R.id.api_secret_key_input_text);

        //observibal list
        keyViewModel = new KeyViewModel(getApplicationContext());
        keyViewModel.getAllKey().observe(this, new Observer<List<KeyModel>>() {

            @Override
            public void onChanged(@Nullable List<KeyModel> reminders) {
				System.out.println(reminders.size() +" onchage");
				listKey = reminders;
				System.out.println(listKey.size() +" onchage_checl");

				//laat api settings als er een object bestaat
				loadApiSettings(reminders);
            }
        });

        //button
        saveButton = findViewById(R.id.save_id);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(keyModelExcits){
                    KeyModel keyModel = listKey.get(0);
                    keyModel.setApiKey(apiKey.getText().toString());
                    keyModel.setSecretKey(apiSecretKey.getText().toString());
                    keyViewModel.update(keyModel);
                } else {
                    KeyModel keyModel = new KeyModel(apiKey.getText().toString(),
                            apiSecretKey.getText().toString());
                    keyViewModel.insert(keyModel);
                }

                //stop de intent
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadApiSettings(List<KeyModel> listKey){
		System.out.println("list size "+listKey.size());
        if(listKey.size() !=0) {
            KeyModel keyModel = this.listKey.get(0);
            if (keyModel != null) {
                apiKey.setText(keyModel.getApiKey());
                apiSecretKey.setText(keyModel.getSecretKey());

                this.keyModelExcits = true;
            } else {
                this.keyModelExcits = false;
            }
        } else {
			this.keyModelExcits = false;
		}
    }

}
