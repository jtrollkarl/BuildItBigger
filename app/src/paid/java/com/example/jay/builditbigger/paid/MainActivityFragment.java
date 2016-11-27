package com.example.jay.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jay.builditbigger.EndpointsAsyncTask;
import com.example.jay.builditbigger.R;
import com.example.jay.displayjoke.DisplayJokeActivity;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    @BindView(R.id.bJoke)
    Button bJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.bJoke)
    public void bJokeClick(Button button) {

        String joke = "";
        try {
            joke = new EndpointsAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage());
        }

        //only send intent if joke is not empty (actually contains a joke)
        if (!joke.equals("")) {
            Intent intent = new Intent(getActivity(), DisplayJokeActivity.class);
            intent.putExtra("key", joke);
            startActivity(intent);
        }
    }
}
