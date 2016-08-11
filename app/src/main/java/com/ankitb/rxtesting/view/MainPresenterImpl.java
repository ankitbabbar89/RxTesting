package com.ankitb.rxtesting.view;

/**
 * Created by Ankit on 10/08/16.
 */
public class MainPresenterImpl implements IPresenter {
    private IView mView;
    public MainPresenterImpl(IView view){
        mView = view;
    }
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    public void onSearchClick(String username){

    }
}
