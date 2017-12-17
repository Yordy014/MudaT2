package text.android.mudat2.Entity;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Y014 on 12/16/2017.
 */

public class Utils {

    public static final String LOGIN_NAME = "LOGIN_NAME";
    public static final String LOGIN_ID = "LOGIN_ID";

    public static final String PREFERENCE_NAME = "Mudat_Preferences";

    public static void guardarPreferencia(Context context, String name, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE).edit();
        editor.putString(name, value);
        editor.apply();
    }

    public static String getPreferencia(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String val = null;

        String restoredText = prefs.getString(name, null);

        if (restoredText != null) {
            val = prefs.getString(name, "No name defined");//"No name defined" is the default value.
        }

        return val;
    }

    public static String getFechaFormateada() {
        String Retorno;

        try {
            Calendar cal = new GregorianCalendar();
            Date date = cal.getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Retorno = df.format(date);
        } catch (NumberFormatException ex) {
            Retorno = "";
        }

        return Retorno;
    }

    public static String formatearFecha(Date Fecha) {
        String Retorno;

        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Retorno = df.format(Fecha);
        } catch (NumberFormatException ex) {
            Retorno = "";
        }

        return Retorno;
    }
}
