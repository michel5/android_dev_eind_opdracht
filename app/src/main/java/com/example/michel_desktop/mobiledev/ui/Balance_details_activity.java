package com.example.michel_desktop.mobiledev.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.michel_desktop.mobiledev.R;

public class Balance_details_activity extends AppCompatActivity {
	public TextView cointagText;
	public TextView set_balance_text;
	public TextView setAvailable_text;
	private Button back_button;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coin_details);

		cointagText = findViewById(R.id.coin_tag_id);
		set_balance_text = findViewById(R.id.set_balance_id);
		setAvailable_text = findViewById(R.id.set_available_id);

		//haal de data op uit de intent
		Intent intent = getIntent();
		cointagText.setText(intent.getStringExtra("cointag"));
		setAvailable_text.setText(intent.getStringExtra("available"));
		set_balance_text.setText(intent.getStringExtra("balance"));

		back_button = findViewById(R.id.button_id);
		back_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

	}
}
