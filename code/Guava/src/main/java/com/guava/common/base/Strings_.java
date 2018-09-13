package com.guava.common.base;


import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

/**
 *  emptyToNull   (String)
 *  isNullOrEmpty (String)
 *  nullToEmpty   (String)
 */
public class Strings_ {

  public static void main(String[] args) {
    System.out.println(Strings.emptyToNull(new String()));
    System.out.println(Strings.nullToEmpty(null));
    System.out.println(Strings.isNullOrEmpty(null));
    System.out.println(Strings.isNullOrEmpty(""));

    System.out.println(Strings.padStart("7",10,'0'));
    System.out.println(Strings.padEnd("7",10,'0'));
    System.out.println(Strings.padStart("2004",3,'0'));

    System.out.println(MoreObjects.firstNonNull(null, "1"));
  }

}
