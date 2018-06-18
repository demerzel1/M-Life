package com.dhu.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by demerzel on 2018/6/18.
 */
@Service
public interface ExcelService {
    String getMovieExcel(String path) throws IOException;
    String getUserExcel(String path) throws IOException;
    String getTimeExcel(String path) throws IOException;
    String getCinemaExcel(String path) throws IOException;
}
