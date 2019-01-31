package br.livroandroid.lembretes.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.livroandroid.lembretes.R;

public class BaseActivity extends AppCompatActivity {

    protected void setUpToolbar() {
        Toolbar t = findViewById(R.id.toolbar);

        if (t != null) {
            setSupportActionBar(t);
        }
    }
}
