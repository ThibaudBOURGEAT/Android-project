package tbo.ynov.com.projetandroidtbo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import tbo.ynov.com.projetandroidtbo.Interfaces.IVolleyCallback;
import tbo.ynov.com.projetandroidtbo.Services.SchoolService;

public class ListActivity extends AppCompatActivity {

    public ImageButton returnButton;
    private SchoolService schoolService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        returnButton = findViewById(R.id.return_button);
        returnButton.setOnClickListener(genericOnClickListener);

        schoolService = new SchoolService();
        schoolService.showAllSchool(this);
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
