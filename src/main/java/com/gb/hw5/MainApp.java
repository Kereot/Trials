package com.gb.hw5;

import java.util.List;
import java.util.logging.Level;

public class MainApp {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        SessionFactoryUtils sessionFactoryUtils = SessionFactoryUtils.getSessionFactoryUtils();

        try {
            StudentDao studentDao = new StudentDao(sessionFactoryUtils);
            for (int i = 0; i < 100; i++) {
                studentDao.merge(new Student("Student #" + (i + 1), (int)(Math.random()*100)));
            }

            Student badStudent = studentDao.findById(51L);
            badStudent.setMark(-1);
            System.out.println(studentDao.merge(badStudent));

            studentDao.merge(new Student("Hero", 999));

            System.out.println(studentDao.findById(76L).getName() + ", mark: " + studentDao.findById(76L).getMark());
            studentDao.deleteById(76L);

            List<Student> studentList = studentDao.findAll();
            for (Student student : studentList) {
                System.out.println("Name: " + student.getName() + ", Mark: " + student.getMark());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}
