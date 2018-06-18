package com.dhu.service.impl;

import com.dhu.model.MovieEntity;
import com.dhu.model.OrderEntity;
import com.dhu.model.TimeEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.repository.OrderRepository;
import com.dhu.repository.TimeRepository;
import com.dhu.service.MovieService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.ExcelUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.apache.xmlbeans.impl.jam.mutable.MElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public class MovieServiceImpl implements MovieService {
   @Autowired
    MovieRepository movieRepository;

   @Autowired
    OrderRepository orderRepository;

   @Autowired
    TimeRepository timeRepository;

    @Override
    public List<MovieEntity> findAllMovie(){
        return movieRepository.findAll();
    }
    @Override
    public MovieEntity findMovieByName(String name){
        return movieRepository.findFirstByName(name);
    }

    @Override
    public MovieEntity findMovieById(Integer id){
        return movieRepository.findFirstById(id);
    }

    @Override
    @Transactional
    public MovieEntity updateMovie(MovieEntity movieEntity) {
        return movieRepository.saveAndFlush(movieEntity);
    }

    @Override
    @Transactional
    public Boolean deleteMovieById(Integer id) {
        MovieEntity movieEntity=movieRepository.findFirstById(id);
        System.out.println(Jacksons.me().readAsString(movieEntity));
        movieRepository.delete(movieEntity);
        movieRepository.flush();
        if(movieRepository.findFirstById(id)==null){
            System.out.println(3333);
            return true;
        }
        System.out.println(2222);
        return false;
    }

    @Override
    @Transactional
    public MovieEntity addMovie(MovieEntity movieEntity) {
        return movieRepository.saveAndFlush(movieEntity);
    }

    @Override
    @Transactional
    public List<MovieEntity> findAllMovieByDate(Date date) {
        movieRepository.flush();
        return movieRepository.findAllByBeginTimeLessThanEqualAndEndTimeGreaterThanEqual(date,date);
    }

    @Override
    @Transactional
    public List<MovieEntity> findWatchedByUserId(Integer user_id) {
        orderRepository.flush();
        List<OrderEntity> lstOrder=orderRepository.findAllByUserId(user_id);
        Set<Integer> setTime=new HashSet<>();
        for(OrderEntity orderEntity:lstOrder){
            setTime.add(orderEntity.getTimeId());
        }
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        if(setTime.size()==0){
            return null;
        }
        timeRepository.flush();
        List<TimeEntity> timeEntityList=timeRepository.findAllByIdInAndStartTimeLessThan(setTime,timestamp);
        Set<Integer> movieSet=new HashSet<>();
        for(TimeEntity timeEntity:timeEntityList){
            movieSet.add(timeEntity.getMovieId());
        }
        if(movieSet.size()==0)
            return null;
        movieRepository.flush();
        List<MovieEntity> movieEntityList=movieRepository.findAllByIdIn(movieSet);

        return movieEntityList;
    }

    @Override
    public List<MovieEntity> findNotOn() {
        Date date=new Date(System.currentTimeMillis());
        movieRepository.flush();
        return movieRepository.findAllByBeginTimeGreaterThan(date);
    }

    @Override
    public List<MovieEntity> addFromExcel(MultipartFile file) {
        ExcelUtils excelUtils=new ExcelUtils();
        List<MovieEntity> movieEntityList=excelUtils.getExcelInfo(file);
        System.out.println(Jacksons.me().readAsString(movieEntityList));
        for(MovieEntity movieEntity:movieEntityList){
            movieRepository.saveAndFlush(movieEntity);
        }
        return movieEntityList;
    }

    @Override
    public List<MovieEntity> findByStrName(String str) {
        movieRepository.flush();
        return movieRepository.findAllByNameContaining(str);
    }

    @Override
    public Map findNumberOfTimesAndNumerOfWatchedByMovie(Integer movieId) {
        MovieEntity movieEntity=movieRepository.findFirstById(movieId);
        List<TimeEntity> timeEntityList=timeRepository.findAllByMovieId(movieId);
        Integer cntTime=timeEntityList.size();
        List<OrderEntity> orderEntityList=new ArrayList<>();
        Double money=0.0;
        for(TimeEntity timeEntity:timeEntityList){
            Integer timeId=timeEntity.getId();
            List<OrderEntity> orderEntityList1=orderRepository.findAllByTimeId(timeId);
            money+=orderEntityList1.size()*timeEntity.getCost();
            for(OrderEntity orderEntity:orderEntityList1){
                orderEntityList.add(orderEntity);
            }
        }
        Integer cntOrder=orderEntityList.size();
        Map map=new HashMap<>();
        map.put("movieId",movieId);
        map.put("cntTime",cntTime);
        map.put("cntOrder",cntOrder);
        map.put("boxOffice",money);
        return map;
    }

    @Override
    public List<Map> findNumberOfTimesAndNumerOfWatchedByDate(Date date) {
        List<MovieEntity> movieEntityList=findAllMovieByDate(date);
        List<Map> mapList=new ArrayList<>();
        for(MovieEntity movieEntity:movieEntityList){
            Integer movieId=movieEntity.getId();
            Map map=findNumberOfTimesAndNumerOfWatchedByMovie(movieId);
            mapList.add(map);
        }
        return mapList;
    }

    Comparator<Map> comparator = new Comparator<Map>() {
        public int compare(Map o1, Map o2) {
           Double c1=Double.valueOf(o1.get("boxOffice").toString());
           Double c2=Double.valueOf(o2.get("boxOffice").toString());
           if(c1<c2)
               return 1;
           else
               return -1;
        }
    };

    @Override
    public List<Map> findTopXMoney(Integer X) {
        List<MovieEntity> movieEntityList=findAllMovie();
        List<Map> mapList=new ArrayList<>();
        for(MovieEntity movieEntity:movieEntityList){
            Map map=findNumberOfTimesAndNumerOfWatchedByMovie(movieEntity.getId());
            mapList.add(map);
        }
        Collections.sort(mapList,comparator);
        List<Map> mapList1=new ArrayList<>();
        for(int i=0;i<X;i++){
            mapList1.add(mapList.get(i));
        }
        return mapList1;
    }
}
