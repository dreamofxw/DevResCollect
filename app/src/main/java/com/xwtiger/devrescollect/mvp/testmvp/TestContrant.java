package com.xwtiger.devrescollect.mvp.testmvp;

import com.xwtiger.devrescollect.mvp.BasePresenter;
import com.xwtiger.devrescollect.mvp.BaseView;

/**
 * Created by xwadmin on 2018/4/17.
 */

public class TestContrant {

    interface View extends BaseView<Presenter>{

        public void testViewOnew();
        public void testViewTwo();

    }

    interface Presenter extends BasePresenter{
        public void testPresenterFirst();
        public void testPresenterTwo();
    }

}
