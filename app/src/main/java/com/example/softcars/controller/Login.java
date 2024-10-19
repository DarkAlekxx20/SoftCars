package com.example.softcars.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.softcars.R;
import com.example.softcars.api.API;
import com.example.softcars.model.Usuarios;
import com.example.softcars.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private UserService userService;
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

       userService = API.getUrl().create(UserService.class);
       btnLogin.setOnClickListener(this::loginUser);
    }

    private void loginUser(View view){
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Usuario y contraseña son requeridos", Toast.LENGTH_SHORT).show();
            return;
        }else{
            String userData = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
            Call<Usuarios> call = userService.login(userData);
            call.enqueue(new Callback<Usuarios>() {
                @Override
                public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Login.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Error: Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Usuarios> call, Throwable t) {
                    Toast.makeText(Login.this, "Error en la red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}