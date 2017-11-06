package com.vinsofts.asus.apphero.Utils;


import com.vinsofts.asus.apphero.model.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by AT on 11/6/2017.
 */

public interface APIService {
    @FormUrlEncoded
    @POST("api/user/login")
    Call<Post> postLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/user/social_login")
    Call<Post> post(@Field("email") String username, @Field("fid") String password);
}
