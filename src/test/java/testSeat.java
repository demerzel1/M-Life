import com.dhu.model.MovieEntity;
import com.dhu.model.UserEntity;
import com.dhu.service.MovieService;
import com.dhu.service.SeatService;
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
    @Test
    public void main() {
        System.out.println("111");
        System.out.println(Jacksons.me().readAsString(resultGenerator.getSuccessResult( movieService.findMovieById(1)) ));
    }
}
