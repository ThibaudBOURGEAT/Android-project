package tbo.ynov.com.projetandroidtbo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    public ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(genericOnClickListener);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener() { /*Preparation du click*/
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.return_button: /*Si on appuie sur le bouton valider*/
                    showMenuActivity();
                    break;
            }
        }
    };

    public void showMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
