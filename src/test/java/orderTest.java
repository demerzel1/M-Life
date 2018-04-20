import com.dhu.model.HallEntity;
import com.dhu.model.MovieEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.service.*;
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
import java.util.Random;

/**
 * Created by demerzel on 2018/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class orderTest {
    @Autowired
    OrderService orderService;

    @Autowired
    TimeService timeService;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CinemaService cinemaService;

    @Autowired
    MovieService movieService;

    @Autowired
    HallService hallService;

    @Test
    public void main() {
        Random random=new Random();
        for(int uid=82;uid<=131;++uid){
            for(int i=1;i<=10;++i){
                Integer t=random.nextInt(1200);
                t+=6220;
                Integer row=random.nextInt(6);
                row+=1;
                Integer col=random.nextInt(9);
                col+=1;
                System.out.printf("%d %d %d\n",t,row,col);
                orderService.addOrder(t,row,col,uid);
            }
        }
    }
}
