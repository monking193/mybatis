package com.example.mybatis.thread.threadPool;

import com.example.mybatis.dao.StudentMapper;
import com.example.mybatis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
public class StudentAddDataTest {

    private static ExecutorService pool;

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/test1")
    public void test1 () {
        long start = System.currentTimeMillis();
        for (int i = 0;i< 1000;i++) {
            Student student = new Student();
            student.setAddress("2343");
            student.setMoney(66666666);
            student.setName("djd");
            student.setSto_no("18850542239");
            student.setTel("18030040892");
            int num = studentMapper.insertStudent(student);
        }
        System.out.print("单条数据逐个插入耗时：" + (System.currentTimeMillis() - start));

    }

    @GetMapping("/test2")
    public void test2 () {
        long start = System.currentTimeMillis();
        List<Student> students = new ArrayList<>();
        for (int i = 0;i< 10000;i++) {
            Student student = new Student();
            student.setAddress("2343");
            student.setMoney(66666666);
            student.setName("djd");
            student.setSto_no("18850542239");
            student.setTel("18030040892");
            students.add(student);
        }
        studentMapper.batchInsert(students);
        System.out.print("批量插入耗时：" + (System.currentTimeMillis() - start));

    }

    @GetMapping("/test3")
    public void test3 () throws InterruptedException{
        long start = System.currentTimeMillis();
        List<Student> students = new ArrayList<>();
        int j = 0;
        for (int i = 0;i< 3340;i++) {
            Student student = new Student();
            student.setAddress("2343");
            student.setMoney(66666666);
            student.setName("djd");
            student.setSto_no("18850542239");
            student.setTel("18030040892");
            students.add(student);
        }
        pool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0;i < 3; i ++) {
            pool.execute(new ThreadTask(studentMapper,students));
        }
        pool.shutdown();
        pool.awaitTermination(10,TimeUnit.SECONDS);
        System.out.print("批量插入耗时：" + (System.currentTimeMillis() - start));
        // 线程全部执行完毕,判断线程池是否执行完毕pool.isTerminated()
       /* while (true) {
            if (pool.isTerminated()) {

                break;
            }
        }*/

    }




}
