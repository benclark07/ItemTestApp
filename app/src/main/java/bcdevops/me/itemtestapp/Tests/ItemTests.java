package bcdevops.me.itemtestapp.Tests;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import bcdevops.me.itemtestapp.Models.Region;
import bcdevops.me.itemtestapp.Tools.CallManager;
import bcdevops.me.itemtestapp.Tools.FileManager;
import bcdevops.me.itemtestapp.Models.Result;
import bcdevops.me.itemtestapp.Models.User;

public class ItemTests {
    private static int itemID = 76749;

    public static ArrayList<Result> GetAllItemTestResults(){
        ArrayList<Result> results = new ArrayList<Result>();

        results.add(PositiveAssertion_US());
        results.add(PositiveAssertion_Europe());
        results.add(PositiveAssertion_Korea());
        results.add(PositiveAssertion_Taiwan());
        results.add(PositiveAssertion_China());

        return results;
    }

    //region One item ID, all regions, one region-specific locale
    private static Result PositiveAssertion_US(){
        Date start = new Date();

        User user = new User(new Region("US", "us.battle.net", "en_US"));
        CallManager cm = new CallManager(user);
        FileManager fm = new FileManager();

        JSONObject testItem = cm.getItem(itemID);
        JSONObject truthItem = fm.getItem(user);

        boolean successful = (truthItem == null || testItem == null) ? false : truthItem.toString().equals(testItem.toString());
        long runTime = new Date().getTime() - start.getTime();

        String errorLine = successful ? "Pass." : "Fail.";
        errorLine += " " + FormatUserInfo(user);
        String messageLine = GetMessageLine(runTime);

        Result result = new Result("Item#"+itemID+", PositiveAssertion_US", errorLine, messageLine, successful ? 0 : 1, runTime);

        return result;
    }
    private static Result PositiveAssertion_Europe(){
        Date start = new Date();

        User user = new User(new Region("Europe", "eu.battle.net", "en_GB"));
        CallManager cm = new CallManager(user);
        FileManager fm = new FileManager();

        JSONObject testItem = cm.getItem(itemID);
        JSONObject truthItem = cm.getItem(itemID);

        boolean successful = (truthItem == null || testItem == null) ? false : truthItem.toString().equals(testItem.toString());
        long runTime = new Date().getTime() - start.getTime();

        String errorLine = successful ? "Pass." : "Fail.";
        errorLine += " " + FormatUserInfo(user);
        String messageLine = GetMessageLine(runTime);

        Result result = new Result("Item#"+itemID+", PositiveAssertion_Europe", errorLine, messageLine, successful ? 0 : 1, runTime);

        return result;
    }
    private static Result PositiveAssertion_Korea(){
        Date start = new Date();

        User user = new User(new Region("Korea", "kr.battle.net", "ko_KR"));
        CallManager cm = new CallManager(user);
        FileManager fm = new FileManager();

        JSONObject testItem = cm.getItem(itemID);
        JSONObject truthItem = cm.getItem(itemID);

        boolean successful = (truthItem == null || testItem == null) ? false : truthItem.toString().equals(testItem.toString());
        long runTime = new Date().getTime() - start.getTime();

        String errorLine = successful ? "Pass." : "Fail.";
        errorLine += " " + FormatUserInfo(user);
        String messageLine = GetMessageLine(runTime);

        Result result = new Result("Item#"+itemID+", PositiveAssertion_Korea", errorLine, messageLine, successful ? 0 : 1, runTime);

        return result;
    }
    private static Result PositiveAssertion_Taiwan(){
        Date start = new Date();

        User user = new User(new Region("Taiwan", "tw.battle.net", "zh_TW"));
        CallManager cm = new CallManager(user);
        FileManager fm = new FileManager();

        JSONObject testItem = cm.getItem(itemID);
        JSONObject truthItem = cm.getItem(itemID);

        boolean successful = (truthItem == null || testItem == null) ? false : truthItem.toString().equals(testItem.toString());
        long runTime = new Date().getTime() - start.getTime();

        String errorLine = successful ? "Pass." : "Fail.";
        errorLine += " " + FormatUserInfo(user);
        String messageLine = GetMessageLine(runTime);

        Result result = new Result("Item#" + itemID + ", PositiveAssertion_Taiwan", errorLine, messageLine, successful ? 0 : 1, runTime);

        return result;
    }
    private static Result PositiveAssertion_China(){
        Date start = new Date();

        User user = new User(new Region("China", "www.battlenet.com.cn", "zh_CN"));
        CallManager cm = new CallManager(user);
        FileManager fm = new FileManager();

        JSONObject testItem = cm.getItem(itemID);

        cm = new CallManager(user);
        JSONObject truthItem = cm.getItem(itemID);

        boolean successful = (truthItem == null || testItem == null) ? false : truthItem.toString().equals(testItem.toString());
        long runTime = new Date().getTime() - start.getTime();

        String errorLine = successful ? "Pass." : "Fail.";
        errorLine += " " + FormatUserInfo(user);
        String messageLine = GetMessageLine(runTime);

        Result result = new Result("Item#"+itemID+", PositiveAssertion_China", errorLine, messageLine, successful ? 0 : 1, runTime);

        return result;
    }
    //endregion

    //region Helper methods
    private static String FormatUserInfo(User user){
        return "Region name: " + user.getRegionName() + ", Host: " + user.getHost() + ",\nLocale: " + user.getLocale();
    }
    private static String GetMessageLine(long runTime){
        return "Run time:  " + String.valueOf(runTime) + " ms";
    }
    //endregion
}
