package com.pengwang.mybaby.storage;

import com.pengwang.mybaby.domain.models.User;
import com.pengwang.mybaby.domain.repository.DatabaseRepository;
import com.pengwang.mybaby.network.RestConnection;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by Peng on 3/31/2017.
 * The test for DatabaseRepositoryMongodbImp
 */
public class DatabaseRepositoryMongodbImpTest {
    private static final String DOMAIN_NAME = "https://Arthurbwang.com";
    private static final String API_VERSION = "/api/v1";
    private RestConnection restConnection;

    @Before
    public void setUp() throws Exception {
        restConnection = mock(RestConnection.class);
    }

    @Test
    public void saveFacebookUserNullUserId() throws Exception {
        DatabaseRepository databaseRepository = new DatabaseRepositoryMongodbImp(restConnection);
        User user = mock(User.class);
        when(user.getFacebookId()).thenReturn(null);
        when(user.getFacebookId()).thenReturn("facebookId");
        when(user.getGoogleId()).thenReturn(null);
        when(user.getName()).thenReturn(null);
        when(user.getJsonString()).thenReturn("{}");

        when(restConnection.get(DOMAIN_NAME + API_VERSION + "/users?facebookId=facebookId")).thenReturn("id");
        when(restConnection.post(DOMAIN_NAME + API_VERSION + "/users", "{}")).thenReturn("123");

        databaseRepository.saveFacebookUser(user);
        verify(restConnection).get(DOMAIN_NAME + API_VERSION + "/users?facebookId=facebookId");
        verify(user).setId("id");
        verify(restConnection).post(DOMAIN_NAME + API_VERSION + "/users", "{}");
        verify(user).setId("123");
        verifyZeroInteractions(restConnection);
    }

}