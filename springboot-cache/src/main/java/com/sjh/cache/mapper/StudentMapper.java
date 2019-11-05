package com.sjh.cache.mapper;

import com.sjh.cache.bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


public interface StudentMapper {

    @Select("select * from tb_stu where sno=#{id}")
    Student getStudentById(Integer id);

    @Update("update tb_stu(sname,sbrith,saddr) set sname=#{sname},sbrith=#{sbrith},saddr=#{saddr} ")
    @Options(useGeneratedKeys = true,keyProperty = "sno")
    void updateStudent(Student student);


    @Delete("delete from tb_stu where sno=#{id}")
    void deleteStudentById(Integer id);
}
