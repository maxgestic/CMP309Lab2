package com.maxgestic.cmp309lab2;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    float[] hsv = new float[3];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hsv[0] = 0.0f; // Hue
        hsv[1] = 0.0f; // Saturation
        hsv[2] = 1.0f; // Value

        View layout = findViewById(R.id.constraint);
        layout.setBackgroundColor(Color.HSVToColor(hsv));

        Button Button1 = findViewById(R.id.button);
        Button Button2 = findViewById(R.id.button2);
        Button Button3 = findViewById(R.id.button3);

        Button2.setOnTouchListener(this);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button3.setOnClickListener(this);
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState) {
        super.onRestoreInstanceState (savedInstanceState);

        hsv = savedInstanceState.getFloatArray("hsv");
        View layoutRef = findViewById(R.id.constraint);
        layoutRef.setBackgroundColor(Color.HSVToColor(hsv));

        Button button2 = findViewById(R.id.button2);
        button2.setText(savedInstanceState.getCharSequence("button2txt"));

        //TODO add method to load btn color from state

    }
    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState (outState);

        Button button2 = findViewById(R.id.button2);

        outState.putFloatArray("hsv", hsv);
        outState.putCharSequence("button2txt", button2.getText());

        //TODO add method to save btn color
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        View layoutRef = findViewById(R.id.constraint);

        if (keyCode == 24){

            if (hsv[2] > 0.0f) {

                hsv[2] = hsv[2] - 0.1f;

            }

        }
        else if (keyCode == 25) {

            if (hsv[2] < 1.0f) {

                hsv[2] = hsv[2] + 0.1f;

            }

        }


        layoutRef.setBackgroundColor(Color.HSVToColor(hsv));
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Button button = findViewById(R.id.button2);
        String action = MotionEvent.actionToString(event.getAction());

        switch (action){

            case "ACTION_UP":
                button.setBackgroundColor(Color.GREEN);
                break;

            case "ACTION_DOWN":
                button.setBackgroundColor(Color.RED);
                break;

            case "ACTION_MOVE":
                button.setBackgroundColor(Color.YELLOW);
                break;

        }


        return false;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        changeBackgroundColour(event);

        return super.onTouchEvent(event);
    }

    public void changeBackgroundColour(MotionEvent event) {

        Button button = findViewById(R.id.button2);
        String action = MotionEvent.actionToString(event.getAction());

        button.setText(action);

        View layoutRef = findViewById(R.id.constraint);
        float eventX = event.getX();
        float eventY = event.getY();
        float height = layoutRef.getHeight(); // make sure the ref is declared and initialised (this is a reference to your root layout)
        float width = layoutRef.getWidth();
        hsv[0] = eventY / height * 360; // (0 to 360)
        hsv[1] = eventX / width + 0.1f; // (0.1 to 1)

        layoutRef.setBackgroundColor(Color.HSVToColor(hsv));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button:
                Toast.makeText(this, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button2:
                Toast.makeText(this, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button3:
                Toast.makeText(this, "Button 3 Clicked", Toast.LENGTH_SHORT).show();
                break;
        }

    }




}