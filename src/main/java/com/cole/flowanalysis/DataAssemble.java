package com.cole.flowanalysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.SimpleFormatter;

public class DataAssemble {
	
	
	private List<VisitData> sourceData ;
	
	
	
	
	public int calcUV(){
		return calcUV(sourceData);
	}
	
	
	private int calcUV(List<VisitData> visits){
		if ( visits != null && visits.size() > 0 ){
			Set<String> uv = new HashSet<String>();
			for(VisitData vd : visits){
				uv.add(vd.getSourceMacAddress());
			}
			return uv.size();
			
		}else{
			return 0;
		}
	}
	
	
	public long calcStayTime(int totalUV) throws Exception{
		if ( sourceData != null && sourceData.size() > 0 ){
			Map<String,List<VisitData>> map = new HashMap<String,List<VisitData>>();
			List<VisitData> uv2 = null;
			for(VisitData vd : sourceData){
				if( map.containsKey(vd.getSourceMacAddress())){
					uv2 = map.get(vd.getSourceMacAddress());
					if(uv2.size() == 1 ){
						if( uv2.get(0).getSourceMacAddress().compareTo(vd.getSourceMacAddress()) > 0 ){
							uv2.add(0, vd);
						}else{
							uv2.add(vd);
						}
					}else if( uv2.size() > 1 ){
						VisitData min = uv2.get(0);
						VisitData max = uv2.get(1);
						if( min.getSourceMacAddress().compareTo(vd.getSourceMacAddress()) > 0 ){
							uv2.set(0, vd);
						}else{
							if ( max.getSourceMacAddress().compareTo(vd.getSourceMacAddress()) < 0 ){
								uv2.set(1, vd);
							}
						}
					}else{
						uv2.add(vd);
					}
				}else{
					uv2 = new ArrayList<VisitData>();
					uv2.add(vd);
					map.put(vd.getSourceMacAddress(), uv2);
				}
			}
			for(Map.Entry<String, List<VisitData>> entry:map.entrySet()){
				if( entry.getValue() == null || entry.getValue().size() < 2 ){
					map.remove(entry.getKey());
				}
				
			}
			long st = 0l;
			for(Map.Entry<String, List<VisitData>> entry:map.entrySet()){
				 long time = visitTime(entry.getValue().get(1).getSourceMacAddress()).getTime()  - visitTime(entry.getValue().get(0).getSourceMacAddress()).getTime();
				 st+=time;
				 
			}
			
			if ( totalUV != 0 ){
				return st/totalUV;
			}else{
				return 0;
			}
			
			
		}else{
			return 0;
		}
		
		
		
	}
	
	
	/**
	 * 计算跳失率
	 * 
	 * @param totalUV
	 * @return
	 * @throws Exception
	 */
	public long calcLossRate(int totalUV) throws Exception{
		if ( sourceData != null && sourceData.size() > 0 ){
			Map<String,List<VisitData>> map = new HashMap<String,List<VisitData>>();
			List<VisitData> uvList = null;
			for(VisitData vd : sourceData){
				if( map.containsKey(vd.getSourceMacAddress())){
					uvList = map.get(vd.getSourceMacAddress());
					uvList.add(vd);
				}else{
					uvList = new ArrayList<VisitData>();
					uvList.add(vd);
					map.put(vd.getSourceMacAddress(), uvList);
				}
			}
			for(Map.Entry<String, List<VisitData>> entry:map.entrySet()){
				if( entry.getValue() != null && entry.getValue().size() > 1 ){
					map.remove(entry.getKey());
				}else if ( entry.getValue() == null ){
					map.remove(entry.getKey());
				}
				
			}
			if ( totalUV != 0 ){
				return map.size()/totalUV;
			}else{
				return 0;
			}
			
			
		}else{
			return 0;
		}
		
		
		
	}
	
	
	public List<UVPVIndicator> calcUVPVDimension(String d){
		if ( sourceData != null && sourceData.size() > 0 ){
			List<UVPVIndicator> data = new ArrayList<UVPVIndicator>();
			Map<String,List<VisitData>> map = new HashMap<String,List<VisitData>>();
			List<VisitData> vdList = new ArrayList<VisitData>();
			
			if ( "minutes".equals(d)){
				for( VisitData vd : sourceData){
					if( map.containsKey(vd.getTime())){
						vdList = map.get(vd.getTime());
						vdList.add(vd);
					}else{
						vdList = new ArrayList<VisitData>();
						vdList.add(vd);
						map.put(vd.getTime(), vdList);
					}
				}
			}else if("rssi".equals(d)){
				
			}
			return data;
		}else{
			return null;
		}
	}
	
	
	private Date visitTime(String vt) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.parse(vt);
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2013-11-21 1:08"));
	}

}
