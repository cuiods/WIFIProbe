package com.codingfairy.tool;

import com.codingfairy.config.NodeConfig;
import com.codingfairy.vo.PhoneJson;
import com.codingfairy.vo.ProbeJson;
import com.google.gson.Gson;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import javax.xml.soap.Node;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author darxyan
 * @version 2017-05-09 20:20:31
 */
public class HDFSTool {

    public static void main(String[] args) {
        try {
            readFromHdfs("/input/sample.txt");
//            Gson gson = new Gson();
//            String file = "/home/darxan/code/WIFIProbe/mapreduce/common/src/main/resources/sample.txt";
//            ProbeJson probeJson = gson.fromJson(new FileReader(file), ProbeJson.class);
//            List<ProbeJson> probeJsonList = new ArrayList<ProbeJson>(1);
//            probeJsonList.add(probeJson);
//            System.out.println(gson.toJson(probeJsonList));
//            uploadFiles(new FileInputStream(file), "/input/a.txt");
        }catch (Exception e) {

        }
    }



    private static final int BUFFER_SIZE = 1024*1024;

    public static void uploadFiles(final InputStream in, final String outputFile) throws IOException{

        String dst = NodeConfig.HDFS_PATH+outputFile;
        System.out.println(dst);
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.print(". ");
            }
        });
        IOUtils.copyBytes(in, out, BUFFER_SIZE, true);
    }



    /**从HDFS上读取文件*/
    public static DataInputStream readFromHdfs(String fileName) throws IOException {

        String dst = NodeConfig.HDFS_PATH+fileName;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        return fs.open(new Path(dst));
    }


    /**从HDFS上删除文件*/
    public static void deleteFromHdfs(String fileName) throws IOException {
        String dst = NodeConfig.HDFS_PATH + fileName;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        fs.deleteOnExit(new Path(dst));
        fs.close();
    }

    /**从HDFS上清空目录*/
    public static void emptyDirectory(String directory) throws IOException {
        String dst = NodeConfig.HDFS_PATH + directory;
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        FileStatus fileList[] = fs.listStatus(new Path(dst));
        List<String> subFiles = new ArrayList<String>(fileList.length);
        for (FileStatus aFileList : fileList) {
            fs.delete(aFileList.getPath(), true);
        }
        fs.close();
        
    }


    /**遍历HDFS上的文件和目录*/
    public static List<String> getDirectoryFromHdfs(String directory) throws IOException {

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

//    public static void concat(String ...args) throws IOException {
//        if(args.length < 2) {
//            System.err.println("Usage HDFSConcat target srcs..");
//           return;
//        }
//
//        Configuration conf = new Configuration();
//        String uri = conf.get("fs.default.name", NodeConfig.HDFS_PATH);
//        Path path = new Path(uri);
//        DistributedFileSystem dfs =
//                (DistributedFileSystem)FileSystem.get(path.toUri(), conf);
//
//        Path [] srcs = new Path[args.length-1];
//        for(int i=1; i<args.length; i++) {
//            srcs[i-1] = new Path(args[i]);
//        }
//        dfs.concat(new Path(args[0]), srcs);
//    }


    public static void concat(String dest, String dir, boolean delete) throws IOException {
        String directory = NodeConfig.HDFS_PATH + dir;
        Configuration conf = new Configuration();
        DistributedFileSystem fs = (DistributedFileSystem)FileSystem.get(URI.create(directory), conf);
        FileStatus fileList[] = fs.listStatus(new Path(directory));
        ArrayList<Path>  srcs = new ArrayList<Path>(fileList.length);
        for (FileStatus fileStatus : fileList) {
            if (fileStatus.isFile()) {
                srcs.add(fileStatus.getPath());
            }
        }
        
        Path appended = new Path(dest);
        Path[] sources = new Path[srcs.size()];
        for (int i=0; i<srcs.size(); i++) {
            sources[i] = srcs.get(i);
        }
        fs.create(appended);
        fs.concat(appended, sources );
        if (delete) {
            for (Path file:srcs) {
                fs.delete(file, false);
            }
        }
        
        fs.close();
    }
}
