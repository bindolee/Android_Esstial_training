package essential.sbin.com.sbin_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sbin on 10/25/2016.
 */

public class ProductListAdapter extends ArrayAdapter<Product> {
    private List<Product> products;

    public ProductListAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        products = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false );
        }
        Product product = products.get(position);
        TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
        nameText.setText(product.getName());
        return convertView;
    }
}
