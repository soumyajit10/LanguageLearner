
package com.example.android.languagelearner;
import android.view.View;
import android.widget.Toast;




public class EventListener implements View.OnClickListener {
    @Override
    public void onClick(View view){
        Toast.makeText(view.getContext(),
                "just opened",Toast.LENGTH_SHORT).show();
    }
    public void NumberOnClick(View view){

    }
}
