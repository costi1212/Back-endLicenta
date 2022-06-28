package com.parcel.ParcelManagerBackend.services;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.net.URI;

@Service
public class HDFSService {

    @Value("${data.path}")
    private String dataPath;

    private static final Configuration configuration;
    private static final String HDFS_PATH = "hdfs://sandbox-hdp.hortonworks.com:8020";
    private static final String HDFS_USER = "raj_ops";
    private static FileSystem fs;

    static { configuration = new Configuration(); }
    public FileSystem getFileSystem(){
        try{
            if(fs == null)
                fs = FileSystem.get(new URI(HDFS_PATH), configuration, HDFS_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fs;
    }

    public void createDir(String dirName) throws IOException {
        FileSystem fs = getFileSystem();
        fs.mkdirs(new Path("/user/raj_ops/"+dirName));
        //fs.close();
    }

    public void copyAllFiles(){
        FileSystem fs = getFileSystem();

    }

    public void copyFromLocalFile(String fileName) throws IOException {
        FileSystem fs = getFileSystem();
        Path localFile = new Path(dataPath + fileName);
        Path destPath = new Path("/user/raj_ops/parcels/raw/"+ fileName);
        fs.copyFromLocalFile(localFile, destPath);
        fs.close();
    }

    public void copyAll() throws IOException{
        File dir = new File(dataPath);
        File [] fileList = dir.listFiles();
        if(fileList!= null){
            for (File file:fileList)
                copyFromLocalFile(file.toString().split("\\\\")[file.toString().split("\\\\").length-1]);
        }
    }

    public void copyToLocalFile(String sourceFilePath, String localFilePath) throws IOException {
        fs.copyToLocalFile(new Path(sourceFilePath), new Path(localFilePath));
    }

    public void delete(String path) throws IOException {
        fs.delete(new Path(path));
    }



}
