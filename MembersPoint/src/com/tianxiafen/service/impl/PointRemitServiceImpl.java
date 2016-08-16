package com.tianxiafen.service.impl;

import java.util.Date;
import java.util.List;

import com.tianxiafen.dao.IPointRemitDao;
import com.tianxiafen.dao.impl.PointRemitDaoImpl;
import com.tianxiafen.entity.Pointremit;
import com.tianxiafen.service.IPointRemitService;
import com.tianxiafen.util.CommonUtil;

public class PointRemitServiceImpl implements IPointRemitService {

	IPointRemitDao dao = new PointRemitDaoImpl();
	@Override
	public List<Pointremit> getPointRemit(String remitName,String serialNum,String cardNum,int companyId,Date startTime,Date endTime,Integer statues,int pageIndex,int pageSize) {
		return dao.getPointRemit(remitName, serialNum,cardNum,companyId, startTime, endTime,statues,pageIndex,pageSize);
	}

	@Override
	public Pointremit addPointRemit(Pointremit pointRemit) {
		return dao.addPointRemit(pointRemit);
	}

	@Override
	public boolean updatePointRemitStatues(Pointremit pointRemit) {
		return dao.updatePointRemitStatues(pointRemit);
	}

	@Override
	public int getMaxId() {
		return dao.getMaxId();
		
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

}
