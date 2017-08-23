package com.pangpang6.utils.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;

/**
 * Created by jiangjg on 2017/6/9.
 */
public class Renders {
    /**
     * 绕过Template,直接输出内容的简便函数.
     */
    protected static void render(HttpServletResponse response, String text, String contentType) {
        try {
            response.setContentType(contentType);
            response.getWriter().write(text);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Renders.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 直接输出字符串.
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, text, "text/plain;charset=UTF-8");
    }

    /**
     * 直接输出JSON.
     */
    public static void renderJson(HttpServletResponse response, String json) {
        OutputStream os = null;
        response.setContentType("application/json");
        response.setContentLength(json.getBytes().length);

        try {
            byte[] bs = json.getBytes("UTF-8");
            response.setContentLength(bs.length);
            os = response.getOutputStream();
            os.write(bs);
            os.flush();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Renders.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Renders.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * 直接输出HTML.
     */
    public static void renderHtml(HttpServletResponse response, String html) {
        render(response, html, "text/html;charset=UTF-8");
    }

    /**
     * 直接输出XML.
     */
    public static void renderXML(HttpServletResponse response, String xml) {
        render(response, xml, "text/xml;charset=UTF-8");
    }
}
