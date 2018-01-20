package com.crystyanoalmeida.crudretrofit;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.crystyanoalmeida.crudretrofit.Helper.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UpdateActivity extends AppCompatActivity {

    String BASE_URL = "http://app-android-cc.umbler.net";

    EditText etName, etAge, etPhone, etMail;

    BootstrapButton btnSave, btnCancel;

    String id, name, age, phone, mail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //EditTexts
        etName = (EditText) findViewById(R.id.etNameUpdate);
        etAge = (EditText) findViewById(R.id.etAgeUpdate);
        etPhone = (EditText) findViewById(R.id.etPhoneUpdate);
        etMail = (EditText) findViewById(R.id.etMailUpdate);

        //Bootstrap Buttons
        btnSave = (BootstrapButton) findViewById(R.id.btnSaveUpdate);
        btnCancel = (BootstrapButton) findViewById(R.id.btnCancel);

        View view = findViewById(R.id.contentUpdate);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        name = i.getStringExtra("name");
        age = i.getStringExtra("age");
        phone = i.getStringExtra("phone");
        mail = i.getStringExtra("mail");

        //Setting Data
        etName.setText(name);
        etAge.setText(age);
        etPhone.setText(phone);
        etMail.setText(mail);

        //OnClicks
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_data(id);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Coming back to ListActivity
                Intent i = new Intent(UpdateActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
    }

    public void update_data(String id) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL) //Setting the Root URL
                .build();

        AppConfig.update api = adapter.create(AppConfig.update.class);

        api.updateData(
                id,
                etName.getText().toString(),
                etAge.getText().toString(),
                etPhone.getText().toString(),
                etMail.getText().toString(),
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            String resp;
                            resp = reader.readLine();
                            Log.d("success", "" + resp);

                            JSONObject jObj = new JSONObject(resp);
                            int success = jObj.getInt("success");

                            if (success == 1) {
                                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ListActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Update Failed", Toast.LENGTH_SHORT).show();
                            }

                        } catch (IOException e) {
                            Log.d("Exception", e.toString());
                        } catch (JSONException e) {
                            Log.d("JsonException", e.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(UpdateActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}