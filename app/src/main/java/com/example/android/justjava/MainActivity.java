package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;

    /**
     * This method is called when the order button is clicked.
     */



    public void submitOrder(View view) {
        // Имя клиента
        EditText clientName = findViewById(R.id.clientName);
        String name = clientName.getText().toString();
        // Взбитые сливки
        CheckBox slivkiCheckBox = findViewById(R.id.slivkiCheckBox);
        boolean hasSlivki = slivkiCheckBox.isChecked();
        //Шоколад
        CheckBox chocolate = findViewById(R.id.chocolateCheckBox);
        boolean hasChocolate = chocolate.isChecked();
        //Price method
        int price = calculatePrice(hasSlivki, hasChocolate);
        //Final Message
        createOrderSummery(price, hasSlivki, hasChocolate, name);

//        //Toast message
//        Toast toast = Toast.makeText(this, "Заказ обработан!", Toast.LENGTH_SHORT);
//        toast.show();
    }

    /**
     * This Method decrement quantity
     */

    public void decrement(View view) {
        Button decrement = findViewById(R.id.decrement);
        Button increment = findViewById(R.id.increment);
        if (quantity == 1) {
            decrement.setEnabled(false);
            Toast.makeText(this, getString(R.string.decrement), Toast.LENGTH_SHORT).show();
            return;
        } else {
            increment.setEnabled(true);
        }
        display(--quantity);
    }

    /**
     * This Method increment quantity
     */

    public void increment(View view) {
        Button decrement = findViewById(R.id.decrement);
        Button increment = findViewById(R.id.increment);
        if (quantity == 100) {
            increment.setEnabled(false);
            Toast.makeText(this, getString(R.string.increment), Toast.LENGTH_SHORT).show();
            return;
        } else {
            decrement.setEnabled(true);
        }
        display(++quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }


    /**
     * Просчет цены заказа
     * @param slivki - добавлять сливки
     * @param chocolate - добавлять шоколад
     * @return количество * цену
     */
    private int calculatePrice(boolean slivki, boolean chocolate) {
        int price = 5;
        if (slivki) {
            price += 1;
        }
        if (chocolate) {
            price += 2;
        }
        return price * quantity;
    }

    /**
     * @param total        - Общая сумма заказа
     * @param topSlivki    -  Добавлять сливки
     * @param topChocolate - добавлять шоколад
     * @param name - имя клиента
     */
    private void createOrderSummery(int total, boolean topSlivki, boolean topChocolate, String name) {
        String[] addresses = {"mail@mail.com"};
        String order = getString(R.string.nameStr, name);
        order += "\n" + getString(R.string.addSlivki) + topSlivki;
        order += "\n" + getString(R.string.addChocolate) + topChocolate;
        order += "\n" + getString(R.string.orderAmount) + quantity;
        order += "\n" + getString(R.string.total) + total + getString(R.string.currency);
        order += "\n" + getString(R.string.thanks);

        //Create Intent
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //только для email приложений
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subjectIntent, name));
        intent.putExtra(Intent.EXTRA_TEXT, order);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}