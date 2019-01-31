package br.livroandroid.lembretes.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.livroandroid.lembretes.entities.Lembrete;

public class LembretesDB extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static LembretesDB lembretesDB;


    public static synchronized LembretesDB getInstance(Context context) {
        if (lembretesDB == null) {
            lembretesDB = new LembretesDB(context);
        }

        return lembretesDB;
    }

    private final static String[] chaves = new String[]{"lembrete", "codigo", "titulo", "conteudo", "realizado", "nivel_importancia"};
    private final static String NAME_DB = chaves[0];
    private final static Integer VERSION_DB = 1;

    private LembretesDB(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS lembrete( ");
        sb.append("codigo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ");
        sb.append("titulo TEXT NOT NULL, ");
        sb.append("conteudo TEXT NOT NULL, ");
        sb.append("realizado INTEGER NOT NULL, ");
        sb.append("nivel_importancia INTEGER NOT NULL ");
        sb.append(");");

        db.execSQL(sb.toString());
    }

    public Boolean inserirLembrete(Lembrete lembrete) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(chaves[2], lembrete.getTitulo());
        contentValues.put(chaves[3], lembrete.getConteudo());
        contentValues.put(chaves[4], lembrete.getRealizado());
        contentValues.put(chaves[5], lembrete.getNivel_importancia());

        return db.insert(chaves[0], null, contentValues) > 0;
    }

    public List<Lembrete> encontrarTodos() {
        Cursor c = db.query(chaves[0], null, null, null, null, null, null, null);

        return toList(c);
    }

    private List<Lembrete> toList(Cursor c) {
        List<Lembrete> lembretes = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Lembrete l = new Lembrete();

                l.setCodigo(c.getInt(c.getColumnIndex(chaves[1])));
                l.setTitulo(c.getString(c.getColumnIndex(chaves[2])));
                l.setConteudo(c.getString(c.getColumnIndex(chaves[3])));
                l.setRealizado(c.getInt(c.getColumnIndex(chaves[4])));
                l.setNivel_importancia(c.getInt(c.getColumnIndex(chaves[5])));

                lembretes.add(l);

            } while (c.moveToNext());
        }

        return lembretes;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
