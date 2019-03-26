package io.springboot.multidatasource.mapper2;

import io.springboot.multidatasource.entity.UserEntity;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Mapper2 {
	
	@Select("SELECT DATABASE();")
	@ResultType(String.class)
	String dataBaseName();

	@Select("Select Real_name from admin;")
	@ResultType(UserEntity.class)
	List<UserEntity> admins();
}
