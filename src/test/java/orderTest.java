import com.dhu.model.MovieEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.service.CinemaService;
import com.dhu.service.OrderService;
import com.dhu.service.TimeService;
import com.dhu.utils.Jacksons.Jacksons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by demerzel on 2018/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class orderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    TimeService timeService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CinemaService cinemaService;

    @Test
    public void main() {
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = null;
        try {
            date1 = dateFormat1.parse("2018-02-14");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date=new Date(date1.getTime());
        System.out.println(Jacksons.me().readAsString(cinemaService.findByMovieAndDateAndCity(1,date,1)));
    }
}
