package com.ncu.Exceptions;

public class FileAlreadyExists extends Exception
{
    public FileAlreadyExists(String s)
    {
        super(s);
    }
}