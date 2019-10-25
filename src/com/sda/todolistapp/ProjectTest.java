package com.sda.todolistapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import java.util.Arrays;
import java.util.List;

public class ProjectTest {

    private Project project;

    @Before
    public void setUp() {
        //List with 5 Tasks
        List<Task> tasks = Arrays.asList(
                createTask("task1", "task1 description", "10.10.2019", true),
                createTask("task2", "task2 description", "11.10.2019", true),
                createTask("task3", "task3 description", "12.10.2019", true),
                createTask("task4", "task4 description", "13.10.2019", false),
                createTask("task5", "task5 description", "14.10.2019", false)
        );

        project = new Project();
        project.setName("project Name");
        project.setTasks(tasks);

    }

    @Test
    public void testCountDoneTasks() {

        //Given

        //When
        int result = project.countDoneTasks();

        //Then
        Assert.assertEquals(result, 3);

    }

    @Test
    public void testCountNotDoneTasks() {

        //Given

        //When
        int result = project.countNotDoneTasks();

        //Then
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testGetTasksIfDone() {

        //Given

        //When
        List<Task> result = project.getDoneTasks();

        //Then
        Assert.assertEquals(result.size(), 3);

    }

    @Test
    public void testGetTasksIfNotDone() {

        //Given

        //When
        List<Task> result = project.getNotDoneTasks();

        //Then
        Assert.assertEquals(result.size(), 2);
    }


    private Task createTask(String title, String description, String dueDate, boolean done) {
        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setDone(done);
        return task;
    }

}