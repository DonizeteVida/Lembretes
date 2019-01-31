package br.livroandroid.lembretes.util;

import java.util.HashMap;

import br.livroandroid.lembretes.R;

public class CodigoValor {

    public static CodigoValor codigoValor;

    public static CodigoValor getInstance() {
        if (codigoValor == null) {
            codigoValor = new CodigoValor();
        }

        return codigoValor;
    }

    private HashMap<String, Integer> palavra_codigo;

    private CodigoValor() {
        palavra_codigo = new HashMap<>();
        palavra_codigo.put("Nada importante", R.color.verde);
        palavra_codigo.put("Ta valendo", R.color.azul);
        palavra_codigo.put("Importantasso", R.color.red);
    }

    public HashMap<String, Integer> getPalavra_codigo() {
        return palavra_codigo;
    }

    public void setPalavra_codigo(HashMap<String, Integer> palavra_codigo) {
        this.palavra_codigo = palavra_codigo;
    }
}
