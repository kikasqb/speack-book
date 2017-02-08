package com.example.pccuong.appbook.Presenter.TrangChu.XulyMenu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.pccuong.appbook.Model.DangNhap_DangKy.LoginBook;
import com.example.pccuong.appbook.Model.ObjectClass.Categories;
import com.example.pccuong.appbook.Model.XuLyHomePage.DataJsonMenu;
import com.example.pccuong.appbook.View.HomePage.HomePageActivity;
import com.example.pccuong.appbook.View.HomePage.ViewXuLyMenu;
import com.example.pccuong.appbook.connectInternet.DowloadJSON;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by PCCuong on 2/6/2017.
 */

public class PresenteLogicXuLyMenu implements  IPresenteXulyMenu {
    ViewXuLyMenu viewXuLyMenu;
    String tennguoidung = "";
    public  PresenteLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }
    @Override
    public void layDanhSachMenu() {
        List<Categories> categoriesList;
        String dataJSON ="";
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String duongdan = "http://192.168.17.2/Appbook/sanpham.php";
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id","9");
        attrs.add(hashMap);
        DowloadJSON dowloadJSON = new DowloadJSON(duongdan,attrs);
        dowloadJSON.execute();

        try {
            dataJSON = dowloadJSON.get();
            DataJsonMenu dataJsonMenu = new DataJsonMenu();
           categoriesList = dataJsonMenu.paraseJson(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(categoriesList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
   Log.d("kiemtrachuoimotmach", attrs.toString());
    }

    @Override
    public AccessToken layTenNguoiDungFacebook() {
        LoginBook loginBook = new LoginBook();
        AccessToken accessToken =  loginBook.layTockenFacebookRuntime();
        DataJsonMenu dataJsonMenu = new DataJsonMenu();
        return  accessToken;
    }

}
