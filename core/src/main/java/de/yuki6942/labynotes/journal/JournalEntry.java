package de.yuki6942.labynotes.journal;

public class JournalEntry {

  private long timestamp;
  private String type;
  private String message;
  private String dimension;
  private int x;
  private int y;
  private int z;
  private String screenshot;

  // needed by Gson
  public JournalEntry() {
  }

  public JournalEntry(String type, String message, String dimension, int x, int y, int z) {
    this.timestamp = System.currentTimeMillis();
    this.type = type;
    this.message = message;
    this.dimension = dimension;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public String getType() {
    return this.type;
  }

  public String getMessage() {
    return this.message;
  }
  public String getDimension() {
    return this.dimension;
  }
  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }
  public int getZ() {
    return this.z;
  }

  public String getScreenshot() {
    return this.screenshot;
  }

  public void setScreenshot(String screenshot) {
    this.screenshot = screenshot;
  }

}
