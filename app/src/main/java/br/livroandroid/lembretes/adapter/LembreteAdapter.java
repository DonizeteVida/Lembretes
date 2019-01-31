package br.livroandroid.lembretes.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.livroandroid.lembretes.R;
import br.livroandroid.lembretes.entities.Lembrete;

public class LembreteAdapter extends RecyclerView.Adapter<LembreteAdapter.LembreteHolder> {

    private List<Lembrete> lembretes;
    private Context context;
    private LembreteAdapterInterface lembreteAdapterInterface;

    public LembreteAdapter(List<Lembrete> lembretes, Context context, LembreteAdapterInterface lembreteAdapterInterface) {
        this.lembretes = lembretes;
        this.context = context;
        this.lembreteAdapterInterface = lembreteAdapterInterface;
    }

    @NonNull
    @Override
    public LembreteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.lembrete_adapter, viewGroup, false);
        return new LembreteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final LembreteHolder lembreteHolder, final int i) {
        final Lembrete lembrete = lembretes.get(i);

        lembreteHolder.titulo.setText(lembrete.getTitulo());
        lembreteHolder.titulo.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/bebas.ttf"));
        lembreteHolder.conteudo.setText(lembrete.getConteudo());

        lembreteHolder.ic_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, lembreteHolder.ic_more);
                popupMenu.inflate(R.menu.lembrete_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        lembreteAdapterInterface.onClickMenu(item, lembrete);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lembretes != null ? lembretes.size() : 0;
    }

    public interface LembreteAdapterInterface {
        void onClickMenu(MenuItem item, Lembrete lembrete);
    }

    public void deleteLembrete(Lembrete lembrete) {
        lembretes.remove(lembrete);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 1500);
    }

    public void inserirLembrete(Lembrete lembrete) {
        lembretes.add(lembrete);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 1500);
    }

    class LembreteHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView conteudo;
        ImageView ic_more;

        public LembreteHolder(@NonNull View v) {
            super(v);
            titulo = v.findViewById(R.id.titulo);
            conteudo = v.findViewById(R.id.conteudo);
            ic_more = v.findViewById(R.id.ic_more);
        }


    }


}
