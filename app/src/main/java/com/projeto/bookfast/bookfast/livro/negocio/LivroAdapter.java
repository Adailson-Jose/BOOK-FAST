package com.projeto.bookfast.bookfast.livro.negocio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    Context contexto;
    int id;
    ArrayList<Livro> elementos;

    public LivroAdapter(Context context, int id, ArrayList<Livro> elementos) {
        super(context, id, elementos);
        this.contexto = context;
        this.elementos = elementos;
        this.id = id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Livro livro;
        ImageView foto;
        TextView nome;
        TextView isbn;
        Bitmap raw;
        byte[] fotoArray;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(id, parent, false);
        }
        nome = (TextView) view.findViewById(R.id.textViewNomeLivro);
        isbn = (TextView) view.findViewById(R.id.textViewIsbn);
        foto = (ImageView) view.findViewById(R.id.imageView);
        livro = elementos.get(position);
        nome.setText(livro.getNome());
        isbn.setText(String.valueOf(livro.getIsbn()));
        fotoArray = livro.getFotoLivro();

        if (fotoArray != null) {
            raw = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            foto.setImageBitmap(raw);
        }

        return view;
    }


}
