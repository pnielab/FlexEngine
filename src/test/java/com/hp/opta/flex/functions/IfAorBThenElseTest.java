package com.hp.opta.flex.functions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class IfAorBThenElseTest {

    Function f = null;

    @Before
    public void setUp() throws Exception {
        f = new IfAorBThenElse();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void basicTest() {

        List<Object> objectArray = new ArrayList<Object>();
        objectArray.add("test1");
        objectArray.add("test2");
        objectArray.add("test3");
        objectArray.add("true");
        objectArray.add("false");

        Object obj = f.apply(objectArray);
        Assert.assertEquals(obj, "false");

        objectArray.set(1, "test1");
        obj = f.apply(objectArray);
        Assert.assertEquals(obj, "true");

        objectArray.set(1, "test2");
        objectArray.set(2, "test1");
        obj = f.apply(objectArray);
        Assert.assertEquals(obj, "true");

    }

}
