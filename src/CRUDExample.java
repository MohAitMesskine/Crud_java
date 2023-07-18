import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class CRUDExample extends JFrame {
    private ArrayList<Student> studentList;
    private JTextField idField, nameField, ageField;
    private JButton addButton, updateButton, deleteButton, viewButton;
    private JTextArea displayArea;

    public CRUDExample() {
        studentList = new ArrayList<>();

        idField = new JTextField(10);
        nameField = new JTextField(10);
        ageField = new JTextField(10);

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                Student student = new Student(id, name, age);
                studentList.add(student);

                clearFields();
                displayMessage("Student added successfully!");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());

                for (Student student : studentList) {
                    if (student.getId() == id) {
                        studentList.remove(student);
                        Student updatedStudent = new Student(id, name, age);
                        studentList.add(updatedStudent);

                        clearFields();
                        displayMessage("Student updated successfully!");
                        return;
                    }
                }

                displayMessage("Student with ID " + id + " not found.");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());

                for (Student student : studentList) {
                    if (student.getId() == id) {
                        studentList.remove(student);

                        clearFields();
                        displayMessage("Student deleted successfully!");
                        return;
                    }
                }

                displayMessage("Student with ID " + id + " not found.");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");

                for (Student student : studentList) {
                    displayMessage(student.getId() + "\t" + student.getName() + "\t" + student.getAge());
                }
            }
        });
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
    }

    private void displayMessage(String message) {
        displayArea.append(message + "\n");
    }

    public static void main(String[] args) {
        CRUDExample crudExample = new CRUDExample();
        crudExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        crudExample.setSize(400, 300);
        crudExample.setVisible(true);
        crudExample.setTitle("crud java");
    }
}
