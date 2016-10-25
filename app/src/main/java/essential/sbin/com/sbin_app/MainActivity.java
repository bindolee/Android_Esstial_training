package essential.sbin.com.sbin_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    private CoordinatorLayout coordinatorLayout;
    private static final int MENU_ITEM_LOGOUT = 1000;
    private static String webUrl = "https://www.facebook.com/H-Sport-1388674971422183/";
    private static String email = "info@hplussport.com";
    private List<Product> products = DataProvider.productList;

    private static final int DETAIL_REQUEST = 1111;
    public static final String RETURN_MESSAGE = "RETURN_MESSAGE";

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
                String[] addresses = {email};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "information request");
                intent.putExtra(Intent.EXTRA_TEXT,"plz send some info");
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }


            }
        });
        /*String[] items = getResources().getStringArray(R.array.clothing);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, items);*/
        ListView lv = (ListView) findViewById(R.id.listview);
        ProductListAdapter adapter =
                new ProductListAdapter(this, R.layout.list_item, products);
        lv.setAdapter(adapter);

        //PUt on click listener when user touches the list view item.
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Product product = products.get(position);
                intent.putExtra(PRODUCT_ID, product.getProductId());
                startActivityForResult(intent, DETAIL_REQUEST);
            }
        });
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
/*            Snackbar.make(coordinatorLayout, "You selected About menu", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == MENU_ITEM_LOGOUT) {
            Snackbar.make(coordinatorLayout, "You selected logout", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        else if (id == R.id.action_web) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
            if (webIntent.resolveActivity(getPackageManager()) != null){
                startActivity(webIntent);
            }
            return true;
        }
        else if (id == R.id.action_cart) {
            Snackbar.make(coordinatorLayout, "You selected shopping cart", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return true;
        }
        else {

            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DETAIL_REQUEST){
            if (resultCode == RESULT_OK){
                String message = data.getStringExtra(RETURN_MESSAGE);
                Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_LONG)
                        .setAction("Go to shopping cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this,
                                        "Going to cart", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        }
    }

    /*public void buttonHandler(View view) {
        EditText et = (EditText) findViewById(R.id.editText2);
        String name = et.getText().toString();
        Snackbar.make(coordinatorLayout, "Your name is "+ name, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }*/
}
