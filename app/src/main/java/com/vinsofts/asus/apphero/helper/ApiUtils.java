package com.vinsofts.asus.apphero.helper;

import com.vinsofts.asus.apphero.Utils.APIService;
import com.vinsofts.asus.apphero.Utils.RetrofitClient;

/**
 * Created by AT on 11/6/2017.
 */

public class ApiUtils {
    private ApiUtils() {
    }

    public static final String BASE_URL = "http://webhero.vn/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
