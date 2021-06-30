package com.androidavanzado.minitwitt.frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidavanzado.minitwitt.R;
import com.androidavanzado.minitwitt.models.Request.RequestLogin;
import com.androidavanzado.minitwitt.models.Response.ResponseAuth;
import com.androidavanzado.minitwitt.services.AuthService;
import com.androidavanzado.minitwitt.services.ServiceClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etMail, etPassword;

    ServiceClient serviceClient;
    AuthService authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        //iniciar variables de retrofit
        retrofitInit();

        //encontrar campos
        findViews();
    }

    private void retrofitInit() {
        //instancia cliente
        serviceClient = ServiceClient.getInstance();
        //instancia servicio
        authService = serviceClient.getAuthService();
    }

    private void findViews() {
        etMail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

    }

    public void onclickSign(View view) {
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    public void onClickIniciarSesion(View view) {
        String email = etMail.getText().toString();
        String password = etPassword.getText().toString();
        //validaciones
        if(email.isEmpty()){
            etMail.setError("Email es requerido");
        }else if(password.isEmpty()){
            etPassword.setError("Contraseña es requerida");
        }else{
            RequestLogin requestLogin = new RequestLogin(email,password);
            Call<ResponseAuth> call = authService.doLogin(requestLogin);
            call.enqueue(new Callback<ResponseAuth>() {
                @Override
                public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                    //aca recibo correctamente
                    //revisar si la respuesta viene con código correcto
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Sesion iniciada correctamente",Toast.LENGTH_SHORT);
                        //Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                        //startActivity(i);
                        //destruimos este activity para no regresar
                        //finish();
                    }else{
                        Toast.makeText(MainActivity.this,"Algo no funcionó correctamente!",Toast.LENGTH_SHORT);
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuth> call, Throwable t) {
                    //fallo de comunicacion
                    //se podría revisar si tiene conexión a internet
                    Toast.makeText(MainActivity.this,"Problemas de conexión. intente de nuevo",Toast.LENGTH_LONG).show();

                }
            });
        }

    }
}