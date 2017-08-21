package com.pangpang6.utils.taglibs;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class EscapeBackslashTag extends TagSupport {

    private String value;

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print(StringUtils.replace(value, "\\", "\\\\"));
        } catch (IOException ex) {
            Logger.getLogger(EscapeBackslashTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TagSupport.EVAL_PAGE;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
