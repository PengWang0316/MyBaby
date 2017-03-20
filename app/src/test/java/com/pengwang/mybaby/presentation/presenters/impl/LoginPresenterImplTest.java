package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserInteractor;
import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.presentation.presenters.LoginPresenter;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by Peng on 2/24/2017.
 * Test for LoginPresenter
 */
public class LoginPresenterImplTest {

    private Executor executor;
    private MainThread mainThread;
    private LoginPresenter.View view;
    private GetUserInteractor interactor;

    @Before
    public void setUp() throws Exception {
        executor=mock(Executor.class);
        mainThread=mock(MainThread.class);
        view=mock(LoginPresenter.View.class);
        interactor=mock(GetUserInteractor.class);
    }

    @Test
    public void checkLoginStatus() throws Exception {
        LoginPresenter presenter=new LoginPresenterImpl(executor,mainThread,view);
        ((LoginPresenterImpl)presenter).setGetUserInteractor(interactor);
        presenter.checkLoginStatus();

        verify(view).showProgress();
        verify(interactor).execute();

    }

    @Test
    public void onUsernameRetrieved() throws Exception {
        LoginPresenterImpl presenter=new LoginPresenterImpl(executor,mainThread,view);
        presenter.onUserRetrieved(null);
        verify(view).hideProgress();
        verifyZeroInteractions(view);

        User user=new User();
        presenter.onUserRetrieved(user);
        verify(view).setUserToApplication(user);
    }

}