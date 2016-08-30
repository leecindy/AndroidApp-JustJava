/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 0;
    boolean whippedCream = false;
    boolean chocolate = false;
    int priceCoffee = 5;
    int priceWhippedCream = 1;
    int priceChocolate = 2;
    String showName = "";
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        int total = calculatePrice();
        createOrderSummary(total);


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Java Order for " + showName);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivity(intent);

    }

    public void increment(View view) {

        if (quantity<100) {
            quantity++;
            displayQuantity(quantity);
        }
        else {
            quantity = 100;
            Toast toast = Toast.makeText(this, "You can only order 100 cups of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void decrement(View view) {

        if (quantity>0) {
            quantity--;
            displayQuantity(quantity);
        }
        else {
            quantity = 0;
            Toast toast = Toast.makeText(this, "You can not order less than 0 cup of coffee", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void hasWhippedCream(View view) {
        whippedCream = ((CheckBox) view).isChecked();
    }

    public void hasChocolate(View view) {
        chocolate = ((CheckBox) view).isChecked();
    }

    private int calculatePrice() {
        int price = priceCoffee;
        if (whippedCream)
            price = price + priceWhippedCream;
        if (chocolate)
            price = price + priceChocolate;

        return quantity*price;
    }



    public void createOrderSummary(int total) {
        EditText name = (EditText)findViewById(R.id.name);
        showName = name.getText().toString();
        message = getString(R.string.show_name, showName) +
                "\n" + getString(R.string.add_cream, whippedCream) +
                "\n" + getString(R.string.add_chocolate, chocolate) +
                "\n" + getString(R.string.show_quantity, quantity) +
                "\n" + getString(R.string.show_total, total) +
                "\n" + getString(R.string.thank_you);

        //displayMessage(message);
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView summaryTextView = (TextView) findViewById(R.id.summary_text_view);
//        summaryTextView.setText(message);
//    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}