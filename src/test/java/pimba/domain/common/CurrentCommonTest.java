package pimba.domain.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pimba.login.model.user.User;
import pimba.login.spring.service.CurrentUserVerication;
import pimba.login.spring.service.impl.SecurityServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 28/04/17.
 */
public class CurrentCommonTest {
    @InjectMocks
    CurrentUserVerication current;

    @Mock
    SecurityServiceImpl security;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void currentCommonTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        User user = new User("paulosantosphs@hotmail.com", common);

        when(security.getCurrentUser()).thenReturn(Optional.of(user));
        Assert.assertTrue(current.isCurrentUserCommon());
        Assert.assertFalse(current.isCurrentUserCustomer());
    }
}
