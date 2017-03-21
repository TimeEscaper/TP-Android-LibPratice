package com.sibirsky.tp.libapp;

import android.os.UserManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Doubles;
import com.google.gson.Gson;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private Button btnLoad;

    Ordering<UserModel> customOrdering = new Ordering<UserModel>() {
        @Override
        public int compare(UserModel left, UserModel right) {
            return Doubles.compare(Math.abs(left.getWeight()-50),
                    Math.abs(right.getWeight()-50));
        }
    };

    Predicate<UserModel> filter = new Predicate<UserModel>() {
        @Override
        public boolean apply(UserModel input) {
            return  ((input.getGender().equals("FEMALE")) && (input.getProtected().equals(0))
                    && (input.getWeight() < 1000));
        }
    };

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button)findViewById(R.id.btn_load);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnLoadClick();
            }
        });
    }

    private void onBtnLoadClick() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://json-storage.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<UserList> call = service.loadData("1");
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                Ordering<UserModel> ordering = customOrdering.nullsLast().onResultOf(
                        new Function<UserModel, UserModel>() {
                    @Nullable
                    @Override
                    public UserModel apply(UserModel input) {
                        if (filter.apply(input))
                            return input;
                        else
                            return null;
                    }
                });

                ImmutableList<UserModel> result = ImmutableList.copyOf(
                        ordering.sortedCopy(Iterables.filter(response.body().getUsers(), filter)));

                for (UserModel userModel : result) {
                    Log.d(TAG, gson.toJson(userModel).toString());
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d(TAG, "Loading error!");
            }
        });

    }
}
