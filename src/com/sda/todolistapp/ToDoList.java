package com.sda.todolistapp;

import java.io.*;
import java.util.*;

public class ToDoList {
    List<Project> projects = new ArrayList<>();

    public ToDoList() {
        projects = new ArrayList<>();
    }

    private int countAllDoneTasks() {
        int counter = 0;
        for(Project project : projects) {
            counter += project.countDoneTasks();
        }
        return counter;
    }

    private int countAllNotDoneTasks() {
        int counter = 0;
        for(Project project : projects) {
            counter += project.countNotDoneTasks();
        }
        return counter;
    }

    public void write() {
        File toDoListFile=new File(Constants.TODO_LIST_FILE_NAME);
        try(FileOutputStream fos=new FileOutputStream(toDoListFile);
            ObjectOutputStream oos=new ObjectOutputStream(fos)) {

            oos.writeObject(projects);
            oos.flush();
        }catch(Exception e) {


        }
    }

    public void read() {
        File toDoListFile=new File(Constants.TODO_LIST_FILE_NAME);
        try(FileInputStream fis=new FileInputStream(toDoListFile);
            ObjectInputStream ois=new ObjectInputStream(fis)) {

            projects = (List<Project>) ois.readObject();
        } catch(Exception e) {

        }
    }

    public void start() {
        read();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Project selectedProject = null;

        System.out.println(Constants.WELCOME_MESSAGE);
        while (!exit) {
            int choice;
            System.out.println(String.format("You have %d tasks todo and %d tasks are done!", countAllNotDoneTasks(), countAllDoneTasks()));
            if(selectedProject == null) {
                System.out.println("Please pick a project: ");
                selectedProject = handleProjectChoice(scanner);
            } else {
                System.out.println("Project: " + selectedProject.getName());
                System.out.println(Constants.USER_CHOICES);
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.println("Enter task data:");
                        System.out.println();
                        Task task = new Task();

                        System.out.println("Enter title: ");
                        String title = scanner.nextLine();
                        task.setTitle(title);

                        System.out.println("Enter description: ");
                        String description = scanner.nextLine();
                        task.setDescription(description);

                        System.out.println("Enter due date in format dd.mm.yyyy: ");
                        String dueDate = scanner.nextLine();
                        task.setDueDate(dueDate);

                        selectedProject.addTask(task);
                        System.out.println("Task successfuly added to " + selectedProject.getName());
                        break;
                    case 2:
                        printTasks(selectedProject.getTasks());
                        break;
                    case 3:
                        printTasks(selectedProject.getDoneTasks());
                        break;
                    case 4:
                        printTasks(selectedProject.getNotDoneTasks());
                        break;
                    case 5:
                        printTasks(selectedProject.sortTasksByDueDate());
                        break;
                    case 6:
                        System.out.println("Select the task you want to mark as done: ");
                        printTasks(selectedProject.getTasks());
                        selectedProject.setTaskAsDone(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 7:
                        System.out.println("Select the task you want to edit: ");
                        printTasks(selectedProject.getTasks());

                        int index = scanner.nextInt();
                        scanner.nextLine();

                        Task selectedTask = selectedProject.getTask(index);

                        System.out.println("Enter new title: ");
                        selectedTask.setTitle(scanner.nextLine());

                        System.out.println("Enter new description: ");
                        selectedTask.setDescription(scanner.nextLine());

                        System.out.println("Enter new due date in format dd.mm.yyyy: ");
                        selectedProject.setTask(index, selectedTask);
                        break;
                    case 8:
                        System.out.println("Select the task you want to remove: ");
                        printTasks(selectedProject.getTasks());
                        selectedProject.removeTask(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 9:
                        System.out.println("Saving...");
                        write();
                        System.out.println("Changes are saved.");
                        break;
                    case 10:
                        System.out.println("Back ...");
                        selectedProject = null;
                        break;
                    case 11:
                        System.out.println("Exit ...");
                        exit = true;
                        write();
                        break;
                    default:
                        break;
                }
            }

        }

    }

    private void printTasks(List<Task> tasks) {
        for(int i=0; i< tasks.size(); i++) {
            System.out.println(i+". " + tasks.get(i).toString());
        }
    }
    private Project handleProjectChoice(Scanner scanner) {
        showProjects();
        int projectIndex = scanner.nextInt();
        scanner.nextLine();
        Project project;
        if(projectIndex == projects.size()) {
            System.out.println("Enter project name: ");
            String newProjectName = scanner.nextLine();
            project = createNewProject(newProjectName);
        } else {
            project = getProject(projectIndex);
        }
        return project;
    }

    private void showProjects() {
        int projectsSize = projects.size();
        if(projects.size() == 0) {
            System.out.println("Projects do not exist. Please create project");
        } else {
            System.out.println("Please select the project name in which this task belongs or create new project");
            for(int i=0; i<projects.size(); i++) {
                System.out.println(String.format("%3d. %s", i, projects.get(i).getName()));
            }
        }
        System.out.println(String.format("%3d. Create new", projectsSize));
    }

    private Project createNewProject(String projectName) {
        Project project = new Project(projectName);
        projects.add(project);
        return project;
    }

    private Project getProject(int index) {
        return projects.get(index);
    }

}
