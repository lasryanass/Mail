package com.tp2.mail;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText mailOfDestinationField;
    EditText mailContentField;
    EditText mailSubjectField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mailOfDestinationField = findViewById(R.id.mailOfDestination);
        mailContentField = findViewById(R.id.mailContent);
        mailSubjectField = findViewById(R.id.mailSubject);
        Button boutonDemarrer = findViewById(R.id.sendEmail);
        boutonDemarrer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                envoyerEmail();
            }
        });
    }
    protected void envoyerEmail() {
        Log.i("Send email", "");
        String mailOfDestination = mailOfDestinationField.getText().toString();
        String mailContent = mailContentField.getText().toString();
        String mailSubject = mailSubjectField.getText().toString();
        String[] TO = {mailOfDestination}; //String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//      emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mailContent);
        try {
            startActivity(Intent.createChooser(emailIntent, "Envoyer mail..."));
            finish();
            Log.i("Envoi du mail termine...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Pas de client mail installe", Toast.LENGTH_SHORT).show();
        }
    }
}