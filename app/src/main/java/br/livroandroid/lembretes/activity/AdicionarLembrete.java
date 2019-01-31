package br.livroandroid.lembretes.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.livroandroid.lembretes.R;
import br.livroandroid.lembretes.entities.Lembrete;
import br.livroandroid.lembretes.util.CodigoValor;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdicionarLembrete extends BaseActivity {

    private EditText titulo, conteudo;
    private Spinner nivel_importancia;
    private Button salvar;
    private ArrayAdapter<String> adapter;
    private CircleImageView imageView;
    List<String> dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lembrete);

        setUpToolbar();

        titulo = findViewById(R.id.titulo);
        conteudo = findViewById(R.id.conteudo);
        nivel_importancia = findViewById(R.id.spinner);
        salvar = findViewById(R.id.salvar);
        imageView = findViewById(R.id.imageView);


        dados = new ArrayList<>();

        for (Map.Entry<String, Integer> atual : CodigoValor.getInstance().getPalavra_codigo().entrySet()) {
            dados.add(atual.getKey());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dados);

        nivel_importancia.setAdapter(adapter);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lembrete l = new Lembrete("Um bonito titulo", "aa", 1, 1);
                Intent i = new Intent();
                i.putExtra("lembrete", l);

                setResult(RESULT_OK, i);
                finish();
            }
        });

        nivel_importancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setColor(dados.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setColor(String key) {
        imageView.setBackgroundResource(CodigoValor.getInstance().getPalavra_codigo().get(key));
    }


}
