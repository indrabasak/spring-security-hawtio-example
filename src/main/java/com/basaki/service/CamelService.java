package com.basaki.service;

import com.basaki.model.MyBean;

/**
 * {@code CamelService} modifies the bean.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/12/17
 */
public class CamelService {

    public static void doSomething(MyBean bodyIn) {
        bodyIn.setName( "Hello, " + bodyIn.getName() );
        bodyIn.setId(bodyIn.getId()*10);
    }
}
