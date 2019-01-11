package com.example.michel_desktop.mobiledev.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michel_desktop.mobiledev.R;
import com.example.michel_desktop.mobiledev.securty.Hmacsha256;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private TextView textVieuwWachtwoord;
    private TextInputEditText input_field_wachtwoord;
    private final String HMAC_PASSWORD_STRING =
            "002986ab81163b3108737839f5bc2bf996e5686627b3e20a1be4f661c57540ad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_in_activity);
        input_field_wachtwoord = findViewById(R.id.wachtwoord_id);
        textVieuwWachtwoord = findViewById(R.id.wachtwoord_fout);
        button = findViewById(R.id.log_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hmacsha256 hmacSha256 = new Hmacsha256();
                String wachtwoord = input_field_wachtwoord.getText().toString();
                String key = hmacSha256.generateHashWithHmac256(wachtwoord, wachtwoord);

                if(key.equals(HMAC_PASSWORD_STRING)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    textVieuwWachtwoord.setText("Wachtwoord is fout");
                }
            }
        });
    }
}
