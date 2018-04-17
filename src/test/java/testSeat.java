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
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
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
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        java.util.Date date=null;
        String str="2018-02-14";
        try{
            date=format1.parse(str);
        }catch (ParseException e){
        }
        long l=date.getTime();
        date1=new Date(l);
        System.out.println(Jacksons.me().readAsString(timeService.findByCidAndDate(1,date1)));
    }
}
