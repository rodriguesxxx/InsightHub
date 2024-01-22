package com.danielrodrigues.app.unitary;

import com.danielrodrigues.app.dto.BCryptDTO;
import com.danielrodrigues.app.utils.BCryptUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BCryptTest {

    @Autowired
    protected BCryptUtil bCrypt;

    @Test
    void mustEncrypt() {
        BCryptDTO result = bCrypt.encrypt("rodriguesxxx");
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(BCryptDTO.class, result);
    }

    @Test
    void mustDecrypt() {
        BCryptDTO result = bCrypt.encrypt("rodriguesxxx");
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(BCryptDTO.class, result);
        String value = bCrypt.decrypt(result);
        Assertions.assertNotNull(value);
        Assertions.assertEquals("rodriguesxxx", value);
    }

}
