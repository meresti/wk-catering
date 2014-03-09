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

        final ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setPlainDigest(true);
        passwordEncryptor.setAlgorithm(DIGEST_ALGORITHM);
        passwordEncryptor.setStringOutputType("hexadecimal");
        final String encrypted = passwordEncryptor.encryptPassword(value);
        return encrypted;
    }
}
