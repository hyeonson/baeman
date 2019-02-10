package project.hs.baeman;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import project.hs.baeman.Network.ApiService;
import project.hs.baeman.Request.ReqSignup;
import project.hs.baeman.Request.ReqValidate;
import project.hs.baeman.Request.ReqValidate2;
import project.hs.baeman.Response.DefaultRes;
import project.hs.baeman.Response.ResValidate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Signup2 extends AppCompatActivity {
    String et_nickname_val;
    String et_email_val;
    String et_password_val;

    EditText et_phone_num;
    EditText et_validate;
    Button btn_validate;
    Button btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Intent intent2 = getIntent();
        et_email_val = intent2.getStringExtra("et_email_val");
        et_nickname_val = intent2.getStringExtra("et_nickname_val");
        et_password_val = intent2.getStringExtra("et_password_val");

        et_phone_num = (EditText)findViewById(R.id.et_phone_num);
        et_validate = (EditText)findViewById(R.id.et_validate);
        btn_finish = (Button)findViewById(R.id.btn_finish);
        btn_validate = (Button)findViewById(R.id.btn_validate);

        btn_finish.setEnabled(false);

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate2(et_phone_num.getText().toString(), et_validate.getText().toString());
            }
        });

        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate1(et_phone_num.getText().toString());
            }
        });


        et_validate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_validate.getText().toString().length() == 4){
                    btn_finish.setBackgroundColor(Color.rgb(26, 211, 211));
                    btn_finish.setTextColor(Color.WHITE);
                    btn_finish.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
    }
    private void validate1(String phoneNumber)
    {
        ReqValidate req_validate = new ReqValidate(phoneNumber);
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResValidate> res = apiService.validate1(req_validate);
        res.enqueue(new Callback<ResValidate>() {
            @Override
            public void onResponse(Call<ResValidate> call, Response<ResValidate> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        //response.body().isSuccess();
                        Toast.makeText(getApplicationContext(), "인증번호 요청 성공", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "인증번호 요청 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResValidate> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void validate2(String phoneNumber, String authNumber)
    {
        ReqValidate2 req_validate2 = new ReqValidate2(phoneNumber, authNumber);
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<DefaultRes> res = apiService.validate2(req_validate2);
        res.enqueue(new Callback<DefaultRes>() {
            @Override
            public void onResponse(Call<DefaultRes> call, Response<DefaultRes> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        //response.body().isSuccess();
                        //Toast.makeText(getApplicationContext(), "인증번호 일치", Toast.LENGTH_LONG).show();
                        signup(et_phone_num.getText().toString(), et_email_val, et_password_val, et_nickname_val);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "인증번호 불일치", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<DefaultRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void signup(String userPhoneNumber, String userEmailId, String userPw, String userNickname)
    {
        ReqSignup req_signup = new ReqSignup(userPhoneNumber,userEmailId, userPw, userNickname);
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<DefaultRes> res = apiService.signup(req_signup);
        res.enqueue(new Callback<DefaultRes>() {
            @Override
            public void onResponse(Call<DefaultRes> call, Response<DefaultRes> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == 200) {
                        Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Signup2.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<DefaultRes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
