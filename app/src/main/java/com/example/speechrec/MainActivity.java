package com.example.speechrec;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.style.IconMarginSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView tvtext;
    ImageView iv;
    Float a,b,c;


    TextView tv2;
    TextToSpeech ts;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvtext=findViewById(R.id.tvtext);
        iv = findViewById(R.id.iv);
        tv2 = findViewById(R.id.tv2);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                tv2.setText(null);
                tvtext.setText(null);

                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,10);

                }
                else{
                    Toast.makeText(MainActivity.this,"Device Doesnt Support",Toast.LENGTH_SHORT).show();

                }
            }
        });

        ts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    ts.setLanguage(Locale.getDefault());
                }
                else{
                    Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                if (resultCode == RESULT_OK && data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvtext.setText(result.get(0));
                    String text = tvtext.getText().toString();
                    String[] arr = text.split(" ");


                    try{




                        String o = arr[1];

                        if (arr[0].toLowerCase().equals("log")){
                            Double t =Math.log10(Double.parseDouble(o));
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c),TextToSpeech.QUEUE_FLUSH,null); /* log input error */
                        }

                        if (arr[0].toLowerCase().equals("cos")||(arr[0].toLowerCase().equals("cosine"))){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = Math.cos(p);
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (arr[0].toLowerCase().equals("sin")){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = Math.sin(p);
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (arr[0].toLowerCase().equals("tan")||(arr[0].toLowerCase().equals("tangent"))){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = Math.tan(p);
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (arr[0].toLowerCase().equals("cot")){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = 1/(Math.tan(p));
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (arr[0].toLowerCase().equals("cosec")){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = 1/(Math.sin(p));
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if (arr[0].toLowerCase().equals("sec")){
                            Double p = Math.toRadians(Double.parseDouble(o));
                            Double t = 1/(Math.cos(p));
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);
                        }
                        else if ((arr[0].toLowerCase().equals("square"))||arr[1].toLowerCase().equals("root")){
                            Double t = Math.sqrt(Double.parseDouble(arr[3]));
                            c = t.floatValue();
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);

                        }


                        else{
                            a= Float.parseFloat(arr[0]);





                            if (arr[1].toLowerCase().equals("divided")){

                                b= Float.parseFloat(arr[3]);


                                c=a/b;
                            }
                            else if (arr[1].startsWith("-") && arr[1].length()!=1){
                                b = Float.parseFloat(arr[1]);
                                c=a+b;

                            }
                            else if ((arr[1].equals("raised")||arr[1].equals("raise"))&& (arr[3].equals("power"))){
                                b= Float.parseFloat(arr[4]);
                                Double e =(Math.pow(a,b));
                                c = e.floatValue();
                            }
                            else if ((arr[1].equals("raised")||arr[1].equals("raise"))&& (arr[4].equals("power"))){
                                b= Float.parseFloat(arr[5]);
                                Double e =(Math.pow(a,b));
                                c = e.floatValue();

                            }




                            else{
                                b= Float.parseFloat(arr[2]);

                            }


                            if (o.equals("+")||(o.equals("plus"))){
                                c = a+b;

                            }
                            else if (o.equals("-")||(o.equals("minus"))){
                                c= a-b;

                            }
                            else if(o.toLowerCase().equals("x")||o.toLowerCase().equals("into")){
                                c=a*b;

                            }
                            else if (o.equals("%")||o.toLowerCase().equals("mod")){
                                c= a%b;
                            }
                            else if(o.equals("/")){
                                c=a/b;
                            }
                            tv2.setText(String.valueOf(c));
                            ts.speak(String.valueOf(c), TextToSpeech.QUEUE_FLUSH, null);








                        }







                    }

                    catch (Exception e){
                        Toast.makeText(MainActivity.this,"Try Again "+ e.getMessage(),Toast.LENGTH_SHORT).show();
                    }






















                }

        }
    }

}
