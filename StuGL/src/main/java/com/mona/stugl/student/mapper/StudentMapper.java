package com.mona.stugl.student.mapper;

import com.mona.stugl.student.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@Mapper
public interface  StudentMapper {
    public List<Student> selectStuList();
    public int addStudent(Student student);
    public int deleteStudentById(int id);
    public int updateStudent(Student student);

    Student selectStuById(Integer id);

    List<Student> selectStuPage(Integer pageIndex, Integer pageSize);

    int deleteStudentByList(Integer[] ids);

    List<Student> selectStuByName(String name);
}
