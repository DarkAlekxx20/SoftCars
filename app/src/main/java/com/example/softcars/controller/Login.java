package com.example.softcars.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.softcars.R;
import com.example.softcars.api.API;
import com.example.softcars.model.Usuarios;
import com.example.softcars.services.UserService;

import org.json.JSONObject;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private UserService userService;
    private API urlBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_view);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogIn);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLogin.setOnClickListener(this::loginUser);
    }

    private void loginUser(View view){
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Campos vacíos")
                    .setContentText("Usuario y contraseña son requeridos!")
                    .setConfirmText("OK")
                    .show();
            return;
        }else{
            userService = urlBase.getUrl().create(UserService.class);
            String userData = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
            Call<ResponseBody> call = userService.login(userData);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try{
                       if(response.isSuccessful() && response.body() != null){
                           String jsonResponse = response.body().string();
                           JSONObject jsonObject = new JSONObject(jsonResponse);
                           if (jsonObject.has("success")){
                               new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                                       .setTitleText("¡Éxito!")
                                       .setContentText(jsonObject.getString("success"))
                                       .setConfirmText("OK")
                                       .show();
                           }else if(jsonObject.has("error")){
                               new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                       .setTitleText("Error")
                                       .setContentText(jsonObject.getString("error"))
                                       .setConfirmText("OK")
                                       .show();
                           }else{
                               new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                       .setTitleText("Error")
                                       .setContentText("Datos incorrectos")
                                       .setConfirmText("OK")
                                       .show();
                           }
                        }
                    }catch (Exception e){
                        new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Error")
                                .setContentText("Error al procesar la respuesta: " + e.getMessage())
                                .setConfirmText("OK")
                                .show();
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error de red")
                            .setContentText("Error: " + t.getMessage())
                            .setConfirmText("OK")
                            .show();
                }
            });
        }
    }
}