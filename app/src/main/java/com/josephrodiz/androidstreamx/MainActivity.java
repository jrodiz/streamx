package com.josephrodiz.androidstreamx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jrodiz.stream.Stream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stream.of("uno", "dos")
                .forEach(System.out::println);
    }
}
