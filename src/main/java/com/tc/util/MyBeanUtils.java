package com.tc.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/2/3 18:15
 * @Description:
 */
public class MyBeanUtils {

    public static String[] getNullPropertyNames(Object source){

        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }

    public static void main(String[] args) {
        //BeanUtils.c
    }
}
