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

        final Calendar vencimento = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                vencimento.set(Calendar.YEAR,year);
                vencimento.set(Calendar.MONTH,monthOfYear);
                vencimento.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                textVencimentoInput.setText(format.format(vencimento.getTime()));


            }
        };

        textVencimentoInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,vencimento.get(Calendar.YEAR),vencimento.get(Calendar.MONTH),vencimento.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public void salvar(View v){
        Produto produto = new Produto();

        String nomeProduto = editNome.getText().toString();
        String vencimentoProduto = textVencimentoInput.getText().toString();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            produto.setVencimento(format.parse(vencimentoProduto));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        produto.setNome(nomeProduto);

        Calendar vencimentoCalendar = Calendar.getInstance();
        vencimentoCalendar.setTime(produto.getVencimento());
        Calendar alerta = Calendar.getInstance();
        alerta.add(Calendar.DATE,10);

        if(alerta.after(vencimentoCalendar))
            textVencimento.setTextColor(Color.RED);
        else
            textVencimento.setTextColor(Color.GREEN);

        textVencimento.setText(vencimentoProduto);
        textNome.setText(nomeProduto);
    }
}
