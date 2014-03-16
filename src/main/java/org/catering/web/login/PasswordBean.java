package org.catering.web.login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.enterprise.context.RequestScoped;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

@RequestScoped
public class PasswordBean {

    private static final String DIGEST_ALGORITHM = "SHA-256";

    public String encryptPassword(String value) {
        final ConfigurablePasswordEncryptor passwordEncryptor = createPasswordEncryptor();
        return passwordEncryptor.encryptPassword(value);
    }

    private ConfigurablePasswordEncryptor createPasswordEncryptor() {
        final ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setPlainDigest(true);
        passwordEncryptor.setAlgorithm(DIGEST_ALGORITHM);
        passwordEncryptor.setStringOutputType("hexadecimal");
        return passwordEncryptor;
    }

    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        final ConfigurablePasswordEncryptor passwordEncryptor = createPasswordEncryptor();
        return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
