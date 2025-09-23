/*
5. Machine Coding Round (22 March) Interviewer Designation: SSE Duration: 1 hr Problem:
Implement the File system API.
The function will mimic their respective Linux commands
Implement mkdir
Implement cd (The path may contain regex) note :: note :: (parent variable required for implementing this)
Implement pwd   note :: (parent variable required for implementing this)
Implement ls

 */


package com.example.SpringTest;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

interface FileSystemNode{
    void ls(String indent);
    Directory cd(String path);
    void pwd();
    void mkdir(String path);

}

class File implements FileSystemNode{
    String name;
    Directory parent;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void ls(String indent) {
        System.out.println(indent+"+"+name);
    }

    @Override
    public Directory cd(String path) {
        System.out.println("Not a directory.");
        return this.parent;
    }

    @Override
    public void pwd() {
        System.out.println("File :: pwd not supported!");
    }

    @Override
    public void mkdir(String dirName) {
        System.out.println("Not a directory.");
    }
}

class Directory implements FileSystemNode{

    public List<FileSystemNode> fileSystemNodes = new ArrayList<>();

    String name ;
    Directory parent; //to be added for pwd



    public Directory(String name) {
        this.name = name;
    }



    public void addFs(FileSystemNode fs)
    {
        fileSystemNodes.add(fs);
        if(fs instanceof Directory dir)
        {
            dir.parent = this;
        }
        else {
            File file = (File)fs;
            file.parent = this;
        }
    }

    @Override
    public void ls(String indent) {
        System.out.println(indent+"-"+name);
        for(FileSystemNode fs : fileSystemNodes)
        {
            fs.ls(indent+" ");
        }
    }

    @Override
    public Directory cd(String path) {
        if(path.equals("."))
        {
            return this;
        }
        else if(path.equals(".."))
        {
            if(this.parent != null)
                return this.parent;
            else{
                System.out.println("already at root :");
                return this;
            }
        }
        else
        {
            for(FileSystemNode fs : fileSystemNodes)
            {
                if(fs instanceof Directory dir && dir.name.equals(path))
                {
                    return dir;
                }
            }
            System.out.println("Invalid directory");
            return this;
        }

    }

    @Override
    public void pwd() {
        List<String> path = new ArrayList<>();
        Directory curr = this;
        while(curr!=null)
        {
//            System.out.println(curr.name);
            path.add(curr.name);
            curr = curr.parent;
        }
        Collections.reverse(path);
        String s = "/";
        for(String str : path)
        {
            s  = s + str + "/";
        }
        System.out.println(s);
    }

    //added just for sake, addFs does this work.
    @Override
    public void mkdir(String dirName) {
        Directory node = new Directory(dirName);
        node.parent = this;
        fileSystemNodes.add(node);
    }
}

public class FileSystem {



    public static void main(String[] args) {

        Directory root = new Directory("root");
        Directory tmp = new Directory("tmp");

        root.addFs(tmp);
        File text1 = new File("text1.text");
        File text2 = new File("text2.text");

        tmp.addFs(text1);
        tmp.addFs(text2);

        root.ls("");
        root.pwd();
        root.cd("tmp");
        tmp.pwd();

    }

}
