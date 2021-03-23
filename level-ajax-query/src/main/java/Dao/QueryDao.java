package Dao;

import entity.City;
import entity.Province;

import java.util.List;

public interface QueryDao {
    public List<Province> queryProvinceList();
    public List<City> queryCityList(Integer proid);
}
