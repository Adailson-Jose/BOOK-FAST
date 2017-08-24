package com.projeto.bookfast.bookfast.aluguel.negocio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A Classe DataLivro foi criada para gerar as datas com os métodos getDataAtual() e getDataDevolucao()
 */

public class DataLivro {

    public static String getDataAtual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDataDevolucao() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = calendar.getTime();
        return dateFormat.format(data);
    }
}
