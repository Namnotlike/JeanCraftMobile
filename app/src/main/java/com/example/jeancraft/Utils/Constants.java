package com.example.jeancraft.Utils;

public class Constants {
    public static final String baseUrl = "https://jeancraft-be.onrender.com";

    //API User
    public static final String loginAPI = "/User/login";
    public static final String registerAPI = "/User/register";
    public static final String getUserbyIdAPI = "/api/UserProfile/";

    //API Product
    public static final String  listProductAPI = "/api/Product/product/getProductList";
    public static final String  getProductByIdAPI = "/api/Product/product/getProductByID/";
    public static final String  searchProductAPI = "/api/Product/product/searchProduct";
}
