package com.example.SpringTest;

import java.util.ArrayList;
import java.util.List;

interface FileNode{
    void ls(String indent);
    Dir mkdir(String name);
}

class Dir implements FileNode{

    public String dirName;
    public List<FileNode> fileNodes = new ArrayList<>();

    public Dir(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void ls(String indent) {
        System.out.println(indent + "+" + dirName );
        for(FileNode node : fileNodes)
        {
            node.ls(indent + " ");
        }
    }

    @Override
    public Dir mkdir(String name)
    {
        for(FileNode node : fileNodes)
        {
            if(node instanceof Dir dir && dir.dirName.equals(name))
            {
                System.out.println("Directory with that name already exist - "+name);
                return this;
            }
        }
        Dir temp = new Dir(name);
        fileNodes.add(temp);
        return temp;
    }

    public Filee touch(String name)
    {
        Filee temp = new Filee(name);
        this.fileNodes.add(temp);
        return temp;
    }
}

class Filee implements FileNode{
    String fileeName;

    public Filee(String fileeName) {
        this.fileeName = fileeName;
    }

    @Override
    public void ls(String indent) {
        System.out.println(indent + "-" + fileeName);
    }

    @Override
    public Dir mkdir(String name)
    {
        System.out.println("mkdir in file not supported");
        return null;
    }
}

public class FileSystemOnlyLs {
    public static void main(String[] args) {
        Dir root = new Dir("root");
//        Dir tmp = new Dir("tmp");
        Dir tmp = root.mkdir("tmp");
//        root.fileNodes.add(tmp);
        Filee hello = tmp.touch("hello.txt");
       
        root.ls(" ");
//        Dir etc = new Dir("etc");
//        root.fileNodes.add(etc);
//        root.ls("");
    }
}
