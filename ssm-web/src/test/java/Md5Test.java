import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;



public class Md5Test {

    @Test
    public void test() throws Exception{
        SimpleHash hash = new SimpleHash("md5","123456","hzw",10);
        System.out.println(hash);
    }

}
