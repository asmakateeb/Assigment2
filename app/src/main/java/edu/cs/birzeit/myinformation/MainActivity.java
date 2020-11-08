package edu.cs.birzeit.myinformation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    private EditText edtName;
    private TextView result;
    public static final String Flag="Flag";
    private Button btnSend;
    public static final String Name="Name";
   private Spinner spenergender;
    public static final String HIGHT="Height";
   private EditText txtName;
   private SharedPreferences prefs;
   public static final String Wieght="Wieght";
    public static final String Spinner="Spinner";
    private CheckBox chex;
    private SharedPreferences .Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        btnSend=findViewById(R.id.btnSend);
        chex=findViewById(R.id.checkbox);
        spenergender = findViewById(R.id.spenergender);
        txtName=findViewById(R.id.txtName);
setupViews();
setupSharedprefs();
Check();
        addToSpinner();           }

    public void btnOnclickcalculate(View view){
        String h=height.getText().toString();
        String w=weight.getText().toString();
        if(h != null && !"".equals(h)
            && w !=null&& !"".equals(w)){
            double height =Double.parseDouble(h)/100;
            double weight =Double.parseDouble(w);
            double BMI =weight/(height*height);
            BMI(BMI);

        }
    }
    private void BMI(double bmi){
        String bmiresult="";
        if (Double.compare(bmi, 15f) <= 0) {
           bmiresult="Severe Thinness";
        } else if (Double.compare(bmi, 15f) > 0  &&  Double.compare(bmi, 16f) <= 0) {
            bmiresult="Moderate Thinness";
        } else if (Double.compare(bmi, 16f) > 0  &&  Double.compare(bmi, 18.5f) <= 0) {
            bmiresult ="Mild Thinness ";
        } else if (Double.compare(bmi, 18.5f) > 0  &&  Double.compare(bmi, 25f) <= 0) {
            bmiresult = "Normal";
        } else if (Double.compare(bmi, 25f) > 0  &&  Double.compare(bmi, 30f) <= 0) {
            bmiresult = "Over weight";
        } else if (Double.compare(bmi, 30f) > 0  &&  Double.compare(bmi, 35f) <= 0) {
            bmiresult = "Obese Class I";
        } else if (Double.compare(bmi, 35f) > 0  &&  Double.compare(bmi, 40f) <= 0) {
            bmiresult = "Obese Class II";
        } else {
            bmiresult ="Obese Class III";
        }
        bmiresult = bmi + "\n" + bmiresult;
        result.setText(bmiresult);


    }
    private void addToSpinner(){
        String[]array=new String[]{"","Female","Male"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,array);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spenergender.setAdapter(adapter);


    }private void setupSharedprefs(){
     prefs= PreferenceManager.getDefaultSharedPreferences(this);
     editor=prefs.edit();
}
private void setupViews(){
edtName=findViewById(R.id.txtName);
height=findViewById(R.id.height);
weight=findViewById(R.id.weight);
spenergender=findViewById(R.id.spenergender); chex=findViewById(R.id.checkbox);
}
private void Check(){
boolean flag=prefs.getBoolean(Flag,false);
if(flag){
String name =prefs.getString(Name,"");
String Height=prefs.getString(HIGHT,"");
String Weight=prefs.getString(Wieght,"");
edtName.setText(name);
height.setText(Height);
weight.setText(Weight); chex.setChecked(true);
}
}
    public void onclickSave(View view) {
        String name = edtName.getText().toString();
        String Height=height.getText().toString();
        String Weight=weight.getText().toString();
if(chex.isChecked()){
editor.putString(Name,name);
editor.putString(HIGHT,Height);
editor.putString(Wieght,Weight);
editor.putBoolean(Flag,true);
editor.commit();
}
}
    public void onClicksend(View view) {
        String msg =btnSend.getText().toString();
        Intent intent = new Intent(this, Timer.class);
        intent.putExtra("data", msg);
        startActivity(intent);}
}