package umn.ac.id.musicplayer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.security.acl.Permission;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  EditText etUsername, etPassword;
  Button btSubmit;
  private TextView attemptsInfo;

  private String Username = "uasmobile";
  private String Password = "uasmobilegenap";

  boolean isValid = false;
  private int counter = 5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    btSubmit = findViewById(R.id.bt_submit);
    attemptsInfo = findViewById(R.id.attemptsInfo);

    btSubmit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String inputName = etUsername.getText().toString();
        String inputPassword = etPassword.getText().toString();

        if (inputName.isEmpty() || inputPassword.isEmpty()) {
          Toast.makeText(MainActivity.this, "Please fill the box", Toast.LENGTH_SHORT).show();
        } else {
          isValid = validate(inputName, inputPassword);

          if (!isValid){
            counter--;

            Toast.makeText(MainActivity.this, "Incorrect Username / Password", Toast.LENGTH_SHORT).show();

            attemptsInfo.setText("No. of attempts remaining: " + counter);
            if (counter == 0){
              btSubmit.setEnabled(false);
            }
          }else{
            Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, ListSong2.class);
            startActivity(intent);
          }
        }
      }
    });
  }
  private boolean validate(String name, String password){
    if (name.equals(Username) && password.equals(Password)){
      return true;
    }
    return false;
  }
}
