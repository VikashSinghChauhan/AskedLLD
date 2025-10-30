package com.example.SpringTest;

import java.util.ArrayList;
import java.util.List;

interface FileNodeCd{
    DirCd cd(String path);
}

class DirCd implements FileNodeCd{

    String dirName;
    DirCd parent;
    List<FileNodeCd> children = new ArrayList<>();

    public DirCd(String dirName, DirCd parent) {
        this.dirName = dirName;
        this.parent = parent;
    }

    public DirCd extendedCd(String path){
        String[] pathList = path.split("/");
        DirCd curr = this;
        for(int i=0;i<pathList.length;i++)
        {
            if(pathList[i].equals(".") || pathList[i].equals(""))continue;
            else if(pathList[i].equals(".."))
            {
                curr = (curr.parent!=null?curr.parent:curr);
                continue;
            }
            boolean found = false;

           for(FileNodeCd node : curr.children) {
               if (node instanceof DirCd dir && dir.dirName.equals(pathList[i])) {

                   curr = dir;
                   found = true;
                   break;
               }
           }
           if(!found) {
               System.out.println("Extended path not found !!");
               return this;
           }
        }
        return curr;
    }

    @Override
    public DirCd cd(String path) {
        if(path.equals("."))return this;
        if(path.equals(".."))return parent!=null?parent:this;
        for(FileNodeCd node : children) {
            if (node instanceof DirCd)
            {
                DirCd dirCd = (DirCd) node;
                if(dirCd.dirName.equals(path))
                    return dirCd;
            }
        }
        System.out.println("Directory with name : "+path + " not found.");
        return this;
    }
}

class FileCd implements FileNodeCd{
    String fileName;
    DirCd parent;

    public FileCd(String fileName, DirCd parent) {
        this.fileName = fileName;
        this.parent = parent;
    }

    @Override
    public DirCd cd(String path) {

        System.out.println("cd not supported in a file : "+fileName);
        return parent;
    }
}
public class FileSystemOnlyCd {
    public static void main(String[] args) {
        DirCd root = new DirCd("root",null);
        DirCd tmp = new DirCd("tmp", root);
        DirCd etc = new DirCd("etc", tmp);
        root.children.add(tmp);
        tmp.children.add(etc);
        System.out.println(tmp.cd("..").cd("..").dirName);
        System.out.println(root.extendedCd("tmp/./etc/..").dirName);
    }
}
