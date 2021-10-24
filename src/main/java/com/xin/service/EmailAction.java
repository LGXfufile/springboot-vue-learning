package com.xin.service;

/*
@DATE 2021/10/23 22:57
@PACKAGE_NAME com.xin.service
@USER A
*/

public interface EmailAction {
    /**
     * 发送简单邮件
     * @param mailContent 内容
     * @param targetEailAddress 收件人地址
     */
    void sendSimpleMsg(String mailContent,String targetEailAddress);

    /**
     * 发送带图片的邮件
     * @param mailContent 邮件内容
     * @param targetEailAddress  收件人地址
     * @param imgSrc 图片地址
     */
    void sendHtmlWithImg(String mailContent,String targetEailAddress,String imgSrc);

}
