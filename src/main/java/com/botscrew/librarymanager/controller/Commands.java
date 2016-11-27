package com.botscrew.librarymanager.controller;


public enum Commands {
    ADD,
    EDIT,
    REMOVE,
    ALL_BOOKS,
    HELP,
    EXIT;

    public static String getHelp() {
        return ADD.name() + "\t\t | {author} \"{book name}\"\n" +
                EDIT.name() + "\t | {book name}\n" +
                REMOVE.name() + "\t | {book name}\n" +
                ALL_BOOKS.name() + "| no params\n" +
                HELP.name() + "\t | no params\n" +
                EXIT.name() + "\t | no params";
    }
}
