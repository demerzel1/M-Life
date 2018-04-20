import com.dhu.model.MovieEntity;
import com.dhu.model.UserEntity;
import com.dhu.service.MovieService;
import com.dhu.service.SeatService;
import com.dhu.service.TimeService;
import com.dhu.service.UserService;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class testSeat {

    @Autowired
    UserService userService;

    @Autowired
    SeatService seatService;

    @Autowired
    MovieService movieService;

    @Autowired
    ResultGenerator resultGenerator;

    @Autowired
    TimeService timeService;

    @Test
    public void main() {
        for(int hall=2;hall<=27;hall++){
            for(int i=1;i<=7;++i){
                for(int j=1;j<=10;++j){
                    seatService.addSeat(hall,i,j);
                }
            }
        }

    }
}
