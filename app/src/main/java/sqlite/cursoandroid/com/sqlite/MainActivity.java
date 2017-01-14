package sqlite.cursoandroid.com.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("Aplicativo", MODE_PRIVATE, null);

            //tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( id INTEGER PRIMARY KEY AUTOINCREMENT, nome VACHAR, idade INT(4), profissao VACHAR )");

            //deletar tabela
            //bancoDados.execSQL("DROP TABLE pessoas");

            //inserir dados
            /***
             bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Batman', 61, 'playboy')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('MulherMaravilha', 549, 'arqueologa')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('HomemAranha', 16, 'estudante')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Poseidon', 2516, 'DeusDosMares')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Seya', 14, 'cavaleiroDeAthena')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Goku', 98, 'sayajin')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Shiryu', 15, 'cavaleiroDeAthena')");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade, profissao) VALUES ('Zeus', 5046, 'DeusDosDeuses')");
             */

            //fazendo update dos dados
            //bancoDados.execSQL("UPDATE pessoas SET nome = 'MrRobot' WHERE nome = 'Monique'");

            //deletando dados
            //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Marcos' ");

            //método rawQuery que recupera os dados inseridos na tabela
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM pessoas ", null);

            //inteiro para recuperar o indice da coluna com o método getColumnUndex, passando apenas o nome da coluna ele recupera o +indice
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");
            int indiceColunaProfissao = cursor.getColumnIndex("profissao");
            int indiceColunaId = cursor.getColumnIndex("id");

            //fazer o cursor voltar para o primeiro índice
            cursor.moveToFirst();

            while ( cursor != null) {

                Log.i("RESULTADO - Nome: ", cursor.getString(indiceColunaNome) );
                Log.i("RESULTADO - Idade: ", cursor.getString(indiceColunaIdade) );
                Log.i("RESULTADO - Id: ", cursor.getString(indiceColunaId) );
                Log.i("RESULTADO - Profissao: ", cursor.getString(indiceColunaProfissao) );

                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
