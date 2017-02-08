package com.example.pccuong.appbook.connectInternet;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PCCuong on 1/29/2017.
 */

public class DowloadJSON extends AsyncTask<String,Void,String> {
    String linkDowload;
    List<HashMap<String, String>> attrs;
    StringBuilder stringBuilder;
    boolean method  = true;

    public DowloadJSON(String linkDowload) {
        this.linkDowload = linkDowload;
        method = true;

    }

    public DowloadJSON(String linkDowload, List<HashMap<String, String>> attrs) {
        this.linkDowload = linkDowload;
        this.attrs = attrs;
        method = false;
    }

    @Override
    protected String doInBackground(String... params) {
        String data = "";
        try {
            URL url = new URL(linkDowload);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (!method){
               data = methodPost(httpURLConnection);
            }else {
              data =  methodGet(httpURLConnection);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("KiemTraDOWNLOADJSONGET", data);
        return data;
    }

    private String methodGet(HttpURLConnection httpURLConnection) {
        String data = "";
        InputStream inputStream = null;
        try {
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            stringBuilder = new StringBuilder();
            String dong = "";
            while ((dong = bufferedReader.readLine()) != null) {
                stringBuilder.append(dong);

            }
            data = stringBuilder.toString();
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("KiemTraDOWNLOADJSONPOST", data);

        return data;
    }

    private String methodPost(HttpURLConnection httpURLConnection) {
        String data = "";
        String key="";
        String values="";
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            Uri.Builder builder = new Uri.Builder();
            int count = attrs.size();
            for (int i = 0; i < count; i++) {
                for (Map.Entry<String, String> entry : attrs.get(i).entrySet()) {
                     key = entry.getKey();
                     values = entry.getValue();
                }
                builder.appendQueryParameter(key,values);
            }
            String query = builder.build().getEncodedQuery();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
             bufferedWriter.write(query);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
            outputStream.close();
            data = methodGet(httpURLConnection);



        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("kiemTRAcHUOIjSONpost", data);
        return  data;

    }
}
