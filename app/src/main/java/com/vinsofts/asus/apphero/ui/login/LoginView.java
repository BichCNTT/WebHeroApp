package com.vinsofts.asus.apphero.ui.login;

import java.util.List;

/**
 * Created by AT on 11/3/2017.
 */

public interface LoginView {
    void getListUserSuccessfully(List<String> listUsers);

    void getListUserFailed(String s);
}
