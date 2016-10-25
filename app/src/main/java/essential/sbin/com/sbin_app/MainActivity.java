package essential.sbin.com.sbin_app;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private static final int MENU_ITEM_LOGOUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.editText2);
                String entry = et.getText().toString();

                Snackbar.make(view, "You entered: " + entry, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i<10;i++){
            builder.append(getString(R.string.lorem_ipsum) + "\n\n");
        }

        TextView tv = (TextView) findViewById(R.id.long_text);
        tv.setText(builder);

        ImageView iv = (ImageView) findViewById(R.id.photo);
        //iv.setImageResource(R.drawable.jacket101);

        String imageName = "jacket101";
        /*int res = getResources().getIdentifier(imageName,"drawable", getPackageName());
        iv.setImageResource(res);*/

        try {
            InputStream stream = getAssets().open(imageName+".png");
            Drawable d = Drawable.createFromStream(stream,null);
            iv.setImageDrawable(d);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Snackbar.make(coordinatorLayout, "Landscape mode: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else{
            Snackbar.make(coordinatorLayout, "Potrait mode: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.add(0,MENU_ITEM_LOGOUT, 102, R.string.logout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(coordinatorLayout, "You selected Option menu", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        else if (id == R.id.action_about) {
            Snackbar.make(coordinatorLayout, "You selected About menu", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        else if (id == MENU_ITEM_LOGOUT) {
            Snackbar.make(coordinatorLayout, "You selected logout", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        else {

            return super.onOptionsItemSelected(item);
        }
    }

    /*public void buttonHandler(View view) {
        EditText et = (EditText) findViewById(R.id.editText2);
        String name = et.getText().toString();
        Snackbar.make(coordinatorLayout, "Your name is "+ name, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }*/
}
