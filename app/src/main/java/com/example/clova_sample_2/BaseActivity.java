package com.example.clova_sample_2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {

            case R.id.menu_csr:

                intent = new Intent(getApplicationContext(), CsrActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_home:

                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_css:

                intent = new Intent(getApplicationContext(), CssActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_nmt:

                intent = new Intent(getApplicationContext(), NmtActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_csr_nmt_css:

                intent = new Intent(getApplicationContext(), NmtActivity2.class);
                startActivity(intent);

                return true;

            case R.id.menu_voice_chatbot:

            intent = new Intent(getApplicationContext(), VoiceChatBotActivity.class);
            startActivity(intent);

            return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }
}