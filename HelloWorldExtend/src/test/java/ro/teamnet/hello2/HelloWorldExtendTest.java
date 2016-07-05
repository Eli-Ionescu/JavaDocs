package ro.teamnet.hello2;

import org.junit.Test;

/**
 * Created by Eli Ionescu on 7/5/2016.
 */
public class HelloWorldExtendTest {
    @Test
    public void test() throws Exception {
        new HelloWorldExtend().extendSayHello();
        assert new HelloWorldExtend().extendSayHello() == 3;
    }
}

