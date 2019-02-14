package com.ncu.Download;
import com.ncu.exceptions.*;
import java.io.*;
import java.net.*;
import java.util.*;

class Download extends Observable implements Runnable {
    public static final String[] STATUSES = {"Downloading", "Complete", "Error", "Paused", "Cancelled", };
    public static final int DOWNLOADING = 0, PAUSED = 1, COMPLETE = 2, CANCELLED = 3, ERROR = 4;
    private URL url;
    private int size, downloaded, status;
    private static final int MAX_BUFFER_SIZE = 1024;

    public Download(URL url) {
        this.url = url;
        size = -1;
        downloaded = 0;
        status = DOWNLOADING;
        download();
    }
    public String getUrl() {
        return url.toString();
    }
    public int getSize() {
        return size;
    }
    public float getProgress() {
        return ((float) downloaded / size) * 100;
    }
    public int getStatus() {
        return status;
    }
    private void stateChanged() {
        setChanged();
        notifyObservers();
    }
    public void pause() {
        status = PAUSED;
        stateChanged();
    }
    public void resume() {
        status = DOWNLOADING;
        stateChanged();
        download();
    }
    public void cancel() {
        status = CANCELLED;
        stateChanged();
    }

    private void error() throws DownloadError {
        status = ERROR;
        stateChanged();
        throw new DownloadError("Download Error");
    }

    private void download() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private String getFileName(URL url) {
        String fileName = url.getFile();
        return fileName.substring(fileName.lastIndexOf('/') + 1);
    }

    public void run() {
        RandomAccessFile file = null;
        InputStream stream = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
            connection.connect();
            try {
                if (connection.getResponseCode() / 100 != 2)
                {
                    error();
                }
            }
            catch (DownloadError e){
                System.out.println(" Not a valid URL download file ");
                System.out.println(e);
            }
            int contentLength = connection.getContentLength();
            if (contentLength < 1) {
                error();
            }
            if (size==-1) {
                size = contentLength;
                stateChanged();
            }

            file = new RandomAccessFile(getFileName(url), "rw");
            file.seek(downloaded);
            stream = connection.getInputStream();
            while (status==DOWNLOADING) {
                byte buffer[];
                if (size-downloaded>MAX_BUFFER_SIZE) {
                    buffer = new byte[MAX_BUFFER_SIZE];
                }
                else {
                    buffer = new byte[size - downloaded];
                }
                int read = stream.read(buffer);
                if (read == -1) {
                    break;
                }
                file.write(buffer, 0, read);
                downloaded += read;
                stateChanged();
            }
            if (status==DOWNLOADING) {
                status=COMPLETE;
                stateChanged();
            }
        } catch (Exception e) {
            try {
                error();
            }
            catch (DownloadError e1)
            {
                System.out.println(e);
            }
        } finally {
            if (file!=null) {
                try {
                    file.close();
                } catch (Exception e) {}
            }
            if (stream!=null) {
                try {
                    stream.close();
                } catch (Exception e) {}
            }
        }
    }
}
