package com.pangpang6.utils.taglibs;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class SimplePaginationTag extends TagSupport {

    private static final long serialVersionUID = 1L;
    private int currentPage;
    private int currentCount;
    private int pageSize;
    private String controller;

    @Override
    public int doEndTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String queryParams = request.getQueryString();
        queryParams = StringUtils.remove(queryParams, "&page=" + currentPage);
        queryParams = StringUtils.remove(queryParams, "page=" + currentPage);
        String queryString = "";
        String queryStringWithPage = "?page=";
        if (StringUtils.isNotBlank(queryParams)) {
            queryString = "?" + queryParams;
            queryStringWithPage = "?" + queryParams + "&page=";
        }

        StringBuilder pagination = new StringBuilder();
        pagination.append("<div class=\"page\">");
        pagination.append("<span>第 ").append(currentPage).append(" 页</span>");

        //显示"上一页"
        if (currentPage > 1) {
            pagination.append("<a href=\"").append(controller).append(queryString).append("\" title=\"首页\">&lt;&lt;</a>");
            pagination.append("<a href=\"").append(controller).append(queryStringWithPage).append(currentPage - 1).append("\" class=\"prev\">上一页</a>");
        }

        //显示"下一页"
        if (currentCount >= pageSize) {
            pagination.append("<a href=\"").append(controller).append(queryStringWithPage).append(currentPage + 1).append("\" class=\"next\">下一页</a>");
        }

        pagination.append("</div>");

        try {
            pageContext.getOut().print(pagination.toString());
        } catch (IOException ex) {
            Logger.getLogger(SimplePaginationTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TagSupport.EVAL_PAGE;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

