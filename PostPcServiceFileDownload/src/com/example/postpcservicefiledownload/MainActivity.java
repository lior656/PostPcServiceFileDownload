package com.example.postpcservicefiledownload;





import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        
        Button culcButton = (Button) findViewById(R.id.btnAdd);
        final TextView tipRes = (TextView) findViewById(R.id.result);
        culcButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				count++;
				tipRes.setText(Integer.toString(count));		
			}
		});
        
        final MainActivity thisA = this;
        Button dButton = (Button) findViewById(R.id.download1);
        dButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(thisA, FileDownload.class);
			    intent.putExtra("urlpath", "http://www.angelfire.com/tx5/worldofmagic/attractions.htm#rides");
			    intent.putExtra("fileName", "harryPotter.html");
			    startService(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.counter, menu);
        return true;
    }
    
}
