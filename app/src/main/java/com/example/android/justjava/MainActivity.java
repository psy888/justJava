package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    /**
     * This method is called when the order button is clicked.
     */

    int quantity = 0;

    public void submitOrder(View view) {
        // Имя клиента
        EditText clientName = findViewById(R.id.clientName);
        String name = clientName.getText().toString();
        // Взбитые сливки
        CheckBox slivkiCheckBox = findViewById(R.id.slivkiCheckBox);
        boolean hasSlivki = slivkiCheckBox.isChecked();
        //Шоколад
        CheckBox chokolate = findViewById(R.id.chokolateCheckBox);
        boolean hasChokolate = chokolate.isChecked();
        //Price method
        int price = calculatePrice();

        //Final Message
        displayMessage(createOrderSummery(price, hasSlivki, hasChokolate, name));

        //Toast message
        Toast toast = Toast.makeText(this, "Заказ обработан!", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * This Method decrement quantity
     */
    public void decrement(View view) {
        display(--quantity);
        //int price = calculatePrice();
        displayMessage(String.valueOf(calculatePrice()));
    }

    /**
     * This Method increment quantity
     */
    public void increment(View view) {
        display(++quantity);
        displayMessage(String.valueOf(calculatePrice()));
    }
    /**
     * ----- промежуточная цена-----
     */

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }

    /**
     * This method displays the given price on the screen.
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Просчет цены заказа
     *
     * @return количество * цену
     */
    private int calculatePrice() {
        return 5 * quantity;
    }

    /**
     * @param total        - Общая сумма заказа
     * @param topSlivki    -  Добавлять сливки
     * @param topChokolate - добавлять шоколад
     * @param name - имя клиента
     * @return - возвращает текст заказа с входящими параметрами
     */
    private String createOrderSummery(int total, boolean topSlivki, boolean topChokolate, String name) {
        return "Имя: " + name + "\nДобавить Взбитые сливки? :" + topSlivki + "\nДобавить шоколад? :" + topChokolate + "\nКоличество: " + quantity + " \nИтого: " + total + "грн.\nСпасибо за покупку!";
    }

}