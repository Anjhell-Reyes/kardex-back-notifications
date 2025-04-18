package com.kardex.domain.utils;

import java.util.Objects;

public class Constants {

    //Controller

    // Base paths
    public static final String NOTIFICATIONS_BASE_PATH = "/notifications";
    public static final String NOTIFICATION_ID_PATH = "/{notificationId}";
    public static final String LOW_STOCK_PATH = "/lowStock";
    public static final String ORDER_UPDATE_PATH = "/orderUpdate";
    public static final String NOTIFICATION_STATUS_UPDATE = "/{notificationId}/status";
    public static final String GET_NOTIFICATIONS_STATUS_PATH = "/notificationStatus";

    // HTTP Status Codes
    public static final String CREATED = "201";
    public static final String CONFLICT = "409";
    public static final String OK = "200";
    public static final String NOT_FOUND = "404";

    // Default Pagination Values
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_ASC = "true";

    //Cors
    public static final String CORS_ALLOWED_PATHS = "/**";
    public static final String CORS_ALLOWED_ORIGIN = "http://127.0.0.1:5501";
    public static final String[] CORS_ALLOWED_METHODS = { "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS" };
    public static final String TOKEN_USER = "433091311";

    //use cases
    public static final Long LOW_STOCK_TYPE_ID = 1L;
    public static final Long ORDER_TYPE_ID = 2L;
    public static final Long DEFAULT_STATUS_ID = 1L;

    public static String CONTENT_LOW_STOCK_MESSAGE(String productName, String companyName) {
        return String.format("El producto %s tiene un stock bajo. Puedes contactar con %s para realizar un nuevo pedido",
                productName, companyName);
    }
    public static String CONTENT_ORDER_STATUS_UPDATE_MESSAGE(String productName, String orderStatus) {
        String status = switch (orderStatus){
            case "PENDING" -> "Pendiente";
            case "SHIPPED" -> "Enviado";
            case "IN_TRANSIT" -> "En transito";
            case "DELIVERED" -> "Entregado";
            case "CANCELED" -> "Cancelado";
            default -> "Error";
        };
        return String.format("Tu pedido de %s ha cambiado de estado a %s",
                productName, status);
    }


    private Constants() {

    }
}
