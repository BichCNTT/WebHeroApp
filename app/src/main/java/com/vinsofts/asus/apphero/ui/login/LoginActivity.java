package com.vinsofts.asus.apphero.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.vinsofts.asus.apphero.R;
import com.vinsofts.asus.apphero.Utils.APIService;
import com.vinsofts.asus.apphero.helper.ApiUtils;
import com.vinsofts.asus.apphero.model.Post;
import com.vinsofts.asus.apphero.ui.home.HomeActivity;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Nút đăng nhâp
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, LoginView {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.cb_remember)
    CheckBox cbRemember;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_webSite)
    TextView tvWebSite;
    @BindView(R.id.btn_login_with_fb)
    LoginButton btnLoginWithFb;
    CallbackManager callbackManager;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login_with_gg)
    SignInButton btnLoginWithGg;
    private GoogleApiClient mGoogleApiClient;
    public static final int REQ_CODE = 1995;
    public LoginPresenter loginPresenter;
    private String mUsername;
    private String mPassword;
    private APIService mAPIService;
    private String mEmail;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
    }

    @OnClick({R.id.cb_remember, R.id.btn_login, R.id.btn_login_with_gg, R.id.tv_webSite, R.id.btn_login_with_fb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_remember:
//                Lưu lại dữ liệu trong máy: Lưu lại token
                break;
            case R.id.btn_login:
                mUsername = edtEmail.getText().toString().toLowerCase().trim();
                mPassword = edtPassword.getText().toString();
                if (TextUtils.isEmpty(mUsername)) {
                    edtEmail.setError("Nhập vào một email");
                    edtEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(mUsername).matches()) {
                    edtEmail.setError("Email của bạn không đúng");
                    edtEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mPassword)) {
                    edtPassword.setError("Nhập mật khẩu");
                    edtPassword.requestFocus();
                    return;
                }
                sendPostLogin(mUsername, mPassword);
                break;
            case R.id.btn_login_with_fb:
                startLoginWithFacebook();
                sendPost(mEmail, mId);
                break;
            case R.id.btn_login_with_gg:
                startLoginWithGoogle();
                sendPost(mEmail, mId);
                break;
            case R.id.tv_webSite:
                tvWebSite.setText("http://webhero.vn/");
                Linkify.addLinks(tvWebSite, Linkify.WEB_URLS);
                break;
        }
    }

    private void sendPost(String mEmail, String mId) {
        mAPIService.post(mEmail, mId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                checkResponse(response);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi tải trang", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPostLogin(String mUsername, String mPassword) {
        mAPIService.postLogin(mUsername, mPassword).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                checkResponse(response);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi tải trang", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkResponse(Response<Post> response) {
        if (response.isSuccessful()) {
            if (response.body().getUser() != null) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startHomeActivity();
                Log.e("======>LoginActivity", response.toString());
            } else {
                Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                Log.e("=======>LoginActivity", Throwable.class.getName());
            }
        }
    }

    private void startLoginWithGoogle() {
        Intent intentSignInWithGoogle = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intentSignInWithGoogle, REQ_CODE);
    }

    private void startLoginWithFacebook() {
        callbackManager = CallbackManager.Factory.create();
        btnLoginWithFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("----->accessToken", accessToken);
                getEmailFacebook(loginResult);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Hủy đăng nhập", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "Đã xảy ra lỗi trong quá trình đăng nhập", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startHomeActivity() {
        Intent intentStartActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intentStartActivity);
    }

    private void getEmailFacebook(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.e("--------->LoginActivity", response.toString());
                mEmail = object.optString("email").toString();
                mId = object.optString("id").toString();
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void handleResult(GoogleSignInResult signInResult) {
        if (signInResult.isSuccess()) {
            GoogleSignInAccount account = signInResult.getSignInAccount();
            String name = account.getDisplayName();
            String idToken = account.getIdToken();
            String email = account.getEmail();
            edtEmail.setText(email);
            edtPassword.setText("");
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("----->connectFailed: ", connectionResult.getErrorMessage());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(signInResult);
        }
    }

    @Override
    public void getListUserSuccessfully(List<String> listUsers) {

    }

    @Override
    public void getListUserFailed(String s) {

    }
}
