package unit;

import com.javainuse.nastia.controller.SecurityController;
import com.javainuse.nastia.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SecurityControllerTest {
    @Test
    public void SecurityControllerTest() {
        SecurityController homeController = new SecurityController();
        User result = homeController.validateLogin();
        assertEquals(result.getStatus(), "User successfully authenticated");
    }
}
