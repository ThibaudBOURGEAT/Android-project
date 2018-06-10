package tbo.ynov.com.projetandroidtbo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import tbo.ynov.com.projetandroidtbo.Services.AuthenticationService;

public class MainActivity extends AppCompatActivity {

    private TextView login;
    private TextView password;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Identification");


        login = findViewById(R.id.login); /*On reprend l'ID de la zone text du login*/
        password = findViewById(R.id.password);/*On reprend l'ID de la zone text du password*/

        Button confirm = findViewById(R.id.btn_valider);/*On reprend l'ID du bouton valider*/
        confirm.setOnClickListener(genericOnClickListener);

        Button reset = findViewById(R.id.btn_reset);/*On reprend l'ID du bouton reset*/
        reset.setOnClickListener(genericOnClickListener);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.checkBox); /*On reprend l'ID de la checkbox*/
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) { /*Si jamais la checkbox est validé, recupère l'id des champs a enregistré*/
            login.setText(loginPreferences.getString("login", ""));
            password.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
        Log.i("onCreate", "onCreate");
    }

    private void authenticateUser(){
        AuthenticationService auth = new AuthenticationService(MainActivity.this, login.getText().toString(), password.getText().toString());
        auth.authenticate();
    }

    public void showMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener() { /*Preparation du click*/
        public void onClick(final View v) {

            switch (v.getId()) {
                case R.id.btn_valider: /*Si on appuie sur le bouton valider*/
                    authenticateUser();
                    if (TextUtils.isEmpty(login.getText().toString()))
                    {
                        login.setError("Login obligatoire");
                    }
                    else if (TextUtils.isEmpty(password.getText().toString()))
                        password.setError("Mot de passe obligatoire");
                    break;
                case R.id.btn_reset: /*Si on appuie sur le bouton reset*/
                    login.setText(null);
                    password.setText(null);
                    break;
            }
        }
    };
}
