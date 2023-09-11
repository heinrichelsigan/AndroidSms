/*
 *
 * @author           Heinrich Elsigan
 * @version          V 0.0.1
 * @since            API 27 Oreo 8.1
 *
 * <p>AndroidSms - a prototype for sending SMS on Android</p>
 *
 * <p>Coded 2023 by <a href="mailto:heinrich.elsigan@area23.at">Heinrich Elsigan</a></p>
 */
package work.darkstar.androidsms;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceFragmentCompat;

import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_SEND_SMS = 0;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText) findViewById(R.id.editTextPhoneNumber);
        txtMessage = (EditText) findViewById(R.id.editTextMessage);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage() {
        phoneNo = txtphoneNo.getText().toString();
        message = txtMessage.getText().toString();

        // Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        // PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), getText(R.string.sms_sent), Toast.LENGTH_LONG).show();
        } catch (Exception sendSMSEx) {
            Toast.makeText(getApplicationContext(), getText(R.string.sending_sms_failed), Toast.LENGTH_LONG).show();
        }
    }

}