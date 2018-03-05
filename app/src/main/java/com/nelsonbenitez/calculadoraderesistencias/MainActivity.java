/* Autor: Nelson Benitez
 * Fecha: 26-Febrero_2018
 * Laboratorio: Laboratorio 1 Programación dispositivos móviles
 * Profesor: Edwin Cubillos
 * Descripción: Programa que calcula el valor de una resistencia tomando como valores de
 *              entrada los colores de la franjas que tiene la resistencia.  El color de las
 *              franjas se puede cambiar al hacer click en cada banda o franja.
 *
 *              Al hacer click los colores van cambiando de acuerdo al código de colores establecido
 *              en el estándar.  Una vez establecidas las cuatro con sus respectivos colores,
 *              se puede calcular la resistencia respectiva.  Si no se cambian los colores que trae
 *              por defecto, el resultado sera nulo.
 *
 * */


package com.nelsonbenitez.calculadoraderesistencias;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Para contar la cantidad de veces que se hace click sobre una franja de color
    int cantidad_veces_click_franja_uno=0, veces_clik_franja_dos=0,
            veces_click_franja_tres=0,clicks_franja_cuatro=0;

    //Para llevar el control de las cifras y el color
    String ColorBandaUno, cifraUno,cifraDos,tolerancia;

    //Para almacenar el valor total de la respuesta, incluyendo la tolerancia
    String valorTotal;

    //Para modificar los texview
    TextView Result,color_uno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result=(TextView) findViewById(R.id.resultado);
        color_uno= (TextView)findViewById(R.id.franja_uno);
    }

    public void Cambiar_color(View view) {
        if (cantidad_veces_click_franja_uno==9) {cantidad_veces_click_franja_uno=0;}
        cantidad_veces_click_franja_uno++;

        //Se llama la funcion para validar el color de la banda uno
        ColorBandaUno=Colorea(cantidad_veces_click_franja_uno, ColorBandaUno);
        color_uno.setBackgroundColor(Color.parseColor(ColorBandaUno));
    }

    private String Colorea(int veces_click, String band) {


        switch (veces_click){


            case 0:
                band= "#000000"; //negro
                break;

            case 1:
                band= "#800000"; //Cafe o marrón

                break;

            case 2:
                band="#ff0000";   //rojo
                break;

            case 3:
                band="#ff4500";  //naranja
                break;

            case 4:
                band="#ffff00";  //amarillo
                break;

            case 5:
                band="#32cd32";  //verde
                break;

            case 6:
                band="#00bfff"; //Azul
                break;

            case 7:
                band="#800080";  //violeta o púrpura
                break;

            case 8:
                band="#808080";  // gris
                break;

            case 9:
                band="#ffffff";  //blanco
                break;

            case 10:
                band="#ffd700";  //dorado
                break;

            case 11:
                band="#b0c4de";  //plateado
                break;
        }

        return band;
    }

    public void Cambiar_franja_dos(View view) {
        if (veces_clik_franja_dos==10){veces_clik_franja_dos=0;}
        TextView color_uno= (TextView)findViewById(R.id.franja_dos);

        //se valida el color de la segunda banda
        ColorBandaUno=Colorea(veces_clik_franja_dos, ColorBandaUno);
        color_uno.setBackgroundColor(Color.parseColor(ColorBandaUno));
        veces_clik_franja_dos++;
    }

    public void CambiarFranjaTres(View view) {
        if (veces_click_franja_tres==7){veces_click_franja_tres=10;}
        else if(veces_click_franja_tres==12){veces_click_franja_tres=0;}
        TextView color_uno= (TextView)findViewById(R.id.franja_tres);

        //se valida el color de la banda tres
        ColorBandaUno=Colorea(veces_click_franja_tres, ColorBandaUno);
        color_uno.setBackgroundColor(Color.parseColor(ColorBandaUno));
        veces_click_franja_tres++;
    }

    public void Cambiar_franja_cuatro(View view) {
        clicks_franja_cuatro++;
        if (clicks_franja_cuatro==3){clicks_franja_cuatro=10;}
        else if(clicks_franja_cuatro==12){clicks_franja_cuatro=1;}
        TextView color_uno= (TextView)findViewById(R.id.franja_cuatro);

        //validación de color franja cuatro
        ColorBandaUno=Colorea(clicks_franja_cuatro, ColorBandaUno);
        color_uno.setBackgroundColor(Color.parseColor(ColorBandaUno));
        //Log.d("Valor clicks cuatro",String.valueOf(clicks_franja_cuatro));
    }

    public void Calcular_Resistencia(View view) {
       cifraUno=String.valueOf(cantidad_veces_click_franja_uno);
       cifraDos=String.valueOf(veces_clik_franja_dos-1);
       ValorDelMultiplicador(veces_click_franja_tres,cifraUno,cifraDos);
       CalculaTolerancia(clicks_franja_cuatro);
       Result.setText(valorTotal+"Ω"+tolerancia);

    }

    private void CalculaTolerancia(int clicks) {
        switch (clicks)
        {
            case 1:
                tolerancia=" ±1%"; //color café o marrón
                break;

            case 2:
                tolerancia=" ±2%";  //color rojo
                break;

            case 10:
                tolerancia=" ±5%"; //color dorado
                break;
            case 11:
                tolerancia=" ±10%"; //color plateado
                break;
        }
    }

    private void ValorDelMultiplicador(int click_franja_tres,String uno,String dos) {


        switch (click_franja_tres)
        {
            case 1:
                valorTotal=uno+dos; //multiplicacion por uno
                break;

            case 2:
                valorTotal=uno+dos+"0"; //multiplacador 10
                break;

            case 3:
                valorTotal=uno+"."+dos+"k"; //multiplacador 100
                break;
            case 4:
                valorTotal=uno+dos+"k";  //multiplacador 1000
                break;

            case 5:
                valorTotal=uno+dos+"0k"; ////multiplacador 10000
                break;

            case 6:
                valorTotal=uno+"."+dos+"M";  ////multiplacador 100000
                break;

            case 7:
                valorTotal=uno+dos+"M";  ////multiplacador 1000000
                break;

            case 11:
                valorTotal=uno+"."+dos;
                break;

            case 12:
                valorTotal="0."+uno+dos;
                break;
        }
    }
}
