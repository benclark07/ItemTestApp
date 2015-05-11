package bcdevops.me.itemtestapp.Tools;

import android.app.Activity;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Date;

import bcdevops.me.itemtestapp.Models.User;

public class CallManager {
    private static String SET_URI = "/api/wow/item/set/";
    private static String ITEM_URI = "/api/wow/item/";
    private User user;

    public CallManager(User user){
        this.user = user;
    }
    public JSONObject getItem(int itemID){
        String uri = "http://" + user.getHost() + ITEM_URI + String.valueOf(itemID);
        DataAccess access = new DataAccess(uri);

        JSONObject json = access.getJson();
        return json;
    }

    public JSONObject getItemSet(int itemSetID){
        String uri = "http://" + user.getHost() + SET_URI + String.valueOf(itemSetID);
        DataAccess access = new DataAccess(uri);

        JSONObject json = access.getJson();
        return json;
    }

    class DataAccess {
        private String url;
        private JSONObject json = null;
        private Thread readerThread;

        public DataAccess(String url){
            this.url = url;
            readerThread = new AsyncReaderThread();
            readerThread.start();
        }

        public JSONObject getJson(){
            long start = new Date().getTime();
            while(json ==  null && (new Date().getTime() - start < 5000)){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return json;
        }

        class AsyncReaderThread extends Thread{
            @Override
            public void run(){
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                DefaultHttpClient client = new DefaultHttpClient();

                InputStream content = null;
                HttpGet request;
                String response = "";

                try {
                    request = new HttpGet( new URI( url ) );
                    response = client.execute( request, responseHandler );
                    json = new JSONObject(response.toString());
                } catch ( Exception e ) {
                    e.printStackTrace();
                    JSONObject temp = new JSONObject();
                    try{
                        temp.put("Successful", 0);
                        temp.put("Result", "Error");
                    }catch (JSONException e1) {
                        e1.printStackTrace();
                    }

                    json = temp;
                }finally {
                    if (content != null)
                        try {
                            content.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
    }
}
