package scroll.qll.com.myscroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DefineStringActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOne,btnTwo,btnThree,btnFour;
    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        btnOne = (Button)findViewById(R.id.btn_one);
        btnTwo = (Button)findViewById(R.id.btn_two);
        btnThree = (Button)findViewById(R.id.btn_three);
        btnFour = (Button)findViewById(R.id.btn_four);
        tvTest = (TextView)findViewById(R.id.tv_test);

        btnOne.setText(getResources().getString(R.string.btn_one));
        btnOne.setOnClickListener(this);
        btnTwo.setText(getResources().getString(R.string.btn_two));
        btnTwo.setOnClickListener(this);
        btnThree.setText(getResources().getString(R.string.btn_three));
        btnThree.setOnClickListener(this);
        btnFour.setText(getResources().getString(R.string.btn_four));
        btnFour.setOnClickListener(this);
        /**
         *
         <string name="app_name">MyScroll</string>
         <string name="title">%1$s test</string>
         <string name="title2">%1$s %2$s</string>
         <string name="version_name">1.2</string>
         * */
        String appName = getResources().getString(R.string.app_name);
        String versionName = getResources().getString(R.string.version_name);

        String title = getResources().getString(R.string.title, appName); //MyScroll test
        String title2 = getResources().getString(R.string.title2, appName, versionName);//MyScroll 1.2
        String title3 = getResources().getString(R.string.title3,appName);
        String title4 = getResources().getString(R.string.signature,getResources().getString(R.string.my_app_name)+" v",getResources().getString(R.string.my_version_name));
        tvTest.setTextSize(20);
        tvTest.setText("title: "+title+"\ntitle2: "+title2+"\ntitle3: "+title3+"\ntitle4: "+title4);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_one:
            case R.id.btn_two:
            case R.id.btn_three:
                Toast.makeText(getApplicationContext(), "你点击了1 2 3 button", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_four:
                Toast.makeText(getApplicationContext(),"你点击了4button",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

}
