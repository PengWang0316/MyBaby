package com.pengwang.mybaby.presentation.presenters.impl;

import com.pengwang.mybaby.domain.executor.Executor;
import com.pengwang.mybaby.domain.executor.MainThread;
import com.pengwang.mybaby.domain.interactors.GetUserInteractor;
import com.pengwang.mybaby.domain.interactors.SaveFaceBookUserInteractor;
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
    private GetUserInteractor getUserInteractor;
    private SaveFaceBookUserInteractor saveFaceBookUserInteractor;

    @Before
    public void setUp() throws Exception {
        executor = mock(Executor.class);
        mainThread = mock(MainThread.class);
        view = mock(LoginPresenter.View.class);
        getUserInteractor = mock(GetUserInteractor.class);
        saveFaceBookUserInteractor = mock(SaveFaceBookUserInteractor.class);
    }

    @Test
    public void saveFacebookUserInformation() throws Exception {
        String userName = "";
        String userId = "";
        LoginPresenter presenter = new LoginPresenterImpl(executor, mainThread, view);
        ((LoginPresenterImpl) presenter).setSaveFaceBookUserInteractor(saveFaceBookUserInteractor);
        presenter.saveFacebookUserInformation(userId,userName);

        verify(saveFaceBookUserInteractor).setFacebookUserId(userId);
        verify(saveFaceBookUserInteractor).setFacebookUserName(userName);
        verify(saveFaceBookUserInteractor).execute();
    }

    @Test
    public void checkLoginStatus() throws Exception {
        LoginPresenter presenter = new LoginPresenterImpl(executor, mainThread, view);
        ((LoginPresenterImpl) presenter).setGetUserInteractor(getUserInteractor);
        presenter.checkLoginStatus();

        verify(view).showProgress();
        verify(getUserInteractor).execute();

    }

    @Test
    public void onUsernameRetrievedUserNull() throws Exception {
        LoginPresenterImpl presenter = new LoginPresenterImpl(executor, mainThread, view);
        User user = new User();
        presenter.onUserRetrieved(user);
        verify(view).hideProgress();
        verifyZeroInteractions(view);
    }

    @Test
    public void onUsernameRetrievedUserNameNull() throws Exception {
        LoginPresenterImpl presenter = new LoginPresenterImpl(executor, mainThread, view);
        User user = new User();
        user.setId("");
        presenter.onUserRetrieved(user);
        verify(view).hideProgress();
        verifyZeroInteractions(view);
    }

    @Test
    public void onUsernameRetrievedUserIdNull() throws Exception {
        LoginPresenterImpl presenter = new LoginPresenterImpl(executor, mainThread, view);
        User user = new User();
        user.setName("");
        presenter.onUserRetrieved(user);
        verify(view).hideProgress();
        verifyZeroInteractions(view);
    }

    @Test
    public void onUsernameRetrievedUser() throws Exception {
        LoginPresenterImpl presenter = new LoginPresenterImpl(executor, mainThread, view);
        User user = new User();
        user.setId("");
        user.setName("");
        presenter.onUserRetrieved(user);
        verify(view).hideProgress();
        verify(view).setUserToApplication(user);
        verify(view).showMainActivity();
        verifyZeroInteractions(view);
    }

}