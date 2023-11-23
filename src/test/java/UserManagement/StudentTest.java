package UserManagement;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getInstance() {
    }

    @Test
    void getMajor() {
    }

    @Test
    void setMajor() {
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
    }

    @Test
    void isGender() {
    }

    @Test
    void setGender() {
    }

    @Test
    void getEid() {
    }

    @Test
    void setEid() {
    }

    @Test
    void updateInfo() throws SQLException {
        Student patient = new Student(
                new Account("g00087725","4297f44b13955235245b2497399d7a93", "rana"),
                "coe",21,0,"11111");
        patient.updateInfo(patient.getInstance());
        assertEquals("rana",patient );

    }
}