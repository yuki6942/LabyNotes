package de.yuki6942.labynotes.notes;

import de.yuki6942.labynotes.journal.JournalEntry;
import de.yuki6942.labynotes.todo.ToDoItem;
import java.util.ArrayList;
import java.util.List;

public class Notebook {

  public static final int CURRENT_SCHEMA = 1;

  private int schema = CURRENT_SCHEMA;
  private String notes = "";
  private List<ToDoItem> todos = new ArrayList<>();
  private List<JournalEntry> journal = new ArrayList<>();

  public int getSchema() {
    return this.schema;
  }

  public String getNotes() {
    return this.notes;
  }

  public void setNotes() {
    this.notes = notes == null ? "": notes;
  }

  public List<ToDoItem> getTodos() {
    return this.todos;
  }

  public List<JournalEntry> getJournal() {
    return this.journal;
  }

  public void ensureDefaults() {
    if (this.notes == null) this.notes = "";
    if (this.todos == null) this.todos = new ArrayList<>();
    if (this.journal == null) this.journal = new ArrayList<>();
  }
}
