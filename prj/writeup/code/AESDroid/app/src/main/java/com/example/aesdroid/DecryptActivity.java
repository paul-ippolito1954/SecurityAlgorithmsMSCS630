package com.example.aesdroid;

import androidx.appcompat.app.AppCompatActivity;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

public class DecryptActivity extends AppCompatActivity {


    EditText mMessage;
    EditText mKey;
    EditText mCipherText;

    AESCipher aes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
    }

    /**
     * This will run the decryption process
     * if the key and plaintext are both hex
     * and the key is 32 bit. if the key is correct
     * to the specific ciphertext, the unencrypted message will be returned
     * @param view
     */
    public void decryptMessage(View view){

        aes = new AESCipher();

        mMessage = (EditText)findViewById(R.id.decryptMessage);
        mKey = (EditText)findViewById(R.id.decryptKey);
        mCipherText = (EditText) findViewById(R.id.deciphertext);

        String messageDecrypt = mMessage.getText().toString();
        String keyDecrypt = mKey.getText().toString();


        // check both fields exist
        if (!messageDecrypt.isEmpty() && keyDecrypt != null){

            // check both fields are hex
            if(isHex(messageDecrypt) && isHex(keyDecrypt)){

                // make sure key is correct length
                if(keyDecrypt.length() == 32){

                    // do decryption
                    String decodedHex = aes.decrypt(messageDecrypt.toUpperCase(),
                            keyDecrypt.toUpperCase());

                    System.out.println(decodedHex);

                    //String decodedText = hexToCharacter(decodedHex);
                    System.out.println(decodedHex);
                    mCipherText.setText(decodedHex);
                    mMessage.setTextColor(Color.BLACK);
                    mKey.setTextColor(Color.BLACK);
                }
                // error, notify user
                else
                    mCipherText.setText("Key must be 32 bits");
            }

            // notify user of their errors
            else{
                mMessage.setTextColor(Color.RED);
                mMessage.setText("Message must be in hex");
                mKey.setTextColor(Color.RED);
                mKey.setText("Key must be in hex");
            }
        }

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

    /**
     * Method to validate hex Strings
     * @param s
     * @return true or false of valid hex
     */
    protected boolean isHex(String s){
        boolean isHex = s.matches("^[0-9a-fA-F]+$");
        return isHex;
    }

}
