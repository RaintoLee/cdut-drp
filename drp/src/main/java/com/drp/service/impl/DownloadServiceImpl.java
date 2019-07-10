package com.drp.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drp.controller.ESRecord;
import com.drp.dao.DownloadDAO;
import com.drp.models.DownloadRecordPageVO;
import com.drp.models.DownloadRecordVO;
import com.drp.models.LoadRecordVO;
import com.drp.models.PageVO;
import com.drp.service.DownloadService;
import com.drp.util.AddressByIpUtil;
import com.drp.util.IpAddressUtil;
import com.drp.util.UUidUtil;

/**
 * 下载记录接口的实现类
 * @author Administrator
 *
 */

@Service("downloadService")
public class DownloadServiceImpl implements DownloadService {
	
	@Autowired
	private DownloadDAO downloadDAO;

	@Override
	public void insertOneDownloadRecord(String usr_id, String works_id, HttpServletRequest request) throws Exception {
		DownloadRecordVO downloadRecordVO = new DownloadRecordVO();
		downloadRecordVO.setRecord_id(UUidUtil.getUUID());
		downloadRecordVO.setUsr_id(usr_id);
		downloadRecordVO.setWorks_id(works_id);
		downloadRecordVO.setDownload_time(new Date());
		downloadDAO.insertOneDownloadRecord(downloadRecordVO);
		//作品下载记录
		ESRecord<LoadRecordVO> record = new ESRecord<LoadRecordVO>();
		LoadRecordVO loadRecord = new LoadRecordVO();
		String address = IpAddressUtil.getIpAdrress2(request);
        String location = "";
		try {
			location = AddressByIpUtil.getAddresses("ip="+address+"&ak="+"H1DOkgEyoqePW8Z6ahp6GPQq8excwvh0", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(location);
		loadRecord.setFileType("0");
		loadRecord.setIsdelete("0");
		loadRecord.setLoadId(UUidUtil.getUUID());
		loadRecord.setLoadTime(downloadRecordVO.getDownload_time());
		loadRecord.setType("1");
		loadRecord.setLocation(location);
		loadRecord.setUserId(downloadRecordVO.getUsr_id());
		loadRecord.setWorksId(downloadRecordVO.getWorks_id());
		record.create(loadRecord);
	}

	@Override
	public List<DownloadRecordVO> getDownloadRecordByUsrId(String usr_id, int start, int offset) {
		DownloadRecordPageVO downloadRecordPageVO = new DownloadRecordPageVO();
		downloadRecordPageVO.setUsr_id(usr_id);
		PageVO pageVO = new PageVO();
		pageVO.setIndex(start);
		pageVO.setPageSize(offset);
		downloadRecordPageVO.setPageVO(pageVO);
		return downloadDAO.getDownloadRecordByUsrId(downloadRecordPageVO);
	}

}
