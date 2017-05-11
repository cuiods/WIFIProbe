package com.codingfairy.bl.tool;

import com.codingfairy.bl.config.NodeConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author darxyan
 * @version 2017-05-09 20:20:31
 */
public class HDFSTool {

    private static final int BUFFER_SIZE = 1024*1024;

    public static void uploadFiles(final InputStream in, final String outputFile) throws IOException{

        String dst = NodeConfig.HDFS_PATH+outputFile;
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), () -> System.out.print(". "));
        IOUtils.copyBytes(in, out, BUFFER_SIZE, true);
    }

    /**从HDFS上读取文件*/
    public static DataInputStream readFromHdfs(String fileName) throws FileNotFoundException,IOException {

        String dst = NodeConfig.HDFS_PATH+fileName;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        return fs.open(new Path(dst));
    }


    /**从HDFS上删除文件*/
    public static void deleteFromHdfs(String fileName) throws FileNotFoundException,IOException {
        String dst = NodeConfig.HDFS_PATH + fileName;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        fs.deleteOnExit(new Path(dst));
        fs.close();
    }


    /**遍历HDFS上的文件和目录*/
    public static List<String> getDirectoryFromHdfs(String directory) throws FileNotFoundException,IOException {

        String dst = NodeConfig.HDFS_PATH + directory;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FileStatus fileList[] = fs.listStatus(new Path(dst));
        List<String> subFiles = new ArrayList<String>(fileList.length);
        for (FileStatus aFileList : fileList) {
            subFiles.add(aFileList.getPath().getName());
        }
        fs.close();
        return subFiles;
    }

    /**重命名**/
    public static void renameFile(String origin, String newName) throws IOException{
        Configuration conf = new Configuration();
        String str = NodeConfig.HDFS_PATH+origin;
        String dst = NodeConfig.HDFS_PATH+newName;
        FileSystem fs = FileSystem.get(URI.create(str), conf);
        Path srcPath = new Path(str);
        Path dstPath = new Path(dst);
        fs.rename(srcPath, dstPath);
        fs.close();
    }
}
