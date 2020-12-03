package com.example.task31fixes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class Main2Activity extends AppCompatActivity {
    private TextView aaaa, nood,cnam;
    private EditText bookdate,booktime;
    private ImageView im;
    private ImageButton up_btn, down_btn;
    private Cuisine[] cuus;
    private int texy_co=0, i;
    private CheckBox ch;
    String date = new SimpleDateFormat("d-MMM-yyyy",
            Locale.ENGLISH).format(new Date());
    DatePickerDialog pickerDialog;
    TimePickerDialog picker;
    private Button reserve;
    private RecyclerView recyclerView;
    private myCuisineAdapter adapter;
    private ArrayList<Cuisine> checkedCuisines=new ArrayList<Cuisine>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_form);
        initUI();
        setAdapter();


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Cafe caf = (Cafe)bundle.getParcelable("cafe");
            //aaaa.setText(caf.getCafe_name());
            i=caf.getImg();
            im.setImageResource(i);
            if(i==R.drawable.cafe1){
                setCafe1Cuisine();

            }
            else if(i==R.drawable.cafe2){
                setCafe2Cuisine();
            }
            else if(i==R.drawable.cafe3){
               setCafe3Cuisine();
            }
            else if(i==R.drawable.cafe4){
setCafe4Cuisine();
            }
            else if(i==R.drawable.cafe5){
setCafe5Cuisine();
            }


        }

        bookdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_new_date();
            }
        });

        booktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_new_time();
            }
        });
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(texy_co>0){
                    texy_co--;
                }
                else {
                    texy_co=0;
                }
                display_count();
            }
        });

        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m=0;
                if(i==R.drawable.cafe1){
                    if(texy_co!=10) {
                        texy_co++;
                    }
                }
                else if(i==R.drawable.cafe2){
                    if(texy_co!=40) {
                        texy_co++;
                    }
                }
                else if(i==R.drawable.cafe3){
                    if(texy_co!=100) {
                        texy_co++;
                    }
                }
                else if(i==R.drawable.cafe4){
                    if(texy_co!=50) {
                        texy_co++;
                    }

                }
                else if(i==R.drawable.cafe5){
                    if(texy_co!=45) {
                        texy_co++;
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"You have reached the maximum no. of diners", Toast.LENGTH_SHORT).show();
                }

                display_count();
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               StringBuilder ab=null;

                if(texy_co==0){
                    Toast.makeText(getBaseContext(), "X " +
                            "lengkap", Toast.LENGTH_SHORT).show();
                }
                else{
                    StringBuffer cuisine = new StringBuffer();

                    ab= new StringBuilder();
                    int n =0;
                    do{
                        Cuisine myCuisine=adapter.checkedCuisines.get(n);
                        Toast.makeText(getApplicationContext(),"You chose"+cuisine, Toast.LENGTH_SHORT).show();
                        ab.append(myCuisine.getName());
                        if(n != adapter.checkedCuisines.size()-1){
                            ab.append("\n");
                        }n++;
                    }while(n<adapter.checkedCuisines.size());

                    if(adapter.checkedCuisines.size()>0)
                    {
                        cuisine.append("Cuisine: " + ab );
                    }else{
                        Toast.makeText(Main2Activity.this,"Please check an item first",Toast.LENGTH_SHORT).show();
                    }


                    String Cuisine = cuisine.toString();
                    Intent intent = new Intent();
                    //cnam= aaaa.getText().toString();
                   //intent.putExtra("cafe_name", cnam.getText().toString());
                    intent.putExtra("bookdate",bookdate.getText().toString());
                    intent.putExtra("booktime", booktime.getText().toString());
                    intent.putExtra("noofpax", nood.getText().toString());
                    intent.putExtra("cafebg", i);
                    intent.putExtra("cuisine", Cuisine);
                    setResult(Activity.RESULT_OK, intent);
                    finish();



                }
            }
        });
    }

    private void setAdapter() {
        myCuisineAdapter adapter = new myCuisineAdapter(this, getCuisines(),checkedCuisines);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

   private Cuisine[] getCuisines() {
        Cuisine[] myCuisine ={
                new Cuisine("Western",R.drawable.western_icon),
                new Cuisine("Italian",R.drawable.italian_icon),
                new Cuisine("Japanese",R.drawable.japanese_icon),
                new Cuisine("Chinese",R.drawable.chinese_icon),
                new Cuisine("Malay",R.drawable.malay_icon)

        };
        return myCuisine;
    }

    private void select_new_time() {
        final Calendar cld = Calendar.getInstance();
        int hour = cld.get(Calendar.HOUR_OF_DAY);
        int minute = cld.get(Calendar.MINUTE);

        picker=new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
               booktime.setText(hourOfDay+":"+minute);

            }
        }, hour,minute, android.text.format.DateFormat.is24HourFormat(this));

        picker.show();


    }

    private void display_count() {
        nood.setText(String.valueOf(texy_co));
    }

    private void select_new_date() {
        final Calendar cal= Calendar.getInstance();
        final int day= cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);

        pickerDialog = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat dateFormat= new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
                cal.set(year, month, dayOfMonth);
                String convertStirng = dateFormat.format(cal.getTime());
                bookdate.setText(convertStirng);
            }
        }, year, month, day);
        pickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        pickerDialog.show();
    }

    private void initUI() {
        //cafe_name=findViewById(R.id.cafe_name);
        recyclerView=findViewById(R.id.recyclerV);
        im=findViewById(R.id.cafe_bg);
        bookdate=findViewById(R.id.select_date);
        booktime=findViewById(R.id.select_tim);
        nood=findViewById(R.id.nu_of_pe);
        bookdate.setText(date);
        bookdate.setInputType(InputType.TYPE_NULL);
        down_btn=findViewById(R.id.down_btn);
        up_btn=findViewById(R.id.up_btn);
        reserve=findViewById(R.id.reserve_btn);



    }

    private void setCafe1Cuisine() {
        checkedCuisines.add(new Cuisine("Western", R.drawable.western_icon));
        checkedCuisines.add(new Cuisine("Italian", R.drawable.italian_icon));
    }

    private void setCafe2Cuisine(){
        checkedCuisines.add(new Cuisine("Chinese", R.drawable.chinese_icon));
        checkedCuisines.add(new Cuisine("Japanese", R.drawable.japanese_icon));
        checkedCuisines.add(new Cuisine("Malay", R.drawable.malay_icon));
    }

    private void setCafe3Cuisine() {
        checkedCuisines.add(new Cuisine("Western Cuisine", R.drawable.western_icon));
        checkedCuisines.add(new Cuisine("Italian Cuisine", R.drawable.italian_icon));
        checkedCuisines.add(new Cuisine("Japanese Cuisine", R.drawable.japanese_icon));
        checkedCuisines.add(new Cuisine("Chinese Cuisine", R.drawable.chinese_icon));
        checkedCuisines.add(new Cuisine("Malay Cuisine", R.drawable.malay_icon));

    }

    private void setCafe4Cuisine(){
        checkedCuisines.add(new Cuisine("Western", R.drawable.western_icon));
        checkedCuisines.add(new Cuisine("Italian", R.drawable.italian_icon));
        checkedCuisines.add(new Cuisine("Japanese", R.drawable.japanese_icon));

    }

    private void setCafe5Cuisine(){
        checkedCuisines.add(new Cuisine("Indian", R.drawable.indian_icon));
        checkedCuisines.add(new Cuisine("Malay", R.drawable.malay_icon));
    }
}

