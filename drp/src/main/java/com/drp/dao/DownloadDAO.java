package com.drp.dao;

import com.drp.models.DownloadRecordPageVO;
import com.drp.models.DownloadRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DownloadDAO {

    void insertOneDownloadRecord(@Param("downloadRecordVO") DownloadRecordVO downloadRecordVO);
    
    List<DownloadRecordVO> getDownloadRecordByUsrId(@Param("downloadRecordPageVO") DownloadRecordPageVO downloadRecordPageVO);

}
