package com.pangpang6.utils.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Collection;

/**
 * Created by jiangjg on 2017/8/16.
 */
public class CollectionContainsTag extends TagSupport {

    private Collection collection;
    private Object item;

    @Override
    public int doStartTag() throws JspException {
        if (collection != null && collection.contains(item)) {
            return TagSupport.EVAL_BODY_INCLUDE;
        } else {
            return TagSupport.SKIP_BODY;
        }
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
