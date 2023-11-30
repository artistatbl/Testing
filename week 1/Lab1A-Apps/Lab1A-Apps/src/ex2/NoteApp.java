package ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class NoteApp extends JFrame {
    private JTextArea textArea;
    private JButton saveButton;
    private JButton editButton;
    private JButton deleteButton;
    private JList<String> noteList;
    private DefaultListModel<String> listModel;
    private ArrayList<String> notes;

    private int selectedNoteIndex = -1; // Track the index of the selected note

    public NoteApp() {
        // Initialise the GUI components
        textArea = new JTextArea(50, 50);
        saveButton = new JButton("Save");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        listModel = new DefaultListModel<>();
        noteList = new JList<>(listModel);
        notes = new ArrayList<>();

        // Create a JPanel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(saveButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Create a JPanel for the selection panel with BorderLayout
        JPanel selectionPanel = new JPanel(new BorderLayout());
        selectionPanel.add(new JLabel("Notes:"), BorderLayout.NORTH);
        selectionPanel.add(new JScrollPane(noteList), BorderLayout.CENTER);

        // Create a JPanel for the whole content with BorderLayout
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Note Editor:"), BorderLayout.NORTH);
        contentPanel.add(textArea, BorderLayout.WEST);
        contentPanel.add(buttonPanel, BorderLayout.EAST);
        contentPanel.add(selectionPanel, BorderLayout.CENTER);

        // Add the content panel to the frame
        getContentPane().add(contentPanel);

        // Add action listeners to buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNote();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNote();
            }
        });

        noteList.addListSelectionListener(e -> {
            selectedNoteIndex = noteList.getSelectedIndex();
            displaySelectedNote();
        });

        // Load existing notes from file
        loadNotes();

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveNote() {
        String note = textArea.getText();
        if (!note.isEmpty()) {
            if (selectedNoteIndex >= 0) {
                // Replace the selected note with the edited content
                notes.set(selectedNoteIndex, note);
                listModel.set(selectedNoteIndex, "Note " + (selectedNoteIndex + 1));
            } else {
                notes.add(note);
                listModel.addElement("Note " + (listModel.size() + 1));
            }
            textArea.setText("");
            selectedNoteIndex = -1; // Reset the selected note index
            saveNotesToFile();
        }
    }

    private void editNote() {
        if (selectedNoteIndex >= 0) {
            // Populate the text area with the selected note for editing
            textArea.setText(notes.get(selectedNoteIndex));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a note to edit.");
        }
    }

    private void deleteNote() {
        if (selectedNoteIndex >= 0) {
            notes.remove(selectedNoteIndex);
            listModel.remove(selectedNoteIndex);
            textArea.setText("");
            selectedNoteIndex = -1; // Reset the selected note index
            saveNotesToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a note to delete.");
        }
    }

    private void displaySelectedNote() {
        if (selectedNoteIndex >= 0) {
            textArea.setText(notes.get(selectedNoteIndex));
        }
    }

    private void loadNotes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("notes.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
                listModel.addElement("Note " + (listModel.size() + 1));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNotesToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"));
            for (String note : notes) {
                writer.write(note);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NoteApp();
            }
        });
    }
}
