/*
 * @(#)IdentificationTable.java                2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */

package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.Declaration;
import java.util.*;

public final class IdentificationTable {

  private int level;
  private IdEntry latest;
  private IdentificationTable privateEntries;
  private boolean privateReading;

  public ArrayList<String> packagesIDs;

  public IdentificationTable () {
    level = 0;
    latest = null;

    this.packagesIDs = new ArrayList<String>();
  }

  public IdentificationTable (IdentificationTable main) {
    level = main.level;
    latest = main.latest;
    privateEntries = main.privateEntries;
  }

  //Va a necesitar esto
  public IdEntry getLatest() {
    return latest;
  }

  // Opens a new level in the identification table, 1 higher than the
  // current topmost level.

  public void openScope () {
    level ++;
  }

  // Closes the topmost level in the identification table, discarding
  // all entries belonging to that level.

  public void closeScope () {
    IdEntry entry, local;
    // Presumably, idTable.level > 0.
    entry = this.latest;
    while (entry.level == this.level) {
      local = entry;
      entry = local.previous;
    }
    this.level--;
    this.latest = entry;
  }

  public void startPrivateReading(IdentificationTable privateTable){
    this.privateEntries = privateTable;
    this.privateReading = true;
  }

  public void stopPrivateReading(){
    this.privateEntries = null;
    this.privateReading = false;
  }

  /**
   * Modified by Óscar Cortés
   */
  public List<IdEntry> getEntriesUntil(IdEntry currentEntry, String id){
    if (currentEntry == null || currentEntry.id.equals(id))
      return new LinkedList<>();
    else {
      List<IdEntry> result = getEntriesUntil(currentEntry.previous, id);
      result.add(currentEntry);
      return result;
    }
  }

  // Makes a new entry in the identification table for the given identifier
  // and attribute. The new entry belongs to the current level.
  // duplicated is set to to true iff there is already an entry for the
  // same identifier at the current level.

  public void enter (String id, Declaration attr) {
    enter(id, attr, "");
  }

  public void enter (String id, Declaration attr, String pPackage) {

    IdEntry entry = this.latest;
    boolean present = false, searching = true;

    // Check for duplicate entry ...
    while (searching) {
      if (entry == null || entry.level < this.level)
        searching = false;
      else if (entry.id.equals(id)) {
        present = true;
        searching = false;
      } else
        entry = entry.previous;
    }

    attr.duplicated = present;
    // Add new entry ...
    entry = new IdEntry(id, attr, this.level, this.latest, pPackage);
    this.latest = entry;
  }


  // Finds an entry for the given identifier in the identification table,
  // if any. If there are several entries for that identifier, finds the
  // entry at the highest level, in accordance with the scope rules.
  // Returns null iff no entry is found.
  // otherwise returns the attribute field of the entry found.

  public Declaration retrieve (String id)
  {
    return retrieve(id, "");
  }

  public Declaration retrieve (String id, String idPackage) {

    IdEntry entry;
    Declaration attr = null;
    boolean searching = true;


    entry = this.latest;
    while (searching) {

      if (entry == null){
        searching = false;
        //Like, here

        if (privateReading)
          attr = privateEntries.retrieve(id);
      }
      else if (entry.id.equals(id) && entry.idPackage.equals(idPackage)) {
        searching = false;
        attr = entry.attr;
      } else
        entry = entry.previous;
    }
    return attr;
  }


  //Added by Nahomy
  public boolean isPackaged(String idSpelling)
  {
    IdEntry entry;
    boolean present = false, searching = true;
    entry = this.latest;
    while (searching) {
      if (entry == null)
        searching = false;
      else if (entry.id.equals(idSpelling) && !entry.idPackage.equals("")) {
        present = true;
        searching=false;
      } else
        entry = entry.previous;
    }
    return present;
  }
  //Added by Nahomy
  public boolean isPackageCorrect(String idDec, String idPackage)
  {
    IdEntry entry;
    boolean present = false, searching = true;
    entry = this.latest;
    while (searching) {
      if (entry == null)
        searching = false;
      else if (entry.id.equals(idDec) && entry.idPackage.equals(idPackage)) {
        present = true;
        searching=false;
      } else
        entry = entry.previous;
    }
    return present;
  }
  //Added by Nahomy
  public String retrievePackage(String id)
  {
    IdEntry entry;
    boolean  searching = true;
    entry = this.latest;
    while (searching) {
      if (entry == null)
        searching = false;
      else if (entry.id.equals(id)) {
        searching=false;
      } else
        entry = entry.previous;
    }
    return entry.idPackage;
  }
}
