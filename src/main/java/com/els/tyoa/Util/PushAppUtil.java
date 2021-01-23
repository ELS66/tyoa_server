package com.els.tyoa.Util;

import com.tencent.xinge.XingeApp;
import com.tencent.xinge.bean.*;
import com.tencent.xinge.push.app.PushAppRequest;

import java.util.ArrayList;

public class PushAppUtil {
    public static void pushapp(String title,String content,ArrayList<String> tokenlist) {
        XingeApp xingeApp = new XingeApp.Builder()
                .appId(String.valueOf(1500016801))
                .secretKey("72c0f65b7cf73fa4c32524e81e73c0a3")
                .domainUrl("https://api.tpns.tencent.com/")
                .build();
        PushAppRequest pushAppRequest = new PushAppRequest();
        pushAppRequest.setAudience_type(AudienceType.token);
        pushAppRequest.setPlatform(Platform.android);
        pushAppRequest.setMessage_type(MessageType.notify);

        Message message = new Message();
        MessageAndroid messageAndroid = new MessageAndroid();
        ClickAction clickAction = new ClickAction();
        message.setTitle(title);
        message.setContent(content);
        clickAction.setIntent("xgscheme://com.tpns.push/notify_detail?param1=leave");
        clickAction.setAction_type(3);
        messageAndroid.setAction(clickAction);

        message.setAndroid(messageAndroid);
        pushAppRequest.setMessage(message);
        pushAppRequest.setToken_list(tokenlist);
        xingeApp.pushApp(pushAppRequest);
    }
}
