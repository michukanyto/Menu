package model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

import com.appsmontreal.menu2.MainActivity;

public class Email extends MainActivity {
    private TextView[] textViews;
    private Context context;

    public Email(TextView[] textViews, Context context) {
        this.textViews = textViews;
        this.context = context;
    }

    public void sendEmail(){
        Intent intentEmail = new Intent(Intent.ACTION_SENDTO);
        intentEmail.setType("message/rfc822");
        intentEmail.setData(Uri.parse("mailto:"));
        intentEmail.putExtra(Intent.EXTRA_EMAIL  , new String[]{""});
        intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Diet Schedule");
        intentEmail.putExtra(Intent.EXTRA_TEXT   , "MONDAY\n--------------------------\n\t" + "Breakfast =>\t" + textViews[0].getText().toString() + "\n\tLaunch =>\t" + textViews[1].getText().toString() + "\n\tSnacks =>\t" + textViews[2].getText().toString() + "\n\n" +
                "TUESDAY\n-------------------------\n\t" + "Breakfast =>\t" + textViews[3].getText().toString() + "\n\tLaunch =>\t" + textViews[4].getText().toString() + "\n\tSnacks =>\t" + textViews[5].getText().toString() + "\n\n" +
                "WEDNESDAY\n----------------------\n\t" + "Breakfast =>\t" + textViews[6].getText().toString() + "\n\tLaunch =>\t" + textViews[7].getText().toString() + "\n\tSnacks =>\t" + textViews[8].getText().toString() + "\n\n" +
                "THURSDAY\n-----------------------\n\t" + "Breakfast =>\t" + textViews[9].getText().toString() + "\n\tLaunch =>\t" + textViews[10].getText().toString() + "\n\tSnacks =>\t" + textViews[11].getText().toString() + "\n\n" +
                "FRIDAY\n-------------------------\n\t" + "Breakfast =>\t" + textViews[12].getText().toString() + "\n\tLaunch =>\t" + textViews[13].getText().toString() + "\n\tSnacks =>\t" + textViews[14].getText().toString());

        try {
            startActivity(Intent.createChooser(intentEmail, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Email service was not found in this phone.", Toast.LENGTH_SHORT).show();
        }
    }

}
