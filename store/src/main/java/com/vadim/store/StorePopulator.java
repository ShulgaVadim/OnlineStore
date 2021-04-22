package com.vadim.store;

import com.vadim.DataBaseService;
import com.vadim.domain.product.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StorePopulator {
    public List<Category> populateStore (Populator populator) throws SQLException {
        List<Category> categories = new ArrayList<>();
        switch (populator){
            case DB:
                DataBaseService dbs = new DataBaseService();
                categories = dbs.getListOfCategoriesFromDB();
                break;
            case FAKER:
                RandomStorePopulator rsp = new RandomStorePopulator();
                categories = rsp.populateTheStore();
                break;
        } return categories;
    }
}

