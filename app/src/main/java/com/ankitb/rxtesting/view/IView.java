package com.ankitb.rxtesting.view;

import com.ankitb.rxtesting.network.Repository;

import java.util.List;

/**
 * Created by Ankit on 10/08/16.
 */
public interface IView {
    void showProgressDialog(String message);
    void cancelProgressDialog();
    void showRepos(List<Repository> repos);
    void showMessage(String message);
}
