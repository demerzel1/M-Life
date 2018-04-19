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
        HallEntity hallEntity=new HallEntity();
        hallEntity.setCinemaId(2);
        hallEntity.setNumber(3);
        hallService.add(hallEntity);
        for(int i=3;i<=9;++i){
            for(int j=1;j<=3;++j){
                HallEntity hallEntity1=new HallEntity();
                hallEntity1.setCinemaId(i);
                hallEntity1.setNumber(j);
                hallService.add(hallEntity1);
            }
        }
    }
}
