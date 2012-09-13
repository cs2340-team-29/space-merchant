
package cs2340.android.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private TextView topTextView;
    private EditText topTextInput;
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        topTextView = ( TextView ) findViewById( R.id.topTextView );
        topTextInput = ( EditText ) findViewById( R.id.topTextInput );
    }
    
    public void sendInputToText( View v )
    {
        topTextView.setText( topTextInput.getEditableText() );
    }
}
