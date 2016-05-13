package cellcom.com.cn.net.base;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.UUID;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import net.tsz.afinal.http.HttpHandler;

import org.apache.http.Header;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.widget.Toast;
import cellcom.com.cn.bean.DESBeanBase;
import cellcom.com.cn.bean.Info;
import cellcom.com.cn.net.CellComAjaxResult;
import cellcom.com.cn.util.CellcomUtil;
import cellcom.com.cn.util.Consts;
import cellcom.com.cn.util.Des3;
import cellcom.com.cn.util.LogMgr;
import cellcom.com.cn.util.RSAHelper;
import cellcom.com.cn.util.SharepreferenceUtil;
import cellcom.com.cn.util.Tracking;

public class CellComRsahttp implements CellComHttpInterface{
	private Context context;
	private FinalHttp finalHttp;
	
	public CellComRsahttp(Context context) {
		// TODO Auto-generated constructor stub
		this.finalHttp=new FinalHttp();
		this.context=context;
	}
	
	public void get3Des(String url,final NetCallBack<String> callBack) throws Exception{
		String publicKey = RSAHelper.getPublicKey();
		AjaxParams ajaxParams=new AjaxParams();
		Info info = Tracking.getEventInfo(context);
		ajaxParams.put("publickey", publicKey);
		ajaxParams.put("deviceid",info.getDeviceid());//deviceid
		LogMgr.showLog("json:"+ajaxParams.toJson());
		ajaxParams.put("json", ajaxParams.toJson());			
		
		finalHttp.post(url, ajaxParams, new AjaxCallBack<Object>() {
			@Override
		    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
				callBack.onLoading(count, current);
			}
		    
		    @Override
		    public void onSuccess(Object t) {
		    	super.onSuccess(t);
		    	String result=(t==null?"":t).toString().trim();
		    	LogMgr.showLog("result==>"+result);
		    	Serializer serializer = new Persister(); 
		    	DESBeanBase a=null;
				try {
					a = serializer.read(DESBeanBase.class, result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(Consts.STATE_Y.equals(a.getState())){
		    		String desKey=a.getParambuf().size()>0?a.getParambuf().get(0).getKey():"";
		    		SharepreferenceUtil.saveData(context, new String[][]{{"deskey",desKey}});
		    		callBack.onSuccess(new String("success"));
		    	}else{
		    		callBack.onSuccess(new String("fail"));
		    	}
		    }
		    
			@Override
			public void onStart() {
				//开始http请求的时候回调
				callBack.onStart();
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callBack.onFailure(t, strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void configTimeout(int timeout) {
		// TODO Auto-generated method stub
		finalHttp.configTimeout(timeout);
	}

	@Override
	public void configSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		// TODO Auto-generated method stub
		finalHttp.configSSLSocketFactory(sslSocketFactory);
	}

	@Override
	public void configRequestExecutionRetryCount(int count) {
		// TODO Auto-generated method stub
		finalHttp.configRequestExecutionRetryCount(count);
	}

	@Override
	public void addHeader(String header, String value) {
		// TODO Auto-generated method stub
		finalHttp.addHeader(header, value);
	}


	@Override
	public void post(String url, final NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		AjaxParams params=getPublicParams(context,stringBuffer,null);
		finalHttp.post(stringBuffer.toString(), params,new AjaxCallBack<Object>() {
			@Override
		    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
				callBack.onLoading(count, current);
			}
		    
		    
		    @Override
		    public void onSuccess(Object t) {
		    	super.onSuccess(t);
		    	String result=(t==null?"":t).toString().trim();
		    	LogMgr.showLog("result==>"+result);
		    	CellComAjaxResult cellComAjaxResult=new CellComAjaxResult(result);
				if(cellComAjaxResult!=null){					
					callBack.onSuccess(cellComAjaxResult);
				}
		    }
		    
			@Override
			public void onStart() {
				//开始http请求的时候回调
				callBack.onStart();
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callBack.onFailure(t, strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void post(String url, AjaxParams params,final NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		params=getPublicParams(context,stringBuffer,params);
		finalHttp.post(stringBuffer.toString(), params, new AjaxCallBack<Object>() {
			@Override
		    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
				callBack.onLoading(count, current);
			}
		    
		    @Override
		    public void onSuccess(Object t) {
		    	super.onSuccess(t);
		    	String result=(t==null?"":t).toString().trim();
		    	LogMgr.showLog("result==>"+result);
		    	String desRsaKey=SharepreferenceUtil.getDate(context, "deskey");
		    	CellComAjaxResult cellComAjaxResult=null;
				try {
			    	String json=Des3.decode(result, desRsaKey);
			    	LogMgr.showLog("result = "+json);
			    	cellComAjaxResult=new CellComAjaxResult(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(cellComAjaxResult!=null){					
					callBack.onSuccess(cellComAjaxResult);
				}
		    }
		    
			@Override
			public void onStart() {
				//开始http请求的时候回调
				callBack.onStart();
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callBack.onFailure(t, strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void post(String url, Header[] headers, AjaxParams params,
			String contentType,final NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		params=getPublicParams(context,stringBuffer,params);
		finalHttp.post(stringBuffer.toString(), headers, params, contentType, new AjaxCallBack<Object>() {
			@Override
		    public void onLoading(long count, long current) { //每1秒钟自动被回调一次
				callBack.onLoading(count, current);
			}
		    
		    
		    @Override
		    public void onSuccess(Object t) {
		    	super.onSuccess(t);
		    	String result=(t==null?"":t).toString().trim();
		    	LogMgr.showLog("result==>"+result);
		    	String desRsaKey=SharepreferenceUtil.getDate(context, "deskey");
		    	CellComAjaxResult cellComAjaxResult=null;
				try {
					String json=Des3.decode(result, desRsaKey);
			    	LogMgr.showLog("json = "+json);
			    	cellComAjaxResult=new CellComAjaxResult(json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(cellComAjaxResult!=null){					
					callBack.onSuccess(cellComAjaxResult);
				}
		    }
		    
			@Override
			public void onStart() {
				//开始http请求的时候回调
				callBack.onStart();
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callBack.onFailure(t, strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public Object postSync(String url) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		AjaxParams params=getPublicParams(context,stringBuffer,null);
		return finalHttp.postSync(stringBuffer.toString(),params);
	}

	@Override
	public Object postSync(String url, AjaxParams params) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		params=getPublicParams(context,stringBuffer,params);
		return finalHttp.postSync(stringBuffer.toString(), params);
	}

	@Override
	public Object postSync(String url, Header[] headers, AjaxParams params,
			String contentType) {
		// TODO Auto-generated method stub
		StringBuffer stringBuffer=new StringBuffer(url);
		params=getPublicParams(context,stringBuffer,params);
		return finalHttp.postSync(stringBuffer.toString(), headers, params, contentType);
	}


	@Override
	public HttpHandler<File> download(String url, String target,
			final NetCallBack<File> callback) {
		// TODO Auto-generated method stub
		AjaxParams params=getDownloadPublicParams(context,url,null);
		return finalHttp.download(url,params, target, new AjaxCallBack<File>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callback.onFailure(t, strMsg);
				LogMgr.showLog("onFailure==>"+strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				callback.onStart();
			}
			@Override
			public void onSuccess(File t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				Toast.makeText(context, t==null?"null":t.getAbsoluteFile().toString(), Toast.LENGTH_SHORT).show();
				callback.onSuccess(null);
			}
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				callback.onLoading(count, current);
				super.onLoading(count, current);
			}
		});
	}

	@Override
	public HttpHandler<File> download(String url, String target,
			boolean isResume, final NetCallBack<File> callback) {
		// TODO Auto-generated method stub
		AjaxParams params=getDownloadPublicParams(context,url,null);
		return finalHttp.download(url,params, target, isResume,new AjaxCallBack<File>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callback.onFailure(t, strMsg);
//				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				callback.onStart();
			}
			@Override
			public void onSuccess(File t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				callback.onSuccess(null);
			}
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				callback.onLoading(count, current);
				super.onLoading(count, current);
			}
		});
	}

	@Override
	public HttpHandler<File> download(String url, AjaxParams params,
			String target, final NetCallBack<File> callback) {
		// TODO Auto-generated method stub
		params=getDownloadPublicParams(context,url,params);
		return finalHttp.download(url, params, target,new AjaxCallBack<File>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callback.onFailure(t, strMsg);
				LogMgr.showLog("onFailure==>"+strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				callback.onStart();
			}
			@Override
			public void onSuccess(File t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				Toast.makeText(context, t==null?"null":t.getAbsoluteFile().toString(), Toast.LENGTH_SHORT).show();
				callback.onSuccess(null);
			}
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				callback.onLoading(count, current);
				super.onLoading(count, current);
			}
		});
	}

	@Override
	public HttpHandler<File> download(String url, AjaxParams params,
			String target, boolean isResume,final NetCallBack<File> callback) {
		// TODO Auto-generated method stub
		params=getDownloadPublicParams(context,url,params);
		return finalHttp.download(url, params, target,isResume,new AjaxCallBack<File>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				callback.onFailure(t, strMsg);
				LogMgr.showLog("onFailure==>"+strMsg);
				Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				callback.onStart();
			}
			@Override
			public void onSuccess(File t) {
				// TODO Auto-generated method stub
				super.onSuccess(t);
				Toast.makeText(context, t==null?"null":t.getAbsoluteFile().toString(), Toast.LENGTH_SHORT).show();
				callback.onSuccess(null);
			}
			@Override
			public void onLoading(long count, long current) {
				// TODO Auto-generated method stub
				callback.onLoading(count, current);
				super.onLoading(count, current);
			}
		});
	}
	
	private AjaxParams getDownloadPublicParams(Context context,String url,AjaxParams params){
		Info info = Tracking.getEventInfo(context);
		params=(params==null?new AjaxParams():params);
		String token=cellcom.com.cn.util.SharepreferenceUtil.getDate(context, "token");
		params.put("token",token);//token
		params.put("service",info.getService());// 服务
		params.put("os",info.getOs());//手机操作系统
		params.put("version",info.getApp_version());//客户端版本号
		params.put("timestamp",info.getTimestamp()+"");//时间戳
		params.put("imsi",info.getImsi());//imsi
		params.put("deviceid",info.getDeviceid());//deviceid
		LogMgr.showLog("http.url = "+(url+"?"+params.toString()));
		return params;
	}
	public static String getDate(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		String month="";
		if(cal.get(Calendar.MONTH) + 1<10){
			month="0"+cal.get(Calendar.MONTH);
		}else{
			month=cal.get(Calendar.MONTH)+"";
		}
		String day="";
		if(cal.get(Calendar.DAY_OF_MONTH)<10){
			month="0"+cal.get(Calendar.DAY_OF_MONTH);
		}else{
			month=cal.get(Calendar.DAY_OF_MONTH)+"";
		}
		return cal.get(Calendar.YEAR)
				+ month
				+ day;
	}
	public static String getTime(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		String month="";
		if(cal.get(Calendar.MONTH) + 1<10){
			month="0"+cal.get(Calendar.MONTH);
		}else{
			month=cal.get(Calendar.MONTH)+"";
		}
		String day="";
		if(cal.get(Calendar.DAY_OF_MONTH)<10){
			month="0"+cal.get(Calendar.DAY_OF_MONTH);
		}else{
			month=cal.get(Calendar.DAY_OF_MONTH)+"";
		}
		String hour= "";
		if((cal.get(Calendar.HOUR_OF_DAY) > 9)){
			hour=cal.get(Calendar.HOUR_OF_DAY)+"" ;
		}else{
			hour= "0"+ cal.get(Calendar.HOUR_OF_DAY);
		}
		String minute= (cal.get(Calendar.MINUTE) > 9 ? cal.get(Calendar.MINUTE)+""
				: ("0" + cal.get(Calendar.MINUTE)));
		String second= (cal.get(Calendar.SECOND) > 9 ? cal.get(Calendar.SECOND)+""
				: ("0" + cal.get(Calendar.SECOND)));
		return cal.get(Calendar.YEAR)
				+ month
				+ day;
	}
	public static String getAccurateTime(long milliseconds) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(milliseconds);
		String month="";
		if(cal.get(Calendar.MONTH) + 1<10){
			month="0"+cal.get(Calendar.MONTH);
		}else{
			month=cal.get(Calendar.MONTH)+"";
		}
		String day="";
		if(cal.get(Calendar.DAY_OF_MONTH)<10){
			month="0"+cal.get(Calendar.DAY_OF_MONTH);
		}else{
			month=cal.get(Calendar.DAY_OF_MONTH)+"";
		}
		String hour= "";
		if((cal.get(Calendar.HOUR_OF_DAY) > 9)){
			hour=cal.get(Calendar.HOUR_OF_DAY)+"" ;
		}else{
			hour= "0"+ cal.get(Calendar.HOUR_OF_DAY);
		}
		String minute= (cal.get(Calendar.MINUTE) > 9 ? cal.get(Calendar.MINUTE)+""
				: ("0" + cal.get(Calendar.MINUTE)));
		String second= (cal.get(Calendar.SECOND) > 9 ? cal.get(Calendar.SECOND)+""
				: ("0" + cal.get(Calendar.SECOND)));
		return cal.get(Calendar.YEAR)
				+ month
				+ day
				+hour
				+minute
				+second;
	}
	private AjaxParams getPublicParams(Context context,StringBuffer url,AjaxParams params){
		Info info = Tracking.getEventInfo(context);
		String interfacename = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".do"));//接口名称
		String sysParamFlow=Tracking.getSysParamFlow(context);
		String authstring="";
		if(sysParamFlow.equalsIgnoreCase(interfacename)){
			authstring = CellcomUtil.encodeMD5(info.getService()+info.getTimestamp()+"cellcom");
		}else{
			String key=SharepreferenceUtil.getDate(context, Consts.KEY);
			authstring = CellcomUtil.encodeMD5(info.getService()+info.getTimestamp()+key);
		}
		
		String celltype;
		if ((android.os.Build.MODEL).contains("HUA")) {
			celltype = "HUAWEI";
		} else {
			celltype = android.os.Build.MODEL;
		}
		try {
			celltype = URLEncoder.encode(celltype, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params=(params==null?new AjaxParams():params);
		params.put("celltype", celltype);
		params.put("service",info.getService());// 服务
		params.put("os",info.getOs());//手机操作系统
		params.put("version",info.getApp_version());//客户端版本号
		params.put("osversion",info.getOs_version());//客户端版本号
		params.put("timestamp",info.getTimestamp()+"");//时间戳
		params.put("authstring",authstring);//md5加密串
		params.put("imsi",info.getImsi());//imsi
		params.put("deviceid",info.getDeviceid());//deviceid
		params.put("clientid",info.getDeviceid());//deviceid
		String service=SharepreferenceUtil.getDate(context, "service");
		if(sysParamFlow.equalsIgnoreCase(interfacename)){
			params.put("channel","hn");//hn
		}else{
			params.put("channel",service);//hn
		}
		String token=cellcom.com.cn.util.SharepreferenceUtil.getDate(context, "token");
		params.put("token",token);//token
		params.put("systemno","01");//systemno
		String id="yywapp"+getTime(info.getTimestamp())+UUID.randomUUID().toString().replaceAll("-", "");
		params.put("id",id);//id
		String time=getAccurateTime(info.getTimestamp());
		params.put("time",time);//时间戳
		String desRsaKey=SharepreferenceUtil.getDate(context, "deskey");
		if(sysParamFlow.equalsIgnoreCase(interfacename)){
			params.put("signInfo",CellcomUtil.encodeMD5(CellcomUtil.encodeMD5("01"+id+time)+"cellcom"));//systemno	
		}else{
			params.put("signInfo",CellcomUtil.encodeMD5(CellcomUtil.encodeMD5("01"+id+time)+desRsaKey));			
		}
		
		String orginJson=params.toJson();
		//上传的参数是否加密
//		params.clear();
		if(desRsaKey.equalsIgnoreCase("")){
			params.put("json", orginJson);			
		}else{
			try {
//				String desKey = RSAHelper.decode(RSAHelper.getPrivateKey(), desRsaKey);
//				LogMgr.showLog("desKey==>"+desKey);
				if(params.isExitsFile()){
//					url.append("?json="+json);
					url.append("?"+params.toString());
				}
				String json=Des3.encode(orginJson, desRsaKey);
				params.put("json", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LogMgr.showLog("http.url = "+(url+"?"+params.toString()));
		return params;
	}

	@Override
	public void get(String url, NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get(String url, AjaxParams params,
			NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void get(String url, Header[] headers, AjaxParams params,
			NetCallBack<CellComAjaxResult> callBack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSync(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSync(String url, AjaxParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSync(String url, Header[] headers, AjaxParams params) {
		// TODO Auto-generated method stub
		return null;
	}
}
