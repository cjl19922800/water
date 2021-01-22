package com.shanghaiwater.mcs.admin.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thymeleaf.util.StringUtils;

import com.shanghaiwater.mcs.common.enums.CompanyEnum;

public class CommonUtil {

	public static Integer[] TRUEORFALSE = {0,1};//是否常量：0-统一代表否；1-统一代表是
	
	public static String[] SOURCE = {"01","02","03"};
	
	// 包含小写字母
	public static String CONTAIN_LETTER_REGEX = ".*[a-z].*";
    // 包含大写字母
	public static String CONTAIN_LETTER_UUP_REGEX = ".*[A-Z].*";
	//特殊字符
	public static String CONTAIN_SPECIAL_REGEX = "[_`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    
	
	public static String PDWLY_SALE_NO_P="浦合客-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-JMBGY";
	public static String PDWLY_SALE_NO_C="浦合客-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-DWY";
	public static String PDXQ_SALE_NO_P="浦水（民用）-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-ywtb";
	public static String PDXQ_SALE_NO_C="浦水（单位）-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-ywtb";
	public static String BS_SALE_NO_P="宝水-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-WQJM";
	public static String BS_SALE_NO_C="宝水-"+String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+"-WQDW";
	
	
	
	
	public static boolean checkStringIsNull(String... value){
	    for(int i=0;i<value.length;i++){
	        if(StringUtils.isEmpty(value[i].trim())){
	        	return false;
	        }
	    }
	    return true;
	}
	
	
	
	
	/**
	 * 手机号正则表达式检测
	 * @param phone
	 * @return true Or false
	 */
	public static Boolean checkPhone(String phone) {
		if(phone.trim().isEmpty()) {
			return false;
		}
		Pattern p = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
		return p.matcher(phone).matches();
	} 
	
	/**
	 * Date转String
	 * @param dataFormat
	 * @param date
	 * @return
	 */
	public static String dateFormatString(String dataFormat,Date date) {
		return new SimpleDateFormat(dataFormat).format(date);
	}
	
	/**
	 * 日期向后加？天
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date dateAddDay(Date date,int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE,day); 
		return calendar.getTime();
	}
	
	public static Date dateAdd(Date date,int day,int calendarTime) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendarTime,day); 
		return calendar.getTime();
	}
	
	
	/**
     * 获取时间的小时 
     * @param date
     * @return
     */
    public static Integer getHour(Date date){
		if(date==null)
		{
			return null;
		}				
		return getCalendar(date).get(Calendar.HOUR_OF_DAY);
	}
	
	
    public static Calendar getCalendar(Date date){
		if(date==null)
		{
			return null;
		}
		DateFormat df = DateFormat.getDateInstance();
		df.format(date);		 
		return df.getCalendar();		
	}
	
	public static boolean HasDigit(String content) {
	    boolean flag = false;
	    Pattern p = Pattern.compile(".*\\d+.*");
	    Matcher m = p.matcher(content);
	    if (m.matches()) {
	        flag = true;
	    }
	    return flag;
	}
	
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
	}
	
	/**
	 * 字符串是否包含特殊字符
	 * @param str
	 * @return
	 */
	public static boolean stringHasSpecial(String str) {  
		Pattern p = Pattern.compile(CONTAIN_SPECIAL_REGEX);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static boolean hasRegRule(String str,String reg) {
        return str.matches(reg);
    }
	
	public static List<String> findFileList(String filePath) {
		 File dir = new File(filePath);
		 List<String> list = new ArrayList<String>();
		 if (!dir.exists() || !dir.isDirectory()) {// 判断是否存在目录
			 return list;
		 }
		 String[] files = dir.list();// 读取目录下的所有目录文件信息
		 for (int i = 0; i < files.length; i++) {// 循环，添加文件名或回调自身
			 File file = new File(dir, files[i]);
			 if (file.isFile()) {// 如果文件
				 list.add(dir +"/"+ file.getName());// 添加文件全路径名
			 } 
		 }
		 return list;
   }
	
}
