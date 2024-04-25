package com.mona.stugl.student.controller;

import com.mona.stugl.config.VueStaticDir;
import com.mona.stugl.student.domain.Student;
import com.mona.stugl.student.service.StudentService;

import com.mona.stugl.util.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
@CrossOrigin
@RestController
@Slf4j
public class StudentController {
    @Autowired
    VueStaticDir staticDir;
    @Autowired
    StudentService studentService;
    @GetMapping("/selectStu/list")
    public List<Student> selectStuList(){
        List<Student> students = studentService.selectStuList();
        return students;
    }
    @PostMapping("/selectStuPage")
    public List<Student> selectStuPage(@RequestBody PageInfo pageInfo){
        int pageIndex = (pageInfo.getPageNo()-1)*pageInfo.getPageSize();
        List<Student> students = studentService.selectStuPage(pageIndex,pageInfo.getPageSize());
        return students;
    }
    @GetMapping("/selectStuById/{id}")
    public Student selectStuById(@PathVariable("id") Integer id){

        return  studentService.selectStuById(id);

    }

    @GetMapping("/selectStuByName/{name}")
    public List<Student> selectStuById(@PathVariable("name") String name){

        return  studentService.selectStuByName(name);

    }
    @PostMapping("/addStu")
    public int addStu(@RequestBody Student student){

        return studentService.addStudent(student);

    }
    @DeleteMapping("/deleteStudentById/{id}")
    public int deleteStudentById(@PathVariable("id") Integer id){

        File file = new File(staticDir.getStaticDir()+id+".jpg");
        File file2 = new File(staticDir.getStaticDir()+id+".png");
        file.delete();
        file2.delete();
       return studentService.deleteStudentById(id);
    }
    @PostMapping("/deleteStudentByList")
    public int deleteStudentByList(@RequestBody Integer[] ids){


        return studentService.deleteStudentByList(ids);
    }
    @PutMapping("/updateStudent")
    public int updateStudent(@RequestBody Student student){
       return studentService.updateStudent(student);
    }
    @PostMapping("/uploadPhoto")
    public String upload(@RequestParam("id") Integer student_id, MultipartFile photo){
        String fileName = photo.getOriginalFilename();
        String typeName=fileName.substring(fileName.lastIndexOf("."));
        fileName=student_id+typeName;
        try(InputStream inputStream = photo.getInputStream()){
            File file = new File(staticDir.getStaticDir()+fileName);
            log.info(file.getName());
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer)!=-1){
                outputStream.write(buffer);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "/src/assets/img/"+fileName;
    }
}
