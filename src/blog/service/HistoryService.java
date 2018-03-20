package blog.service;

import blog.dao.HistoryDao;
import blog.daoImpl.HistoryDaoImpl;
import blog.model.History;

import java.util.List;

public class HistoryService {


    private static HistoryService instance;
    private HistoryDao dao;

    private HistoryService() {
        dao = HistoryDaoImpl.getInstance();
    }

    public static final HistoryService getInstance() {
        if (instance == null) {
            try {
                instance = new HistoryService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    public List getHistoryList(int number) {
        return dao.getHistoryList(0,number);
    }

    public int getVersionsCount() {
        return dao.howManyVersions();
    }

    public List<History> getHistoryList(int start, int length){
        return dao.getHistoryList(start,length);
    }


}
