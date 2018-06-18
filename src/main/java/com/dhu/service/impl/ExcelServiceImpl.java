package com.dhu.service.impl;

import com.dhu.model.CinemaEntity;
import com.dhu.model.MovieEntity;
import com.dhu.model.TimeEntity;
import com.dhu.model.UserEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.repository.TimeRepository;
import com.dhu.repository.UserRepository;
import com.dhu.service.CinemaService;
import com.dhu.service.ExcelService;
import com.dhu.service.TimeService;
import com.dhu.service.UserService;
import com.dhu.utils.Excel2003Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by demerzel on 2018/6/18.
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    CinemaService cinemaService;

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Override
    public String getMovieExcel(String path) throws IOException {
        String sheetName="movie";
        String sheetTitle = "movie";
        List<String> columnNames = new LinkedList<>();
        columnNames.add("id");
        columnNames.add("name");
        columnNames.add("englishname");
        columnNames.add("director");
        columnNames.add("cast");
        columnNames.add("description");
        columnNames.add("begin_time");
        columnNames.add("end_time");
        columnNames.add("movie_type");
        columnNames.add("duration");
        columnNames.add("country");
        columnNames.add("score");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);

        Excel2003Utils.writeExcelTitle(path, uuid, sheetName, columnNames, sheetTitle, false);

        List<List<Object>> objects = new LinkedList<>();

        List<MovieEntity> movieEntityList=movieRepository.findAll();

        for(MovieEntity movieEntity:movieEntityList){
            List<Object> dataA = new LinkedList<>();
            dataA.add(movieEntity.getId());
            dataA.add(movieEntity.getName());
            dataA.add(movieEntity.getEnglishname());
            dataA.add(movieEntity.getDirector());
            dataA.add(movieEntity.getCast());
            dataA.add(movieEntity.getDescription());
            dataA.add(movieEntity.getBeginTime());
            dataA.add(movieEntity.getEndTime());
            dataA.add(movieEntity.getMovieType());
            dataA.add(movieEntity.getDuration());
            dataA.add(movieEntity.getCountry());
            dataA.add(movieEntity.getScore());
            objects.add(dataA);
        }
        Excel2003Utils.writeExcelData(path, uuid, sheetName, objects);
        return "/static/"+uuid+".xls";
    }

    @Override
    public String getUserExcel(String path) throws IOException {
        String sheetName="User";
        String sheetTitle = "User";
        List<String> columnNames = new LinkedList<>();
        columnNames.add("Id");
        columnNames.add("name");
        columnNames.add("password");
        columnNames.add("tel");
        columnNames.add("money");
        columnNames.add("email");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);

        Excel2003Utils.writeExcelTitle(path, uuid, sheetName, columnNames, sheetTitle, false);

        List<List<Object>> objects = new LinkedList<>();

        List<UserEntity> userEntityList=userRepository.findAll();
        for(UserEntity userEntity:userEntityList){
            List<Object> dataA = new LinkedList<>();
            dataA.add(userEntity.getId());
            dataA.add(userEntity.getName());
            dataA.add(userEntity.getPassword());
            dataA.add(userEntity.getTel());
            dataA.add(userEntity.getMoney());
            dataA.add(userEntity.getEmail());
            objects.add(dataA);
        }
        Excel2003Utils.writeExcelData(path, uuid, sheetName, objects);
        return "/static/"+uuid+".xls";
    }

    @Override
    public String getTimeExcel(String path)  throws IOException{
        String sheetName="Time";
        String sheetTitle = "Time";
        List<String> columnNames = new LinkedList<>();
        columnNames.add("timeId");
        columnNames.add("startTime");
        columnNames.add("endTime");
        columnNames.add("cost");
        columnNames.add("movieId");
        columnNames.add("hallId");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);

        Excel2003Utils.writeExcelTitle(path, uuid, sheetName, columnNames, sheetTitle, false);

        List<List<Object>> objects = new LinkedList<>();

        List<TimeEntity> timeEntityList=timeRepository.findAll();
        for(TimeEntity timeEntity:timeEntityList){
            List<Object> dataA = new LinkedList<>();
            dataA.add(timeEntity.getId());
            dataA.add(timeEntity.getStartTime());
            dataA.add(timeEntity.getEndTime());
            dataA.add(timeEntity.getCost());
            dataA.add(timeEntity.getMovieId());
            dataA.add(timeEntity.getHallId());
            objects.add(dataA);
        }
        Excel2003Utils.writeExcelData(path, uuid, sheetName, objects);
        return "/static/"+uuid+".xls";
    }

    @Override
    public String getCinemaExcel(String path) throws IOException {
        String sheetName="cinema";
        String sheetTitle = "cinema";
        List<String> columnNames = new LinkedList<>();
        columnNames.add("cinemaId");
        columnNames.add("name");
        columnNames.add("address");
        columnNames.add("cityId");
        columnNames.add("lat");
        columnNames.add("lng");
        columnNames.add("lat_now");
        columnNames.add("lng_now");
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);

        Excel2003Utils.writeExcelTitle(path, uuid, sheetName, columnNames, sheetTitle, false);

        List<List<Object>> objects = new LinkedList<>();

        List<CinemaEntity> cinemaEntityList=cinemaService.findAllCinema();
        for(CinemaEntity cinemaEntity:cinemaEntityList){
            List<Object> dataA = new LinkedList<>();
            dataA.add(cinemaEntity.getId());
            dataA.add(cinemaEntity.getName());
            dataA.add(cinemaEntity.getAddress());
            dataA.add(cinemaEntity.getCityId());
            dataA.add(cinemaEntity.getLat());
            dataA.add(cinemaEntity.getLng());
            dataA.add(cinemaEntity.getLatNow());
            dataA.add(cinemaEntity.getLngNow());
            objects.add(dataA);
        }
        Excel2003Utils.writeExcelData(path, uuid, sheetName, objects);
        return "/static/"+uuid+".xls";
    }
}
