package com.mona.stugl.student.service;

import com.mona.stugl.student.domain.Student;
import com.mona.stugl.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StudentService
 * @Author mona
 * @Date 2024/4/23 15:33
 * @Version 1.0
 * @Description TODO
 **/
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    public List<Student> selectStuList(){
        List<Student> students = studentMapper.selectStuList();
        return students;
    }
    public int addStudent(Student student){
        int i = studentMapper.addStudent(student);
        return i;

    }
    public int deleteStudentById(int id){
        return studentMapper.deleteStudentById(id);
    }
    public int updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }

    public Student selectStuById(Integer id) {
        return studentMapper.selectStuById(id);
    }

    public List<Student> selectStuPage(Integer pageIndex, Integer pageSize) {
        return studentMapper.selectStuPage(pageIndex, pageSize);
    }

    public int deleteStudentByList(Integer[] ids) {
        return studentMapper.deleteStudentByList(ids);
    }

    public List<Student> selectStuByName(String name) {
        return studentMapper.selectStuByName(name);
    }
}
