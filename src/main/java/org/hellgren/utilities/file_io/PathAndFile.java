package org.hellgren.utilities.file_io;

public record PathAndFile(
        String path,
        String name,
        String fileType
) {

    public static PathAndFile xlsxOf(String path, String name) {
        return new PathAndFile(path,name,"xlsx");
    }

    public static PathAndFile xlsOf(String path, String name) {
        return new PathAndFile(path,name,"xls");
    }

    public String fullName() {
        return path+name+"."+fileType;
    }

}
