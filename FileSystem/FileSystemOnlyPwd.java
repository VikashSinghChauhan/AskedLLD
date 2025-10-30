package com.example.SpringTest;

import java.util.*;

interface FileNodePwd{

    String pwd();
}

class FilePwd implements FileNodePwd{
    String fileName;
    DirPwd parent;

    public FilePwd(String fileName, DirPwd parent) {
        this.fileName = fileName;
        this.parent = parent;
    }

    @Override
    public String pwd() {
        return "";
    }
}

class DirPwd implements FileNodePwd{
    String dirName;
    DirPwd parent;
    List<FileNodePwd> list = new ArrayList<>();

    public DirPwd(String dirName, DirPwd parent) {
        this.dirName = dirName;
        this.parent = parent;
    }

    @Override
    public String pwd() {
        //        Stack<String> stk = new Stack<>();
        DirPwd curr = this;
        Deque<String> stack = new ArrayDeque<>();
        while(curr!=null)
        {
            stack.push(curr.dirName);
            curr = curr.parent;
        }
        String path = String.join("/", stack);
        path = "/" + path;

        return path;
    }
}
public class FileSystemOnlyPwd {
    public static void main(String[] args) {
        DirPwd root = new DirPwd("root",null);
        DirPwd tmp = new DirPwd("tmp", root);
        DirPwd etc = new DirPwd("etc", tmp);

        root.list.add(tmp);
        tmp.list.add(etc);
        System.out.println(etc.pwd());
    }
}
