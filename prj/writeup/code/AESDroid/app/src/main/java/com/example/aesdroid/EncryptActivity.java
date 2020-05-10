package com.example.aesdroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class EncryptActivity extends AppCompatActivity {

    EditText mMessage;
    EditText mKey;
    EditText mCipherText;
    AESCipher aes;

    private boolean generatedKey = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    public void goMenu(View view){
        Intent intent = new Intent();
    }

    /**
     * When the button is clicked, begin encryption process
     * Get the String values of Message and Key from EditTexts
     * Convert them to Hex
     * Send them in to AEScipher.AES to encrypt
     * Display the cipherText
     * @param view
     */
    public void encryptMessage(View view){

        mMessage = (EditText)findViewById(R.id.editMessage);
        mKey = (EditText)findViewById(R.id.editKey);
        mCipherText = (EditText) findViewById(R.id.cipherText);
        String plainText = mMessage.getText().toString();
        String key = mKey.getText().toString();
        aes = new AESCipher();
        String hexKey = null;
        boolean isHex = false;

//        // Both must be at least 128-bits, so they must be 16 length Strings
//        // or 32 hex. We can achieve this by padding
//        if (plainText.length() !=16){
//            plainText = padRight(plainText, 16);
//            System.out.println("Your plaintext: " + plainText);
//        }

        // generate random 32-bit hex key for encryption
        if (key.isEmpty() || key == null){
            key = getRandomHexString(32);
        }

        //generated valid hex key because none was provided
        if (generatedKey){
            hexKey = key;
        }
        //user provided key
        else{

            isHex = key.matches("^[0-9a-fA-F]+$");

            if (key.length() == 32 && isHex)
                hexKey = key;
            else
                if (key.length() == 16)
                    hexKey = toHex(key);
        }

        if (!plainText.isEmpty() && hexKey !=null && hexKey.length() == 32){

            System.out.println(hexKey);
            // do Encryption
            String cipherText = aes.encrypt(plainText, hexKey);

            // display ciphertext message and the key in hex
            mKey.setText(hexKey);
            mCipherText.setText(cipherText);
        }

        else{
            if (hexKey.length() != 32)
                mKey.setText("Key must be either 16 characters or 32 bit hex");

            if (plainText.isEmpty())
            mMessage.setText("Message is required");
        }

    }

    /**
     * Generates random hex key if one is not provided
     * set generatedKey to true so it knows to use generated key
     * @return the random hex key
     */
    private String getRandomHexString(int numchars){

        generatedKey = false;
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        generatedKey = true;
        return sb.toString().substring(0, numchars).toUpperCase();
    }

    /**
     * Converts given String to Hex
     * @param s : string to be converted
     *          StringBuffer sb: StringBuffer we will use to turn our Hex into an actual String
     *          char[] ch: CharArray to convert each character to Hex
     *          String hex
     */
    protected String toHex(String s){

        StringBuffer sb = new StringBuffer();
        char[] ch = s.toCharArray();

        // for each character in ch
        for (int i = 0; i < ch.length; i++){
            // convert to Hex and append to sb
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        // return sb as String hex, converted to uppercase
        String hex = sb.toString();
        return hex.toUpperCase();
    }


}
