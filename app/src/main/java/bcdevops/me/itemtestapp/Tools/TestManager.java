package bcdevops.me.itemtestapp.Tools;

import android.app.Activity;

import java.util.ArrayList;

import bcdevops.me.itemtestapp.Tests.ItemTests;
import bcdevops.me.itemtestapp.Tests.SetTests;
import bcdevops.me.itemtestapp.Models.Result;

public class TestManager {
    private ArrayList<Result> results;

    public ArrayList<Result> RunItemTests(){
        results = ItemTests.GetAllItemTestResults();

        return results;
    }

    public ArrayList<Result> RunSetTests(){
        results = SetTests.GetAllSetTestResults();

        return results;
    }

    public ArrayList<Result> RunAllTests(){
        results = ItemTests.GetAllItemTestResults();
        ArrayList<Result> temp = SetTests.GetAllSetTestResults();

        for(int a = 0; a < temp.size(); a++){
            results.add(temp.get(a));
        }

        return results;
    }
}
