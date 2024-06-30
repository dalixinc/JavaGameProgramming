package com.dalixinc.javagames.scratch;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * Based on Jose Paumard's Java 8 Fundamentals Input/Output course on Pluralsight.
 *
 * https://app.pluralsight.com/ilx/video-courses/4b179c26-8965-42fe-8586-00a2bc3ba251/2d44487d-e73e-4f73-851e-2a550daa6baf/350300fa-2784-4e46-b832-f3c4af4fbd3b
 *
 */

public class FilePlay {

    private static final Logger logger = LogManager.getLogger(FilePlay.class);
    public static void main(String[] args) {
        System.out.println("Hello, FilePlay!");

        logger.trace("TRACE - We've just greeted the user!");
        logger.debug("DEBUG -  We've just greeted the user!");
        logger.info("INFO - We've just greeted the user!");
        logger.warn("WARN -  We've just greeted the user!");
        logger.error("ERROR - We've just greeted the user!");
        logger.fatal("FATAL -  We've just greeted the user!");

        //File file = new File("src/main/java/com/dalixinc/javagames/scratch/FilePlay.java");
        File file = new File("FilePlay.txt");
        System.out.println("File exists: " + file.exists());
        System.out.println("File is a directory: " + file.isDirectory());
        System.out.println("File is a file: " + file.isFile());
        System.out.println("File is hidden: " + file.isHidden());

        System.out.println("File can be read: " + file.canRead());
        System.out.println("File can be written: " + file.canWrite());
        System.out.println("File can be executed: " + file.canExecute());

        System.out.println("File path: " + file.getPath());
        System.out.println("File absolute path: " + file.getAbsolutePath());
        logger.info("File absolute path: " + file.getAbsolutePath());
        try {
            //System.out.println("File canonical path: " + file.getCanonicalPath());
            logger.info("File canonical path: " + file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("File parent: " + file.getParent());

        System.out.println("File name: " + file.getName());

        System.out.println("File length: " + file.length());

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        System.out.println("File last modified: " + sdf.format(file.lastModified()));

        try {
            File newFile = new File("NewFile.txt");
            System.out.println("File is created: " + newFile.createNewFile());
/*          newFile.mkdir();
            newFile.mkdirs();

            newFile.delete();
            newFile.deleteOnExit();
            newFile.renameTo(new File("RenamedFile.txt");*/

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
