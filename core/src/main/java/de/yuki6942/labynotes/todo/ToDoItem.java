package de.yuki6942.labynotes.todo;

public class ToDoItem {
  private String text;
  private boolean done;
  private long createdAt;

  // needed by Gson
  public ToDoItem() {
  }

  public ToDoItem(String text) {
    this.text = text;
    this.createdAt = System.currentTimeMillis();
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isDone() {
    return this.done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public long getCreatedAt() {
    return this.createdAt;
  }

}
