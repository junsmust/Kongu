package com.example.isp30.wearetheone;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton signInButton;
    private Button logoutBtn;
    private Intent intent;
    private FirebaseUser user;
    private CallbackManager callbackManager;
    public  String name;
    private String email;
    private  String name2;
    private com.example.isp30.wearetheone.UserInfo userInfo = new com.example.isp30.wearetheone.UserInfo();
    public TextView user_name;
    private Bitmap bitmap;
    private Handler handler;
    private ImageView image_main;
    private ProfilePictureView profilePictureView;
    private Button fb_btn;
    private float fbIconScale = 1.10F;
    private LoginButton loginButton;
    private Button totorial_btn;
    public LoginActivity(){
        this.getName();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        totorial_btn = (Button)findViewById(R.id.tutorial_btn);
        totorial_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this,Tutorial.class);
                startActivity(intent);
            }
        });
        ImageView tuto = (ImageView)findViewById(R.id.tutorial);
        tuto.setOnClickListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleApiClient = new GoogleApiClient.Builder(this)

                .enableAutoManage(this, this)

                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)

                .build();

        mAuth = FirebaseAuth.getInstance();



        signInButton = (SignInButton)findViewById(R.id.sign_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
       /* ImageView googleFake = (ImageView) findViewById(R.id.fake_google);
        googleFake.setOnClickListener(this);*/

        ImageView fb_fake = (ImageView)findViewById(R.id.fb_fake);
        fb_fake.setOnClickListener(this);
      /*  logoutBtn = (Button)findViewById(R.id.logout_google);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("알림", "구글 LOGOUT");

                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setMessage("로그아웃 하시겠습니까 ?").setCancelable(false)
                        .setPositiveButton("네 ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                signOut();
                            }
                        }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.setTitle("로그아웃");
                dialog.setIcon(R.drawable.ic_block_black_24dp);
                dialog.show();
            }
        });

*/

        Drawable drawable = getBaseContext().getResources().getDrawable(com.facebook.login.R.drawable.com_facebook_button_icon);
        drawable.setBounds(0,0,(int)(drawable.getIntrinsicWidth()*fbIconScale),(int)(drawable.getIntrinsicHeight()*fbIconScale));

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        // If using in a fragment
        loginButton.setCompoundDrawables(drawable,null,null,null);


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try{
                            userInfo = new com.example.isp30.wearetheone.UserInfo(object.getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("FB","No Key provided");
                        }


                    }
                });
                Bundle paramenters = new Bundle();
                paramenters.putString("fields","id,name,link");
                graphRequest.setParameters(paramenters);
                graphRequest.executeAsync();


                // App code
               user = FirebaseAuth.getInstance().getCurrentUser();


              /*  if(user != null){
                    //name , email, address , and profile photo Url

                    for(UserInfo profile : user.getProviderData()){
                        //ID Of the provider
                        String providerId = profile.getProviderId();
                        //UID specific to the provider
                        String uid = profile.getUid();

                        name = profile.getDisplayName();
                        email = profile.getEmail();
                        com.example.isp30.wearetheone.UserInfo userInfo = new com.example.isp30.wearetheone.UserInfo(name,email);
                        Uri photoUri = profile.getPhotoUrl();
                        Toast.makeText(LoginActivity.this, name+","+email, Toast.LENGTH_SHORT).show();

                       *//* Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    URL url = new URL(link);
                                    InputStream is = url.openStream();
                                    bitmap = BitmapFactory.decodeStream(is);
                                    //핸들러 사용
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            profile_img.setImageBitmap(bitmap);
                                        }
                                    });
                                    profile_img.setImageBitmap(bitmap);



                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();*//*




                    }

                }else{

                }*/
                handleFacebookAccessToken(loginResult.getAccessToken());
                intent = new Intent(LoginActivity.this,MenuLayout.class);
                startActivity(intent);



            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user != null) {

                                for(UserInfo userInfo :user.getProviderData()){
                                    //ID Of the provider
                                    String providerId = userInfo.getProviderId();
                                    //UID specific to the provider
                                    String uid = userInfo.getUid();

                                    name = userInfo.getDisplayName();
                                    email = userInfo.getEmail();

                                    Toast.makeText(LoginActivity.this, name+","+email, Toast.LENGTH_SHORT).show();


                                }

                            } else{

                            }



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void signOut() {
        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                mAuth.signOut();
                if(mGoogleApiClient.isConnected()){
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if(status.isSuccess()){
                                Log.v("알림", "로그아웃 성공");

                                setResult(1);
                            }else{
                                setResult(0);
                            }
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.v("알림",     "Google API Client Connection Suspended");

                setResult(-1);

                finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "로그인 성공",
                                    Toast.LENGTH_SHORT).show();
                            user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user != null){
                                //name , email, address , and profile photo Url
                                for(UserInfo profile : user.getProviderData()){
                                    //ID Of the provider
                                    String providerId = profile.getProviderId();
                                    //UID specific to the provider
                                    String uid = profile.getUid();

                                    name = profile.getDisplayName();
                                    email = profile.getEmail();
                                    com.example.isp30.wearetheone.UserInfo userInfo = new com.example.isp30.wearetheone.UserInfo(name,email);

                                    Uri photoUri = profile.getPhotoUrl();
                                   Toast.makeText(LoginActivity.this, ""+name+""+email, Toast.LENGTH_SHORT).show();
                                }

                            }else{

                            }
                            intent = new Intent(LoginActivity.this,MenuLayout.class);
                            startActivity(intent);




                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }
    public void setName(String names){
        this.name = names;
    }
    public String getName() {
        return name;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("알림", "onConnectionFailed");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fb_fake:
                loginButton.performClick();
                break;
          /*  case R.id.fake_google:
                signInButton.performClick();
                break;*/
            case R.id.tutorial:
                totorial_btn.performClick();
                break;

        }

    }
}
