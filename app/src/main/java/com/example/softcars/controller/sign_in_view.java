package com.example.softcars.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.softcars.R;
import com.example.softcars.api.API;
import com.example.softcars.services.UserService;

import org.json.JSONObject;

import java.io.IOException;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sign_in_view extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private Button btnHome, btnSignIn;
    private Spinner spn;
    private UserService userService;
    private API urlBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in_view);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnHome = findViewById(R.id.btnHome);
        btnSignIn = findViewById(R.id.btnSignIn);
        spn = findViewById(R.id.txtEstatus);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_status,
                android.R.layout.simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        btnSignIn.setOnClickListener(this::signIn);
        btnHome.setOnClickListener(this::backHome);
    }

    private void signIn(View view){
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        int status = spn.getSelectedItemPosition();
        if (username == null || username == "" || password == null || password == ""){
            new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Campos vacios")
                    .setContentText("Usuario y contraseña son requeridos")
                    .setConfirmText("OK")
                    .show();
            return;
        }else if(status == 0){
            new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Estatus Inactivo")
                    .setContentText("El estatus del cliente es inactivo por lo tanto no se puede continuar con la operacion")
                    .setConfirmText("OK")
                    .show();
            return;
        }else{
            userService = urlBase.getUrl().create(UserService.class);
            String userData = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\",\"estatus\":\""+ status +"\"}";
            Call<ResponseBody> call = userService.insertUser(userData);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try{
                        if (response.isSuccessful() && response.body() != null){
                            String jsonResponse = response.body().string();
                            JSONObject jsonObject = new JSONObject(jsonResponse);
                            if (jsonObject.has("success")){
                                new SweetAlertDialog(sign_in_view.this,SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("¡Éxito!")
                                        .setContentText(jsonObject.getString("success"))
                                        .setConfirmText("OK")
                                        .show();
                            }else if(jsonObject.has("error")){
                                new SweetAlertDialog(sign_in_view.this,SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error")
                                        .setContentText(jsonObject.getString("error"))
                                        .setConfirmText("OK")
                                        .show();
                            }else{
                                new SweetAlertDialog(sign_in_view.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Error")
                                        .setContentText("Datos incorrectos")
                                        .setConfirmText("OK")
                                        .show();
                            }
                        }
                    }catch(Exception e){
                        new SweetAlertDialog(sign_in_view.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText("Error al procesar la respuesta: " + e.getMessage())
                                .setConfirmText("OK")
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    new SweetAlertDialog(sign_in_view.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error de red")
                            .setContentText("Error: " + t.getMessage())
                            .setConfirmText("OK")
                            .show();
                }
            });
        }
    }

    private void backHome(View view){
        Intent login_view = new Intent(sign_in_view.this, Login.class);
        startActivity(login_view);
    }
}