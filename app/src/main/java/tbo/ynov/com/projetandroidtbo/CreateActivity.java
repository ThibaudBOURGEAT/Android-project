package tbo.ynov.com.projetandroidtbo;

import android.content.Intent;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import tbo.ynov.com.projetandroidtbo.Models.School;
import tbo.ynov.com.projetandroidtbo.Services.AuthenticationService;
import tbo.ynov.com.projetandroidtbo.Services.SchoolService;

public class CreateActivity extends AppCompatActivity {

    private TextView name;
    private TextView address;
    private TextView zip_code;
    private TextView city;
    private TextView opennings;
    private TextView phone;
    private TextView email;
    private TextView latitude;
    private TextView longitude;
    private TextView nbStudent;
    private Spinner spinner;
    public ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setTitle("Création");

        returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(genericOnClickListener);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.school_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button validate = findViewById(R.id.btn_valid);/*On reprend l'ID du bouton valider*/
        Button cancel = findViewById(R.id.btn_cancel);/*On reprend l'ID du bouton valider*/
        validate.setOnClickListener(genericOnClickListener);
        cancel.setOnClickListener(genericOnClickListener);

        name =  findViewById(R.id.name);
        address =  findViewById(R.id.address);
        zip_code =  findViewById(R.id.zip_code);
        city =  findViewById(R.id.city);
        opennings =  findViewById(R.id.opennings);
        phone =  findViewById(R.id.phone);
        email =  findViewById(R.id.email);
        latitude =  findViewById(R.id.latitude);
        longitude =  findViewById(R.id.longitude);
        nbStudent = findViewById(R.id.nb_student);
    }

    public void showMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private void addSchool(){
        School school = new School(
        name.getText().toString(),
        address.getText().toString(),
        zip_code.getText().toString(),
        city.getText().toString(),
        opennings.getText().toString(),
        phone.getText().toString(),
        email.getText().toString(),
        latitude.getText().toString(),
        longitude.getText().toString(),
        nbStudent.getText().toString(),
        spinner.getSelectedItem().toString());
        SchoolService schoolService = new SchoolService();
        schoolService.add(school, this);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener() { /*Preparation du click*/
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.btn_valid:
                    if (TextUtils.isEmpty(name.getText().toString()) &&
                            TextUtils.isEmpty(address.getText().toString()) &&
                            TextUtils.isEmpty(zip_code.getText().toString()) &&
                            TextUtils.isEmpty(city.getText().toString()) &&
                            TextUtils.isEmpty(opennings.getText().toString()) &&
                            TextUtils.isEmpty(phone.getText().toString()) &&
                            TextUtils.isEmpty(email.getText().toString()) &&
                            TextUtils.isEmpty(latitude.getText().toString()) &&
                            TextUtils.isEmpty(longitude.getText().toString()) &&
                            TextUtils.isEmpty(nbStudent.getText().toString()))
                    {
                        Toast.makeText(CreateActivity.this, "Tous les champs sont obligatoires.", Toast.LENGTH_LONG).show();
                    }else{
                        addSchool();
                    }
                    break;
                case R.id.btn_cancel: /*Si on appuie sur le bouton reset*/
                    showMenuActivity();
                    break;
                case R.id.return_button: /*Si on appuie sur le bouton valider*/
                    showMenuActivity();
                    break;
            }
        }
    };
}
