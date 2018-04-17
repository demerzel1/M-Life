import com.dhu.utils.JWTUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by demerzel on 2018/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class tokenTest {
    @Test
    public void main(){
        String str=JWTUtils.getToken("1");
        System.out.println(str);
        System.out.println(Jacksons.me().readAsString(JWTUtils.checkToken(str)));
    }
}
