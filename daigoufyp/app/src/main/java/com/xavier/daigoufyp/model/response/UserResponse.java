package com.xavier.daigoufyp.model.response;

import com.google.gson.annotations.Expose;
import com.xavier.daigoufyp.model.User;

/**
 * Created by zensis on 20/4/16.
 */
public class UserResponse extends AbsResponse {
    @Expose
    public User user;
}
