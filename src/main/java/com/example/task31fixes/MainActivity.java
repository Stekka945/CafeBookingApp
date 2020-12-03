package com.example.task31fixes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private recyclerAdapter recyclerAdapter;
    private  int REQUEST_CODE=1;
    private TextView cafe_name, booked_date, booked_time, noofpa, cuisi;
    private ImageView cafe_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        recyclerAdapter=new recyclerAdapter(Cafe.createCafeList(), this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void initUI()
    {
        cafe_name=findViewById(R.id.CafeName);
        booked_date = findViewById(R.id.booked_date);
        booked_time=findViewById(R.id.booked_time);
        noofpa=findViewById(R.id.booked_noofdiners);
        cafe_image=findViewById(R.id.cafe_bg);
        cuisi=findViewById(R.id.booked_cuisines);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode==REQUEST_CODE)
        {
            if((resultCode== Activity.RESULT_OK))
            {
                if(intent!=null)
                {
                    setContentView(R.layout.reservation_details);
                    initUI();

                    String cafename ="";
                    String bookdate ="";
                    String booktime ="";
                    String nooo="";
                    String cui=" ";
                    int imag;

                    cafename=intent.getStringExtra("cafe_name");
                    bookdate=intent.getStringExtra("bookdate");
                    booktime=intent.getStringExtra("booktime");
                    nooo=intent.getStringExtra("noofpax");
                    imag=intent.getExtras().getInt("cafebg");
                    cui=intent.getStringExtra("cuisine");

                    cafe_name.setText(cafename);
                    booked_date.setText(bookdate);
                    booked_time.setText(booktime);
                    noofpa.setText(nooo);
                    cafe_image.setImageResource(imag);
                    cuisi.setText(cui);

                }else{
                    Toast.makeText(getBaseContext(), "Your reservation is incomplete", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}