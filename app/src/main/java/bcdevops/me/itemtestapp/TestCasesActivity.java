package bcdevops.me.itemtestapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import bcdevops.me.itemtestapp.Tools.Enums;
import bcdevops.me.itemtestapp.Tools.TestManager;
import bcdevops.me.itemtestapp.Models.Result;

public class TestCasesActivity extends Activity {
    private ArrayList<Result> resultArrayList;
    private ToggleButton testsToggle;
    private ToggleButton setsToggle;
    private ToggleButton allToggle;
    private ProgressBar loading;

    private Enums.Toggles mToggles;

    private TestManager tm;

    //region Activity life-cycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cases);

        testsToggle = (ToggleButton)findViewById(R.id.item_tests_toggle);
        setsToggle = (ToggleButton)findViewById(R.id.item_set_tests_toggle);
        allToggle = (ToggleButton)findViewById(R.id.all_toggle);
        loading = (ProgressBar)findViewById(R.id.loading);

        tm = new TestManager();
    }
    //endregion

    //region UI handlers
    public void ToggleHandlerItems(View view){
        mToggles = Enums.Toggles.ITEM;
        setToggles();

        resultArrayList = tm.RunItemTests();

        populateListView();
    }
    public void ToggleHandlerSets(View view){
        mToggles = Enums.Toggles.SET;
        setToggles();

        resultArrayList = tm.RunSetTests();

        populateListView();
    }
    public void ToggleHandlerAll(View view){
        mToggles = Enums.Toggles.ALL;
        setToggles();

        resultArrayList = tm.RunAllTests();

        populateListView();
    }
    //endregion

    //region Helper methods
    private void populateListView() {
        TestCasesListAdapter adapter = new TestCasesListAdapter(this);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }
    private void startLoad(){
        setEnabled(false);
        loading.setVisibility(View.VISIBLE);
    }
    private void endLoad(){
        setEnabled(true);
        loading.setVisibility(View.GONE);
    }
    private void setEnabled(boolean enabled){
        TableLayout layout = (TableLayout) findViewById(R.id.test_table_layout);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if(child.getId() != R.id.loading)
                child.setEnabled(enabled);
        }
    }
    private void setToggles(){
        switch(mToggles){
            case ITEM:
                testsToggle.setChecked(true);
                setsToggle.setChecked(false);
                allToggle.setChecked(false);
                break;
            case SET:
                testsToggle.setChecked(false);
                setsToggle.setChecked(true);
                allToggle.setChecked(false);
                break;
            case ALL:
                testsToggle.setChecked(false);
                setsToggle.setChecked(false);
                allToggle.setChecked(true);
                break;
            default:
                break;
        }
    }
    //endregion

    // Class specific list adapter
    private class TestCasesListAdapter extends ArrayAdapter<Result> {
        private TestCasesActivity testCasesActivity;

        public TestCasesListAdapter(TestCasesActivity testCasesActivity) {
            super(testCasesActivity.getApplicationContext(), R.layout.result_row, R.id.test_name, resultArrayList);
            this.testCasesActivity = testCasesActivity;
        }

        @Override
        public View getView(final int position,View view,ViewGroup parent) {
            super.getView(position, view, parent);

            View itemView = view;

            if (itemView == null) {
                itemView = testCasesActivity.getLayoutInflater().inflate(R.layout.result_row,
                        parent, false);
            }

            Result rowResult = resultArrayList.get(position);

            ImageView icon = (ImageView)itemView.findViewById(R.id.result_image);

            if(rowResult.getNumErrors() == 0)
                icon.setImageDrawable(getResources().getDrawable(R.drawable.pass));
            else
                icon.setImageDrawable(getResources().getDrawable(R.drawable.fail));

            TextView testName = (TextView)itemView.findViewById(R.id.test_name);
            testName.setText(rowResult.getTestName());

            TextView error = (TextView)itemView.findViewById(R.id.error_count);
            error.setText(rowResult.getErrorLine());

            TextView message = (TextView)itemView.findViewById(R.id.message);
            message.setText(rowResult.getMessageLine());

            return itemView;
        }
    }
}
