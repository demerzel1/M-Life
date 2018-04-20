import com.dhu.model.MovieEntity;
import com.dhu.service.MovieService;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by demerzel on 2018/4/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"})
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TimeTest {

    @Autowired
    TimeService timeService;

    @Autowired
    MovieService movieService;

    @Test
    public void main(){
        List<MovieEntity> movieEntityList=movieService.findAllMovie();
        boolean flag=false;
        for(MovieEntity movieEntity:movieEntityList){
            Date beginTime=movieEntity.getBeginTime();
            if(timeService.checkCanAuto(beginTime,1)==false)
                continue;
            flag=!flag;
            Double cost=30.0;
            if(flag)
                cost=40.0;
            for(int i=1;i<=27;i+=3){
                timeService.autoAddByDateMoiveHall(beginTime,movieEntity.getId(),i,cost);
            }
        }
        //Date date=CommonUtils.me().String2Date("2018-05-14");
        //System.out.println(Jacksons.me().readAsString( timeService.autoAyDateMoiveHall(date,7,1,30.0)));;
    }
}
