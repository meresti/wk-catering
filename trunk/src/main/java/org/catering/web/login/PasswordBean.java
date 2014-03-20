package org.catering.web.login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.enterprise.context.RequestScoped;
import org.jasypt.commons.CommonUtils;
import org.jasypt.digest.StandardStringDigester;

@RequestScoped
public class PasswordBean {

    private static final String DIGEST_ALGORITHM = "SHA-256";

    private static final int ITERATION_COUNT = 1;

    private static final int SALT_SIZE = 0;

    public String encryptPassword(String value) {
        final StandardStringDigester digester = createDigester();
        return digester.digest(value);
    }

    private StandardStringDigester createDigester() {
        final StandardStringDigester digester = new StandardStringDigester();
        digester.setIterations(ITERATION_COUNT);
        digester.setSaltSizeBytes(SALT_SIZE);
        digester.setAlgorithm(DIGEST_ALGORITHM);
        digester.setStringOutputType(CommonUtils.STRING_OUTPUT_TYPE_HEXADECIMAL);
        digester.initialize();
        return digester;
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        final StandardStringDigester digester = createDigester();
        return digester.matches(plainPassword, encryptedPassword);
    }
}
