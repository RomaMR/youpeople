package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.web.commons.RMPath;
import facebook4j.*;
import facebook4j.auth.AccessToken;
import facebook4j.conf.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by romanmudryi on 27.08.15.
 */
@Controller
@RequestMapping(RMPath.Facebook.ROOT)
public class FacebookController {


    @Value("${spring.social.facebook.appId}")
    private String appId;

    @Value("${spring.social.facebook.appSecret}")
    private String appSecret;

    private static final String SCOPE = "email,offline_access,user_about_me,user_birthday,read_friendlists";
    private static final String REDIRECT_URI = "http://localhost:8080/platform-services/social/facebook/callback";
    private static final String DIALOG_OAUTH = "https://www.facebook.com/dialog/oauth";

    private Facebook facebook;

    @RequestMapping(value = RMPath.Facebook.LOGIN, method = RequestMethod.GET)
    public void signIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("https://graph.facebook.com/oauth/access_token?grant_type=client_credentials&client_id="
                + appId + "&client_secret=" + appSecret, String.class);
        String accessToken = result.replaceAll("access_token=", "");
        String permissions = "public_profile,email,user_friends";

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthAppId(appId)
                .setOAuthAppSecret(appSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthPermissions(permissions);
        FacebookFactory facebookFactory = new FacebookFactory(configurationBuilder.build());

        facebook = facebookFactory.getInstance();
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback");

        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
    }

    @RequestMapping(value = RMPath.Facebook.CALLBACK, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> accessCode(@RequestParam("code") String code) throws Exception {
        User user = null;
        AccessToken oAuthAccessToken = facebook.getOAuthAccessToken(code);
        facebook.setOAuthAccessToken(oAuthAccessToken);
        String permissions = "public_profile,email,user_friends,read_friendlists";
        facebook.setOAuthPermissions(permissions);
        //
        Reading readingPage = new Reading();
        readingPage.fields("id,name,email,comments,likes,locale,location");
        Reading readingUser = new Reading();
        readingUser.fields("id,name,comments,likes");
        user = facebook.getMe(readingUser);
        User oleg = facebook.getUser("1114115155268859", readingUser);
        ResponseList<Page> pages = facebook.searchPages("Optigra Software", readingPage);
        Friendlist friendlist = facebook.getFriendlist("1114115155268859", readingUser);
        facebook.searchPages("VICE News", readingPage);
        //facebook.getPosts("235852889908002", readingUser);
        //
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
