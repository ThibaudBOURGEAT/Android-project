package tbo.ynov.com.projetandroidtbo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
        ImageButton list = findViewById(R.id.btn_list);
        list.setOnClickListener(genericOnClickListener);
        ImageButton add = findViewById(R.id.btn_add);
        add.setOnClickListener(genericOnClickListener);
        ImageButton settings = findViewById(R.id.btn_settings);
        settings.setOnClickListener(genericOnClickListener);
        ImageButton map = findViewById(R.id.btn_map);
        map.setOnClickListener(genericOnClickListener);
    }

    final View.OnClickListener genericOnClickListener = new View.OnClickListener() {
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.btn_list: /*Si on appuie sur le bouton list*/
                    intent = new Intent(MenuActivity.this, ListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_add:/*Si on appuie sur le bouton test*/
                    intent = new Intent(MenuActivity.this, CreateActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_settings:
                    intent = new Intent(MenuActivity.this, SettingsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_map:
                    intent = new Intent(MenuActivity.this, MapActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
