package com.example.meta.utils;

import com.example.meta.model.GioHang;
import com.example.meta.model.User;

import java.util.List;

public class Utils {

      public static final String ipaddress = "192.168.100.38:8080";

      public static final String BASE_URL="http://"+ ipaddress +"/meta/ApiAppMeta/"; //ở nhà

    public static final String URL_IMAGE = "http://"+ ipaddress +"/meta/public/assets/images/products/";
    public static final String URL_IMGAEUSSER = "http://"+ ipaddress +"/meta/public/assets/images/testimonials/";
    public static List<GioHang> manggiohang;
    public static User user_current = new User();


//http://192.168.100.24:8080/banhang/
}
