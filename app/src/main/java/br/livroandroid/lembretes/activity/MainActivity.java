package br.livroandroid.lembretes.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import br.livroandroid.lembretes.R;
import br.livroandroid.lembretes.adapter.LembreteAdapter;
import br.livroandroid.lembretes.entities.Lembrete;
import br.livroandroid.lembretes.sqlite.LembretesDB;

public class MainActivity extends BaseActivity implements LembreteAdapter.LembreteAdapterInterface {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private LembretesDB lembretesDB;
    private LembreteAdapter lembreteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();
        lembretesDB = LembretesDB.getInstance(this);

        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        lembreteAdapter = new LembreteAdapter(lembretesDB.encontrarTodos(), MainActivity.this, MainActivity.this);
        recyclerView.setAdapter(lembreteAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdicionarLembrete.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Lembrete lembrete = (Lembrete) data.getSerializableExtra("lembrete");
                lembreteAdapter.inserirLembrete(lembrete);
            }
        }
    }

    @Override
    public void onClickMenu(MenuItem item, Lembrete lembrete) {
        switch (item.getItemId()) {
            case R.id.excluir_lembrete:
                lembreteAdapter.deleteLembrete(lembrete);
                break;
        }
    }
}
