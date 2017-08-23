package com.pangpang6.utils.web;

import com.pangpang6.utils.SimpleStringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangjg on 2017/6/9.
 */
public class VersionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String file = getInitParameter("pomPath");
        InputStream in = request.getSession().getServletContext().getResourceAsStream(file);
        if (in != null) {
            Renders.renderText(response, SimpleStringUtils.InputStream2String(in, "UTF-8"));
        } else {
            Renders.renderText(response, "unknown");
        }

    }
}
