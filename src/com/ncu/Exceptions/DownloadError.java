package com.ncu.Exceptions;

public class DownloadError extends Exception
{
    DownloadError(String s)
    {
        super(s);
    }
}