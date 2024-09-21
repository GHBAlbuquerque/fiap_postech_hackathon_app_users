package com.fiap.hackathon.common.exceptions.custom;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionCodes {

    USER_01_NOT_FOUND,
    USER_02_ALREADY_REGISTERED,
    USER_03_IDENTITY_PROVIDER,
    USER_04_USER_RESPONSE,
    USER_05_NOTIFICATION_FAILED,
    USER_06_MESSAGE_CREATION,
    USER_07_USER_DEACTIVATION,
    USER_08_USER_CREATION,
    USER_09_USER_AUTHENTICATION
}