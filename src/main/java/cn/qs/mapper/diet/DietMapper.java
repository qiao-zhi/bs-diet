package cn.qs.mapper.diet;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.qs.bean.diet.Diet;
import cn.qs.bean.diet.DietExample;
import cn.qs.bean.diet.DietWithBLOBs;
@Mapper
public interface DietMapper {
    int countByExample(DietExample example);

    int deleteByExample(DietExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DietWithBLOBs record);

    int insertSelective(DietWithBLOBs record);

    List<DietWithBLOBs> selectByExampleWithBLOBs(DietExample example);

    List<Diet> selectByExample(DietExample example);

    DietWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DietWithBLOBs record, @Param("example") DietExample example);

    int updateByExampleWithBLOBs(@Param("record") DietWithBLOBs record, @Param("example") DietExample example);

    int updateByExample(@Param("record") Diet record, @Param("example") DietExample example);

    int updateByPrimaryKeySelective(DietWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(DietWithBLOBs record);

    int updateByPrimaryKey(Diet record);
    
    DietWithBLOBs getDietByDiseasename(@Param("diseasename") String diseasename);
}