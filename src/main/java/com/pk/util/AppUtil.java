package com.pk.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pk.dto.BaseDto;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2 
public class AppUtil {
	
   /*@Autowired
    LanguageDetailService languageDetailService;
   */
    
    

	public BaseDto setDesc(BaseDto dto) {
		log.debug("Response Message being sent to the client : " ,dto.getResponseCode() + " --> " + getDesc(dto.getResponseCode()));
		dto.setResponseDescription(getDesc(dto.getResponseCode()));
		return dto;
	}
    
	public String getDesc(String code) {
		return code;
	}
    
	public List<Class> getAllClasses(String pckgname) {
		try {
			List<Class> classes = new ArrayList();
			

			//classes.add(AddressType.class);
			
			
			return classes;

		} catch (Exception e) {
			//log.error("Exception getting all enum class:", e);
			return null;
		}
	}
	
	public Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }
	
	
}
