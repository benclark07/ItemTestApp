package bcdevops.me.itemtestapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;

public class MainActivity extends Activity {
    //region Class Variables
    private TableRow mTestCases;
    private TableRow mDiagrams;
    private TableRow mExtraMaterial;
    //endregion

    //region Activity Life-Cycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestCases = (TableRow)findViewById(R.id.mp_testCase_row);
        mDiagrams = (TableRow)findViewById(R.id.mp_diagrams_row);
        mExtraMaterial = (TableRow)findViewById(R.id.mp_youtube_demo_row);

        setLinks();
    }
    //endregion
    private void setLinks(){
        final MainActivity mainActivity = this;

        mTestCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, TestCasesActivity.class);
                startActivity(intent);
            }
        });

        mDiagrams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, DiagramsActivity.class);
                startActivity(intent);
            }
        });

        mExtraMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.youtube.com/watch?v=nIqEXwL2wI8";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
    // Private Helper Methods
}
