package com.example.thisi.applicationx;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class EnterCustomerCodeActivity extends Activity {
    DatabaseHelper myDb;
    EditText custCodeTextbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entercustomercode);
        custCodeTextbox = (EditText)findViewById(R.id.textCustomerCode);
        setEnterKeyListenerToCustomerCodeTextbox();
        myDb = DatabaseHelper.getHelper(this);
    }

    public void onCustomerCodeOK(View view) { // Edited by Eddie 14/12/2016,
        String textCustomerCode = custCodeTextbox.getText().toString().trim().replaceAll("\\n|\\t|\\r| ","");
        searchCustomerCode(textCustomerCode);
    }

    private void searchCustomerCode(String customerCode) { // Edited by Eddie 14/12/2016
       // String tempCustCode = customerCode;

        //String custCode = customerCode.trim().replaceAll("\\n|\\t|\\r| ","");
        //custCode = custCode.trim();
        //custCode = custCode.replaceAll("\\n", "");
        //custCode = custCode.replaceAll("\\r", "");

        String price_grp_code = "";
        boolean ccExist = false;

        myDb = DatabaseHelper.getHelper(this);
        SQLiteDatabase db = myDb.getReadableDatabase();

        // Start the transaction.
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM customer WHERE CUSTOMER_CODE = '" + customerCode + "';"; // Edited by Eddie 14/12/2016, change from custCode > customerCode

            Cursor cursor = db.rawQuery(selectQuery, null);

            int rowCount = cursor.getCount();

            if (rowCount > 0) {
                cursor.moveToFirst();
                price_grp_code = cursor.getString(cursor.getColumnIndex("PRICE_GRP_CODE"));
                ccExist = true;
            }

            cursor.close();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        if(!ccExist) {
            showMessage("Error", "Customer does not exist!");
        }
        else {
            finish();
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("customer_code", customerCode); // Edited by Eddie 14/12/2016, change from custCode > customerCode
            intent.putExtra("price_group_code", price_grp_code);
            startActivity(intent);
        }
    }

    public void onCustomerCodeCancel(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }

    private void setEnterKeyListenerToCustomerCodeTextbox() { // Edited by Eddie 14/12/2016
        //EditText textCustCode = (EditText) findViewById(R.id.textCustomerCode);
        //textCustCode.setOnKeyListener(new View.OnKeyListener() {

        //http://stackoverflow.com/questions/4151499/edittext-onkeylistener-or-textwatcher-barcode-scanning
        custCodeTextbox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String textCustomerCode = custCodeTextbox.getText().toString().trim().replaceAll("\\n|\\t|\\r| ","");

                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                //event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_F5) {
                    // barcode scanner
                    searchCustomerCode(textCustomerCode);
                    return true;
                }
                return false;

            }
        });
    }


}
