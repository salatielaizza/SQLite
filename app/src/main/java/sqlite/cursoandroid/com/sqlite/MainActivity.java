package sqlite.cursoandroid.com.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

        SQLiteDatabase bancoDados = openOrCreateDatabase("Aplicativo", MODE_PRIVATE, null);

        //tabela
        bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VACHAR, idade INT(3))");

        //inserir dados
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcos', 27)");
        bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcela', 17)");

        //método rawQuery que recupera os dados inseridos na tabela
        Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

        //inteiro para recuperar o indice da coluna com o método getColumnUndex, passando apenas o nome da coluna ele recupera o +indice
        int indiceColunaNome = cursor.getColumnIndex("nome");
        int indiceColunaIdade = cursor.getColumnIndex("idade");

        //fazer o cursor voltar para o primeiro índice
        cursor.moveToFirst();

        while ( cursor != null) {

            Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome) );
            Log.i("RESULTADO - Idade: ", cursor.getString(indiceColunaIdade) );

            cursor.moveToNext();

        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
