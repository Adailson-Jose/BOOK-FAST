package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.bookfast.bookfast.R;
import com.projeto.bookfast.bookfast.livro.dominio.Livro;

import java.util.ArrayList;

/**
 * Created by oi on 08/08/2017.
 */

public class LivroAdapter extends ArrayAdapter<Livro> {
    private final Context context;
    private final ArrayList<Livro> elementos;

    public LivroAdapter(Context context, ArrayList<Livro> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView nomeLivro = (TextView) rowView.findViewById(R.id.nome);
        TextView isbn = (TextView) rowView.findViewById(R.id.isbn);
        ImageView imagem = (ImageView) rowView.findViewById(R.id.imagem);
        nomeLivro.setText(elementos.get(position).getNome());
        isbn.setText(elementos.get(position).getIsbn().toString());
        imagem.setImageResource(R.drawable.livro1);
        return rowView;
    }
}
