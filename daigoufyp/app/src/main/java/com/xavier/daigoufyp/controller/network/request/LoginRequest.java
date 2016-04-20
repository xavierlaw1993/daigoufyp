package com.xavier.daigoufyp.controller.network.request;

import android.content.Context;

import com.xavier.daigoufyp.model.response.UserResponse;

/**
 * Created by zensis on 20/4/16.
 */
public class LoginRequest extends AbsRequest<UserResponse> {
    String user_id, user_name, user_email, user_profile_pic;

    public LoginRequest(Context context, String user_id, String user_name,
                        String user_email, String user_profile_pic) {
        super(context, UserResponse.class);
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_profile_pic = user_profile_pic;
    }

    @Override
    public UserResponse loadDataFromNetwork() throws Exception {
        return getService().login(user_id, user_name, user_email, user_profile_pic);
    }
}
