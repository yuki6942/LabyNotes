package de.yuki6942.labynotes.notes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import de.yuki6942.labynotes.storage.ContextKey;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class NotebookStorage {

  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
  private final Path directory;
  private final Map<ContextKey, Notebook> cache = new HashMap<>();

  public NotebookStorage(Path directory) {
    this.directory = directory;
  }

  public Notebook get(ContextKey key) {
    return this.cache.computeIfAbsent(key, this::loadFromDisk);
  }

  public void save(ContextKey key) throws IOException {
    Notebook notebook = this.cache.get(key);
    if (notebook == null) {
      return;
    }

    Files.createDirectories(this.directory);
    Path target = this.directory.resolve(key.fileName());
    Path temp = this.directory.resolveSibling(key.fileName() + ".tmp");

    try (Writer writer = Files.newBufferedWriter(temp, StandardCharsets.UTF_8)) {
      this.gson.toJson(notebook, writer);
    }
    try {
      Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    } catch (AtomicMoveNotSupportedException e) {
      Files.move(temp, target, StandardCopyOption.REPLACE_EXISTING);
    }
  }

  public  void saveAll() throws IOException {
    for (ContextKey key : this.cache.keySet()) {
      this.save(key);
    }
  }

  private Notebook loadFromDisk(ContextKey key) {
    Path file = this.directory.resolve((key.fileName()));
    if (!Files.exists(file)) {
      return new Notebook();
    }

    try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
      Notebook notebook = this.gson.fromJson(reader, Notebook.class);
      if (notebook == null) {
        return new Notebook();
      }
      notebook.ensureDefaults();
      return  notebook;
    } catch (IOException | JsonParseException e) {
      this.backupBrokenFile(file);
      return new Notebook();
    }
  }


  private void backupBrokenFile(Path file) {
    try {
      Path backup = file.resolveSibling(
          file.getFileName() + ".broken-" + System.currentTimeMillis());
      Files.move(file, backup);
    } catch (IOException ignored) {
    }

  }
}
