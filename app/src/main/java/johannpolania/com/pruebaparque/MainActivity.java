//Autor:Johann Polanía González

package johannpolania.com.pruebaparque;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private int acum=0;
    private EditText eDato;
    private TextView tContador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //En el archivo de preferencias datos se tendrá la información del contador
        prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        editor=prefer.edit();
        acum=prefer.getInt("contador", 0);
        editor.putInt("contador", 0);
        editor.commit();
        eDato=(EditText)findViewById(R.id.eDato);
        tContador=(TextView)findViewById(R.id.tContador);
        tContador.setText(this.getString(R.string.Contador)+acum);


    }


    @Override
    protected void onDestroy() {

//Cuando se destruye la aplicación, se aumenta el contador
        super.onDestroy();
        Toast.makeText(this, String.valueOf(acum+1), Toast.LENGTH_SHORT).show();
        editor.putInt("contador", acum + 1);
        editor.commit();


    }



    public void finalizar(View view)
    {

        finish();

    }

    public void ingresar(View view)
    {

            //Al realizar clic en ingresar, se llama a la otra actvidad utlizando intents
            Intent i=new Intent(this,Resultado.class);
            i.putExtra("dato",eDato.getText().toString());
            i.putExtra("contador",acum);
            startActivity(i);






    }
//La actividad principal tiene un menú, de esta manera inflamos este

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override

    //Si al abrir el menú realiza clic en salir, finaliza la aplicación.
    public boolean onOptionsItemSelected(MenuItem item) {
      int id=item.getItemId();
      if(id==R.id.menu_salir)
      {
          finish();


      }

        return super.onOptionsItemSelected(item);
    }
}
