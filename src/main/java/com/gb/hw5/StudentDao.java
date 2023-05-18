package com.gb.hw5;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;

import java.util.List;

public class StudentDao {
    private final SessionFactoryUtils sessionFactoryUtils;

    public StudentDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    public Student merge(Student student) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.merge(student);
            session.getTransaction().commit();
            return student;
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            MutationQuery query = session.createMutationQuery("DELETE FROM Student s WHERE s.id = :id").setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Student findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
//            List<Student> students = session.createQuery("FROM Student").getResultList();
            CriteriaQuery<Student> cq = session.getCriteriaBuilder().createQuery(Student.class);
            cq.from(Student.class);
            List<Student> studentsList = session.createQuery(cq).getResultList();
            session.getTransaction().commit();
            return studentsList;
        }
    }
}
