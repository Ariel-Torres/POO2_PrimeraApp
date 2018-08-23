package pilet.com.primeraapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Instancias de controles
        Button btn = (Button)findViewById(R.id.btnloggear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tx = new Toast(getApplicationContext());
                //view.setBackgroundColor();
                Toast.makeText(getApplicationContext(),"Logueado!",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this,MainActivity.class));

            }
        });

    }
}
