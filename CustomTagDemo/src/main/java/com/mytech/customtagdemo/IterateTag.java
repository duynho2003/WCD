/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mytech.customtagdemo;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Admin
 */
public class IterateTag extends TagSupport{
    
    int numOfIteration = 0;
    int curOfIteration = 0;
    
    public int getNumOfIteration() {
        return numOfIteration;
    }
    
    public void setNumOfIteration(int numOfIteration){
        this.numOfIteration = numOfIteration;
    }

    @Override
    public int doStartTag() throws JspException {
        curOfIteration = 0;
        return EVAL_BODY_INCLUDE;
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if (curOfIteration < numOfIteration) {
            curOfIteration++;
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }
}
