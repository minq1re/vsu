package ru.vsu.cs.loseva.classes;

public class Lecturer extends ResearchAssociate{
    private Course[] courses;

    public Lecturer(int ssNo, String name, String fieldOfStudy) {
        super(ssNo, name, fieldOfStudy);
    }

    public Course[] getCourses() {
        return this.courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        throw new UnsupportedOperationException();
    }

    public void removeCourse(Course course) {
        throw new UnsupportedOperationException();
    }
}
