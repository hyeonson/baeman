package project.hs.baeman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import project.hs.baeman.Network.ApiService;
import project.hs.baeman.Request.ReqLogin;
import project.hs.baeman.Response.ResLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    LinearLayout btn_signup;
    Button btn_login;
    EditText et_login_id;
    EditText et_login_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_signup = (LinearLayout)findViewById(R.id.btn_signup);
        btn_login = (Button)findViewById(R.id.btn_login);
        et_login_id = (EditText)findViewById(R.id.et_login_id);
        et_login_pw = (EditText)findViewById(R.id.et_login_pw);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(et_login_id.getText().toString(), et_login_pw.getText().toString());
            }
        });
    }

    private void login(String login_id, String login_pw)
    {

        ReqLogin req_login = new ReqLogin(login_id, login_pw);
        Log.d("login_id", login_id);
        Log.d("login_pw", login_pw);
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResLogin> res = apiService.login(req_login);
        res.enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                        //response.body().isSuccess();

                        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", response.body().getData().getJwt());
                        editor.commit();

                        ((BaemanApplication)getApplication()).setUserEmailId(response.body().getData().getUserEmailId());
                        ((BaemanApplication)getApplication()).setUserNickname(response.body().getData().getUserNickname());
                        ((BaemanApplication)getApplication()).setJwt(response.body().getData().getJwt());

                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        //Log.d("서버로 부터 받은 메시지", response.body().getMessage());
                        //Log.d("서버로 부터 받은 상태코드", Integer.toString(response.body().getStatus()));
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }

}
