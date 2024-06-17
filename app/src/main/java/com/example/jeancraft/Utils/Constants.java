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

    //API ShoppingCart
    public static final String  createShoppingCart = "/api/ShoppingCart/Create";
    public static final String  getShoppingCartAPI = "/api/ShoppingCart/";
    public static final String  updateShoppingCartAPI = "/api/ShoppingCart/Update";
    public static final String  getShoppingCartById = "/api/ShoppingCart/GetById";
    public static final String  getCartForUserAPI = "/api/ShoppingCart/GetCartForUser";

    //API CartItem
    public static final String  updateCartItemAPI = "/api/CartItem/update";
    public static final String  deleteCartItemAPI = "/api/CartItem/delete";
    public static final String getByIdAPI = "/api/CartItem/getById";
    public static final String  createCartItemAPI = "/api/CartItem/create";
    public static final String  getCartItemAPI = "/api/CartItem/";


}
