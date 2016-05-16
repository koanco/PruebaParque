//Autor:Johann Polanía González

package johannpolania.com.pruebaparque;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Resultado extends AppCompatActivity {
private String cadena="";
private int contador=0;
    private TextView tResultado,tDescripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Bundle extras=getIntent().getExtras();
        //Se obtienen los datos suministrados por la otra actividad, el dato ingresado por el usuario y el valor del contador
        cadena=extras.getString("dato");
        contador=extras.getInt("contador");
        tResultado=(TextView)findViewById(R.id.tResultado);
        tDescripcion=(TextView)findViewById(R.id.tDescripcion);

       mostrarResultado();
    }

    private void mostrarResultado(){
        int a=0;
        String texto="";
        String numeros="";
        //Si el dato ingresado por el usuario es número, ingresa a esta condición
        if(revisarNumero())
        {

            a=Integer.parseInt(cadena);
            tDescripcion.setText(this.getString(R.string.soloNumeros));
            //Se muestran las operaciones básicas con el contador


            tResultado.setText(this.getString(R.string.Suma)+String.valueOf(a + contador)+"\n"+
                            this.getString(R.string.Resta1)+String.valueOf(contador-a)+"\n"+
                            this.getString(R.string.Resta2)+String.valueOf(a-contador)+"\n"+
                     this.getString(R.string.Producto) + String.valueOf(contador * a) + "\n" +
                                     this.getString(R.string.Division2) + String.valueOf((double) a / contador) + "\n" +
                            this.getString(R.string.Division) + String.valueOf((double) contador / a) + "\n"

                     );



        }
        //Si lo ingresado por el usuario es solo texto, entra a esta condición
       else if(!revisarTexto())

        {    tDescripcion.setText(this.getString(R.string.soloTexto));

            //Se concatena lo ingresado por el usuario y eol contador
             tResultado.setText(cadena+" "+contador);


        }
//Si lo ingresado por el usuario tiene números y letras entra aquí

        else if(revisarTexto()){
            tDescripcion.setText(this.getString(R.string.combinado));
            //Se recorre la cadena ingresada por el usuario para saber lo que es letra y números

            for(int i=0;i<cadena.length();i++)
            {
                //Con esta condición, se separa lo que sea número

                if(Character.isDigit(cadena.charAt(i)))
                {
                    numeros+=cadena.charAt(i);

                }
                //Aquí se separa lo que es texto
                else
                {
                    texto+=cadena.charAt(i);

                }

            }

          //Se muestra el texto y números separados
            tResultado.setText(this.getString(R.string.Number) +numeros
            + "\n"+ this.getString(R.string.Texto)  +texto);



        }


    }

//La siguiente función retorna un valor true si lo ingresado por el usuario es número, en otro caso falso.
    private boolean revisarNumero()
    {
        boolean estado=false;

        try{
            Integer.parseInt(cadena);

            estado=true;

        }
        catch (NumberFormatException ex)
        {


        }

        return estado;
    }
    /*La siguiente función sirve para conocer si por lo menos existe un solo número en la cadena ingresada por el usuario,
      en caso que si exista retorna verdadero, si no, retorna falso

    */
    private boolean revisarTexto()
    {

       for(int i=0;i<cadena.length();i++)
       {


          if(Character.isDigit(cadena.charAt(i)))
          {

              return true;


          }

       }

        return false;

    }
//Regresa a la actividad principal

    public void regresar(View view)
    {
        finish();


    }
}
