package com.selfmate.mes.selfmate.STATIC_RESOURCES;

/**
 * Created by vicki_mes on 11/20/2017.
 */

public class TOPIC {
    public static int MOVIE =1;
    public static int FINANCE =2;
    public static int TECHNOLOGY =3;
    public static int FOOD =4;
    public static int SPORT =5;
    public static int SCIENCE =6;
    public static int BRANDS =7;
    public static int HOT =8;

    public  static String getTopic(int i){
        switch (i){
            case 1:
                return "MOVIE";

            case 2:
                return "FINANCE";

            case 3:
                return "TECHNOLOGY";
            case 4:
                return "FOOD";
            case 5:
                return "SPORT";
            case 6:
                return "SCIENCE";
            case 7:
                return "BRANDS";
            case 8:
                return "HOT";
            default:return "N.O.N";
        }
    }
}
