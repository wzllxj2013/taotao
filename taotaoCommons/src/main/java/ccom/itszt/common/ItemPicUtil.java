package ccom.itszt.common;

import org.apache.commons.lang3.StringUtils;

public class ItemPicUtil {

    public static final String FDFS_BASE_URL="http://192.168.3.122:8888";

    public static String genFullPath(String fdfsPath){

        boolean b = StringUtils.startsWith(fdfsPath, "/");
        if (!b) {
            return FDFS_BASE_URL+"/"+fdfsPath;
        }
        return FDFS_BASE_URL+fdfsPath;

    }

    public static String genFdfsPath(String fullPath){

        return fullPath.replace(FDFS_BASE_URL,"" );

    }
}
