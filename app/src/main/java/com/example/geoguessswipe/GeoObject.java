package com.example.geoguessswipe;

public class GeoObject {


    private String mGeoName;
    private int mGeoImageName;

    public GeoObject(String mGeoName, int mGeoImageName) {
        this.mGeoName = mGeoName;
        this.mGeoImageName = mGeoImageName;
    }

    public void setmGeoName(String mGeoName) {
        this.mGeoName = mGeoName;
    }

    public void setmGeoImageName(int mGeoImageName) {
        this.mGeoImageName = mGeoImageName;
    }


    public String getmGeoName() {
        return mGeoName;
    }

    public int getmGeoImageName() {
        return mGeoImageName;
    }

    public static final String[] PRE_DEFINED_GEO_OBJECT_NAMES = {

            "Denmark",

            "Canada",

            "Bangladesh",

            "Kazachstan",

            "Colombia",

            "Poland",

            "Malta",

            "Thailand",

    };

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {


            R.drawable.img1_yes_denmark,

            R.drawable.img2_no_canada,

            R.drawable.img3_no_bangladesh,

            R.drawable.img4_yes_kazachstan,

            R.drawable.img5_no_colombia,

            R.drawable.img6_yes_poland,

            R.drawable.img7_yes_malta,

            R.drawable.img8_no_thailand,



    };
}
