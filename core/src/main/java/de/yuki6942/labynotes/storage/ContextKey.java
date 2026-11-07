package de.yuki6942.labynotes.storage;

import java.util.Locale;

public final class ContextKey
{
  private final String kind;
  private final String id;

  private ContextKey(String kind, String id) {
    this.kind = kind;
    this.id = id;
  }

  public static ContextKey server(String address) {
    String normalized = address.trim().toLowerCase(Locale.ROOT);
    if (normalized.endsWith(":25565")) {
      normalized = normalized.substring(0, normalized.length() - ":25565".length());
    }
    return new ContextKey("server", normalized);
  }

  public static ContextKey world(String world)
  {
    return new ContextKey("world", world.trim());
  }

  public String kind() {
    return this.kind;
  }

  public String id() {
    return this.id;
  }

  public String fileName() {
    String fileName = this.id.replaceAll("[^a-zA-Z0-9._-]", "_");
    return this.kind + "_" + fileName + ".json";
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof ContextKey other && other.kind.equals(this.kind) && other.id.equals(this.id);
  }

  @Override
  public int hashCode() {
    return 31 * this.kind.hashCode() + this.id.hashCode();
  }

  @Override
  public String toString() {
    return this.kind + ":" + this.id;
  }

}
