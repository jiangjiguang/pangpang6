package com.pangpang6.utils.taglibs;

import com.pangpang6.utils.reflection.Reflections;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 通过反射执行方法
 * Created by jiangjg on 2017/8/16.
 */
public class InvokeMethodTag extends TagSupport {

    private Object obj;
    private String methodName;
    private Object[] args;

    @Override
    public int doEndTag() throws JspException {
        try {
            Object result = Reflections.invokeMethodByName(obj, methodName, args);
            pageContext.getOut().print(result.toString());
        } catch (IOException ex) {
            Logger.getLogger(InvokeMethodTag.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TagSupport.EVAL_PAGE;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
