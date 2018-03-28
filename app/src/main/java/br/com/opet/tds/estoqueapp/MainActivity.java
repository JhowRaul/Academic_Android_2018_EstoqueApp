package br.com.opet.tds.estoqueapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    private EditText editNome;
    private TextView textVencimentoInput;
    private TextView textNome;
    private TextView textVencimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNomeProduto);
        textVencimentoInput = findViewById(R.id.textVencimentoProdutoInput);
        textNome = findViewById(R.id.textNomeProduto);
        textVencimento = findViewById(R.id.textVencimentoProduto);

            /* getInstance() devolve a data atual do sistema */
        final Calendar vencimento = Calendar.getInstance();

        /* Quando ocorrer um date set no DatePickerDialog ele vai chamar o onDateSet */
        /* seta o valor que o usuário selecionou */
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                vencimento.set(Calendar.YEAR,year);
                vencimento.set(Calendar.MONTH,monthOfYear);
                vencimento.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                /* Formatando o formato da data a ser exibida para o usuário */
                /* format pega data e formata em String | parse pega String e transforma em data*/
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                /* getTime retorna um date */
                textVencimentoInput.setText(format.format(vencimento.getTime()));


            }
        };

        /* Exibe tela data para o usuário */
        textVencimentoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* com o DatePickerDialog() na inicialização do objeto pode escolher o que quer passar ; 3 inteiros d/m/a */
                new DatePickerDialog(MainActivity.this,date,vencimento.get(Calendar.YEAR),vencimento.get(Calendar.MONTH),vencimento.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void salvar(View v){
        Produto produto = new Produto();

        String nomeProduto = editNome.getText().toString();
        String vencimentoProduto = textVencimentoInput.getText().toString();

        /* criando format para formatar a String vencimentoProduto que vai a data inserida pelo usuário */
        /* format pega data e formata em String | parse pega String e transforma em data*/
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            produto.setVencimento(format.parse(vencimentoProduto));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        produto.setNome(nomeProduto);

        /* se o prazo para vencimento for em até 10 dias exibe em vermelho, se for mais que 10 exibe verde*/
        Calendar vencimentoCalendar = Calendar.getInstance();

        /* pegando a data inserida pelo usuário */
        vencimentoCalendar.setTime(produto.getVencimento());

        /* alerta pega a data do dia 'hoje' */
        Calendar alerta = Calendar.getInstance();

        /* somando a data de hoje + 10 dias */
        alerta.add(Calendar.DATE,10);

        /* se o alerta tiver depois do vencimento */
        if(alerta.after(vencimentoCalendar))
            textVencimento.setTextColor(Color.RED);
        else /* se o alerta não tiver depois do vencimento */
            textVencimento.setTextColor(Color.GREEN);

        textVencimento.setText(vencimentoProduto);
        textNome.setText(nomeProduto);
    }
}
