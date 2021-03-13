package pimba.domain.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pimba.login.model.user.User;
import pimba.login.spring.service.CurrentUserVerication;
import pimba.login.spring.service.impl.SecurityServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by paulo on 28/04/17.
 */
public class CommonControllerTest {
    @InjectMocks
    CommonController controller;

    @Mock
    CurrentUserVerication current;

    @Mock
    SecurityServiceImpl security;

    @Mock
    CommonService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void currentCommonTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        User user = new User("paulosantosphs@hotmail.com", common);
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(security.getCurrentUser()).thenReturn(Optional.of(user));
        Assert.assertEquals(ResponseEntity.ok(common), controller.getCurrentCommonUser());
    }

    @Test
    public void editNameTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editName("Paulo")).thenReturn(common);
        Assert.assertEquals(ResponseEntity.ok(common), controller.editName("Paulo"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editName(""));
    }

    @Test
    public void editPhoneTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editPhone("123")).thenReturn(common);
        Assert.assertEquals(ResponseEntity.ok(common), controller.editPhone("123"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editPhone(""));
    }

    @Test
    public void editGenderTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editGender(Gender.MALE)).thenReturn(common);
        Assert.assertEquals(ResponseEntity.ok(common), controller.editGender(Gender.MALE));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editGender(null));
    }

    @Test
    public void editPhotoTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editPhoto("url")).thenReturn(common);
        Assert.assertEquals(ResponseEntity.ok(common), controller.editPhoto("url"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editPhoto(""));
    }

    @Test
    public void editCityTest() {
        Common common = new Common("Paulo", "paulosantosphs@hotmail.com");
        when(current.isCurrentUserCommon()).thenReturn(true);
        when(service.editCity("Mariana")).thenReturn(common);
        Assert.assertEquals(ResponseEntity.ok(common), controller.editCity("Mariana"));
        Assert.assertEquals(ResponseEntity.badRequest().body(null), controller.editCity(""));
    }
}
