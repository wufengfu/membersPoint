package com.tianxiafen.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tianxiafen.entity.Formula;
import com.tianxiafen.service.IFormulaService;
import com.tianxiafen.service.IPointAbouchemenetService;
import com.tianxiafen.service.IPointRemitService;
import com.tianxiafen.service.impl.FormulaServiceImpl;
import com.tianxiafen.service.impl.PointAbouchemenetServiceImpl;
import com.tianxiafen.service.impl.PointRemitServiceImpl;

public class CommonUtil {

	/**
	 * 产生流水号
	 * @param type 0为汇入，1位汇出
	 * @return 17位流水号
	 */
	public static String getServialNum(int type){
		
		String servialNum = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmm");
		DecimalFormat decimalFormat = new DecimalFormat("0000"); 
		if(type==0){
			servialNum+="pa";//汇入
			servialNum+=dateFormat.format(new Date());
			IPointAbouchemenetService service = new PointAbouchemenetServiceImpl();
			servialNum+=decimalFormat.format(service.getMaxId()+1);
			
		}else if(type == 1){
			servialNum+="pt";//汇出
			servialNum+=dateFormat.format(new Date());
			IPointRemitService service = new PointRemitServiceImpl();
			servialNum+=decimalFormat.format(service.getMaxId()+1);
		}
		return servialNum;
		
	}
	/**
	 * 生成四位随机数
	 * @return 四位随机数
	 */
	public static String getVerifyCode(){
		return ((int)(Math.random()*9000+1000))+"";
	}
	/**
	 * 根据传递的里程获取对应的积分数
	 * @param companyId 航司标识
	 * @param mileage 里程数
	 * @return 积分数
	 */
	public static Integer getPointByMilage(int companyId,int mileage){
		int point = -1;
		IFormulaService service = new FormulaServiceImpl();
		List<Formula> formula = service.getFormula(companyId,0,0);
		//TODO 详细询问计算公式的计算方法，是阶梯整体变动的
		if(formula!=null && formula.size()>0){
			int realIndex = 0;
			for(int i = 0;i < formula.size();i++){
				if(mileage < formula.get(i).getLevelValue()){
					break;
				}else{
					realIndex = i;
				}
			}
			point = (int) (mileage*(formula.get(realIndex).getLevelRatio()/10000));
		}
		return point;
	}
	
	public static Date formatStringToDate(String date){
		if(date == null)
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
