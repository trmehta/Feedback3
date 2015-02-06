package com.example.feedback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity2 extends Activity {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        final EditText email1 = (EditText)findViewById(R.id.editText1);
        final EditText description1 = (EditText)findViewById(R.id.editText2);


		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.category_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);


        //to get fields dynamically
        boolean contact_field=false, subcategory_field=true, star_field=true;
        LinearLayout rl = (LinearLayout) findViewById(R.id.rl2);

        if(contact_field)
        {
            EditText contactEditText = new EditText(this);
            contactEditText.setHint("contact");
            rl.addView(contactEditText);
        }

        if(subcategory_field)
        {
            Spinner dynamicSpinner = new Spinner(this);
            ArrayList<String> options = new ArrayList<String>();
            options.add("January");
            options.add("February");
            ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options);
            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dynamicSpinner.setAdapter(adp);
            rl.addView(dynamicSpinner);
        }

        if(star_field)
        {
            RatingBar rb = new RatingBar(this);
            rb.setNumStars(5);
            //rb.setVisibility(View.VISIBLE);
            rb.setStepSize((float) 1.0);
            rb.setRating((float) 1.0);
            rb.setScaleX((float) 0.5);
            rb.setScaleY((float) 0.5);
            rl.addView(rb);

        }
        final EditText emailValidate = (EditText)findViewById(R.id.editText1);

        emailValidate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String email = emailValidate.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();

                }
            }
        });

        /*emailValidate .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String email = emailValidate.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern) && s.length() > 0)
                {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                    // or
                    //textView.setText("valid email");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or
                    //textView.setText("invalid email");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });*/

        Button edit = (Button) findViewById(R.id.button2);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), Activity3.class);
                startActivityForResult(myIntent, 0);

            }
        });

        Button submit = (Button) findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                String Email = email1.getText().toString();
                String Description = description1.getText().toString();
                String Category = spinner.getSelectedItem().toString();
                Intent myIntent = new Intent(view.getContext(), Activity4.class);
                myIntent.putExtra("email", Email);
                myIntent.putExtra("description", Description);
                myIntent.putExtra("category", Category);
                //myIntent.putExtra("image",bmp);
                startActivity(myIntent);
                finish();

            }});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
