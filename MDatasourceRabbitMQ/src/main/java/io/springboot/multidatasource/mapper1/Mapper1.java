package io.springboot.multidatasource.mapper1;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Mapper1 {
	
	@Select("SELECT DATABASE();")
	@ResultType(String.class)
	String dataBaseName();

	@Select("Select name from person;")
	@ResultType(String.class)
	List<String> admins();
}
